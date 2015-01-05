(ns maths.mutating)

(def xs (long-array (range 1000000)))
(def ys (long-array (range 1000000)))

(set! *unchecked-math* true)

(defn m2 [^longs xs ^longs ys]
  (dotimes [i (alength xs)]
    (aset ys i
      (* 3 (aget xs i)))))

(dotimes [_ 30]
  (time (m2 xs ys)))

(defn m3 [^longs xs ^longs ys]
 (dotimes [i (alength xs)]
   (aset #^longs ys i
     (unchecked-multiply 3 (aget #^longs xs i)))))

(dotimes [_ 30]
  (time (m3 xs ys)))
