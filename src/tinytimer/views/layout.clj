(ns tinytimer.views.layout
  (:require [hiccup.page :as h]))

(defn page
  [title & body]
  (h/html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport"
              :content "width=device-width, initial-scale=1"}]
      [:title (str title " | Tinytimer")]
      [:link {:rel "stylesheet" :href "/css/bulma.min.css" :type "text/css"}]
      [:link {:rel "stylesheet" :href "/css/styles.css" :type "text/css"}]]
    [:body
      [:nav.navbar.has-shadow
        [:div.container
          [:div.navbar-brand
            [:p.navbar-item "⏱ Tinytimer"]]]]
      [:div#content body]
      [:footer.footer
        [:div.container
          [:div.columns
            [:div.column.is-4
              [:p.is-size-5 [:strong "Tinytimer"]
                            " by "
                            [:a {:href "https://www.github.com/bakku"} "Christian Paling"]
                            "."]
              [:p [:a {:href "https://www.twitter.com/bakku1505"} "@bakku1505"]]]
            [:div.column.is-4
              [:a {:href "https://clojure.org/"}
                [:img.is-block {:src "/img/clojure.png" :width "20%"}]]
              [:a {:href "https://bulma.io/made-with-bulma/"}
                [:img.is-block {:src "/img/made-with-bulma.png" :width "50%"}]]]
            [:div.column.is-4
              [:p "Made in the beautiful city of "
                  [:a {:href "https://en.wikipedia.org/wiki/W%C3%BCrzburg"} "Würzburg"]
                  "."]]]]]
      [:script {:src "/js/moment.min.js"}]
      [:script {:src "/js/tinytimer.js"}]]))
