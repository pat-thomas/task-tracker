(ns task-tracker.server.db
  (:require [clojure.java.jdbc                  :as jdbc]
            [java-jdbc.sql                      :as sql-dsl]
            [task-tracker.server.service-config :refer [config]]))

(defn gen-find-by-sql
  [^clojure.lang.Keyword table ^java.util.Map criteria]
  (str
   (format "SELECT * FROM %s WHERE " (name table))
   (if (> (count (vals criteria)) 1)
     (str (reduce (fn [acc [k v]]
                    (str acc (name k) "='" v "' AND "))
                  ""
                  (drop-last criteria))
          (let [[k v] (last criteria)]
            (str (name k) "='" v "'")))
     (reduce (fn [acc [k v]]
               (str acc (name k) "='" v "'"))
             ""
             criteria))
   ";"))

(defn find-by
  [^clojure.lang.Keyword table ^java.util.Map criteria]
  (jdbc/query
   (config :db)
   [(gen-find-by-sql table criteria)]))

(defn insert-record
  [^clojure.lang.Keyword table ^java.util.Map params]
  (jdbc/insert!
   (config :db)
   table
   params))

(comment
  (gen-where-clause-for-update {:task_description "Debone a chicken."
                                :task_state 2})
  )

(defn- gen-where-clause-for-update
  [^java.util.Map update-params]
  (reduce
   (fn [acc [k v]]
     (conj acc (str (name k) " = ?") v))
   []
   update-params))

(defn update-record
  [^clojure.lang.Keyword table ^java.util.Map lookup-fields ^java.util.Map update-params]
  (let [where-clause (sql-dsl/where lookup-fields)]
    (jdbc/update!
     (config :db)
     table
     update-params
     where-clause)))


(comment
  (update-record
   :users.tasks
   {:user_ttid "mock-pat-thomas"
    :task_ttid "mock-pat-thomas-task-1"}
   {:task_description "Debone a chicken."})
  )
