(ns fhir-standard-patcher.seed
  (:require
    [fhir-standard-patcher.db :as db]
    [clojure.java.jdbc :as j]
    [honeysql.core :as sql]
    [honeysql.helpers :refer :all]
    [honeysql-postgres.format :refer :all]
    [honeysql-postgres.helpers :refer :all])

  (:refer-clojure :exclude [update partition-by]))

(def patients [
                {:id 1
                 :type "Patient"
                 :data ""}

                {:id 2
                 :type "Patient"
                 :data ":key-1 value-1"}
              ]) 

(defn seed-patients
  [data]
  (let [insert-query (-> (insert-into :resources)
                         (values patients)
                         (returning :*)
                         sql/format)]
    (j/query db/db-conn insert-query)))

(def f (clojure.
(comment 
  (seed-patients patients)
  (slurp "../examples/patient-example-a.json"))
