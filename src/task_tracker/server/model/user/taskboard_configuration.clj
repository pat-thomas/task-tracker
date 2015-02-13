(ns task-tracker.server.model.user.taskboard-configuration
  (:require [task-tracker.server.db :as db]
            [clojure.data.json      :as json]))

(defn find-by-user-ttid
  [user-ttid]
  (let [recs (db/find-by :users.taskboard_configuration {:user_ttid user-ttid})]
    (when-not (empty? recs)
      (reduce (fn [acc rec]
                (assoc acc (keyword (:name rec)) (:value rec)))
              {}
              recs))))

(defn update-configuration
  [^java.lang.String user-ttid ^java.util.Map config-params]
  (doseq [[config-key config-val] config-params]
    (db/update-record :users.taskboard_configuration {:user_ttid user-ttid} {:name  (name config-key)
                                                                             :value (json/write-str config-val)})))
