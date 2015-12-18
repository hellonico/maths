(ns unit.speed
  (:use midje.sweet)
  (:require [topcoder.looping :refer :all]
  ))

(fact "Series"
      (series-sum-1 25000) => "4.42"
      (series-sum-2 25000) => "4.42")
