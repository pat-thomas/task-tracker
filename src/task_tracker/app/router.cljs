(ns task-tracker.app.router
  (:require [task-tracker.app.state                 :as app-state]
            [task-tracker.app.history               :as history]
            [task-tracker.app.components.home       :as home]
            [task-tracker.app.components.account    :as account]
            [task-tracker.app.components.task-board :as task-board]
            [task-tracker.app.components.task-detail :as task-detail]
            [secretary.core                         :as secretary :refer-macros [defroute]])
  (:require-macros [task-tracker.app.router :refer [current-view-route!]]))

(defn set-current-view!
  [current-view & [opts]]
  (if-not opts
    (println "setting current view to" current-view "with opts" opts)
    (println "setting current view to" current-view))
  (let [nascent {:view current-view}]
    (swap! app-state/app-state
           #(assoc % :current-view (if opts
                                     (assoc nascent :opts opts)
                                     nascent)))))

(current-view-route! home "/")
(current-view-route! home)
(current-view-route! account)
(current-view-route! task-board)
(current-view-route! task-detail "/task-detail/:task-id" [task-id])

(defroute "*"
  []
  (history/redirect "home"))

(def routing-table
  {""            home/root
   "home"        home/root
   "account"     account/root
   "task-board"  task-board/root
   "task-detail" task-detail/root})
