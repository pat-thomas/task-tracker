(ns task-tracker.app.components.task-board
  (:require [om.core :as om  :include-macros true]
            [om.dom  :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent root
  (render
   (dom/div nil "task board")))
