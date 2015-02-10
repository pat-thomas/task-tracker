(ns task-tracker.app.core
  (:require [om.core                  :as om  :include-macros true]
            [om.dom                   :as dom :include-macros true]
            [task-tracker.app.history :as history]
            [task-tracker.app.router  :as router]
            [task-tracker.app.state   :as state]
            [task-tracker.app.components.navbar :as navbar])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(enable-console-print!)

(defcomponent app-root
  (render
   (dom/div
    #js {:id "app"}
    (om/build navbar/root data {})
    (dom/div
     #js {:id "content-container"}
     (let [current-view (:current-view data)]
       (om/build (get router/routing-table (:view current-view)) data {:opts (:opts current-view)}))))))

(defn init!
  []
  (history/init!)
  (om/root
   app-root
   state/app-state
   {:target (. js/document (getElementById "my-app"))
    :opts   {}}))

(init!)
