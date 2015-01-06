(ns maths.mutating)

(set! *unchecked-math* true)

(defn m2 [^longs xs ^longs ys]
  (dotimes [i (alength xs)]
    (aset ys i
      (* 3 (aget xs i)))))

(defn m3 [^longs xs ^longs ys]
 (dotimes [i (alength xs)]
   (aset #^longs ys i
     (unchecked-multiply 3 (aget #^longs xs i)))))
