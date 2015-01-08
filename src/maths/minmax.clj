(ns maths.minmax)

; http://stackoverflow.com/questions/5794831/lazy-sequence-min-max-finder-stackoverflow-problem

(defn min-max-by-columns [s]
   (reduce (fn [[smallest largest] y]
         [(doall (map min smallest y)) (doall (map max largest y))])
       [(first s) (first s)]
       s))

(min-max-by-columns [ [ 1 2 1 ] [1 2 3] [3 7 2]])
