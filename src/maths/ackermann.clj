(ns maths.ackermann)

; dumb way of computing ackermann
; blows really quickly
(defn ackermann [m n]
  ; (println m ":" n)
  (cond (zero? m) (inc n)
        (zero? n) (ackermann (dec m) 1)
        :else (ackermann (dec m) (ackermann m (dec n)))))

; attempt at an iterative version, but doesn't give the proper results
(defn ackermann-iterative[m n]
    (loop [n n stack (seq [m])]
      (let [m (first stack)
            cn (or (and (not (nil? m)) (== m 0)) (== n 0))
            nextn (if cn (+ n m 1) (dec n))
            newstack (rest stack)
            ]
        ;(println m ":" n ":" newstack)
        (if (empty? stack)
          n
          (if cn
            (recur nextn newstack)
            (recur nextn (flatten (conj [m (dec m)] newstack))))))))

; is 壊れてる
; (ackermann-iterative 2 3)

;2:1:[]
;2:0:[1]
;1:3:[]
;1:2:[0]
;1:1:[0, 0]
;1:0:[0, 0, 0]
;0:2:[0, 0]
;0:3:[0]
;0:4:[]
;5
