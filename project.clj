(defproject hs-test-problem "0.1.0-SNAPSHOT"
  :description "FHIR Standart Patcher"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [cheshire "5.8.0"]
                 [rum "0.10.8"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler fhir-standard-patcher.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
