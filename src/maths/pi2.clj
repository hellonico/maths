(ns maths.pi2
  (:use maths.utils)
  (:require [clojure.core.reducers :as r]))

;
(defn leibniz-numerator [x]
  (* (- (rem x 4) 2) -4.0))

(defn calc-pi-leibniz
    "Calculate pi with Leibniz formula
    4/1 - 4/3 + 4/5 - 4/7 + 4/9 etc
    Very slow convergence"
    [terms]
    (r/fold + (map #(/ (leibniz-numerator %) %) (range 1 terms 2))))

(calc-pi-leibniz 1000000)

(defn calc-pi-leibniz-1 [terms]
  (r/fold + (map / (iterate - 4.0) (range 1.0 terms 2.0))))

(defn calc-pi-leibniz-2 [terms]
  (r/fold + (map / (iterate - 4.0) (range 1.0 terms 2.0))))


(defn time-ms2
  [x expr]
   (let [start# (. System (nanoTime))
        ret# (dotimes [y x] (do expr))
        ela# (double (- (. System (nanoTime)) start#))
        avg# (/ ela# x 1000)]
;      (println `expr)
   avg#))

(time-ms2 100 (calc-pi-leibniz-1 1000000))
(time-ms2 100 (calc-pi-leibniz-2 1000000))

