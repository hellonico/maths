(ns testing.movingaverage
  (:use maths.movingaverage)
  (:use maths.utils)
  (:use criterium.core))

(use '[maths.generators])
(def lst (random-numbers 1000 100))

(load-test-me 10 moving-average-0 [3 lst])
; "Elapsed time: 0.003501 msecs"

(load-test-me 10 moving-average-1 [3 lst])
; "Elapsed time: 0.003868 msecs"
(load-test-me 10 moving-average-2 [3 lst])
; "Elapsed time: 0.029407 msecs"

(load-test-me 10 moving-average-3 [3 lst])
; "Elapsed time: 0.017486 msecs"

(load-test-me 10 moving-average-4 [3 lst])
; "Elapsed time: 2.422047 msecs"

(def moving-average moving-average-1)

(moving-average 5 lst)

(ema 0.5 lst)
