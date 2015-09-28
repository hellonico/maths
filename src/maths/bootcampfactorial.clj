(ns maths.bootcampfactorial)

; https://gist.github.com/tolitius/1721519

(defn fast-factorial [number]
  (loop [n number factorial 1M]
    (if (zero? n)
      factorial
      (recur (- n 1) (* factorial n)))))

(defn fast-no-loop-factorial
  ([number] (fast-no-loop-factorial number 1M))
  ([number factorial]
   (if (zero? number)
     factorial
     (recur (- number 1) (* factorial number)))))

(defn recursive-factorial
  ([number] (recursive-factorial number 1M))
  ([number factorial]
   (if (zero? number)
     factorial
     (recursive-factorial (- number 1) (* factorial number)))))

(defn slow-factorial [number]
  (reduce #(* %1 %2) 1 (range 1M (inc number))))

(defn iter-factorial [number]
  (second
   (nth
  (iterate
    (fn[[x y]]  (vector (inc x) (* x y)))
   [1 1M]
   )
  number
  )))

(defn perf-tests [ n ]

  (println (time (fast-factorial n)))
;"Elapsed time: 0.123 msecs"
;2432902008176640000

(println (time (fast-no-loop-factorial n)))
;"Elapsed time: 0.125 msecs"
;2432902008176640000
;
(println (time (recursive-factorial n)))
;"Elapsed time: 0.135 msecs"
;2432902008176640000

(println (time (slow-factorial n)))
;"Elapsed time: 2.897 msecs"
;2432902008176640000

(println (time (iter-factorial n)))
  )

;(perf-tests 200)

;(time (iter-factorial  30))
;(time (fast-factorial 30))

; mine is faster ;)
(time (iter-factorial 80000))
; this was the fast one
(time (fast-factorial 80000))
