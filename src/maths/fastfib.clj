(ns maths.fastfib
  (:require [clojure.core.reducers :as r]))

(defn fib-0
  "Returns a non-lazy sequence of fibonacci numbers"
  [^long n]
   (if (< n 2) n
    (loop [i 2 l '(1 1)]
     (if (= i n)
     l
     (recur
      (inc i)
      (cons
       (+' (first l) (second l))
       l))))))

(defn fib-2 [n]
  "Returns the last computed fibonacci number"
  (letfn [(fib* [n]
            (if (zero? n)
              [0 1]
              (let [[a b] (fib*  (bit-shift-right n 1))
                    c (*' a (-' (*' 2 b) a))
                    d (+' (*' b b) (*' a a))]
                (if (even? n)
                  [c d]
                  [d (+' c d)]))))]
    (first (fib* n))))

; (fib-0 10)

; http://en.wikibooks.org/wiki/Clojure_Programming/Examples/Lazy_Fibonacci
(def lazy-fib
  "Lazy sequence of fibonacci numbers"
  ((fn rfib [a b]
     (lazy-seq (cons a (rfib b (+' a b)))))
   0 1))

(defn p2a [n]
  (take n lazy-fib))
(p2a 10)

((fn[n]
  (drop 1 (take (inc n) ((fn rfib [a b]
     (lazy-seq (cons a (rfib b (+' a b)))))
   0 1)))) 4)

(defn even-lazy-fib[n]
  (filter even? (take n lazy-fib)))
; (even-lazy-fib 100)

; Project Euler Problem 2
(defn pe2a
  "Quite impressive performance."
  ([] (pe2a 4000000))
  ([^long n]
    (r/fold +
      (even-lazy-fib n))))

(defn pe2a2

  ([] (pe2a 4000000))
  ([^long n]
    (reduce +'
      (even-lazy-fib n))))

(defn pe2as[^String n]
  (println (pe2a (Integer/parseInt n))))
; will probably blow up with out of memory
