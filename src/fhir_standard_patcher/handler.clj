(ns fhir-standard-patcher.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [rum.core :as rum]))

(def test-request
  {:type "Patient"
   :name "Bob"
   :gendter "male"
   :info {} })

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

(defroutes app-routes
  (GET "/patient" [] 
     {:status 200
      :headers { "Content-Type" "text/html; charset=utf-8" }
      :body (str "<!DOCTYPE html>\n" (rum/render-static-markup (layout form)))})
  (POST "/patient" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
  
(str (rum/render-static-markup (layout form)))
