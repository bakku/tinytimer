(ns tinytimer.views.static
  (:require [tinytimer.views.layout :as layout]))

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
