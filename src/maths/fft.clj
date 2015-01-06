(ns maths.fft)

; http://pramode.net/clojure/2010/06/06/fourier-clojure-incanter/
; The fourier series expansion of a square wave is given by:
; sin(x) + (1/3)sin(3x) + (1/5)sin(5x) + ...

(def x (range 0 6.28 0.01))
(def y (map #(Math/sin %) x))

(defn seq-mul [n s]
  (map (fn [x] (* n x)) s))
(defn seq-add [a b]
  (map + a b))
(def odds (iterate #(+ % 2) 1))
(def odd-multiples
   (map seq-mul odds (repeat x)))

(def odd-harmonics
   (map (fn [x] (map #(Math/sin %) x)) odd-multiples))

(def scale-values
   (map (fn [x] (/ 1.0 x)) odds))

(def fourier-series
   (map seq-mul scale-values odd-harmonics))
(range 0 6.28 0.01)

(reduce seq-add (take 100 fourier-series))
