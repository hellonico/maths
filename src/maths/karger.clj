(ns math.karger
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [clojure.pprint :refer [pprint]]))

; http://stackoverflow.com/questions/23932181/implementing-kargers-minimum-cut-algorithm-in-functional-paradigm

(defn make-maps
  [filename]
  (reduce (fn [graph line]
            (let [[node & edges] (->> line
                                      (#(string/split % #"\W+"))
                                      (remove #{""})
                                      (map read-string))]
              (assoc graph node (set edges))))
          {}
          (line-seq (io/reader filename))))

(defn karger
  [graph]
  (if (<= (count (keys graph))
          2)
    (count (graph (apply min (keys graph))))
    (let [start (rand-nth (keys graph))
          finish (rand-nth (vec (graph start)))
          graph (loop [g graph
                       [edge & edges] (seq (graph finish))]
                  (if-not edge
                    g
                    (recur
                     (if (= edge start)
                       g
                       (update-in g [start] conj edge))
                     edges)))
          graph (loop [g graph
                       [edge & edges] (seq (graph finish))]
                  (if-not edge
                    g
                    (let [gr (update-in g [edge] disj finish)
                          gr (if (= edge start)
                               gr
                               (update-in gr [edge] conj start))]
                      (recur gr edges))))
          graph (dissoc graph finish)]
      (recur graph))))

(defn -main
  [& [file]]
  (let [file (or file "resources/kargerAdj.txt")
        graph (make-maps file)]
    (println "min cut is: "
             (reduce min (repeatedly 1801 #(karger graph))))))

(-main)
