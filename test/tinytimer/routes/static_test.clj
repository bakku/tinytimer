(ns tinytimer.routes.static-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [tinytimer.core :refer [application]]))

(deftest home-test
  (testing "should render"
    (is (-> (mock/request :get "/")
            (application)
            (:body)
            (clojure.string/includes? "Create small timers")))))

(deftest legal-notice-test
  (testing "should render"
    (is (-> (mock/request :get "/legal")
            (application)
            (:body)
            (clojure.string/includes? "Section 5 TMG")))))

(deftest privacy-policy-test
  (testing "should render"
    (is (-> (mock/request :get "/privacy")
            (application)
            (:body)
            (clojure.string/includes? "01735680048")))))

(deftest not-found-test
  (testing "should render 404 page"
    (is (-> (mock/request :get "/invalid")
            (application)
            (:body)
            (clojure.string/includes? "This page does not exist")))))

(deftest google-site-verification-test
  (testing "should not render google-site-verification without env var"
    (is (not (-> (mock/request :get "/")
                 (application)
                 (:body)
                 (clojure.string/includes? "google-site-verification")))))
  (testing "should render google-site-verification with env var"
    (with-redefs [environ.core/env {:google-site-verification "test" :app-env "test"}]
      (is (-> (mock/request :get "/")
              (application)
              (:body)
              (clojure.string/includes? "<meta content=\"test\" name=\"google-site-verification\">"))))))
