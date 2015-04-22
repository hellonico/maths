(ns maths.power)

; bad, blows stack
(defn exp0 [x n]
     (if (zero? n) 1
         (* x (exp0 x (dec n)))))

(defn exp1 [x n]
  (loop [acc 1 n n]
    (if (zero? n) acc
        (recur (* x acc) (dec n)))))

(defn exp2 [x n]
  (reduce * (repeat n x)))

(defn exp3 [x n]
  (loop [x x n n r 1]
    (cond
      (= n 0) r
      (even? n) (recur (* x x) (/ n 2) r)
      :else (recur x (dec n) (* r x)))))

(defn exp4 [x pow]
  (apply * (repeat pow x)))

; sneaky-impl, fast
(defn exp5 [b n]
  (let [inner (fn [a b n]
                (cond
                  (= n 0) a
                  (even? n) (recur a (* b b) (bit-shift-right n 1))
                  :else (recur (* a b) b (dec n))))
        ]
    (inner 1 b n)))

; handles negative
(defn abs [p]
  (if (pos? p) p (- p)))

(defn pow [n p]
  (let [result (apply * (take (abs p) (cycle [n])))]
    (if (neg? p) (/ 1 result) result)))

(doseq [ func [exp0 exp1 exp2 exp3 exp4 exp5] ]
  (println "-- " func " --")
  (time (dotimes [i 10000]  (func 23 10) )))
