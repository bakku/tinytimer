(ns tinytimer.core
  (:require [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [tinytimer.routes.home :as home]
            [tinytimer.routes.timers :as timers])
  (:gen-class))

(defroutes app-routes
  home/routes
  timers/routes
  (route/resources "/css" {:root "public/css"})
  (route/resources "/img" {:root "public/img"})
  (route/resources "/js"  {:root "public/js"})
  (route/not-found "Not Found"))

(def application
  (-> app-routes
      wrap-params))

(defn -main
  []
  (let [port (Integer/parseInt (or (System/getenv "PORT")
                                   "8080"))]
    (ring/run-jetty application {:port  port
                                 :join? false})))
