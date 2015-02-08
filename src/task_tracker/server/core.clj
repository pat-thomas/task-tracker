(ns task-tracker.server.core
  (:require [com.stuartsierra.component         :as component]
            [task-tracker.server.service-config :as service-config :refer [config]]
            [task-tracker.server.db             :as db]))

(def system
  (component/system-map
   :db (db/new-database (config :db))))

(defn init!
  []
  (do
    (service-config/load-config! "service-config.json")
    (component/start system)))

(comment
  (init!)
  )
