(ns task-tracker.server.model.user.tasks
  (:require [task-tracker.server.db            :as db]
            [task-tracker.server.model.session :as session]))

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

(defn ensure
  [task-ttid {:keys [task-description] :as params}]
  (if-let [existing-rec (find-by-user-and-task-ttid (session/session-user-ttid) task-ttid)]
    (db/update-record :users.tasks
                      {:user_ttid (session/session-user-ttid)
                       :task_ttid task-ttid}
                      {:task_description task-description})
    (db/insert-record :users.tasks {:user_ttid (session/session-user-ttid)
                                    :task_ttid task-ttid})))
