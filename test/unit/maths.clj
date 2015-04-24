(ns unit.maths
  (:use midje.sweet)
  (:require
  [maths.pascal :refer :all]
  [maths.mercenne :refer :all]
  [maths.sieve :refer :all]
  [maths.zeckendorf :refer :all]
  [maths.fastfib :refer :all]
  [maths.luhn :refer :all]
  [maths.binarysearch :refer :all]
  [maths.maxes :refer :all]
  [maths.takeuchi.functional :refer :all]
  [topcoder.crypt :refer :all]
  [maths.gcd :refer :all]
  [maths.squareless :refer :all]
  [maths.numbers :refer :all]
   [maths.ackermann :refer [ackermann]]
   [maths.breadslices :refer :all]
   [maths.caesar :refer :all]
   [maths.hofstadter :refer [qfirst]]
   [maths.cartesian :refer[ cartesian-product cross pcross]]
   [maths.binaryperm :refer [binary-permutation]]
   [maths.recuradd :refer [recursive-sum]]))

(fact "Counting Binary Permutation"
      (count (binary-permutation 5)) => 32)

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
           count) => 49798)

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

(facts "Catalan numbers"
  (map catalan (range 15)) =>
      '(1 2 5N 14N 42N 132N 429N 1430N 4862N 16796N 58786N 208012N 742900N 2674440N 9694845N))

(facts "Binomial Coefficient"
       (bc 10 3) => 120
       (bc 5 3) => 10
       (bc 10 2) => 45
       (bc 4 3) => 4)

(facts "Narayama Numbers"
       (narayama 6 2) => 15
       (narayama 16 5) => 496860
       )

(facts "Delannoy Numbers"
       (delannoy 50 2) => 5101)

;(tabular
;  (facts "Binomial Coefficient"
;         (bc ?x ?y) => ?expected)
;         ?x ?y ?expected
;         10    3   120
;         5      3    10
;         4      3    4
;        10     2    45
;         )

(facts "shroder-hipparchus numbers"
       (map shroderhipparchus (range 1 15))
            => '(1.0 3.0 11.0 45.0 197.0 903.0 4279.0 20793.0 103049.0 518859.0 2646723.0 1.3648869E7 7.1039373E7 3.72693519E8)
       )

(facts "Square less: 3 methods"
  (sum-square-less-1000-1 1000) => 5456
  (sum-square-less-1000-2) => 5456
  (sum-square-less-1000-3) => 5456)

(facts "GCD"
      (gcd  5799 300) => 3
      (gcd  4900 32) => 4)

(facts "Crypt"
  (crypt [1 2 3]) => 12
  (crypt [1000 999 998 997 996 995]) => 986074810223904000
  (crypt [1 1 1 1]) => 2
  (crypt [1 1 1 0]) => 0)

(facts "Takeuchi numbers"
   (first (takeuchi_number 20)) => 1200320663197275)

(facts "Luhn"
  (luhn?       49927398717) => false
  (luhn? 1234567812345678) => false
  (luhn? 1234567812345670) => true)

(facts "Maxes"
      (let [ test1 (fn [x]  (+ (- (* x x x)) (* -2 x x ) (* 3 x) -14))]
  (maxes test1  [-0.2 -10 0.1] ) =>  [-10]))

(facts "Binary search clj"
       (binary-search-clj [1 2 3 4 5 6] 3) => 2
       (binary-search-clj [1 2 3 4 5 6] 7) => nil
       (binary-search-clj [1 2 3 4 5 6] 6) => 5)

(facts "Binary search java"
       (binary-search-java [1 2 3 4 5 6] 3) => 2)

(facts "Fibonacci-0"
        (fib-0 11) => '(89 55 34 21 13 8 5 3 2 1 1))

(facts "Fibonacci-1"
        (take 10 fib-1) => '(1 1 2 3 5 8 13 21 34 55))

(facts "Fibonacci-2"
        (fib-2 11) => 89)

(facts "Lazy fib"
       (take 10 lazy-fib) => '(0 1 1 2 3 5 8 13 21 34))

(facts "Even Lazy fib"
       (last (even-lazy-fib 100)) => 218922995834555169026)

(facts "Zeckendorf"
       (zeckendorf 14) => "100001")

(fact  "Sieve"
       (last (.toLongArray (sieve 1e6))) => 149533623451648 )

(fact "Primes"
      (first (primes)) => 2
      (second (primes)) => 3
      (nth (primes) 100) => 547
      (nth (primes) 10000) => 104743)

(fact "Pascal"
      (pascal 3) => '([1] [1 1] [1 2 1]))

(fact "mersenne"
      (filter mersenne? (filter prime? (range 0 200))) => '(2 3 5 7 13 17 19))

