(ns task-tracker.server.db
  (:require [clojure.java.jdbc                  :as jdbc]
            [task-tracker.server.service-config :refer [config]]))

(def db (config :db))

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
   db
   [(gen-find-by-sql table criteria)]))

(defn insert-record
  [^clojure.lang.Keyword table ^java.util.Map params]
  (jdbc/insert!
   db
   table
   params))
