(ns maths.simple.observers)

(def numbers (atom []))

(defn adder [key ref old-state new-state]
  (println "Current sum is " (reduce + new-state)))

(add-watch numbers :adder adder)


(->> [1 2 3 4 5 6]
     (map inc)
     (filter even?)
     (reduce +))

(dosync (swap! numbers conj 30))

(remove-watch numbers :adder)
