(ns tinytimer.views.static
  (:require [tinytimer.views.layout :as layout]
            [tinytimer.shared :refer [zero-pad]]))

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
  (layout/page {:title "Home"
                :show-navbar-create-btn false}
               [:section.hero.is-primary.start-section-padding
                 [:div.container
                   [:div.hero-body
                     [:h1.title "Tinytimer allows you to take more delight in your upcoming events"]
                     [:p.subtitle "Create small timers and share them with your "
                       [:strong "friends"]
                       " and "
                       [:strong "family"]]
                     [:div.has-text-centered
                       [:p
                         [:strong
                           [:a.button.is-danger.start-create-button
                            {:href "/t/new"}
                            "Create timer"]]]]]]]
               [:section.section.has-text-centered.start-section-padding
                 [:div.container
                   [:p.is-size-1.example-time (random-time)]
                   (vec (concat [:p.is-size-2] (random-timer)))]]))

(defn legal-disclosure
  []
  (layout/page {:title "Legal Disclosure"}
               [:section.section
                 [:div.container
                   (slurp (clojure.java.io/resource "pages/legal.html"))]]))

(defn privacy-policy
  []
  (layout/page {:title "Privacy Policy"}
               [:section.section
                 [:div.container
                   (slurp (clojure.java.io/resource "pages/privacy.html"))]]))

(defn not-found
  []
  (layout/page {:title "This page does not exist"}
               [:section.section.timer-wrapper.has-text-centered
                [:div.container
                 [:p.is-size-2 "🙁"]
                 [:p.is-size-2 "This page does not exist"]
                 [:p.is-size-2
                  [:a#not-found-create-button.button.is-danger.is-medium
                    {:href "/t/new"}
                    "Create a timer instead"]]]]))
