(ns task-tracker.server.model.user.tasks
  (:require [task-tracker.server.db :as db]))

(defn find-by-user-ttid
  [user-ttid]
  (let [recs (db/find-by :users.tasks {:user_ttid user-ttid})]
    (when-not (empty? recs)
      recs)))

(defn find-by-user-and-task-ttid
  [user-ttid task-ttid]
  (let [recs (db/find-by :users.tasks {:user_ttid user-ttid
                                       :task_ttid task-ttid})]
    (when-not (empty? recs)
      (first recs))))

(defn find-by-task-ttid
  [task-ttid]
  (let [recs (db/find-by :users.tasks {:task_ttid task-ttid})]
    (when-not (empty? recs)
      (first recs))))
