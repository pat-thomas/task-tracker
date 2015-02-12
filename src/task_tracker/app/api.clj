(ns task-tracker.app.api)

(def ^:private meths
  {:get    "GET"
   :post   "POST"
   :put    "PUT"
   :delete "DELETE"})

(defn api-definition-vec->fn-name
  [^clojure.lang.Keyword http-method-kw api-definition-vec]
  (symbol (str
           (name http-method-kw)
           "-"
           (clojure.string/join "-" (->> api-definition-vec (filter keyword?) (map name))))))

(defn part->request-url-part
  [part last?]
  (let [suffix (if last? "" "/")]
    (if (keyword? part)
      (str (name part) suffix)
      (str ":" part suffix))))

(defn api-definition-vec->raw-request-url
  "[:user user-ttid :task task-ttid] => user/:user-ttid/task/:task-ttid"
  [api-definition-vec]
  (str
   (apply str
          (map (fn [part]
                 (part->request-url-part part false))
               (drop-last api-definition-vec)))
   (part->request-url-part (last api-definition-vec) true)))

(comment
  (macroexpand-1 '(make-api :get [:task task-ttid]))
  (make-api :get [:task task-ttid])
  )

(defmacro make-api
  [^clojure.lang.Keyword http-method-kw api-definition-vec]
  (let [http-method     (get meths http-method-kw)
        fn-name         (api-definition-vec->fn-name http-method-kw api-definition-vec)
        raw-request-url (api-definition-vec->raw-request-url api-definition-vec)]
    `(defn ~fn-name
       [params# on-complete# & [data#]]
       (let [url# (reduce (fn [acc# part#]
                            (if (task-tracker.app.xhr/starts-with? part# ":")
                              (str acc# (get params# (keyword (apply str (rest part#)))) "/")
                              (str acc# part# "/")))
                          ""
                          ~(clojure.string/split raw-request-url #"\/"))]
         (task-tracker.app.xhr/xhr-req
          (if data#
            {:method      ~http-method
             :on-complete on-complete#
             :url         url#
             :data        data#}
            {:method      ~http-method
             :on-complete on-complete#
             :url         url#}))))))
