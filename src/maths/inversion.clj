(ns maths.inversion)
(set! *unchecked-math* true)

(defn count-inversions [coll]
     (let [^ints integers (int-array coll)
           size (long (count integers))]
       (loop [c (long 0)
              i (long 0)]
         (if (>= i size)
           c
           (recur
             (loop [c (long c)
                    v (aget integers i)
                    j (inc i)]
               (if (>= j size)
                 c
                 (recur (long (if (> v (aget integers j)) (inc c) c)) v (inc j))))
             (inc i))))))

(time
 (count-inversions (for [i (range 100000)] (rand-int 100))))
