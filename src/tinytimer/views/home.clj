(ns tinytimer.views.home
  (:require [tinytimer.views.layout :as layout]))

(defn- zero-pad
  [input]
  (if (< input 10)
    (str "0" input)
    (str input)))

(defn- random-time
  []
  (let [hours   (+ (rand-int 180) 10)
        minutes (+ (rand-int 50) 9)
        seconds (+ (rand-int 50) 9)]
    (str (zero-pad hours)
         ":"
         (zero-pad minutes)
         ":"
         (zero-pad seconds))))

(def available-timers
  [["💇‍♀️" [:br] "Hairdresser"]
   ["🏄‍♂️" [:br] "Surfing in Miami"]
   ["🍜 ⛩" [:br] "Trip to Japan"]
   ["📦 🚗" [:br] "New car arrives"]
   ["👵 🎂 🥳" [:br] "Mum's birthday"]])

(defn- random-timer
  []
  (get available-timers (rand-int (count available-timers))))

(defn home
  []
  (layout/page "Home"
               [:section.hero.is-primary
                 [:div.container
                   [:div.hero-body
                     [:h1.title "Tinytimer allows you to take more delight in your upcoming events"]
                     [:p.subtitle "Create small timers and share them with your "
                       [:strong "friends"]
                       " and "
                       [:strong "family"]]]]]
               [:section.section.has-text-centered
                 [:div.container
                   [:p.is-size-1.example-time (random-time)]
                   (vec (concat [:p.is-size-2] (random-timer)))]]))
