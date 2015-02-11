(ns task-tracker.server.api.v1.task
  (:require [kinematic.dsl :as dsl :refer [defapi api-get api-post api-put api-delete]]
            [task-tracker.server.model.user.tasks :as model]))

(defapi :api ["task/:task-ttid"])

(api-get
 (let [rec (model/find-by-task-ttid (get-in request [:route-params :task-ttid]))]
   (if rec
     {:status "OK"
      :rec    rec}
     {:status "NotFound"})))

(api-post
 (do 
   (model/ensure (get-in request [:route-params :task-ttid])
                 (select-keys body [:task-description]))
   {:status "OK"}))
