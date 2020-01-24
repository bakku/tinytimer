(ns tinytimer.db
  (:require [next.jdbc :as jdbc]
            [environ.core :refer [env]]))

;; `datasource` should not be evaluated when this file is parsed e.g. during
;; compile time. It should be evaluated when it is actually used.
;; Therefore, the creation is delayed and access to the datasource
;; is only provided via `get-datasource`
(def ^:private datasource
  (delay (jdbc/get-datasource (env :database-url))))

(defn get-datasource
  []
  @datasource)
