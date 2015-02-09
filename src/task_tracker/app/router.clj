(ns task-tracker.app.router)

(defn- route-name->route-url
  [route-name]
  (str "/" route-name))

(defmacro current-view-route!
  ([route-name]
   `(secretary.core/defroute ~route-name ~(route-name->route-url route-name)
      []
      (task-tracker.app.router/set-current-view! ~(str route-name))))
  ([route-name url]
   `(secretary.core/defroute ~route-name ~url
      []
      (task-tracker.app.router/set-current-view! ~(str route-name))))
  ([route-name url args-vec]
   (let [current-view-opts (reduce (fn [acc arg]
                                     (assoc acc (keyword arg) arg))
                                   {}
                                   args-vec)]
     `(secretary.core/defroute ~route-name ~url
        ~args-vec
        (task-tracker.app.router/set-current-view! ~(str route-name) ~current-view-opts)))))
