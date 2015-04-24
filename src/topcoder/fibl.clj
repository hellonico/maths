(ns topcoder.fibl
  (:require [maths.fastfib :refer :all]))

(defn p2a [n]
  (take n lazy-fib))
(p2a 10)

((fn[n]
  (drop 1 (take (inc n) ((fn rfib [a b]
     (lazy-seq (cons a (rfib b (+' a b)))))
   0 1)))) 4)

; Project Euler Problem 2
(defn pe2a
  "Quite impressive performance."
  ([] (pe2a 4000000))
  ([^long n]
    (r/fold +
      (even-lazy-fib n))))

(defn pe2a2
  ([] (pe2a 4000000))
  ([^long n]
    (reduce +'
      (even-lazy-fib n))))

(defn pe2as[^String n]
  (println (pe2a (Integer/parseInt n))))
; will probably blow up with out of memory
