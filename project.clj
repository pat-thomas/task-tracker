(defproject task-tracker "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure        "1.6.0"]
                 [org.clojure/data.json      "0.2.5"]
                 [com.stuartsierra/component "0.2.2"]
                 [org.clojure/java.jdbc      "0.3.6"]
                 [java-jdbc/dsl              "0.1.1"]
                 [mysql/mysql-connector-java "5.1.25"]
                 [ring/ring-core             "1.3.2"]
                 [com.relaynetwork/kinematic "1.3.8"]
                 [http-kit                   "2.1.16"]

                 ;; client side
                 [org.clojure/clojurescript  "0.0-2760"]
                 [org.omcljs/om              "0.8.8"]
                 [om-utils                   "0.4.0"]
                 [secretary                  "1.2.1"]
                 [com.cognitect/transit-cljs "0.8.199"]]
  :cljsbuild {:builds [{:id           "development"
                        :source-paths ["src/task_tracker/app"]
                        :compiler     {:output-to     "task_tracker.js"
                                       :output-dir    "out"
                                       :optimizations :none
                                       :source-map    true}}
                       {:id           "production"
                        :source-paths ["src/task_tracker/app"]
                        :compiler     {:output-to     "task_tracker_prod.js"
                                       :optimizations :advanced
                                       :pretty-print  false
                                       :preamble      ["react/react.min.js"]
                                       :externs       ["react/externs/react.js"]}}]})
