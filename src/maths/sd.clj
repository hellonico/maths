(ns maths.sd)

; In statistics and probability theory, the standard deviation (SD)
; (represented by the Greek letter sigma, σ) measures the amount of variation
; or dispersion from the average.[1]
; A low standard deviation indicates that the data points tend to be very close to the mean
; (also called expected value); a high standard deviation indicates that the data points
; are spread out over a large range of values.

(defn std-dev [samples]
  (let [n (count samples)
	mean (/ (reduce + samples) n)
	intermediate (map #(Math/pow (- %1 mean) 2) samples)]
    (Math/sqrt
     (/ (reduce + intermediate) n))))

(println (std-dev  [2 4 4 4 5 5 7 9])) ;;2.0

(println (std-dev  [-10 100 4 4 5 5 7 9])) ;; 32.4
