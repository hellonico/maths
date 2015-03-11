(ns maths.simple.maths)

(defn map-function-on-map-vals [m f]
  (reduce
   (fn [altered-map [k v]] (assoc altered-map k (f v))) {} m))

(map-function-on-map-vals {:a "test" :b "testing"} #(.toUpperCase %))
; {:b TESTING, :a TEST}


(defn remap
  "returns a function which takes a map as argument
  and applies f to each value in the map"
  [f]
  #(into {} (map (fn [[k v]] [k (f v)]) %)))

((remap inc) {:foo 1})

(reduce
 (fn [a k] (update-in a k inc))
 {:a 1 :b 2 :c 3 :d 4} [[:a] [:c]])
; {:a 2, :b 2, :c 4, :d 4}

(defn partition-when
  [f coll]
  (lazy-seq
   (when-let [s (seq coll)]
     (let [fst (first s)
           run (cons fst (take-while #(not (f %)) (next s)))]
       (cons run (partition-when f (seq (drop (count run) s))))))))

(partition-when
(fn [s] (.startsWith s ">>"))
[">> 1" "2" "3" ">> 4" "5" "6"])
