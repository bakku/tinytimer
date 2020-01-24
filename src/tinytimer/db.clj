(ns tinytimer.db
  (:require [next.jdbc :as jdbc]
            [environ.core :refer [env]]))

(def datasource
  (jdbc/get-datasource (env :database-url)))
