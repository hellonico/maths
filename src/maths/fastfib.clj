(ns maths.fastfib
   (:use [criterium.core]))

(defn fib-0 [^long n]
   (if (< n 2) n
    (loop [i 2 l '(1 1)]
     (if (= i n)
     (first l)
     (recur
      (inc i)
      (cons
       (+' (first l) (second l))
       l))))))

(quick-bench (fib-0 6000))
; Execution time mean : 1.116173 ms

(defn fib-2 [n]
  (letfn [(fib* [n]
            (if (zero? n)
              [0 1]
              (let [[a b] (fib*  (bit-shift-right n 1))
                    c (*' a (-' (*' 2 b) a))
                    d (+' (*' b b) (*' a a))]
                (if (even? n)
                  [c d]
                  [d (+' c d)]))))]
    (first (fib* n))))

(quick-bench (fib-2 6000))
; Execution time mean : 39.797381 µs

(time (fib-0 16))
(time (fib-2 16))
