(ns task-tracker.app.components.task-detail
  (:require [om.core              :as om  :include-macros true]
            [om.dom               :as dom :include-macros true]
            [task-tracker.app.model.task :as task-model]
            [task-tracker.app.xhr :as xhr])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent root
  (init-state
   (task-model/get-task
    (:task-ttid opts)
    {:on-complete (fn [rec]
                    (om/set-state! owner
                                   {:task-state       (get rec "task_state")
                                    :task-description (get rec "task_description")}))}))
  (render-state
   (dom/div
    #js {:id "task-detail"}
    (dom/div
     nil
     (dom/div
      nil
      (dom/label nil "Task description: ")
      (dom/textarea
       #js {:value    (om/get-state owner :task-description)
            :onChange (fn [e]
                        (let [val (.. e -target -value)]
                          (om/set-state! owner :task-description val)))}))
     (dom/button
      #js {:onClick (fn [_]
                      (task-model/save
                       (:task-ttid opts)
                       {:on-complete (fn [resp]
                                       (println resp))
                        :params      {:task-description (om/get-state owner :task-description)}}))}
      "Save")))))
