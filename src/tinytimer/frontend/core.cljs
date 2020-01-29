(ns tinytimer.frontend.core
  (:require [tinytimer.frontend.start :as start]
            [tinytimer.frontend.timers :as timers]
            [tinytimer.frontend.util :as util]))

(defn init-navbar
  []
  (let [el-burger (util/get-element ".navbar-burger")
        el-menu   (util/get-element ".navbar-menu")]
    (when el-burger
      (util/toggle-class el-burger "is-active")
      (util/toggle-class el-menu "is-active"))))

(defn main
  []
  (init-navbar)
  (start/init-time-decrementer)
  (timers/init-expires-at-input))
