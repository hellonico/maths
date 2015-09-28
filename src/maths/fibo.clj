
(defn fibo-1 [n]
 (last
  (reduce
  (fn [a b]
    (conj a (+' (last a) (last (butlast a)))))
  [0 1]
  (range n))))

; super slow
(time (fibo-1 10000))

; this is a super trick
 (defn fibo-2  [n]
  (reduce
     (fn [[a b] _] [b (+' a b)])  ; function to calculate the next pair of values
     [0 1]                       ; initial pair of fibonnaci numbers
     (range n)))

 (time (fibo-2 10000))

(defn fibo-3 [n]
 (nth
  (iterate
   (fn[[x y]] (vector y (+' x y)))
   [0 1])
  n))

(time (fibo-3 10000))
