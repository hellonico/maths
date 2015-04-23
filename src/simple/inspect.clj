(ns simple.inspect
  (:require [clojure.inspector :refer :all]))

; shows how you can inspect a map or a tree
; graphically without any additional software
; the below will start a new  jframe
(def a {:a 1 :b 2 :c {:a 2 :b 1}})
(inspect-tree a)

