(ns fhir-standard-patcher.seed
  (:require
    [fhir-standard-patcher.db :as db]
    [clojure.java.jdbc :as j]
    [honeysql.core :as sql]
    [honeysql.helpers :refer :all]
    [honeysql-postgres.format :refer :all]
    [honeysql-postgres.helpers :refer :all])

  (:refer-clojure :exclude [update partition-by]))

;; -----patients----------
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
;; ------------------------
(def fhir-versions
  [:id 1
   :version "3.0.0"
   :schema ""])
(comment 
  (seed-patients patients)
  (seed-fhir-versions fhir-versions)
  (slurp "examples/patient-example-a.json"))
