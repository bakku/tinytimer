(ns tinytimer.middleware)

(defn- formatted-query-string
  [request]
  (if-let [q (:query-string request)]
    (str "?" q)
    ""))

(defn- formatted-request-method
  [request]
  (-> (:request-method request)
      (name)
      (.toUpperCase)))

(defn wrap-logger
  [handler]
  (fn [request]
    (let [start-ms       (System/currentTimeMillis)
          path           (:uri request)
          query-string   (formatted-query-string request)
          request-method (formatted-request-method request)
          response       (handler request)
          difference     (- (System/currentTimeMillis) start-ms)
          status         (:status response)]
      (println (str request-method " " path query-string " - Completed " status " in " difference " ms"))
      response)))
