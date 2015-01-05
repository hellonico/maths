
(defn test-me [myfunc]
  (dotimes [_ 30]
    (time (def b (myfunc (double-array [1 2 3]) (double-array (range 10000)))))))

(defn filt-1 [b-vec x-vec]
  (let [bc (count b-vec) xc (count x-vec)]
    (loop [n 0 sig x-vec result []]
        (if (= n (- xc bc))
          result
          (recur (inc n) (rest sig) (conj result (->> sig
                                                  (take bc)
                                                  (reverse)
                                                  (map * b-vec)
                                                  (apply +))))))))

(test-me filt-1)

(defn filt-2 [^doubles b-arr
              ^doubles x-arr]
     (let [bc (alength b-arr)
           xc (alength x-arr)
           rc (inc (unchecked-subtract-int xc bc))
           result ^doubles (double-array rc)]
       (dotimes [i rc]
         (dotimes [j bc]
           (aset result i (+ (aget result i) (* (aget x-arr (+ i j)) (aget b-arr j))))))
       result))

(test-me filt-2)

(defn filt-3 [b-vec x-vec]
  (let [bc (count b-vec) xc (count x-vec) bb-vec (reverse b-vec)]
    (loop [n 0 sig x-vec result []]
        (if (= n (- xc bc))
          result
          (recur (inc n) (rest sig) (conj result (->> sig
                                                  (take bc)
                                                  (map * bb-vec)
                                                  (apply +))))))))

(test-me filt-3)

(set! *unchecked-math* true)

(defn filt-4 [^doubles b-vec ^doubles x-vec]
  (let [bc (count b-vec) xc (count x-vec) bb-vec (reverse b-vec)]
    (loop [n 0 sig x-vec ^doubles result []]
        (if (= n (- xc bc))
          result
          (recur (unchecked-inc n) (rest sig) (conj result (->> sig
                                                  (take bc)
                                                  (map * bb-vec)
                                                  (apply +))))))))
(test-me filt-4)

