(ns maths.nqueens)

;
; This produces all solutions by essentially a backtracking algorithm.
; The heart is the extends? function, which takes a partial solution
; for the first k<size columns and sees if the solution can be extended
; by adding a queen at row n of column k+1.
; The extend function takes a list of all partial solutions for k columns
; and produces a list of all partial solutions for k+1 columns.
; The final list solutions is calculated by starting with
; the list of 0-column solutions (obviously this is the list [ [] ],
; and iterates extend for size times.
;

(def size 5)

(defn extends? [v n]
  (let [k (count v)]
    (not-any? true?
      (for [i (range k) :let [vi (v i)]]
        (or
          (= vi n)  ;check for shared row
          (= (- k i) (Math/abs (- n vi)))))))) ;check for shared diagonal

(defn extend [vs]
  (for [v vs
        n (range 1 (inc size)) :when (extends? v n)]
    (conj v n)))

(def solutions
  (nth (iterate extend [[]]) size))

; (println (first solutions))
(doseq [s solutions]
  (println s))

(println (count solutions) "solutions")
