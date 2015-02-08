(ns task-tracker.server.webserver
  (:require [com.stuartsierra.component :as component]
            [org.httpkit.server         :as http-kit]))

(defn start-server
  [port app]
  (http-kit/run-server app {:port port}))
