(ns tinytimer.views.timers
  (:require [hiccup.form :as h]
            [tinytimer.views.layout :as layout]))

(defn new
  []
  (layout/page "Create new timer"
               [:section.section
                 [:div.container
                   [:h1.is-size-3.headline "Create new timer"]
                   [:form {:action "/t" :method "POST"}
                     [:div.field
                       [:label.label "Description"]
                       [:textarea.textarea.is-large
                        {:name "description"
                         :placeholder "Mom's birthday, Flight to Cape Town, ... you name it 💪"}]]
                     [:div.field
                       [:label.label "Expires at"]
                       [:input#expires-at-input {:name "target-time"
                                                 :type "date"}]]
                     [:div.field.has-text-centered
                       [:button.button.is-success {:type "submit"} "Create"]]]]]))
