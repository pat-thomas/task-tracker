(ns task-tracker.server.model.user.account
  (:require [task-tracker.server.db         :as db]
            [task-tracker.server.model.ttid :as ttid]))

(defn find-by-ttid
  [ttid]
  (when-let [recs (db/find-by :users.accounts {:user_ttid ttid})]
    (first recs)))

(defn create-new-user ;; need to generate ttid
  [{:keys [username email password]}]
  (db/insert-record :users.accounts {:username  username
                                     :email     email
                                     :password  password
                                     :user_ttid (ttid/gen-ttid "account")}))
