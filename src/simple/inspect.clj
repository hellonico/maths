(ns simple.inspect
  (:require [clojure.inspector :refer :all]))

(def a {:a 1 :b 2 :c {:a 2 :b 1}})
(inspect-tree a)
; jframe starts
