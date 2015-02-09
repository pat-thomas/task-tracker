(ns task-tracker.app.components.navbar
  (:require [om.core                  :as om  :include-macros true]
            [om.dom                   :as dom :include-macros true]
            [task-tracker.app.history :as history])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent navbar-link
  (render
   (let [{:keys [link-text link-url current-view]} opts
         class-name (if (= link-url current-view)
                      "navbar-list-item highlighted"
                      "navbar-list-item")]
     (dom/li
      #js {:className class-name
           :onClick (fn [_]
                      (history/redirect link-url))}
      link-text))))

(defcomponent root
  (render
   (dom/header
    #js {:id "navbar"}
    (apply dom/ul
           #js {:id "navbar-list"}
           (map (fn [opts]
                  (om/build navbar-link data {:opts (assoc opts :current-view (:current-view data))}))
                [{:link-text "Sign Out" :link-url "sign-out"}
                 {:link-text "My Account" :link-url "account"}
                 {:link-text "Home" :link-url "home"}])))))
