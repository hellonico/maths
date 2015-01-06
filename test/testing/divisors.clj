(ns testing.divisors
  (:use maths.divisors)
  )

(time (prn (loop [i (long 1)] (if (has-all-divisors-1 i) i (recur (inc i))))))
; "Elapsed time: 13871.574506 msecs"


 (time (prn (loop [i (long 1)] (if (has-all-divisors-2 i) i (recur (inc i))))))
 ; "Elapsed time: 3063.138457 msecs"


