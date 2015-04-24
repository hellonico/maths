(ns simple.file2channel
  (:require [clojure.core.async :refer :all]
            [clojure.core.async :as async]
            [clojure.java.io :as io])
  )

(defn process-file [ch file]
  (thread
   (with-open [input (io/reader file)]
     (doseq [line (line-seq input)]
       (async/>!! ch line)))))

(defn parse [line]
  (str "Parsed: " line)) ; change it to do whatever you want

(defn mapping [ch]
  (async/map parse [ch]))

(defn start []
  (let [events (mapping
                (async/chan))]
    (process-file events "10_events.json")
    (async/go-loop []
                   (let [v (async/<! events)]
                     (println v)
                     (recur)))))
