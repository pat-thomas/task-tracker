(ns task-tracker.app.api
  (:require-macros [task-tracker.app.api :refer [make-api]]))

(make-api :get [:task task-ttid])
(make-api :post [:task task-ttid])

(make-api :get [:user user-ttid :tasks])
(make-api :get [:user user-ttid :taskboard])
(make-api :get [:user user-ttid :taskboard-configuration])
(make-api :put [:user user-ttid :taskboard-configuration])
