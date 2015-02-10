(ns task-tracker.app.xhr
  (:require [goog.events           :as events]
            [task-tracker.app.util :as util])
  (:import [goog.net XhrIo]
           goog.net.EventType
           [goog.events EventType]))

(def ^:private meths
  {:get    "GET"
   :post   "POST"
   :put    "PUT"
   :delete "DELETE"})

(def ^:private api-base-url "http://localhost:3141/api/")

(defn xhr-req
  [{:keys [method url data on-complete]}]
  (let [xhr (XhrIo.)]
    (events/listen xhr goog.net.EventType.COMPLETE
                   (fn [e]
                     (on-complete (util/parse-json (.getResponseText xhr)))))
    (. xhr
       (send (str api-base-url url) (meths method) (when data (util/write-json data))
             #js {"Content-Type" "application/json"}))))
