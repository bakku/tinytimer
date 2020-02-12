(ns tinytimer.frontend.timers
  (:require ["bulma-calendar" :as bulma-calendar]
            ["moment" :as moment]
            [tinytimer.frontend.util :as util]
            [tinytimer.shared :refer [zero-pad]]))

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
    (when input
      (when-let [v (.. input -attributes -value)]
        (as-> (moment (.-value v) "DD/MM/YYYY HH:mm") date
              (.format date "MM/DD/YYYY HH:mm")
              (.value calendar date))))))

(defn- timer-expired?
  [diff-duration]
  (> (.asMilliseconds diff-duration) 0))

(defn- decrement-timer
  []
  (if-let [timer (util/get-element "#timer-expires-at")]
    (let [expires-at    (.. timer -attributes -data -value)
          now           (moment)
          as-moment     (moment expires-at "DD/MM/YYYY HH:mm")
          diff          (.diff as-moment now)
          diff-duration (.duration moment diff)
          hours         (zero-pad (Math/floor (.asHours diff-duration)))
          min-and-sec   (as-> (.asMilliseconds diff-duration) ms
                              (.utc moment ms)
                              (.format ms ":mm:ss"))]
      (if (timer-expired? diff-duration)
        (do
          (set! (.-innerText timer) (str hours min-and-sec))
          (js/setTimeout decrement-timer 1000))
        (set! (.-innerText timer) "00:00:00")))))

(defn init-expires-at
  []
  (decrement-timer))

(defn copy-silent-link-to-clipboard
  []
  (let [loc (.-location js/window)]
    (util/copy-to-clipboard (str loc "?s=1"))))

(defn init-link-copy-button
  []
  (if-let [button (util/get-element "#copy-link")]
    (.addEventListener button "click" copy-silent-link-to-clipboard)))

(defn init-timers-show-page
  []
  (init-expires-at)
  (init-link-copy-button))