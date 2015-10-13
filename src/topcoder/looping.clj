; lessons to learn here is related to the :
; http://stackoverflow.com/questions/26954404/why-this-code-does-not-throw-stackoverflow-exception
; http://stackoverflow.com/questions/33091143/clojure-optimize-a-threaded-map-reduce/33091453#33091453
;
; namely, we should convert to a double or a float from the start in order to avoid unnecessary casting

(defn series-sum-0
  "Compute a series : (+ 1 1/4 1/7 1/10 1/13 1/16 ...)"
  [n]
  (->> (iterate (partial + 3) 1)
       (map #(/ 1 %))
       (take n)
       (reduce +)
       float
       (format "%.2f")
       (str)))


(defn series-sum-1
  [n]
  (loop [i 0 acc 0.0]
    (if (< i n)
      (recur (inc i)
             (+ acc (/ (float 1) (inc (* 3 i)))))
      (format "%.2f" acc))))

(defn series-sum-2
  "Compute a series : (+ 1 1/4 1/7 1/10 1/13 1/16 ...)"
  [n]
  (->> (iterate (partial + 3) 1)
       (take n)
       (map #(/ 1.0 %))
       (reduce +)
       (format "%.2f")
       (str)))

(series-sum-0 2500)
(series-sum-1 2500)

(series-sum-2 25000)
