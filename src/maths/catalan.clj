(ns maths.catalan)

(defn c [n]
  (if (zero? n)
    1
    (*' (/ (bit-shift-left
           (inc (bit-shift-left n 1)) 1)
          (+ 2 n))
       (c (dec n))
       )))
(def c (memoize c))

(map c (range 15))
