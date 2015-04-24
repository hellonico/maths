(ns maths.numbers)


; shows how not to do it, blows stack trace
;(defn bc-inefficient "binomial coefficient" [n k]
;  (if (or (zero? k) (= n k))
;    1
;    (+' (bc (dec n) (dec k)) (bc (dec n) k))))

;; Helper function to compute C(n,k) via forward recursion
; http://en.wikipedia.org/wiki/Binomial_coefficient
(defn binomial [n k]
  (let[
       binomial-iter (fn [n k i prev]
      (if (>= i k)
    prev
    (recur n k (+ i 1) (/ (* (- n i) prev) (+ i 1))))
       )]
    (if (< k (-  n k))
      (binomial-iter n k 0 1)
      (binomial-iter n (- n k) 0 1))))
(def bc (memoize binomial))


(defn catalan [n]
  (if (<=  n 1)
    1
    (*' (/ (bit-shift-left
             (inc (bit-shift-left n 1)) 1)
           (+ 2 n))
        (catalan (dec n))
        )))
;(def catalan (memoize catalan))
(defn catalan [n]
  (* (/ 1 (inc n))
     (bc (bit-shift-left n 1) n)
  ))

(defn narayana
  "narayana numbers,
  based on binomial coefficient"
  [n k]
  (*
    (/ 1 n)
    (bc n k)
    (bc n (dec k))))
(def narayama (memoize narayana))

; (narayana 6 2)

(defn x[k n]
  (reduce
    +'
    (map
      #(*
        (narayana n %)
        (Math/pow k (dec %)))
      (range 1 (inc n))
      )))

; ; same as catalan numbers
(def c2
  (partial x 1))

;(map c2
;     (range 1 100))

; shroderhipparchus numbers
(def shroderhipparchus
  (partial x 2))
;(map s  (range 1 15))

(defn delannoy [m n]
  (if (or (zero? n) (zero? m))
    1
    (+
      (delannoy (dec m) n)
      (delannoy (dec m) (dec n))
      (delannoy m (dec n)))))
; (time (delannoy 150 10))

(defn delannoy2 [m n]
  (reduce
    +
    (map
      #(* (bc m %) (bc n %) (Math/pow 2 %))
      (range 0 (min m n)))))


(defn fuss-catalan [p r m ]
  (*
    (/ r m)
    (bc (* m p (dec r)) (dec m))
    ))
(def catalan3 (partial fuss-catalan 2 1))

(defn lobb-numbres [m n]
  (*
    ( / (inc (bit-shift-left m 1)) (+ m n 1))
        (bc (bit-shift-left n 1) (+ m n))))
