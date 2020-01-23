(ns tinytimer.routes.home
  (:require [compojure.core :refer [defroutes GET]]
            [tinytimer.views.home :as view]))

(defroutes routes
  (GET "/" [] (view/home)))
