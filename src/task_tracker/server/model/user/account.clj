(ns task-tracker.server.model.users.account
  (:require [com.stuartsierra.component :as component]
            [task-tracker.server.db     :as db]))

(defrecord Account [db]
  component/Lifecycle
  (start [component]
    component)
  (stop [component]
    component))

(defn find-by-username
  [{:keys [db] :as account} username]
  (db/find-by db :users.accounts {:username username}))

(defn create-new-user
  [{:keys [db] :as account} {:keys [username email password]}]
  (db/insert-record db :users.accounts {:username username
                                        :email    email
                                        :password password}))
