(ns maths.expt)

(defn fast-expt-iter-1 [^long b ^long n]
  (let [inner (fn [a b n]
                (cond
                  (= n 0) a
                  (even? n) (recur a (*' b b) (unchecked-divide-int n 2))
                  :else (recur (*' a b) b (unchecked-subtract-int n 1))))
        ]
    (inner 1 b n)))

(defn ^long fast-expt-iter-2 [^long b ^long n]
  (loop [a (int 1) b b n n]
    (if (= n 0) a
      (if (even? n)
        (recur a (*' b b) (unchecked-divide-int n 2))
        (recur (*' a b) b (unchecked-subtract-int n 1))))))


(defn fast-expt-iter-3
  [x pow]
    (apply *' (repeat pow x)))
