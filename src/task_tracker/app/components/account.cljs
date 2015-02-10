(ns task-tracker.app.components.account
  (:require [om.core :as om  :include-macros true]
            [om.dom  :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent account-field
  [field-name field-label-text]
  (render
   (let [data-key (keyword field-name)]
     (dom/div
      #js {:className "account-field"}
      (dom/label
       #js {:className "account-field-label"}
       (str field-label-text ":"))
      (dom/input #js {:onChange (fn [e]
                                  (let [val (.. e -target -value)]
                                    (om/update! data data-key val)))
                      :value (data-key data)})))))

(defcomponent root
  (render
   (dom/div
    #js {:id "account"}
    (om/build account-field (:account-info data) {:opts {:field-name       "username"
                                                         :field-label-text "Username"}})
    (om/build account-field (:account-info data) {:opts {:field-name       "email"
                                                         :field-label-text "Email"}})
    (om/build account-field (:account-info data) {:opts {:field-name       "password"
                                                         :field-label-text "Password"}}))))
