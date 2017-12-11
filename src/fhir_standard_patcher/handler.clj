(ns fhir-standard-patcher.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [rum.core :as rum]
            [ring.adapter.jetty :as jetty]
            [fhir-standard-patcher.middleware :refer :all]
            [ring.middleware.json :refer [wrap-json-response]]))

; ----components-----
(rum/defc form []
  [:form {:method "post" :action "/patient"}
   [:input {:type "test"
            :name "name"}]
   [:button {:type "submit"} "Send"]])

(rum/defc layout [content]
  [:html
   [:head
    [:meta { :http-equiv "Content-Type" :content "text/html; charset=UTF-8"}]
    [:meta { :name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:title "FHIR"]]
   [:body
    (content)
    ]])
;; ------------------
;; -----handlers-----
(defn add-handler [request]
  {:status 200
   :body (str "<!DOCTYPE html>\n" (->> form
                                       (layout)
                                       (rum/render-static-markup)))}) 
;; ------------------ 

(defn create-handler [request]
  {:status 200
   :body  {:status "OK" :patient-id "123"}})

(defroutes app-routes
  (GET "/patient" [] (wrap-text-html add-handler))                   ;; add new patient route form 
  (POST "/patient" [] (wrap-json-response create-handler))      ;; save new patient 
  (GET "/patient/:id" [] "showing patient....")
              ;; show specific patient by id
  (GET "/patients" [] "Patients list")              ;; show patients list 
  (route/not-found "Not Found"))

(defn start []
  (jetty/run-jetty #'app-routes {:port 8080 :join? false}))

(def app #'app-routes)
