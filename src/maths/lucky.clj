(ns maths.lucky)

(defn drop-nth [n coll]
  (lazy-seq
   (when-let [s (seq coll)]
     (concat (take (dec n) s) (drop-nth n (drop n s))))))


(defn lucky
  "Returns sequence of Lucky numbers less than max"
  ([max] (when (pos? max) (lucky 1 (range 1 max 2))))
  ([i acc]
   (if-let [n (nth acc i nil)]
     (recur (inc i) (drop-nth n acc))
     acc)))

(lucky 10)
