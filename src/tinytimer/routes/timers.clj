(ns tinytimer.routes.timers
  (:require [compojure.core :refer [defroutes GET POST]]
            [tinytimer.views.timers :as view]))

(def create-missing-details-flash
  "You did not provide all necessary details to create a timer")

(defn- create-timer
  [desc expires-at]
  (if (or (empty? desc)
          (empty? expires-at))
    (view/new :description desc
              :expires-at expires-at
              :flash create-missing-details-flash)))

(defroutes routes
  (GET "/t/new" [] (view/new))
  (POST "/t"
        [description expires-at]
        (create-timer description expires-at)))
