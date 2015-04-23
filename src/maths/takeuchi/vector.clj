(ns maths.vector)

(defn tak [x y z ^longs counter]
  (if (<= x y)
    y
    (do
      (aset counter 0 (inc (aget counter 0)))
      (tak (tak (dec x) y z counter)
           (tak (dec y) z x counter)
           (tak (dec z) x y counter)
           counter))))

(defn takeuchi_number [n]
  (let [counter (long-array [0])]
    (tak n 0 (inc n) counter)
    (aget counter 0)))

; slow
; (time (takeuchi_number 14))
