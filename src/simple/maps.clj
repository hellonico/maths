(ns simple.maps)

(defn map-function-on-map-vals [m f]
  (reduce
   (fn [altered-map [k v]]
     (assoc altered-map k (f v))) {} m))

(defn remap
  "returns a function which takes a map as argument
  and applies f to each value in the map"
  [f]
  #(into {} (map (fn [[k v]] [k (f v)]) %)))

(defn partition-when
  [f coll]
  (lazy-seq
   (when-let [s (seq coll)]
     (let [fst (first s)
           run (cons fst (take-while #(not (f %)) (next s)))]
       (cons run (partition-when f (seq (drop (count run) s))))))))

