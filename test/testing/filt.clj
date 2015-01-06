(ns testing.filt
  (:use maths.filt)
  (:use maths.utils))

(load-test-me 10 filt-1 [(double-array [1 2 3]) (double-array (range 10000))])
(load-test-me 10 filt-2 [(double-array [1 2 3]) (double-array (range 10000))])
(load-test-me 10 filt-3 [(double-array [1 2 3]) (double-array (range 10000))])
(load-test-me 10 filt-4 [(double-array [1 2 3]) (double-array (range 10000))])
