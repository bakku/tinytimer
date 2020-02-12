{:dev  {:env {:database-url "jdbc:sqlite:development.db"
              :app-env "development"}}
 :repl {:env {:database-url "jdbc:sqlite:test.db"
              :app-env "test"}}
 :test {:env {:database-url "jdbc:sqlite:test.db"
              :app-env "test"}}
 :uberjar {:aot :all}}
