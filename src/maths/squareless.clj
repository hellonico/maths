(ns maths.squareless
   (:use [criterium.core]))

(defn sum-square-less-1000 [^long i]
  "Find the sum of all the squared odd numbers under 1000"
  (->> (iterate inc 0)
       (map (fn [n] (* n n)))
       (take-while (partial > i))
       (filter odd?)
       (reduce +)))
(time (sum-square-less-1000 1000))

(defn sum-square-less-1000-2 []
  (reduce #(unchecked-add %1 (unchecked-multiply %2 %2)) 0 (range 1 32 2)))

(time (sum-square-less-1000-2))
; "Elapsed time: 0.047802 msecs"

(defn sum-square-less-1000-3 []
  (->> (range)
       (map (fn [n] (* n n)))
       (take-while #(> 1000 %))
       (filter odd?)
       (reduce +)))
(time (sum-square-less-1000-3))

(quick-bench (sum-square-less-1000-1))
; Execution time mean : 15.174236 µs
(quick-bench (sum-square-less-1000-2))
; Execution time mean : 1.212684 µs
(quick-bench (sum-square-less-1000-3))
; Execution time mean : 13.639832 µs
