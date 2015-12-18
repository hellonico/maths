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

; compact version
(def fib-1
  (lazy-cat [1 1]
            (map + fib-1 (rest fib-1))))

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

; http://en.wikibooks.org/wiki/Clojure_Programming/Examples/Lazy_Fibonacci
(def lazy-fib
  "Lazy sequence of fibonacci numbers"
  (
   (fn rfib [a b]
     (println a b)
     (lazy-seq (cons a (rfib b ('+ a b)))))
   0 1))

(defn even-lazy-fib[n]
  (filter even? (take n lazy-fib)))

(even-lazy-fib 10)

;(take 10 (range 10))

;(compile 'maths.fastfib)
