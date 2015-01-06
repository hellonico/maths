(ns maths.collatz)

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

;  (time (dorun (loop [i 1] (when (< i 1000000) (collatz-length-2 i) (recur (inc i))))))


; https://gist.github.com/leedm777/326665
; http://en.wikipedia.org/wiki/Collatz_conjecture#Statement_of_the_problem

(defn
#^{:doc "Clojure code to compute a Collatz sequence."}
  collatz [n]
  (if (= n 1)
  '(1)
  (cons n
  (collatz (if (= (mod n 2) 0)
  (/ n 2)
  (+ (* n 3) 1))))))
