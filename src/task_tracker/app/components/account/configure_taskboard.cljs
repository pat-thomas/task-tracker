(ns task-tracker.app.components.account.configure-taskboard
  (:require [om.core              :as om  :include-macros true]
            [om.dom               :as dom :include-macros true]
            [task-tracker.app.util :as util]
            [task-tracker.app.api :as api])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent delete-button
  [states task-state-index]
  (render
   (dom/div
    #js {:className "delete-button"
         :onClick   (fn [_]
                      (let [new-task-state-list (util/vec-remove (js->clj states) task-state-index)]
                        (api/put-user-taskboard-configuration
                         {:user-ttid (get-in data [:account-info :user-ttid])}
                         (fn [{:strs [config]}]
                           (om/update! data :taskboard-configuration config))
                         {:config {:states new-task-state-list}})))}
    "Delete")))

(defcomponent task-state
  "Has a label showing task state, a button to edit, and a button to delete."
  [task-state-text]
  (render
   (dom/div
    #js {:className "task-state"}
    (dom/label nil task-state-text)
    (om/build delete-button data {:opts opts}))))

(defcomponent task-states
  (render
   (let [states (get-in data [:taskboard-configuration "states"])]
     (dom/label nil "States:")
     (apply
      dom/div
      #js {:id "task-states"}
      (map-indexed
       (fn [idx task-state-text]
         (om/build task-state data {:opts {:task-state-text  task-state-text
                                           :task-state-index idx
                                           :states           states}}))
       states)))))

(defcomponent header
  (render
   (dom/div nil "Configure Taskboard")))

(defcomponent root
  (will-mount
   (api/get-user-taskboard-configuration
    {:user-ttid (get-in data [:account-info :user-ttid])}
    (fn [{:strs [config]}]
      (om/update! data :taskboard-configuration (update-in config ["states"] #(.parse js/JSON %))))))
  (render
   (when (get-in data [:taskboard-configuration "states"])
     (dom/div
      #js {:id "configure-taskboard-container"}
      (om/build header data)
      (om/build task-states data)))))
