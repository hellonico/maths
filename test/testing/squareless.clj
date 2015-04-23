(ns testing.squareless
   (:use [maths.squareless])
   (:use [criterium.core]))



(time (sum-square-less-1000-2))
; "Elapsed time: 0.047802 msecs"

(time (sum-square-less-1000 1000))

(time (sum-square-less-1000-3))

(quick-bench (sum-square-less-1000-1 1000))
; Execution time mean : 15.174236 µs
(quick-bench (sum-square-less-1000-2))
; Execution time mean : 1.212684 µs
(quick-bench (sum-square-less-1000-3))
; Execution time mean : 13.639832 µs
