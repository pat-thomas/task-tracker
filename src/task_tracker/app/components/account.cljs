(ns task-tracker.app.components.account
  (:require [om.core :as om  :include-macros true]
            [om.dom  :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent account-field
  [field-name field-label-text]
  (render
   (dom/div
    #js {:className "account-field"}
    (dom/label
     #js {:className "account-field-label"}
     (str field-label-text ":"))
    (dom/input nil))))

(defcomponent root
  (render
   (dom/div
    #js {:id "account"}
    (om/build account-field data {:opts {:field-name       "username"
                                         :field-label-text "Username"}})
    (om/build account-field data {:opts {:field-name       "email"
                                         :field-label-text "Email"}})
    (om/build account-field data {:opts {:field-name       "password"
                                         :field-label-text "Password"}}))))
