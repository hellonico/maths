(ns maths.divisors)

(defn has-all-divisors-1 [^long num]
  (loop [d (long 2)]
    (if (zero? (mod num d))
      (if (>= d 20) true (recur (inc d)))
      false)))

(defn has-all-divisors-2 [^long num]
  (loop [d (long 2)]
    (if (== 0 (rem num d))
      (if (>= d 20) true (recur (inc d)))
      false)))
