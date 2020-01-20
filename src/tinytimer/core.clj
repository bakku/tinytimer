(ns tinytimer.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring])
  (:gen-class))

(defroutes app-routes
  (GET "/" [] "<h1>Hello World</h1>"))

(def application app-routes)

(defn -main
  []
  (ring/run-jetty application {:port  8080
                               :join? false}))
