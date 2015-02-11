(ns task-tracker.server.core
  (:require [task-tracker.server.service-config :as service-config :refer [config]]
            [task-tracker.server.webserver      :as webserver]
            [task-tracker.server.api.core       :as api]
            task-tracker.server.db))

(defn init!
  "Entry point for app."
  []
  (service-config/load-config! "service-config.json")
  (webserver/start-server (config :webserver :port) #'api/web-app))

(comment
  (init!)
  )
