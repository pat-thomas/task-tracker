(ns task-tracker.server.api.v1.user.account
  (:require [kinematic.dsl :as dsl :refer [defapi api-get api-post api-put api-delete]]))

(defapi :api ["user/:username/account"])

(api-get
 (def request request)
 {:status "OK"
  :recs   "implement-me"})
