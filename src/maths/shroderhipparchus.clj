(ns maths.shroderhipparchus)

(defn bc "binomial coefficient" [n k]
  (if (or (zero? k) (= n k))
    1
    (+' (bc (dec n) (dec k)) (bc (dec n) k))))
(def bc (memoize bc))

(defn narayana
  "narayana numbers,
  based on binomial coefficient"
  [n k]
  (*
   (/ 1 n)
   (bc n k)
   (bc n (dec k))))
(def narayama (memoize narayana))

; (narayana 6 2)

(defn x[k n]
  (reduce
   +'
  (map
   #(*
     (narayana n %)
     (Math/pow k (dec %)))
   (range 1 (inc n))
   )))

; ; same as catalan numbers
(def c2
  (partial x 1))

(map c2
     (range 1 100))

; shroderhipparchus numbers
(def s
  (partial x 2))
(map s  (range 1 15))

(defn delannoy [m n]
  (if (or (zero? n) (zero? m))
    1
    (+
     (delannoy (dec m) n)
     (delannoy (dec m) (dec n))
     (delannoy m (dec n)))))
(time (delannoy 150 10))

(defn delannoy2 [m n]
  (reduce
   +
     (map
      #(* (bc m %) (bc n k) (Math/pow 2 %))
       (range 0 (min m n)))))
