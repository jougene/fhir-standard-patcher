(ns fhir-standard-patcher.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure.data.json :as json]
            [fhir-standard-patcher.handler :refer :all]))

(def correct-test-request
  {:type "Patient"
   :name "Bob"
   :gendter "male"
   :info {} })

(json/read-str "{\"a\":1,\"b\":2}")

(get-in {:status 200
         :body {:status "OK"
                :patient-id 123}}
        [:body :patient-id])

(deftest test-app
  (testing "can see creating patient form"
    (let [response (app (mock/request :get "/patient"))]
      (is (= (:status response) 200))
      ;; add parsing body and see form
      ))

  (testing "can save patient in db with correct values"
    (let [response (app (mock/request :post "/patient" correct-test-request))]
      (is (= (:status response) 200))
      ;; add check for correct json response
      ;; {"status" : "OK", "patient-id": <id>}
      ;; add check that patient exists in db
      ))



  
  (testing "can see specific patient by id"
    ;; perform a request to add new patient
    (let [create-res (app (mock/request :post "/patient" correct-test-request))
          patient-id (get-in (json/read-str (:body create-res)) ["patient-id"])
          response (app (mock/request :get (str "/patient/" patient-id)))]
      (is (= (:status response) 200))
      ;; add check for correct json response
      ;; add check that patient exists in db
      ))


  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

(def create-res
  (app (mock/request :post "/patient" correct-test-request)))
