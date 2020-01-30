(ns tinytimer.routes.timers-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [tinytimer.core :refer [application]]))

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
            (clojure.string/includes? "You did not provide all necessary details to create a timer")))))
