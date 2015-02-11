(ns task-tracker.app.util
  (:require [cognitect.transit :as transit])
  (:import goog.History))

(def ^:private r (transit/reader :json))
(def ^:private w (transit/writer :json))

(defn parse-json
  [raw-json]
  (transit/read r raw-json))

(defn write-json
  [clj-data]
  (->> clj-data clj->js (.stringify js/JSON)))

(defn parse-int
  [int-str]
  (when (string? int-str)
    (.parseInt js/window int-str)))
