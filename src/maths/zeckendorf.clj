(ns maths.zeckendorf
  (:use [maths.fastfib]))

; http://codereview.stackexchange.com/questions/61141/zeckendorf-numbers-the-clojure-way?rq=1
(def fibs fib-1) ; using the  mapcat version

(defn zeckendorf [n]
  (if (zero? n)
    "0"
    (let [ps (->> fibs (take-while #(<= % n)) rest reverse)
          fz (fn [[s n] p]
                (if (>= n p)
                  [(conj s 1) (- n p)]
                  [(conj s 0) n]))]
      (->> ps (reduce fz [[] n]) first (apply str)))))
