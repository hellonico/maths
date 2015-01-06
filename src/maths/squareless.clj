(ns maths.squareless)

(defn sum-square-less-1000-1 [^long i]
  "Find the sum of all the squared odd numbers under 1000"
  (->> (iterate inc 0)
       (map (fn [n] (* n n)))
       (take-while (partial > i))
       (filter odd?)
       (reduce +)))

(defn sum-square-less-1000-2 []
  (reduce #(unchecked-add %1 (unchecked-multiply %2 %2)) 0 (range 1 32 2)))

(defn sum-square-less-1000-3 []
  (->> (range)
       (map (fn [n] (* n n)))
       (take-while #(> 1000 %))
       (filter odd?)
       (reduce +)))
