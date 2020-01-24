(ns tinytimer.lein
  (:require [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]
            [environ.core :refer [env]]))

(defn- ragtime-config
  []
  {:datastore  (jdbc/sql-database (env :database-url))
   :migrations (jdbc/load-resources "migrations")})

(defn migrate []
  (repl/migrate (ragtime-config)))

(defn rollback []
  (repl/rollback (ragtime-config)))
