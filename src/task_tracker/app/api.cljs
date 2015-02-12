(ns task-tracker.app.api
  (:require-macros [task-tracker.app.api :refer [make-api]]))

(make-api :get [:task task-ttid])
(make-api :post [:task task-ttid])
(make-api :get [:user user-ttid :tasks])
