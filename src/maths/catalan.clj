(ns maths.catalan)

(defn catalan [n]
  (if (zero? n)
    1
    (*' (/ (bit-shift-left
           (inc (bit-shift-left n 1)) 1)
          (+ 2 n))
       (catalan (dec n))
       )))
(def catalan (memoize catalan))
