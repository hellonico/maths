(ns maths.breadth-first)

(defn bfs-eager [tree]
  (loop [ret [], queue (conj clojure.lang.PersistentQueue/EMPTY tree)]
    (if (seq queue)
      (let [[node & children] (peek queue)]
        (recur (conj ret node) (into (pop queue) children)))
      ret)))

(defn bfs-lazy [tree]
  ((fn step [queue]
     (lazy-seq
      (when (seq queue)
        (let [[node & children] (peek queue)]
          (cons node
                (step (into (pop queue) children)))))))
   (conj clojure.lang.PersistentQueue/EMPTY tree)))

(def tree [1 [2 [4] [5]] [3 [6]]])

(bfs-lazy tree)

(defn bf [& roots]
   (if (seq roots)
       (concat (map first roots) ;; values in roots
               (apply bf (mapcat rest roots))))) ;; recursively for children

(bf tree)
