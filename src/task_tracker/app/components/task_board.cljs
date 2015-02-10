(ns task-tracker.app.components.task-board
  (:require [om.core :as om  :include-macros true]
            [om.dom  :as dom :include-macros true]
            [task-tracker.app.history :as history])
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
          ;;data (fetch from server eventually)
          [{:task-state       0
            :task-description "foo"}
           {:task-state       1
            :task-description "bar"}
           {:task-state       2
            :task-description "baz"}])))))

(defcomponent root
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
