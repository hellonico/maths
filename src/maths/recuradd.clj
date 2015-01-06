(ns maths.recuradd)

(defn recursive-sum
  ([coll] (recursive-sum coll 0))
  ([coll acc]
   (if
     (empty? coll)
     acc
     (recur (rest coll) (+ (first coll) acc)))))
