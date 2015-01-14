(ns maths.simple.dots
;  (:use maths.utils)
  )

(defn dot-product-1 [^doubles ws ^doubles xs]
  (reduce + (map * ws xs)))

(defn dot-product-2 [^doubles ws ^doubles xs]
 (areduce xs i ret 0.0
   (+ ret (* (aget xs i)
     (aget ws i)))))

;(time-ns 10000000 (dot-product-1 [2.0 1.0 2.0 3.0 -1.0] [4.0 5.0]))

;(time-ns 10000000
;        (dot-product-2 (double-array [2.0 1.0]) (double-array [4.0 5.0])))
