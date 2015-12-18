(loop [n [] s (set (range 0 10))]
  (if (and (= 10 (count (set n)))
           (every? s n))
    n
    (recur (conj n (rand-int 10)) s)))

(loop [ n [] ]
  (println n)
  (if (> (count n) 9)
    n
    (let [i (rand-int 10)]
      (if (some (vec i) n )
        (recur n)
        (recur (conj n i))))))

(loop [n 0]
  (if (> n 10)
    n
    (recur (inc n))))
