(ns task-tracker.server.model.user.taskboard
  (:require [task-tracker.server.model.user.tasks                   :as tasks-model]
            [task-tracker.server.model.user.taskboard-configuration :as config-model]))

;; from a data model perspective a "taskboard", as identified by a
;; user-ttid, consists of the following:
;;;; the configuration for the taskboard and
;;;; the tasks (that are associated to that taskboard) themselves
(defn find-by-user-ttid
  [user-ttid]
  (let [tasks-recs  (tasks-model/find-by-user-ttid user-ttid)
        config-recs (config-model/find-by-user-ttid user-ttid)]
    {:tasks         tasks-recs
     :configuration config-recs}))
