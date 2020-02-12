(ns tinytimer.routes.static-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [tinytimer.core :refer [application]]))

(deftest not-found-test
  (testing "should render 404 page"
    (is (-> (mock/request :get "/invalid")
            (application)
            (:body)
            (clojure.string/includes? "This page does not exist")))))
