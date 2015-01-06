(ns testing.mutating
  (:use maths.utils)
  (:use maths.mutating))


(def xs (long-array (range 1000000)))
(def ys (long-array (range 1000000)))


(load-test-me 10 m2 [xs ys])
(load-test-me 10 m3 [xs ys])
