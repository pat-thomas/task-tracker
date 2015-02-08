(ns task-tracker.server.db
  (:require [com.stuartsierra.component         :as component]
            [korma.db                           :as korma]
            [clojure.java.jdbc                  :as jdbc]
            [task-tracker.server.service-config :refer [config]]))

(defrecord Database [uri]
  component/Lifecycle

  (start [component]
    (println ";; starting database")
    component)

  (stop [component]
    (println ";; stopping database")
    component))

(defn new-database
  [{:keys [subprotocol subname user password] :as db-config}]
  (map->Database {:subprotocol subprotocol
                  :subname     subname
                  :user        user
                  :password    password}))

(defn select-all
  [^java.util.Map db ^java.lang.String sql]
  (jdbc/query db
              [sql]))

(defn insert-record
  [db params])

(comment
  (let [db (.start (new-database (config :db)))]
    (exec-sql db "INSERT INTO users.accounts (username, email, password) VALUES ('OatRhombus', 'oat@rhom.bus', 'bar');"))
  
  ;; db initialization commands
  )
