(ns tinytimer.frontend.util)

(defn get-element
  [selector]
  (.querySelector js/document selector))

(defn toggle-class
  [el class]
  (.. el classList toggle class))
