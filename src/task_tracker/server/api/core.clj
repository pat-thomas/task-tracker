(ns task-tracker.server.api.core
  (:require [kinematic.core                 :as kinematic]
            [kinematic.dsl                  :as dsl]
            [ring.middleware.session        :as session]
            [ring.middleware.session.cookie :as cookie]))

(dsl/defweb :api
  :mount-point "/"
  :app-ns-prefix :task-tracker.server.api.v1)

(defmacro app
  [& handler-forms]
  `(-> ~@(reverse handler-forms)))

(def web-app
  (app
   (session/wrap-session {:store       (cookie/cookie-store {:key "abcdefgh12345678"})
                          :cookie-name "task-tracker-api"})
   (dsl/dyn-handler :api)))
