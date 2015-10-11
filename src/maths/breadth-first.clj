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



(defn bf [& roots]
   (if (seq roots)
       (concat (map first roots) ;; values in roots
               (apply bf (mapcat rest roots))))) ;; recursively for children

(bf tree)

(def test2
  ["33L"
   ["32R"
      ["31L"
         ["30R" [false]]
         ["11R" ["01L"
                 ["00R" [true]]] ["00L" [false]]]]
    ["30L" [false]] ["22L" ["11R" ["01L" ["00R" [true]]] ["00L" [false]]] ["02R" ["01L" ["00R" [true]]] ["00L" [false]]]]]
   ["31R"
    ["30L" [false]]
    ["11L" ["01R" ["00L" [false]]] ["00R" [true]]]]
   ["22R" ["11L" ["01R" ["00L" [false]]] ["00R" [true]]]
    ["02L" ["01R" ["00L" [false]]] ["00R" [true]]]]])

(bf test2)
(bfs-lazy test2)

(def answer '("33L" "32R" "31R" "22R" "31L" "30L" "22L" "30L" "11L" "11L" "02L" "30R" "11R" false "11R" "02R" false "01R" "00R" "01R" "00R" "01R" "00R" false "01L" "00L" "01L" "00L" "01L" "00L" "00L" true "00L" true "00L" true "00R" false "00R" false "00R" false false false false true true true))

(= (bf test2) answer)
