(ns tinytimer.frontend.start
  (:require [tinytimer.frontend.util :as util]))

(declare decrement)

(defn- split-time
  [time]
  (let [[h m s] (.split time ":")]
    {:seconds (js/parseInt s)
     :minutes (js/parseInt m)
     :hours   (js/parseInt h)}))

(defn zero-padded
  [num]
  (if (< num 10)
    (str "0" num)
    (str num)))

(defn- decrement-seconds!
  [el {:keys [seconds minutes hours]}]
  (set! (.-innerText el)
        (str (zero-padded hours)
             ":"
             (zero-padded minutes)
             ":"
             (zero-padded (- seconds 1)))))

(defn- decrement-minutes!
  [el {:keys [seconds minutes hours]}]
  (set! (.-innerText el)
        (str (zero-padded hours)
             ":"
             (zero-padded (- minutes 1))
             ":59")))

(defn- decrement-hours!
  [el {:keys [seconds minutes hours]}]
  (set! (.-innerText el)
        (str (zero-padded (- hours 1)) ":59:59")))

(defn- decrement-one-second
  [el {:keys [seconds minutes hours] :as time-map}]
  (cond
    (> seconds 0) (decrement-seconds! el time-map)
    (> minutes 0) (decrement-minutes! el time-map)
    :else         (decrement-hours! el time-map))
  (js/setTimeout #(decrement el) 1000))

(defn- decrement
  [el]
  (let [curr-time     (.-innerText el)
        splitted-time (split-time curr-time)]
    (when (not (every? zero? (vals splitted-time)))
      (decrement-one-second el splitted-time))))

(defn init-time-decrementer
  []
  (let [el (util/get-element ".example-time")]
    (when el
      (decrement el))))
