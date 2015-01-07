(ns maths.zeckendorf
  (:use [maths.utils])
  (:use [maths.fastfib]))

(def fibs
  (lazy-cat [1 1] (map + fibs (rest fibs))))

(defn z [n]
  (if (zero? n)
    "0"
    (let [ps (->> fibs (take-while #(<= % n)) rest reverse)
          fz (fn [[s n] p]
                (if (>= n p)
                  [(conj s 1) (- n p)]
                  [(conj s 0) n]))]
      (->> ps (reduce fz [[] n]) first (apply str)))))

(defn -main[& args]
  (doseq [n (range 0 (str->int (first args) 30))]
    (println n (z n))))
