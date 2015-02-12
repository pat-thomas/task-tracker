(ns task-tracker.app.xhr
  (:require [goog.events           :as events]
            [task-tracker.app.util :as util])
  (:import [goog.net XhrIo]
           goog.net.EventType
           [goog.events EventType]))

(def ^:private api-base-url "http://localhost:3141/api/")

(defn starts-with?
  [s target]
  (= (.indexOf s target) 0))

(defn xhr-req
  "base-url is something like task/"
  [{:keys [method url data on-complete]}]
  (let [xhr (XhrIo.)]
    (events/listen xhr goog.net.EventType.COMPLETE
                   (fn [e]
                     (on-complete (util/parse-json (.getResponseText xhr)))))
    (. xhr
       (send (str api-base-url url) method (when data (util/write-json data))
             #js {"Content-Type" "application/json"}))))
