(ns tinytimer.views.layout
  (:require [hiccup.page :as h]
            [environ.core :refer [env]]))

(defn- navbar
  [{:keys [show-navbar-create-btn]
    :or {show-navbar-create-btn true}}]
  [[:nav.navbar.has-shadow
     [:div.container
       [:div.navbar-brand
         [:a.is-size-4.navbar-item {:href "/"} "⏱ Tinytimer"]
         (when show-navbar-create-btn
           [:a.navbar-burger
             (for [x (range 3)]
               [:span])])]
       (when show-navbar-create-btn
         [:div.navbar-menu
           [:div.navbar-end
             [:div.navbar-item
               [:div.buttons
                 [:a.button.is-danger {:href "/t/new"}
                   [:strong "Create timer"]]]]]])]]])

(defn- body
  [content]
  [[:div#content content]])

(defn- footer
  []
  [[:footer.footer
     [:div.container
       [:div.columns
         [:div.column.is-4.has-text-centered
           [:p.is-size-5 [:strong "Tinytimer"]
                         " by "
                         [:a {:href "https://www.github.com/bakku"} "Christian Paling"]
                         "."]
           [:p "Made in the beautiful city of "
               [:a {:href "https://en.wikipedia.org/wiki/W%C3%BCrzburg"} "Würzburg"]
               "."]]
         [:div.column.is-4
           [:a {:href "https://clojure.org/"}
             [:img.is-block {:src "/img/clojure.png" :width "20%"}]]
           [:a {:href "https://bulma.io/made-with-bulma/"}
             [:img.is-block {:src "/img/made-with-bulma.png" :width "50%"}]]]
         [:div.column.is-4.has-text-centered
           [:p [:a {:href "/imprint"} "Imprint"]]
           [:p [:a {:href "/privacy"} "Privacy Policy"]]]]]]
   (if (= (:app-env env "development") "development")
     [:script {:src "/js/dev/main.js"}]
     [:script {:src "/js/main.js"}])])

(defn page
  [{:keys [title] :as options} & content]
  (h/html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport"
              :content "width=device-width, initial-scale=1"}]
      [:title (str title " | Tinytimer")]
      [:link {:rel "stylesheet" :href "/css/bulma.min.css" :type "text/css"}]
      [:link {:rel "stylesheet" :href "/css/bulma-calendar.min.css" :type "text/css"}]
      [:link {:rel "stylesheet" :href "/css/styles.css" :type "text/css"}]]
    [:body
      (concat
        (navbar options)
        (body content)
        (footer))]))
