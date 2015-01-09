(ns maths.simple.diff)


(defn my-diff-func [X Y]
   (reduce #(remove (fn [x] (= x %2)) %1) X Y ))


(require '[clojure.core.reducers :as r])

(defn seq-contains?
  [coll target] (some #(= target %) coll))


(defn my-remove [a b]
  "remove values from seq b that are present in seq a"
 (into [] (r/filter #(not (seq-contains? b %)) a)))
(my-remove [1 2 3 4 5] [2 3 5] )
