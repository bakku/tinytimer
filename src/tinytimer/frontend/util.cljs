(ns tinytimer.frontend.util)

(defn get-element
  [selector]
  (.querySelector js/document selector))

(defn toggle-class
  [el class]
  (.. el -classList (toggle class)))

(defn- create-invisible-textarea
  [s]
  (let [dummy-el (.createElement js/document "textarea")]
    (set! (.-value dummy-el) s)
    (.setAttribute dummy-el "readonly" "")
    (set! (.. dummy-el -style -position) "absolute")
    (set! (.. dummy-el -style -left) "-9999px")
    (.appendChild (.-body js/document) dummy-el)
    dummy-el))

(defn- get-current-selection
  []
  (if (> (.-rangeCount (.getSelection js/document)) 0)
    (.getRangeAt (.getSelection js/document) 0)
    nil))

(defn- restore-selection
  [selection]
  (when selection
    (.removeAllRanges (.getSelection js/document))
    (.addRange (.getSelection js/document) selection)))

(defn copy-to-clipboard
  [s]
  (let [el        (create-invisible-textarea s)
        selection (get-current-selection)]
    (.select el)
    (.execCommand js/document "copy")
    (.removeChild (.-body js/document) el)
    (restore-selection selection)))