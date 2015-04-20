(ns maths.bronkerbosh
  (:require [clojure.set :as s]))

(defn empty-graph [n]
 (vec (repeat n #{})))

(defn add-directed-edge [g n1 n2]
 (let [n1-neighbors (g n1)]
   (assoc g n1 (conj n1-neighbors n2))))

(defn add-edge [g [n1 n2]]
 (-> g
     (add-directed-edge n1 n2)
     (add-directed-edge n2 n1)))

;The random generation is easy:
(defn random-edge [n]
  (vector (rand-int n) (rand-int n)))

;(add-edge (empty-graph 5) (random-edge 5))
;;=> [#{} #{} #{4} #{} #{2}]

(defn add-rand-edge [g]
    (add-edge g (random-edge (count g))))
; A lazy sequence of an
; evolving random graph:
(take 5 (iterate add-rand-edge (empty-graph 5)))
;([#{} #{} #{} #{} #{}   [#{} #{} #{} #{4} #{3}]
; [#{0} #{} #{} #{4} #{3}]  [#{0} #{} #{4} #{4} #{2 3}]
; [#{0} #{} #{4} #{4} #{2 3}])

(defn random-graph-a [n e]
     (last (take e (iterate add-rand-edge (empty-graph n)))))

(random-graph-a 10 15)


(defn BK4 [r p x graph]
  (if (and (empty? p) (empty? x))
    [r] ;; r is already a set
    (loop [p p, x x, cliques []]
      (if (empty? p)
        cliques
        (let [v (first p) ;; p is a set, first is not necessary the next in sequence
              nv (graph v) ;; take v-th set from graph
              cliques (concat cliques
                            (BK4 (conj r v) ;; add v to the set r
                                 (s/intersection p nv)
                                 (s/intersection x nv)
                                 graph))]
          (recur (disj p v) (conj x v) cliques))))))

(defn get-BK4 [graph]
  (BK4 #{} (set (range (count graph))) #{} graph))

(let [graph (doall (random-graph-a 1000 1000))
      bk4 (time (get-BK4 graph))]
  bk4)
