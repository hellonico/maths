(ns maths.fastfib
   (:use [criterium.core]))

(defn fib [^long n]
   (if (< n 2) n
    (loop [i 2 l '(1 1)]
     (if (= i n)
     (first l)
     (recur
      (inc i)
      (cons
       (+' (first l) (second l))
       l))))))

(quick-bench (fib 6000))
; Execution time mean : 60.921101 µs

(time (fib 6000))
