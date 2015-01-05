(ns maths.collatz
  (:use [criterium.core]))

(set! *unchecked-math* true)

(defn collatz-length-1
  [n]
  (loop [x n acc 1]
    (if (= 1 x)
      acc
      (recur (if (even? x)
               (/ x 2)
               (inc (* 3 x)))
             (inc acc)))))

(defn collatz-length-2
  ^long [^long n]
  (loop [x n acc 1]
    (if (= 1 x)
      acc
      (recur (if (zero? (rem x 2))
               (quot x 2)
               (inc (* 3 x)))
             (inc acc)))))

; (time (dorun (loop [i 1] (when (< i 1000000) (collatz-length i) (recur (inc i))))))

 ; even? uses boxing ->  (zero? (bit-and x 1))
 ; quot runs in one instruction
 ; unchecked not used by default
 ; bit-shit-right divides by 2

 (defn collatz-length-3
  ^long [^long n]
  (loop [x n acc (int 1)]
    (if (= 1 x)
      acc
      (recur (if (zero? (bit-and x 1))
               (bit-shift-right x 1)
               (unchecked-inc (* 3 x)))
             (unchecked-inc acc)))))

 (dotimes [_ 30] (time (collatz-length-3 1000000)))

;  (time (dorun (loop [i 1] (when (< i 1000000) (collatz-length-2 i) (recur (inc i))))))
