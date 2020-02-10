(ns tinytimer.shared)

(defn zero-pad
  [input]
  (if (< input 10)
    (str "0" input)
    (str input)))

