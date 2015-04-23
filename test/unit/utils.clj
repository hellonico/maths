(ns unit.utils
  (:use clojure.test)
  (:use midje.sweet)
  (:require
   [simple.rightreduce :refer :all]
   [simple.maps :refer :all])
  )

(facts "Map Functions on maps vals"
  (map-function-on-map-vals {:a "test" :b "testing"} #(.toUpperCase %))  =>   {:b "TESTING", :a "TEST"})

(facts "Remap"
    ((remap inc) {:foo 1}) => {:foo 2})

(facts "Using Reduce"
(reduce
 (fn [a k] (update-in a k inc))
 {:a 1 :b 2 :c 3 :d 4} [[:a] [:c]]) =>  {:a 2, :b 2, :c 4, :d 4})

(facts "Partition When"
 (partition-when
 (fn [s] (.startsWith s ">>"))
 [">> 1" "2" "3" ">> 4" "5" "6"]) => '((">> 1" "2" "3") (">> 4" "5" "6")))

(facts "Break from infinite loop"
   (multiply-all
     (cycle [1 2 3 4 0])) => 0)
