(ns maths.dfs)

; http://codereview.stackexchange.com/questions/15961/depth-first-search-algorithm-in-clojure

(defn- dfs
  [graph goal]
  (fn search
    [path visited]
    (let [current (peek path)]
      (if (= goal current)
        [path]
        (->> current graph keys
             (remove visited)
             (mapcat #(search (conj path %) (conj visited %))))))))

(defn findpath
  "Returns a lazy sequence of all directed paths from start to goal
  within graph."
  [graph start goal]
  ((dfs graph goal) [start] #{start}))

(def graph
  {:s {:a 3 :d 4}
   :a {:s 3 :d 5 :b 4}
   :b {:a 4 :e 5 :c 4}
   :c {:b 4}
   :d {:s 4 :a 5 :e 2}
   :e {:d 2 :b 5 :f 4}
   :f {:e 4 :g 1}})

 (findpath graph :s :g)
