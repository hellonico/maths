(ns maths.mercenne
  (:require [maths.sieve :refer [prime?]]))

(defn mersenne? [^long p] (or (= p 2)
  (let [mp (dec (Math/pow 2 p))]
    (loop [n (long 3) s (long 4)]
      (if (> n p)
        (zero? s)
        (recur (inc n) (rem (- (*' s s) 2) mp)))))))

