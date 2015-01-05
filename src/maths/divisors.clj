(defn has-all-divisors [^long num]
  (loop [d (long 2)]
    (if (zero? (mod num d))
      (if (>= d 20) true (recur (inc d)))
      false)))

(time (prn (loop [i (long 1)] (if (has-all-divisors i) i (recur (inc i))))))
; "Elapsed time: 13871.574506 msecs"

(defn has-all-divisors [^long num]
  (loop [d (long 2)]
    (if (== 0 (rem num d))
      (if (>= d 20) true (recur (inc d)))
      false)))

 (time (prn (loop [i (long 1)] (if (has-all-divisors i) i (recur (inc i))))))
 ; "Elapsed time: 3063.138457 msecs"
