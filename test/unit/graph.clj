(ns unit.graph
  (:use midje.sweet)
  (:require [maths.dijkstra :refer :all]
  ))


(def demo-graph {:red    {:green 10, :blue   5, :orange 8},
                 :green  {:red 10,   :blue   3},
                 :blue   {:green 3,  :red    5, :purple 7},
                 :purple {:blue 7,   :orange 2},
                 :orange {:purple 2, :red    2}})

(fact "Dijkstra:looking for orange"
  (dijkstra demo-graph :orange) => {:green 10, :blue 7, :purple 2, :red 2, :orange 0})

(fact "Dijkstra:looking for red and purple"
  (dijkstra demo-graph :red :purple) => {:purple 10})

; (use 'clojure.tools.trace)
; (trace (dijkstra demo-graph :orange))
