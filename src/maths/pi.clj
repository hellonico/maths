(ns maths.pi
    (:use maths.utils)
    (:require [clojure.core.reducers :as r]))

(defn calculate-pi
"Calculates Pi using the approximation 4 * (1 - 1/3 + 1/5 - 1/7 + ...)"
[iterations]
(let [odd-numbers (filter odd? (iterate inc 1))]
  (* 4.0
    (apply + (map / (cycle [1 -1]) (take iterations odd-numbers))))))

(defn calculate-pi2
"same as above using reducers"
[iterations]
(let [odd-numbers (filter odd? (iterate inc 1))]
  (* 4.0
    (r/fold +' (map / (cycle [1 -1]) (take iterations odd-numbers))))))


(calculate-pi 1000)
(calculate-pi2 1000)

;(calculate-pi2 100)

; (println "calculated pi =" (calculate-pi 100000))
; (println "Math/PI =" Math/PI)



; pi monte carlo method

; A Monte Carlo Simulation is a way of approximating the value of a function
; where calculating the actual value is difficult or impossible.

; It uses random sampling to define constraints on the value
; and then makes a sort of "best guess."

; A simple Monte Carlo Simulation can be used to calculate the value for π. If you had a circle and a square where the length of a side of the square was the same as the diameter of the circle, the ratio of the area of the circle to the area of the square would be π/4.

; So, if you put this circle inside the square and
; select many random points inside the square,
; the number of points inside the circle divided by
; the number of points inside the square
; and the circle would be approximately π/4.

(defn calc-pi [iterations]
  "using monte carlo simulation"
  (loop [x (rand) y (rand) in 0 total 1]
    (if (< total iterations)
      (recur (rand) (rand) (if (<= (+ (* x x) (* y y)) 1) (inc in) in) (inc total))
      (double (* (/ in total) 4)))))

(calc-pi 100000)

; (doseq [x (take 5 (iterate #(* 10 %) 10))] (println (str (format "% 8d" x) ": " (calc-pi x))))


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
  (reduce + (map / (iterate - 4.0) (range 1.0 terms 2.0))))

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

