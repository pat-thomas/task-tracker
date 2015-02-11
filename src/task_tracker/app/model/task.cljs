(ns task-tracker.app.model.task
  (:require [task-tracker.app.xhr   :as xhr]
            [task-tracker.app.state :as state]))

(defn get-task
  [task-ttid {:keys [on-complete]}]
  (xhr/xhr-req
   {:method      :get
    :url         (str "task/" task-ttid)
    :on-complete (fn [resp]
                   (let [rec (get resp "rec")]
                     (on-complete rec)))}))

(defn save
  [task-ttid {:keys [on-complete params]}]
  (xhr/xhr-req
   {:method      :post
    :url         (str "task/" task-ttid)
    :data        params
    :on-complete on-complete}))
