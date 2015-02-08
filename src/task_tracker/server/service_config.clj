(ns task-tracker.server.service-config
  (:require [clojure.data.json :as json]))

(def ^{:private true} config-atom (atom nil))

(defn load-config!
  [config-file-path]
  (reset! config-atom (-> config-file-path slurp json/read-json)))

(defn config
  [& ks]
  (get-in @config-atom ks))
