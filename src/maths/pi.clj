(ns maths.pi
    (:require [clojure.core.reducers :as r]))


(defn calculate-pi
"Calculates Pi using the approximation 4 * (1 - 1/3 + 1/5 - 1/7 + ...)"
[iterations]
(let [odd-numbers (filter odd? (iterate inc 1))]
  (* 4.0
    (apply + (map / (cycle [1 -1]) (take iterations odd-numbers))))))

(defn calculate-pi2
[iterations]
(let [odd-numbers (filter odd? (iterate inc 1))]
  (* 4.0
    (r/fold +' (map / (cycle [1 -1]) (take iterations odd-numbers))))))

;(calculate-pi2 100)

; (take 5  (map (filter odd? (iterate inc 1)))

(println "calculated pi =" (calculate-pi 100000))
(println "Math/PI =" Math/PI)

(def odd-numbers (filter odd? (iterate inc 1)))
(def cycled-odd (lazy-seq (map cycle [1 -1] odd-numbers)))

(take 5 cycled-odd)

(calculate-pi 1000)
