(ns task-tracker.app.history
  (:require [secretary.core         :as secretary]
            [goog.events            :as events]
            [goog.history.EventType :as EventType])
  (:import goog.History))

(def app-history (History.))

(defn init!
  []
  (secretary/set-config! :prefix "#")
  (events/listen app-history EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
  (doto app-history (.setEnabled true)))

(defn redirect
  [redirect-location]
  (.setToken app-history redirect-location))
