(ns maths.solveit)

; solving diff equations
; http://www.learningclojure.com/2013/02/clojure-is-fast-is-clojure-still-fast.html

(def ^:dynamic *cpuspeed* 2.7)

;; We care about how many cycles each iteration of the solver takes:

(defmacro cyclesperit [expr its]
  `(let [start# (. System (nanoTime))
         ret# ( ~@expr (/ 1.0 ~its) ~its )
         finish# (. System (nanoTime))]
     (int (/ (* *cpuspeed* (- finish# start#)) ~its))))

(set! *unchecked-math* true)
;; Which I don't think has an equivalent in 1.2, which cuts a few cycles off:


(defn f [t y] (- t y))

(defn solveit-4 [t0 y0 h its]
  (let [zero (long 0)]
    (loop [t0 (double t0) y0 (double y0) h (double h) its (long its)]
      (if (> its zero)
        (let [t1 (+ t0 h)
              y1 (+ y0 (* h (f t0 y0)))]
          (recur t1 y1 h (dec its)))
      [t0 y0 h its]))))

(defn solveit [t0 y0 h its]
  (if (> its 0)
    (let [t1 (+ t0 h)
          y1 (+ y0 (* h (f t0 y0)))]
      (recur t1 y1 h (dec its)))
    [t0 y0 h its]))

(defn myf [^double t ^double y] (- t y))
(defn mysolveit [^double t0 ^double y0 ^double h ^long its]
  (if (<= its 0)
    [t0 y0 h its]
      (recur  
        (unchecked-add t0 h) 
        (unchecked-add y0 (unchecked-multiply h (myf t0 y0)))
        h 
        (unchecked-dec its))))
