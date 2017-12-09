(ns fhir-standard-patcher.middleware
  )

(defn wrap-text-plain [handler]
  (fn [request]
    (assoc (handler request) :headers {"Content-Type" "text/html; charset=utf-8"} )))

