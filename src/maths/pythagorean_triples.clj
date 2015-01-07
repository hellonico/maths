(ns maths.pythagoreans-triples
  (:use [maths.gcd]))

; A Pythagorean triple is defined as three positive integers (a,b,c)
; where a < b < c, and a2 + b2 = c2. They are called primitive triples
; if a,b,c are coprime, that is,
; if their pairwise greatest common divisors gcd(a,b) = gcd(a,c) = gcd(b,c) = 1.
; Because of their relationship through the Pythagorean theorem,
; a, b, and c are coprime if a and b are coprime (gcd(a,b) = 1).
; Each triple forms the length of the sides of a right triangle,
; whose perimeter is P = a + b + c.

; The task is to determine how many Pythagorean triples there are
; with a perimeter no larger than 100 and
; the number of these that are primitive.

; http://rosettacode.org/wiki/Pythagorean_triples#Clojure

; This version is based on Euclid's formula:
; for each pair (m,n) such that m>n>0, m and n coprime
; and of opposite polarity (even/odd),
; there is a primitive Pythagorean triple.
; It can be proven that the converse is true as well.

(defn pyth [peri]
  (for [m (range 2 (Math/sqrt (/ peri 2)))
        n (range (inc (mod m 2)) m 2) ; n<m, opposite polarity
        :let [p (* 2 m (+ m n))]      ; = a+b+c for this (m,n)
        :while (<= p peri)
        :when (= 1 (gcd m n))
        :let [m2 (* m m), n2 (* n n),
              [a b] (sort [(- m2 n2) (* 2 m n)]), c (+ m2 n2)]
        k (range 1 (inc (quot peri p)))]
    [(= k 1) (* k a) (* k b) (* k c)]))

(defn rcount [ts] ; (->> peri pyth rcount) produces [total, primitive] counts
  (reduce (fn [[total prims] t] [(inc total), (if (first t) (inc prims) prims)])
    [0 0]
    ts))

; To handle really large perimeters,
; we can dispense with actually generating
; the triples and just calculate the counts
(defn pyth-count [peri]
  (reduce (fn [[total prims] k] [(+ total k), (inc prims)]) [0 0]
    (for [m (range 2 (Math/sqrt (/ peri 2)))
          n (range (inc (mod m 2)) m 2) ; n<m, opposite polarity
          :let [p (* 2 m (+ m n))]      ; = a+b+c for this (m,n)
          :while (<= p peri)
          :when (= 1 (gcd m n))]
      (quot peri p))))

(pyth-count 100000000)
