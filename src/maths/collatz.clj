(ns maths.collatz)

(set! *unchecked-math* true)

(defn collatz-length-1
  [n]
  (loop [x n acc 1]
    (if (= 1 x)
      acc
      (recur (if (even? x)
               (/ x 2)
               (inc (* 3 x)))
             (inc acc)))))

(defn collatz-length-2
  ^long [^long n]
  (loop [x n acc 1]
    (if (= 1 x)
      acc
      (recur (if (zero? (rem x 2))
               (quot x 2)
               (inc (* 3 x)))
             (inc acc)))))

; (time (dorun (loop [i 1] (when (< i 1000000) (collatz-length i) (recur (inc i))))))

 ; even? uses boxing ->  (zero? (bit-and x 1))
 ; quot runs in one instruction
 ; unchecked not used by default
 ; bit-shit-right divides by 2

 (defn collatz-length-3
  ^long [^long n]
  (loop [x n acc (int 1)]
    (if (= 1 x)
      acc
      (recur (if (zero? (bit-and x 1))
               (bit-shift-right x 1)
               (unchecked-inc (* 3 x)))
             (unchecked-inc acc)))))

;  (time (dorun (loop [i 1] (when (< i 1000000) (collatz-length-2 i) (recur (inc i))))))


; https://gist.github.com/leedm777/326665
; http://en.wikipedia.org/wiki/Collatz_conjecture#Statement_of_the_problem

(defn
#^{:doc "Clojure code to compute a Collatz sequence."}
  collatz [n]
  (if (= n 1)
  '(1)
  (cons n
  (collatz (if (= (mod n 2) 0)
  (/ n 2)
  (+ (* n 3) 1))))))


(defn hailstone
  ([^long n]
   (hailstone n '()))
  ([n acc]
    (lazy-seq
     (cond
      (= n 1) (reverse (conj acc 1))
      (even? n) (hailstone (/ n 2) (conj acc n))
      :else (hailstone (+ 1 (* 3 n)) (conj acc n))))))

(defn hailstone-len
  ([n] (hailstone-len n 0))
  ([n acc]
    (cond
      (= n 1) (inc acc)
      (even? n) (recur (bit-shift-left n 1) (inc acc))
      :else (recur (+ 1 (* 3 n)) (inc acc)))))


; (hailstone 27)
(time (count (hailstone 30000)))
(time (hailstone-len 30000))

(defn longest-hailstone-seq[n]
  (let [{max-i :num max-len :len}
      (reduce #(max-key :len %1 %2)
              (for [i (range 1 100000)]
                {:num i, :len (count (hailstone i))}))]
  (println "Maximum length" max-len "was found for hailstone(" max-i ")."))
