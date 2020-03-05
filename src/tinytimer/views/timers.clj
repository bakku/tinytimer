(ns tinytimer.views.timers
  (:require [tinytimer.views.layout :as layout]))

(defn- split-lines
  [s]
  (->> (clojure.string/split s #"\r\n|\r|\n")
       (interpose [:br])
       vec))

(defn new
  [& {:keys [description expires-at flash]}]
  (layout/page {:title "Create new timer"
                :show-navbar-create-btn false}
               [:section.section
                 [:div.container
                   (when (seq flash)
                     [:div.notification.is-danger flash])
                   [:h1.is-size-3.headline "Create new timer"]
                   [:form {:action "/t" :method "POST"}
                     [:div.field
                       [:label.label "Description"]
                       [:textarea.textarea.is-large
                        {:name "description"
                         :placeholder "Mom's birthday, Flight to Cape Town, ... you name it 💪"}
                        description]]
                     [:div.field
                       [:label.label "Expires at"]
                       [:input#expires-at-input {:name "expires-at"
                                                 :type "date"
                                                 :value expires-at}]]
                     [:div.field.has-text-centered
                       [:strong
                         [:button.button.is-success.has-text-weight-bold.is-medium
                          {:type "submit"}
                          "Create"]]]]]]))

(defn show
  [{:keys [caption expires-at]} silent]
  (layout/page {:title caption}
               [:section {:class
                          (str "section has-text-centered timer-wrapper "
                               (if silent "timer-top-padding-lg" "timer-top-padding-md"))}
                [:div.container
                 [:p#timer-expires-at.is-size-1 {:data expires-at}]
                 (vec (concat [:p.is-size-2] (split-lines caption)))
                 (when (not silent)
                  [:button#copy-link.button.is-light "Copy this link"])]]))

