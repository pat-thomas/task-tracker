(ns task-tracker.server.api.v1.user.task
  (:require [kinematic.dsl :as dsl :refer [defapi api-get api-post api-put api-delete]]
            [task-tracker.server.model.user.tasks :as model]))

(defapi :api ["user/:user-ttid/task/:task-ttid"])

(api-get
 (let [{:keys [user-ttid task-ttid]} (:route-params request)]
   (let [rec (model/find-by-user-and-task-ttid user-ttid task-ttid)]
     (if rec
       {:status "OK"
        :rec    rec}
       {:status "NotFound"}))))
