(ns maths.catsdogshanoi
  (:require [clojure.string :refer :all]))

(def dictionary
  (->> (slurp "/usr/share/dict/words")
       split-lines
       (map lower-case)
       (into #{})))

; (filter dictionary ["cuspidor" "cromulent" "xebec"])
(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn edits [^String word]
  "Returns words that differ from word by one letter. E.g.,
  cat => fat, cut, can, etc."
  (->> word
       (map-indexed (fn [i c]
                      (let [sb (StringBuilder. word)]
                        (for [altc alphabet :when (not= altc c)]
                          (str (doto sb (.setCharAt i altc)))))))
       (apply concat)
       (filter dictionary)))

(defn find-path [neighbors start end]
  "Return a path from start to end with the fewest hops (i.e. irrespective
  of edge weights), neighbors being a function that returns adjacent nodes"
  (loop [queue (conj clojure.lang.PersistentQueue/EMPTY start)
         preds {start nil}]
    (when-let [node (peek queue)]
      (let [nbrs (remove #(contains? preds %) (neighbors node))]
        (if (some #{end} nbrs)
          (str (cons end (take-while identity (iterate preds node))))
          (recur (into (pop queue) nbrs)
                 (reduce #(assoc %1 %2 node) preds nbrs)))))))

(find-path edits "meat" "lady")
; "(\"lady\" \"fady\" \"fany\" \"fant\" \"fent\" \"feat\" \"meat\")"
