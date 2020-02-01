(ns tinytimer.db
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
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

(def with-snake-case-columns
  {:column-fn
   #(clojure.string/replace % #"-" "_")})

(defn- kebab-map-fn
  [rs opts]
  (let [kebab #(clojure.string/replace % #"_" "-")
        new-opts (assoc opts :qualifier-fn kebab :label-fn kebab)]
    (rs/as-unqualified-modified-maps rs new-opts)))

(def as-kebab-maps
  {:builder-fn kebab-map-fn})
