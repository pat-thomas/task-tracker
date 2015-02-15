(ns task-tracker.app.components.task-board
  (:require [om.core                  :as om  :include-macros true]
            [om.dom                   :as dom :include-macros true]
            [task-tracker.app.history :as history]
            [task-tracker.app.api     :as api])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent header
  [column-title]
  (render
   (dom/div
    #js {:className "header"}
    column-title)))

(defcomponent cell
  [column-number task-state task-description task-ttid]
  (render
   (dom/div
    #js {:className (if (= column-number task-state)
                      "cell active"
                      "cell inactive")
         :onClick   (fn [_]
                      (history/redirect (str "task-detail/" task-ttid)))}
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

(defn states->taskboard-columns
  [states]
  (map-indexed
   (fn [idx state]
     {:column-title state :column-number idx})
   states))

(defcomponent root
  (will-mount
   (let [user-ttid (get-in data [:account-info :user-ttid])]
     (api/get-user-taskboard
      {:user-ttid user-ttid}
      (fn [{:strs [recs]}]
        (let [tasks  (map (fn [rec]
                            {:task-state       (get rec "task_state")
                             :task-description (get rec "task_description")
                             :task-ttid        (get rec "task_ttid")})
                          (get recs "tasks"))
              config (-> recs
                         (get "configuration")
                         (update-in ["states"] #(.parse js/JSON %)))]
          (om/update! data :tasks tasks)
          (om/update! data :taskboard-configuration config))))))
  (render
   (apply
    dom/div
    #js {:id "task-board"}
    (map (fn [column-data]
           (om/build column (:tasks data) {:opts column-data}))
         (-> data :taskboard-configuration (get "states") states->taskboard-columns)))))
