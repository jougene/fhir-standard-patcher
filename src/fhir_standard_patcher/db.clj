(ns fhir-standard-patcher.db
  (:require [clojure.java.jdbc :as j]))

(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost:5432/fhir_standart_patcher"
   :user "fhir"
   :password "12345"
   :sslmode "require"
   })

(j/query db
   ["select now()"])
