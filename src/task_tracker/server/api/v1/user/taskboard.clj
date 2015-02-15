(ns task-tracker.server.api.v1.user.taskboard
  (:require [kinematic.dsl :as dsl :refer [defapi api-get api-post api-put api-delete]]
            [task-tracker.server.model.user.taskboard :as model]))

(defapi :api ["user/:user-ttid/taskboard"])

(api-get
 (let [{:keys [tasks config] :as recs} (model/find-by-user-ttid (get-in request [:route-params :user-ttid]))]
   (if (or (not (empty? tasks))
           (not (empty? config)))
     {:status "OK"
      :recs   recs}
     {:status "NotFound"})))
