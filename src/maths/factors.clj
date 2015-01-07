(ns maths.factors)

(defn factors-1
  "Best version probably because not using sort and sqrt"
  [n]
	(filter #(zero? (rem n %)) (range 1 (inc n))))

(defn factors-0 [n]
  (filter #(zero? (rem n %)) (range 1 (Math/sqrt n))))

(println (factors 45))

; only count until the square root of the number
; duplicates otherwise
(defn factors-2 [n]
  (into (sorted-set)
    (mapcat (fn [x] [x (/ n x)])
      (filter #(zero? (rem n %)) (range 1 (inc (Math/sqrt n)))) )))

(println (factors 45))

(defn factors-3 [n]
  (into (sorted-set)
    (reduce concat
      (for [x (range 1 (inc (Math/sqrt n))) :when (zero? (rem n x))]
        [x (/ n x)]))))

(comment
  ; seems like all the version without squareroot are actually
  ; faster to compute
  (use 'maths.utils)
  (def i (rand-int 100000000))
  (load-test-me 50 factors-2 [i])

  )
