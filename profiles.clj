{:dev  {:env {:database-url "jdbc:sqlite:development.db"
              :app-env "development"}}
 :test {:env {:database-url "jdbc:sqlite:test.db"
              :app-env "test"}}
 :uberjar {:aot :all}}
