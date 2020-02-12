(ns tinytimer.routes.home-test
  (:require [clojure.test :refer :all]
            [environ.core :refer [env]]
            [ring.mock.request :as mock]
            [tinytimer.routes.home :refer :all]))

(deftest show-test
  (testing "should render"
    (is (-> (mock/request :get "/")
            (routes)
            (:body)
            (clojure.string/includes? "Create small timers")))))
