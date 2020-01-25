(ns tinytimer.routes.timers
  (:require [compojure.core :refer [defroutes GET]]
            [tinytimer.views.timers :as view]))

(defroutes routes
  (GET "/t/new" [] (view/new)))
