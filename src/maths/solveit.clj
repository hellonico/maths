(ns maths.solveit
  (:use [criterium.core]))


(def ^:dynamic *cpuspeed* 2.7)

;; We care about how many cycles each iteration of the solver takes:

(defmacro cyclesperit [expr its]
  `(let [start# (. System (nanoTime))
         ret# ( ~@expr (/ 1.0 ~its) ~its )
         finish# (. System (nanoTime))]
     (int (/ (* *cpuspeed* (- finish# start#)) ~its))))

(set! *unchecked-math* true)
;; Which I don't think has an equivalent in 1.2, which cuts a few cycles off:

(defn solveit-4 [t0 y0 h its]
  (let [zero (long 0)]
    (loop [t0 (double t0) y0 (double y0) h (double h) its (long its)]
      (if (> its zero)
        (let [t1 (+ t0 h)
              y1 (+ y0 (* h (f t0 y0)))]
          (recur t1 y1 h (dec its)))
      [t0 y0 h its]))))

(cyclesperit (solveit-4 0.0  1.0) 100000000) ; 37


(defn f [t y] (- t y))

(defn solveit [t0 y0 h its]
  (if (> its 0)
    (let [t1 (+ t0 h)
          y1 (+ y0 (* h (f t0 y0)))]
      (recur t1 y1 h (dec its)))
    [t0 y0 h its]))


(cyclesperit (solveit 0.0 1.0) 1000000)

(defn myf [^double t ^double y] (- t y))

(defn mysolveit [^double t0 ^double y0 ^double h ^long its]
  (if (<= its 0)
    [t0 y0 h its]
      (recur  (unchecked-add t0 h) (unchecked-add y0 (unchecked-multiply h (myf t0 y0))) h (unchecked-dec its)))
    )

(cyclesperit (mysolveit 0.0 1.0) 1000000)

(quick-bench (solveit 0.0 1.0 (/ 1.0 1000000) 1000000))
; Execution time mean : 60.180673 ms
(quick-bench (solveit-4 0.0 1.0 (/ 1.0 1000000) 1000000))
; Execution time mean : 28.973029 ms
(bench (mysolveit 0.0 1.0 (/ 1.0 1000000) 1000000))
; Execution time mean : 3.898780 ms

(time (mysolveit 0.0 1.0 (/ 1.0 1000000) 1000000))
