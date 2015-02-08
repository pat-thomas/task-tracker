(ns task-tracker.server.model.helpers)

(defn- model-name->symbol
  [^clojure.lang.Symbol model-name]
  (->> (-> model-name str (.split "(?=\\p{Upper})"))
       (drop 1)
       (map (fn [el]
              (.toLowerCase el)))
       (clojure.string/join "-")
       symbol))

(defmacro defmodel
  [model-name]
  `(defrecord ~model-name [~'db]
     com.stuartsierra.component/Lifecycle

     (com.stuartsierra.component/start [component#]
       component#)

     (com.stuartsierra.component/stop [component#]
       component#)))
