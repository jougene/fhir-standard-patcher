(ns fhir-standard-patcher.db
  (:require
    ; [java-jdbc.ddl :as ddl]
    [clojure.java.jdbc :as j]
    [honeysql.core :as sql]
    [honeysql.helpers :refer :all]
    [honeysql-postgres.format :refer :all]
    [honeysql-postgres.helpers :refer :all]
    ;;
    )
  (:refer-clojure :exclude [update partition-by]))

(def db-conn
  {:classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname     "//localhost:5432/fhir"
   :user        "fhir"
   :password    "12345"
   :sslmode     "disable"
   })

(comment 
  (j/query db-conn (-> (select :*)
                       (from :resources)
                       sql/format))

  (j/query db-conn
          (sql/format (drop-table :resources))))
         
