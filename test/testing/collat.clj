(ns maths.collat
  (:require [maths.collatz :refer :all])
  (:use [criterium.core]))

(quick-bench (collatz-length-1 1000000))
(quick-bench (collatz-length-2 1000000))
(quick-bench (collatz-length-3 1000000))

(quick-bench (collatz 1000000))
