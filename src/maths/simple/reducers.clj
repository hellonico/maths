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

; http://adambard.com/blog/clojure-reducers-for-mortals/
(defn reducer-test [nums]
    (into [] (r/filter even? (r/map inc nums))))

 (defn benchmark2 [f N times]
    (let [nums (vec (range N))
          start (java.lang.System/currentTimeMillis)]
      (dotimes [n times]
        (f nums))
      (- (java.lang.System/currentTimeMillis) start)))

  (benchmark2 reducer-test 1000000 10)

  (defn plus [a b] (+ a b))
  (defn plus+
    ([] 0)
    ([a b] (+ a b)))
  (reduce plus [1 2 3 4]) ; => 10
  (r/fold plus [1 2 3 4]) ; Throws an exception
  (r/fold plus+ [1 2 3 4])
