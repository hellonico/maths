(ns maths.mercenne)

(defn prime? [^long i]
  (cond (< i 4)           (>= i 2)
        (zero? (rem i 2)) false
  :else (not-any? #(zero? (rem i %)) (range 3 (inc (Math/sqrt i))))))

(defn mersenne? [^long p] (or (= p 2)
  (let [mp (dec (Math/pow 2 p))]
    (loop [n (long 3) s (long 4)]
      (if (> n p)
        (zero? s)
        (recur (inc n) (rem (- (*' s s) 2) mp)))))))

; (mersenne? 2045)
;(filter mersenne? (filter prime? (range 0 5000)))

