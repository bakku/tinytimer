(defproject tinytimer "1.0.0-RELEASE"
  :description "Small application to create and share timers"
  :url "https://github.com/bakku/tinytimer"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [ring/ring-core "1.8.0"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 [environ "1.1.0"]
                 [ragtime "0.8.0"]
                 [ring/ring-mock "0.4.0"]
                 [seancorfield/next.jdbc "1.0.13"]
                 [org.xerial/sqlite-jdbc "3.30.1"]]
  :plugins [[lein-ring "0.12.5"]
            [lein-environ "1.1.0"]]
  :main ^:skip-aot tinytimer.core
  :target-path "target/%s"
  :ring {:handler tinytimer.core/application}
  :aliases {"migrate"  ["run" "-m" "tinytimer.lein/migrate"]
            "rollback" ["run" "-m" "tinytimer.lein/rollback"]})
