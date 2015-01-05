(ns maths.cosine
  (:use [criterium.core]))

(set! *unchecked-math* true)
(set! *warn-on-reflection* true)

(defn cosine-similarity [^doubles va ^doubles vb]
    (loop [p 0.0
           na 0.0
           nb 0.0
           i  (dec (count va))]
      (if (> 0 i)
        (/ p (* (Math/sqrt na) (Math/sqrt nb)))
        (let [a  (aget va i)
              b  (aget vb i)]
          (recur (+ p (* a b))
                 (+ na (* a a))
                 (+ nb (* b b))
                 (dec i))))))

(defn rand-double-arr [n m]
  (double-array
   (take n (repeatedly #(rand m)))))

(def ma (rand-double-arr 200 10000))
(def mb (rand-double-arr 200 10000))

(quick-bench (cosine-similarity ma mb))
; Execution time mean : 672.277530 ns
