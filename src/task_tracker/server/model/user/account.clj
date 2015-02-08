(ns task-tracker.server.model.users.account
  (:require [task-tracker.server.db :as db]))

(defn find-by-username
  [username]
  (db/find-by :users.accounts {:username username}))

(defn create-new-user
  [{:keys [username email password]}]
  (db/insert-record :users.accounts {:username username
                                     :email    email
                                     :password password}))
