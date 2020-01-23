(ns tinytimer.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [tinytimer.routes.home :as home])
  (:gen-class))

(defroutes app-routes
  home/routes
  (route/resources "/css" {:root "public/css"})
  (route/resources "/img" {:root "public/img"})
  (route/resources "/js"  {:root "public/js"})
  (route/not-found "Not Found"))

(def application app-routes)

(defn -main
  []
  (ring/run-jetty application {:port  8080
                               :join? false}))
