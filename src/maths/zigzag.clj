(ns maths.zigzag)

; friend of the jpeg compression

(defn partitions [sizes coll]
  (lazy-seq
   (when-let [n (first sizes)]
     (when-let [s (seq coll)]
       (cons (take n coll)
	     (partitions (next sizes) (drop n coll)))))))

(defn take-from [n colls]
  (lazy-seq
   (when-let [s (seq colls)]
     (let [[first-n rest-n] (split-at n s)]
       (cons (map first first-n)
	     (take-from n (concat (filter seq (map rest first-n)) rest-n)))))))

(defn zig-zag [n]
  (->> (partitions (concat (range 1 (inc n)) (range (dec n) 0 -1)) (range (* n n)))
       (map #(%1 %2) (cycle [reverse identity]) ,)
       (take-from n ,)))

; (clojure.pprint/pprint (zig-zag 10))
