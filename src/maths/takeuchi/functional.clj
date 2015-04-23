(ns maths.takeuchi.functional)

; define the counter as a parameter of the function
; being free of side effects, we can memoize it
; this is the best solution.

(defn tak [c x y z]
  (if (<= x y)
    [c y]
    (let [[a- x-] (tak 0 (dec x) y z)
          [b- y-] (tak 0 (dec y) z x)
          [c- z-] (tak 0 (dec z) x y)]
      (recur (+' 1 a- b- c- c) x- y- z-))))

(defn takeuchi_number [n]
   (tak 0 n 0 (inc n)))

(def tak (memoize tak))

; the real thing
(time
 (takeuchi_number 500))
