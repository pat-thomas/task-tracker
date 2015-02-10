(ns task-tracker.app.router
  (:require [task-tracker.app.state              :as app-state]
            [task-tracker.app.history            :as history]
            [task-tracker.app.components.home    :as home]
            [task-tracker.app.components.account :as account]
            [task-tracker.app.components.task-board :as task-board]
            [secretary.core                      :as secretary :refer-macros [defroute]])
  (:require-macros [task-tracker.app.router :refer [current-view-route!]]))

(defn set-current-view!
  [current-view & [opts]]
  (println "setting current view to" current-view)
  (if opts
    (swap! app-state/app-state #(assoc % :current-view {:view current-view
                                                        :opts opts}))
    (swap! app-state/app-state #(assoc % :current-view {:view current-view}))))

(current-view-route! home "/")
(current-view-route! home)
(current-view-route! account)
(current-view-route! task-board)

(defroute "*"
  []
  (history/redirect "home"))

(def routing-table
  {""           home/root
   "home"       home/root
   "account"    account/root
   "task-board" task-board/root})
