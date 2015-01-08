(ns maths.breadslices
  (:use [maths.gcd]))

(defn min-number-of-slices [l b]
      (let [side (gcd l b)]
           (/ (* l b) (* side side))))

(min-number-of-slices 10 2)
