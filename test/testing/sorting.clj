(ns testing.sorting
  (:use clojure.pprint)
  (:use [maths.sort.bubble])
  (:use [maths.sort.heap])
  (:use [maths.sort.quick])
  (:use [maths.sort.sleep])
  (:use [maths.generators]))



(heap-sort  [4 5 3 1 2 7 6])


(sleep-sort [4 5 3 1 2 7 6] 5)

(pprint (quick-sort (rand-long-array)))
