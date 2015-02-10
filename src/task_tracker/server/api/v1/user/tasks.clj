(ns task-tracker.server.api.v1.user.tasks
  (:require [kinematic.dsl :as dsl :refer [defapi api-get api-post api-put api-delete]]
            [task-tracker.server.model.user.tasks :as model]))

(defapi :api ["user/:user-ttid/tasks"])

(api-get
 (if-let [recs (model/find-by-user-ttid (get-in request [:route-params :user-ttid]))]
   {:status "OK"
    :recs   recs}
   {:status "NotFound"}))
