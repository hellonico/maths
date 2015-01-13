(ns maths.dijkstra)

; from
; http://loganlinn.com/blog/2013/04/22/dijkstras-algorithm-in-clojure
; Dijkstra’s algorithm is a simple, yet useful method to find shortest-path to nodes a graph.

(def ^:private inf Double/POSITIVE_INFINITY)

(defn update-costs
  "Returns costs updated with any shorter paths found to curr's unvisisted
  neighbors by using curr's shortest path"
  [g costs unvisited curr]
  (let [curr-cost (get costs curr)]
    (reduce-kv
      (fn [c nbr nbr-cost]
        (if (unvisited nbr)
          (update-in c [nbr] min (+ curr-cost nbr-cost))
          c))
      costs
      (get g curr))))

(defn dijkstra
  "Returns a map of nodes to minimum cost from src using Dijkstra algorithm.
  Graph is a map of nodes to map of neighboring nodes and associated cost.
  Optionally, specify destination node to return once cost is known"
  ([g src]
    (dijkstra g src nil))
  ([g src dst]
    (loop [costs (assoc (zipmap (keys g) (repeat inf)) src 0)
           curr src
           unvisited (disj (apply hash-set (keys g)) src)]
      (cond
       (= curr dst)
       (select-keys costs [dst])

       (or (empty? unvisited) (= inf (get costs curr)))
       costs

       :else
       (let [next-costs (update-costs g costs unvisited curr)
             next-node (apply min-key next-costs unvisited)]
         (recur next-costs next-node (disj unvisited next-node)))))))

(def demo-graph {:red    {:green 10, :blue   5, :orange 8},
                 :green  {:red 10,   :blue   3},
                 :blue   {:green 3,  :red    5, :purple 7},
                 :purple {:blue 7,   :orange 2},
                 :orange {:purple 2, :red    2}})

(dijkstra demo-graph :orange)
;; => {:green 8, :blue 5, :purple 10, :red 0, :orange 8}

(dijkstra demo-graph :red :purple)
;; => {:purple 10}

; (use 'clojure.tools.trace)
; (trace (dijkstra demo-graph :orange))
