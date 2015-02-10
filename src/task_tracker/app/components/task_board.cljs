(ns task-tracker.app.components.task-board
  (:require [om.core :as om  :include-macros true]
            [om.dom  :as dom :include-macros true]
            [task-tracker.app.history :as history]
            [task-tracker.app.xhr :as xhr])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent header
  [column-title]
  (render
   (dom/div
    #js {:className "header"}
    column-title)))

(defcomponent cell
  [column-number task-state task-description task-id]
  (render
   (dom/div
    #js {:className (if (= column-number task-state)
                      "cell active"
                      "cell inactive")
         :onClick   (fn [_]
                      (history/redirect (str "task-detail/" (or task-id "none"))))}
    (if (= column-number task-state)
      task-description
      "-"))))

(defcomponent column
  [column-title column-number]
  (render
   (dom/div
    #js {:className "column"}
    (om/build header data {:opts {:column-title column-title}})
    (apply
     dom/div
     #js {:id "task-rows"}
     (map (fn [task-data]
            (om/build cell data {:opts (assoc task-data :column-number column-number)}))
          data)))))

(defcomponent root
  (will-mount
   (xhr/xhr-req
    {:method      :get
     :url         (str "user/" (get-in data [:account-info :user-ttid]) "/tasks")
     :on-complete (fn [resp]
                    (om/update! data
                                :tasks
                                (map
                                 (fn [rec]
                                   {:task-state       (get rec "task_state")
                                    :task-description (get rec "task_description")})
                                 (get resp "recs"))))}))
  (render
   (apply
    dom/div
    #js {:id "task-board"}
    (map (fn [column-data]
           (om/build column (:tasks data) {:opts column-data}))
         (or (:task-board-columns data)
             [{:column-title "To Do" :column-number 0}
              {:column-title "In Progress" :column-number 1}
              {:column-title "Done" :column-number 2}])))))
