(ns testing.suite.sorting
  (:use clojure.test)
  (:use midje.sweet)
  (:require
   [sort.bead :refer :all]
      [sort.bubble :refer :all]
      [sort.heap :refer :all]
      [sort.merge :refer :all]
      [sort.quick :refer :all]
      [sort.sleep :refer :all]))

(facts "bubble-sort"
      (bubble-sort [10 9 8 7 6 5 4 3 2 1]) =>  '(1 2 3 4 5 6 7 8 9 10))

(facts "heap-sort"
      (heap-sort [10 9 8 7 6 5 4 3 2 1]) =>  '(1 2 3 4 5 6 7 8 9 10))

(facts "merge-sort"
      (merge-sort [10 9 8 7 6 5 4 3 2 1]) =>  '(1 2 3 4 5 6 7 8 9 10))

(facts "quick-sort"
     (qsort1 [10 9 8 7 6 5 4 3 2 1]) =>  '(1 2 3 4 5 6 7 8 9 10))


(facts "sleep-sort using core.async"
     (sleep-sort [10 9 8 7 6 5 4 3 2 1]) =>  '(1 2 3 4 5 6 7 8 9 10))
