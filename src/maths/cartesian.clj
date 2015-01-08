(ns maths.cartesian)

; Specifically, the Cartesian product of two sets X
; (for example the points on an x-axis)
; and Y (for example the points on a y-axis),
; denoted X × Y, is the set of all possible ordered pairs
; whose first component is a member of X and whose second component
; is a member of Y (e.g., the whole of the x–y plane):

; http://stackoverflow.com/questions/18246549/cartesian-product-in-clojure
(defn cartesian-product [colls]
  (if (empty? colls)
    '(())
    (for [x (first colls)
          more (cartesian-product (rest colls))]
      (cons x more))))

; (cartesian-product '((a b c) (1 2 3) (black white)))



; concurrent cartesian-product

(defn cross [& seqs]
  (when seqs
    (if-let [s (first seqs)]
      (if-let [ss (next seqs)]
        (for [x  s
              ys (apply cross ss)]
          (cons x ys))
        (map list s)))))

(defn pcross [s1 s2 s3]
  (when (and (first s1)
             (first s2)
             (first s3))
    (let [l1 (count s1)
          [half1 half2] (split-at (quot l1 2) s1)
          s2xs3 (cross s2 s3)
          f1 (future (for [x half1 yz s2xs3] (cons x yz)))
          f2 (future (for [x half2 yz s2xs3] (cons x yz)))]
      (concat @f1 @f2))))

(time (last (doall (cartesian-product [(range 100) (range 100) (range 100)]))))
; "Elapsed time: 1363.283195 msecs"

(time (last (doall (pcross (range 100) (range 100) (range 100)))))
; "Elapsed time: 98.906513 msecs"

(time (last (doall (cross (range 100) (range 100) (range 100)))))
; "Elapsed time: 1092.313364 msecs"

