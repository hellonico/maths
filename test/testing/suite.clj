(ns testing.suite
  (:use clojure.test)
  (:use midje.sweet)
  (:require
    [maths.catalan :refer :all]
   [maths.ackermann :refer [ackermann]]
   [maths.breadslices :refer :all]
   [maths.caesar :refer :all]
   [maths.hofstadter :refer [qfirst]]
   [maths.cartesian :refer[ cartesian-product cross pcross]]
   [maths.binaryperm :refer [binary-permutation]]
   [maths.recuradd :refer [recursive-sum]]))

(fact "Counting Binary Permutation"
      (count (binary-permutation 5)) => 32
      )

(fact "Recursive Sum"
      (recursive-sum (range 1 9999999) 0) => 49999985000001)

(fact "Catersian Product"
      (count
        (cartesian-product
          '((a b c) (1 2 3) (black white))) ) => 18 ; 3x3x2
      )

(facts "Hofstadter"
      (qfirst 10) =>  [1 1 2 3 3 4 5 5 6 6]
      (last (qfirst 1000)) => 502
      (->> (qfirst 100000)
           (partition 2 1)
           (filter #(apply > %))
           count) => 49798
      )

(facts "Ackermann function"
      (ackermann 2 7) => 17
      (ackermann 2 8) => 19
      (ackermann 3 4) => 125)
;
;(tabular
;  (facts "Ackermann"
;         (ackermann ?x ?y) => ?expected)
;    ?x ?y ?expected
;    2 7 17
;    2 8 19
;    3 4 125)

;(tabular
;  (facts "Slicing bread"
;         (min-number-of-slices ?x ?y) => ?expected)
;                         ?x ?y ?expected
;                               10 2 5
;                               17 3 51)

(facts "Slicing bread"
       (min-number-of-slices 10 2) => 5
       (min-number-of-slices 17 3) => 51)

(facts "Caesar Encryption"
       (let [text "The Quick Brown Fox Jumps Over The Lazy Dog."
             enc (encrypt -1 text)]
         enc => "Sgd Pthbj Aqnvm Enw Itlor Nudq Sgd Kzyx Cnf."
         (decrypt -1 enc) => text))

(fact "Catalan numbers"
  (map catalan (range 15)) => '(1 2 5N 14N 42N 132N 429N 1430N 4862N 16796N 58786N 208012N 742900N 2674440N 9694845N))
