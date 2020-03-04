(ns tinytimer.routes.static
  (:require [compojure.core :refer [defroutes GET]]
            [tinytimer.views.static :as static]))

(defroutes routes
  (GET "/" [] (static/home))
  (GET "/legal" [] (static/legal-disclosure))
  (GET "/privacy" [] (static/privacy-policy)))
