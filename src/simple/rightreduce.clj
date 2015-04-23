(ns simple.rightreduce)

; no rigt reduce here, but we can short-circuit a computation when
; a value is invalid


; in the below multiply-all example, when the input contains
; a 0, we short-cirtcuit the computation using
; *clojure.core/reduced*
;

(defn multiply-all [coll]
  (reduce
   (fn [accumulator next-value]
     (if (zero? next-value)
       (reduced 0)
       (* accumulator next-value)))
   1
   coll))


; we can demonstrate that the *infite* computation below
; actually finishes thanks to the reduced call above.
(multiply-all
 (cycle [1 2 3 4 0]))

; the below crashes your REPL session
; it never ends.
; (reduce * (cycle [1 2 3 4 0]))
