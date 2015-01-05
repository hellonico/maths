(ns maths.expt
  (:use [criterium.core]))

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

(quick-bench (.pow (BigInteger. "102") 100))
; Execution time mean : 894.031507 ns

(quick-bench (fast-expt-iter-1 102 100))
; Execution time mean : 1.151487 µs

(quick-bench (fast-expt-iter-2 102 100))
; Execution time mean : 896.172230 ns

(quick-bench (fast-expt-iter-3 102 100))
; Execution time mean : 25.483448 µs
