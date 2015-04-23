(ns sort.quick)

(set! *unchecked-math* true)
(set! *warn-on-reflection* true)

(defn swap [^longs a ^long i ^long j]
  (let [t (aget a i)]
    (aset a i (aget a j))
    (aset a j t)))

(defn ^long apartition [^longs a ^long pivot ^long i ^long j]
  (loop [i i j j]
    (if (<= i j)
      (let [v (aget a i)]
        (if (< v pivot)
          (recur (inc i) j)
          (do
            (when (< i j)
              (aset a i (aget a j))
              (aset a j v))
            (recur i (dec j)))))
      i)))

(defn quick-sort
  ([^longs a]
     (quick-sort a 0 (long (alength a))))
  ([^longs a ^long lo ^long hi]
     (when
         (< (inc lo) hi)
       (let [pivot (aget a lo)
             split (dec (apartition a pivot (inc lo) (dec hi)))]
         (when (> split lo)
           (swap a lo split))
         (quick-sort a lo split)
         (quick-sort a (inc split) hi)))
     a))
