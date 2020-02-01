(ns tinytimer.fixtures
  (:require [next.jdbc :as jdbc]
            [tinytimer.db :refer [get-datasource]]))

(defn clean-db
  []
  (jdbc/execute! (get-datasource)
                 ["DELETE FROM timers"]))

(defn database-cleaner
  [f]
  (f)
  (clean-db))

