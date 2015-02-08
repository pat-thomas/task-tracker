(ns task-tracker.server.db
  (:require [com.stuartsierra.component         :as component]
            [clojure.java.jdbc                  :as jdbc]
            [task-tracker.server.service-config :refer [config]]))

(defrecord Database [subprotocol subname user password]
  component/Lifecycle
  (start [component]
    (println ";; starting database")
    component)
  (stop [component]
    (println ";; stopping database")
    component))

(defn new-database
  [{:keys [db] :as service-config}]
  (let [{:keys [subprotocol subname user password]} db]
    (map->Database {:subprotocol subprotocol
                    :subname     subname
                    :user        user
                    :password    password})))

(defn gen-find-by-sql
  [^clojure.lang.Keyword table ^java.util.Map criteria]
  (str
   (format "SELECT * FROM %s WHERE " (name table))
   (reduce (fn [acc [k v]]
             (str acc (name k) "='" v "'"))
           ""
           criteria)
   ";"))

(defn find-by
  [^java.util.Map db ^clojure.lang.Keyword table ^java.util.Map criteria]
  (jdbc/query
   db
   [(gen-find-by-sql table criteria)]))

(defn insert-record
  [^java.util.Map db ^clojure.lang.Keyword table ^java.util.Map params]
  (jdbc/insert!
   db
   table
   params))

(comment
  (let [db (.start (new-database (config :db)))]
    )
  )
