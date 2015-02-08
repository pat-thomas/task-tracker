(ns task-tracker.server.model.user.account
  (:require [task-tracker.server.db :as db]))

(defn find-by-username
  [username]
  (when-let [recs (db/find-by :users.accounts {:username username})]
    (first recs)))

(defn create-new-user
  [{:keys [username email password]}]
  (db/insert-record :users.accounts {:username username
                                     :email    email
                                     :password password}))
