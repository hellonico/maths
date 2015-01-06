(ns testing.sorting
  (:use clojure.pprint)
  (:use [maths.sort.bubble])
  (:use [maths.sort.heap])
  (:use [maths.sort.bead])
  (:use [maths.sort.quick])
  (:use [maths.sort.sleep])
  (:use [maths.generators]))

(comment
  (heap-sort  [4 5 3 1 2 7 6])
  (sleep-sort [4 5 3 1 2 7 6] 5)
  (pprint (quick-sort (rand-long-array)))
  (-> [5 2 4 1 3 3 9] bead-sort println)
  )

; (apply >= (sleep-sort (rand-int-array 10 100)))

; (sleep-sort (rand-int-array 10 100))

(apply <= (sleep-sort (rand-int-array 10 100)))

(apply <= (quick-sort (rand-long-array)))

; broken 笑
(bead-sort (rand-long-array 5))
(apply <= (bead-sort (rand-long-array 5)))
