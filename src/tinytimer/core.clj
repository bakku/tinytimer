(ns tinytimer.core
  (:require [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [tinytimer.middleware :refer [wrap-logger]]
            [tinytimer.routes.static :as static]
            [tinytimer.routes.timers :as timers]
            [tinytimer.views.static :refer [not-found]])
  (:gen-class))

(defroutes app-routes
  static/routes
  timers/routes
  (route/resources "/css" {:root "public/css"})
  (route/resources "/img" {:root "public/img"})
  (route/resources "/js"  {:root "public/js"})
  (route/not-found (not-found)))

(def application
  (-> app-routes
      wrap-params
      wrap-logger))

(defn -main
  []
  (let [port (Integer/parseInt (or (System/getenv "PORT")
                                   "8080"))]
    (ring/run-jetty application {:port  port
                                 :join? false})))
