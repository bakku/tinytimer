(ns tinytimer.frontend.timers
  (:require ["bulma-calendar" :as bulma-calendar]
            ["moment" :as moment]
            [tinytimer.frontend.util :as util]))

(defn- create-calendar-instance
  []
  (-> (.attach bulma-calendar
               "#expires-at-input"
               #js {:type "datetime"
                    :dateFormat "DD/MM/YYYY"
                    :displayMode "default"
                    :showTodayButton false
                    :validateLabel "OK"
                    :minDate (.format (moment) "MM/DD/YYYY")})
      first))

(defn init-expires-at-input
  []
  (let [calendar (create-calendar-instance)
        input (util/get-element "#expires-at-input")]
    (when-let [v (.. input -attributes -value)]
      (as-> (moment (.-value v) "DD/MM/YYYY HH:mm") date
            (.format date "MM/DD/YYYY HH:mm")
            (.value calendar date)))))
