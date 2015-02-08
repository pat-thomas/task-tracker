(ns task-tracker.server.core
  (:require [task-tracker.server.service-config :as service-config]))

(defn init!
  []
  (do
    (task-tracker.server.service-config/load-config! "service-config.json")))

(comment
  (init!)
  )
