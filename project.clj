(defproject tinytimer "0.1.0-SNAPSHOT"
  :description "Small application to create and share timers"
  :url "https://github.com/bakku/tinytimer"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 [ring/ring-mock "0.4.0"]]
  :plugins [[lein-ring "0.12.5"]]
  :main ^:skip-aot tinytimer.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :ring {:handler tinytimer.core/application})
