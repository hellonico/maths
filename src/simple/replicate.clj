(ns maths.simple.replicate)

(defn list-replicate [num list]
  (mapcat (partial repeat num) list))

(doseq [x (list-replicate 3 [1 2 3])]
  (println x))
