(ns fhir-standard-patcher.db
  (:require
    ; [java-jdbc.ddl :as ddl]
    [clojure.java.jdbc :as j]
    [honeysql.core :as sql]
    [honeysql.helpers :refer :all]
    [honeysql-postgres.format :refer :all]
    [honeysql-postgres.helpers :refer :all]
    ;;
    ))

(def db-conn
  {:classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname     "//localhost:5432/fhir?sslmode=disable"
   :user        "fhir"
   :password    "12345"
   :sslmode     "require"
   })

(def create-resources-sql
  (-> (create-table :resources)
      (with-columns
        [[:id :integer (sql/call :primary-key)]
         [:type (sql/call :varchar 32) (sql/call :not nil)]
         [:data :text]])
      sql/format))

(j/query db-conn create-resources-sql)

(j/query db-conn (-> (select :*)
                     (from :resources)
                     sql/format))

(j/query db-conn
        (sql/format (drop-table :resources)))
         
