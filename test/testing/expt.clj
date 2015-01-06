(ns testing.expt
  (:use [maths.expt])
  (:use [criterium.core]))

(quick-bench (.pow (BigInteger. "102") 100))
; Execution time mean : 894.031507 ns

(quick-bench (fast-expt-iter-1 102 100))
; Execution time mean : 1.151487 µs

(quick-bench (fast-expt-iter-2 102 100))
; Execution time mean : 896.172230 ns

(quick-bench (fast-expt-iter-3 102 100))
; Execution time mean : 25.483448 µs


