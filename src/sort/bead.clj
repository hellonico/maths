(ns sort.bead)

(defn transpose [xs]
  (loop [ret [], remain xs]
    (if (empty? remain)
      ret
      (recur (conj ret (map first remain))
             (filter not-empty (map rest remain))))))

; severly broken
(defn bead-sort [xs]
  (->> xs
       (map #(repeat 1 %))
       transpose
       transpose
       (map #(reduce + %))))
