(ns task-tracker.server.api.v1.ping
  (:require [kinematic.dsl :as dsl :refer [defapi api-get api-post api-put api-delete]]))

(defapi :api ["ping"])

(api-get
 {:status "OK"
  :msg    "pong"})
