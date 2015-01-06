(ns testing.frequencies
  (:use maths.frequencies)
  (:use maths.generators))

(make-random-string 12)

(dotimes [x 4]
  (println "i: " (int (Math/pow 10.0 x)))
  (time
   (dotimes [_ (int (Math/pow 10.0 x))]
     (let [a (for [_ (range 1000)] (make-random-string 2))]
       (frequencies a)))))

(let [a (for [_ (range 1000)] (make-random-string 2))]
       (frequencies a))
