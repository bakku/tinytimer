(ns tinytimer.frontend.timers
  (:require ["bulma-calendar" :as bulma-calendar]
            ["moment" :as moment]))

(defn init-expires-at-input
  []
  (.attach bulma-calendar
           "#expires-at-input"
           #js {:type "datetime"
                :dateFormat "DD/MM/YYYY"
                :displayMode "default"
                :showTodayButton false
                :validateLabel "OK"
                :minDate (.format (moment) "MM/DD/YYYY")}))
