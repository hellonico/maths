(ns testing.solveit
  (:use [maths.solveit])
  (:use [criterium.core]))

(cyclesperit (mysolveit 0.0 1.0) 1000000)

(comment
(cyclesperit (mysolveit 0.0 1.0) 1000000)

(quick-bench (solveit 0.0 1.0 (/ 1.0 1000000) 1000000))
; Execution time mean : 60.180673 ms
(quick-bench (solveit-4 0.0 1.0 (/ 1.0 1000000) 1000000))
; Execution time mean : 28.973029 ms
(bench (mysolveit 0.0 1.0 (/ 1.0 1000000) 1000000))
; Execution time mean : 3.898780 ms

(time (mysolveit 0.0 1.0 (/ 1.0 1000000) 1000000))

(cyclesperit (solveit-4 0.0  1.0) 100000000) ; 37

(cyclesperit (solveit 0.0 1.0) 1000000)
)
