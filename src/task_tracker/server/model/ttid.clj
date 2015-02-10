(ns task-tracker.server.model.ttid)

(defn gen-ttid
  [& [^java.lang.String prefix]]
  (if prefix
    (str prefix "." (java.util.UUID/randomUUID))
    (java.util.UUID/randomUUID)))
