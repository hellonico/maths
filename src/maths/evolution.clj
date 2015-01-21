(ns maths.evolution)

; trivial example of a genetic algorithm

(def c 100)  ;number of children in each generation
(def p 0.07) ;mutation probability

(def target "I AM ON MY WAY TO PARIS")
(def tsize (count target))

(def alphabet " ABCDEFGHIJLKLMNOPQRSTUVWXYZ")

(defn- fitness [s] (count (filter true? (map = s target))))
(defn- perfectly-fit? [s] (= (fitness s) tsize))
(defn- randc [] (rand-nth alphabet))
(defn- mutate [s] (map #(if (< (rand) p) (randc) %) s))

(loop [generation 1, parent (repeatedly tsize randc)]
  (if (perfectly-fit? parent)
    (println generation, (apply str parent), (/ (fitness parent) tsize))
    (let [children (repeatedly c #(mutate parent))
          fittest (apply max-key fitness parent children)]
      (recur (inc generation), fittest))))
