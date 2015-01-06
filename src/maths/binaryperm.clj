(ns maths.binaryperm)

(defn binary-permutation [n]
  (for [x (range (bit-shift-left 1 n))]
    (apply str
      (take n
        (map #(bit-and 1 %) (iterate #(bit-shift-right % 1) x))))))
