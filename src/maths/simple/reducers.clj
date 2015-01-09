(ns maths.simple.reducers
  (:require [clojure.core.reducers :as r]))

(def workers
  (+ 2 (.. Runtime getRuntime availableProcessors)))

(defn slow+
  ([] 0)
  ([x] x)
  ([x y] (reduce + (range 100000)) (+ x y)))


(defn run-test
  "Compare adding numbers using different methods"
  []
  (let [n 8000]
   (time (reduce slow+ (range n)))
   ; "Elapsed time: 47086.014941 msecs"
   (time (reduce slow+ (pmap #(reduce slow+ %) (partition-all (* workers 100) (range n)))))
   ; "Elapsed time: 23205.69518 msecs"
   (time (r/fold slow+ (vec (range n))))))
   ; "Elapsed time: 23144.566634 msecs"

; (run-test)

; (contains? [7 2 3 4 5 6] 6)

