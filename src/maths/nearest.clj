(ns maths.nearest)

(defn nearest
  [m n]
  (for [[k v]   m
        [nk nv] (subseq m < k < (+ k n))
        :when (not= v nv)]
    [k v nk nv]))


  (def h {50 "Text1"
    70 "Text2"
    372 "Text1"
    391 "Text2"
    759 "Text1"
    778 "Text2"
    })

       (defn nearest-1 [m k]
         (for [m1 (keys m) m2 (keys m)
              :when (and (> m2 m1) (not= (m m1) (m m2)) (< (- m2 m1) k))]
              [m1 (get m m1)  m2 (get m m2)]))

 (defn nearest-quick [m k]
      (let [m1 (keys m) m2 (keys m)]
        (loop [inp m res []  i (first m1) m1 (rest m1) j (first m2) m2 (rest m2)]
          (cond
            (nil? i) res
            (nil? j)(recur inp res (first m1) (rest m1) j m2)
            (= i j) (recur inp res i m1 (first m2) (rest m2))
            (< j i) (recur inp res i m1 (first m2) (rest m2))
            (= (inp i) (inp j)) (recur inp res i m1 (first m2) (rest m2))
            (< (- j i) k) (recur inp (conj res [i (inp i) j (inp j)]) i m1 (first m2) (rest m2))
            (>= (- j i) k) (recur inp res (first m1) (rest m1) (first (rest m1)) (rest (rest m1)))))))


(for [i (take 10  (iterate inc 1)) :when (odd? i)]
  i)
