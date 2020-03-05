(ns tinytimer.routes.timers-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [next.jdbc.sql :as sql]
            [tinytimer.core :refer [application]]
            [tinytimer.db :refer [get-datasource as-kebab-maps with-snake-case-columns]]
            [tinytimer.fixtures :as fixtures]
            [tinytimer.routes.timers :refer [timer-path-len]]))

(use-fixtures :each fixtures/database-cleaner)

(defn within-last-minute?
  [str]
  (let [formatter  (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd HH:mm:ss")
        parsed     (java.time.LocalDateTime/parse str formatter)
        minute-ago (.minusMinutes (java.time.LocalDateTime/now (java.time.ZoneId/of "UTC")) 1)]
    (.isBefore minute-ago parsed)))

(deftest new-test
  (testing "should render"
    (is (-> (mock/request :get "/t/new")
            (application)
            (:body)
            (clojure.string/includes? "Create new timer")))))

(deftest create-test
  (testing "should render new if not all fields were given"
    (is (-> (mock/request :post "/t" {:description "Hello!" :expires-at ""})
            (application)
            (:body)
            (clojure.string/includes? "You did not provide all necessary details to create a timer"))))
  (testing "should create timer if all fields were given"
    (application (mock/request :post "/t" {:description "Hello!" :expires-at "31/01/2020 14:54"}))
    (let [ds (get-datasource)
          records (sql/query ds ["select * from timers"] as-kebab-maps)]
      (is (= 1 (count records)))
      (let [{:keys [path expires-at caption created-at updated-at]} (first records)]
        (is (= timer-path-len (count path)))
        (is (= expires-at "31/01/2020 14:54"))
        (is (= caption "Hello!"))
        (is (within-last-minute? created-at))
        (is (within-last-minute? updated-at)))))
  (testing "should redirect to timer path after creating"
    (let [req  (mock/request :post "/t" {:description "Hello!" :expires-at "31/01/2020 14:54"})
          resp (application req)
          loc  (get-in resp [:headers "Location"])]
      (is (re-matches (re-pattern (str "/t/.{" timer-path-len "}")) loc)))))

(deftest show-test
  (sql/insert! (get-datasource) :timers {:path       "abcde"
                                          :expires-at "31/01/2020 14:00"
                                          :caption    "A TIMER!"}
                                        with-snake-case-columns)
  (testing "should render 404 if timer does not exist"
    (is (-> (mock/request :get "/t/invalid")
            (application)
            (:body)
            (clojure.string/includes? "This page does not exist"))))
  (testing "should render"
    (is (-> (mock/request :get "/t/abcde")
            (application)
            (:body)
            (clojure.string/includes? "Copy this link"))))
  (testing "should hide copy link button for silent page"
    (is (not (-> (mock/request :get "/t/abcde?s=1")
                 (application)
                 (:body)
                 (clojure.string/includes? "Click here to copy a link"))))))
