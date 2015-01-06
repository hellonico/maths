(ns maths.suite
  (:use clojure.test)
  (:require
   [maths.binaryperm :refer [binary-permutation]]
   [maths.recuradd :refer [recursive-sum]]
   ))

(deftest test-permutation
  (is (= (count (binary-permutation 5)) 32)))

(deftest test-recursive-sum
  (is
   (= 49999985000001
    (recursive-sum (range 1 9999999) 0))))
