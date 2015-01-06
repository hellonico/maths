(ns testing.cosine
  (:use maths.cosine)
  (:require
   [maths.generators :refer :all]
   [criterium.core :refer :all]))

(def ma (rand-double-arr 200 10000))
(def mb (rand-double-arr 200 10000))

(quick-bench (cosine-similarity ma mb))
; Execution time mean : 672.277530 ns

