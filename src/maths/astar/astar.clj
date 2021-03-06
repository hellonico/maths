(ns maths.astar
  (:use [clojure.data.priority-map :only [priority-map]]))

; http://clj-me.cgrand.net/2010/09/04/a-in-clojure/

(defn A*
 "Finds a path between start and goal inside the graph described by edges
  (a map of edge to distance); estimate is an heuristic for the actual
  distance. Accepts a named option: :monotonic (default to true).
  Returns the path if found or nil."
 [edges estimate start goal & {mono :monotonic :or {mono true}}]
  (let [f (memoize #(estimate % goal)) ; unsure the memoization is worthy
        neighbours (reduce (fn [m [a b]] (assoc m a (conj (m a #{}) b)))
                      {} (keys edges))]
    (loop [q (priority-map start (f start))
           preds {}
           shortest {start 0}
           done #{}]
      (when-let [[x hx] (peek q)]
        (if (= goal x)
          (reverse (take-while identity (iterate preds goal)))
          (let [dx (- hx (f x))
                bn (for [n (remove done (neighbours x))
                         :let [hn (+ dx (edges [x n]) (f n))
                               sn (shortest n Double/POSITIVE_INFINITY)]
                         :when (< hn sn)]
                     [n hn])]
            (recur (into (pop q) bn)
              (into preds (for [[n] bn] [n x]))
              (into shortest bn)
              (if mono (conj done x) done))))))))

(defn euclidian-distance [a b] ; multidimensional
  (Math/sqrt (reduce + (map #(let [c (- %1 %2)] (* c c)) a b))))

(defn grid [x y w h]
  (into {}
    (for [i (range w) j (range h)
          :let [x0 (+ x i) y0 (+ y j) x1 (inc x0) y1 (inc y0)]]
      {[[x0 y0] [x1 y0]] 1
       [[x1 y0] [x1 y1]] 1
       [[x1 y1] [x0 y1]] 1
       [[x0 y1] [x0 y0]] 1})))

(def g (apply dissoc (grid 0 0 4 4) (keys (grid 1 1 2 2))))

(A* g euclidian-distance [0 3] [4 2])
