(ns task-tracker.server.api.v1.user.account
  (:require [kinematic.dsl                          :as dsl :refer [defapi api-get api-post api-put api-delete]]
            [task-tracker.server.model.user.account :as model]))

(defapi :api ["user/:user-ttid/account"])

(api-get
 (if-let [rec (model/find-by-username (get-in request [:route-params :username]))]
   {:status "OK"
    :rec    rec}
   {:status "NotFound"}))
