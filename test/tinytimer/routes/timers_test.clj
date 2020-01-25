(ns tinytimer.routes.timers-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [tinytimer.routes.timers :refer :all]))

(deftest new-test
  (testing "should render"
    (is (-> (mock/request :get "/t/new")
            (routes)
            (:body)
            (clojure.string/includes? "Create new timer")))))
