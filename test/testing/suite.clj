(ns testing.suite
  (:use clojure.test)
  (:require
   [maths.cartesian :refer[ cartesian-product cross pcross]]
   [maths.binaryperm :refer [binary-permutation]]
   [maths.recuradd :refer [recursive-sum]]))

(deftest test-permutation
  (is (= (count (binary-permutation 5)) 32)))

(deftest test-recursive-sum
  (is
   (= 49999985000001
    (recursive-sum (range 1 9999999) 0))))

(deftest cartesian
   (is
    (=
     (count
      (cartesian-product
       '((a b c) (1 2 3) (black white))) )
      18))) ; 3x3x2
