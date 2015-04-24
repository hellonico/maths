(ns maths.pascal)

(defn- nextrow [row]
  (vec (concat [1]
               (map
                #(apply + %)
                    (partition 2 1 row)) [1] )))

(defn pascal [n]
  (assert (and (integer? n) (pos? n)))
    (take n (iterate nextrow [1])))

(defn print-pascal [n]
   (let [triangle (pascal n)]
    (doseq [row triangle]
      (println row))))
