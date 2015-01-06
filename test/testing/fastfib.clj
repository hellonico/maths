(ns testing.fastfib
  (:use [criterium.core]))


(quick-bench (fib-0 6000))
; Execution time mean : 1.116173 ms

(quick-bench (fib-2 6000))
; Execution time mean : 39.797381 µs

(time (fib-0 16))
(time (fib-2 16))

