(ns maths.aliquot)

; http://blog.twonegatives.com/post/21318658303/functional-thinking-in-clojure-part-3

(defn factor? [number potential-factor]
(= 0 (rem number potential-factor)))

(defn factors [number]
(filter (partial factor? number) (range 1 (+ 1 number))))

(defn aliquot-sum [number]
(let [factors-of-square-root (filter (partial factor? number) (range 1 (Math/ceil (Math/sqrt number))))
symmetrical-factors (map (partial / number) factors-of-square-root)
proper-factors (filter (partial > number) (into factors-of-square-root symmetrical-factors))]
(reduce + proper-factors)))

(defn classify-using [factor-sum-function number]
(cond (= (factor-sum-function number) number) :perfect
(< (factor-sum-function number) number) :deficient
:default :abundant))

(defn classify
([number] (classify-using aliquot-sum number))
([factor-sum-function number] (classify-using factor-sum-function number)))
