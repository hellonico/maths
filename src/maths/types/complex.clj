(ns maths.types.complex)

(deftype I [^double real ^double imag]
  Object
  (toString [this] (str real "+" imag "i")))

(I. 2.0 3.0)

(defn plus [^I z1 ^I z2]
  (let [x1 (double (.real z1))
        y1 (double (.imag z1))
        x2 (double (.real z2))
        y2 (double (.imag z2))]
    (I. (+ x1 x2) (+ y1 y2))))

(defn times [^I z1 ^I z2]
  (let [x1 (double (.real z1))
        y1 (double (.imag z1))
        x2 (double (.real z2))
        y2 (double (.imag z2))]
    (I. (- (* x1 x2) (* y1 y2)) (+ (* x1 y2) (* y1 x2)))))

; (plus (I. 2 3) (I. 2 3))
; (times (I. 2 3) (I. 2 3))

;(time (dotimes [_ 100000]
; #(plus (I. 1 0) (I. 0 1))))
