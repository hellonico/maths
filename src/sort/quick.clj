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

(defn qsort0
  ([^longs a]
     (qsort0 0 (long (alength a))))
  ([^longs a ^long lo ^long hi]
     (when
         (< (inc lo) hi)
       (let [pivot (aget a lo)
             split (dec (apartition a pivot (inc lo) (dec hi)))]
         (when (> split lo)
           (swap a lo split))
         (qsort0 a lo split)
         (qsort0 a (inc split) hi)))
     a))

; http://rosettacode.org/wiki/Sorting_algorithms/Quicksort#Clojure

(defn qsort1 [L]
  (if (empty? L)
      '()
      (let [[pivot & L2] L]
           (lazy-cat (qsort1 (for [y L2 :when (<  y pivot)] y))
                     (list pivot)
                     (qsort1 (for [y L2 :when (>= y pivot)] y))))))

(defn qsort2 [[pvt & rs]]
  (if pvt
    `(~@(qsort2 (filter #(<  % pvt) rs))
      ~pvt
      ~@(qsort2 (filter #(>= % pvt) rs)))))

(defn qsort3 [[pivot & xs]]
  (when pivot
    (let [smaller #(< % pivot)]
      (lazy-cat (qsort3 (filter smaller xs))
		[pivot]
		(qsort3 (remove smaller xs))))))

(def quick-sort qsort2)
