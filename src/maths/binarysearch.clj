(ns maths.binarysearch)

; http://stackoverflow.com/questions/8949837/binary-search-in-clojure-implementation-performance?rq=1


(defn binary-search-clj
  "Finds earliest occurrence of x in xs (a vector) using binary search."
  ([xs x]
     (loop [l 0 h (unchecked-dec (count xs))]
       (if (<= h (inc l))
         (cond
           (== x (xs l)) l
           (== x (xs h)) h
           :else nil)
         (let [m (unchecked-add l (bit-shift-right (unchecked-subtract h l) 1))]
           (if (< (xs m) x)
             (recur (unchecked-inc m) h)
             (recur l m)))))))

(defn binary-search-java [xs x]
  (java.util.Collections/binarySearch xs x compare))
