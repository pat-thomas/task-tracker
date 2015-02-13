(ns task-tracker.server.api.v1.user.taskboard-configuration
  (:require [kinematic.dsl :as dsl :refer [defapi api-get api-post api-put api-delete]]
            [task-tracker.server.model.user.taskboard-configuration :as model]))

(defapi :api ["user/:user-ttid/taskboard-configuration"])

(api-get
 (if-let [config (model/find-by-user-ttid (get-in request [:route-params :user-ttid]))]
   {:status "OK"
    :config config}
   {:status "NotFound"}))

(api-put
 (let [user-ttid (get-in request [:route-params :user-ttid])
       config    (:config body)]
   (model/update-configuration user-ttid config)
   {:status "Accepted"
    :config config}))
