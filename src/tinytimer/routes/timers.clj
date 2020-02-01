(ns tinytimer.routes.timers
  (:require [compojure.core :refer [defroutes GET POST]]
            [next.jdbc.sql :as sql]
            [ring.util.response :refer [redirect]]
            [tinytimer.db :refer [get-datasource with-snake-case-columns]]
            [tinytimer.views.timers :as view]))

(def timer-path-len 20)
(def create-missing-details-flash
  "You did not provide all necessary details to create a timer")

(defn- rand-str [len]
  (->> (repeatedly #(char (+ (rand 26) 97)))
       (take len)
       (apply str)))

(defn- insert-timer
  [desc expires-at]
  (let [ds   (get-datasource)
        path (rand-str timer-path-len)]
    (sql/insert! ds :timers {:path       path
                             :expires-at expires-at
                             :caption    desc}
                            with-snake-case-columns)
    path))

(defn- create-timer
  [desc expires-at]
  (let [path (insert-timer desc expires-at)]
    (redirect (str "/t/" path))))

(defn- process-timer
  [desc expires-at]
  (if (or (empty? desc)
          (empty? expires-at))
    (view/new :description desc
              :expires-at expires-at
              :flash create-missing-details-flash)
    (create-timer desc expires-at)))

(defroutes routes
  (GET "/t/new" [] (view/new))
  (POST "/t"
        [description expires-at]
        (process-timer description expires-at)))
