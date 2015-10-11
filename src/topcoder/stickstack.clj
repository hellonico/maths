
(comment
  ; not used
(defn conj*
  [s x]
  (conj (vec s) x))
  )

(defn m-pop [stack]
(conj
 (vec (butlast (butlast stack)))
 (apply - (take-last 2  stack))))

(defn stick-stack
  ([input]  (stick-stack input []))
  ([input stack]
 ; (println input " > " stack)
  (if (not (first input))
    (first stack)
    (condp = (first input)
          \|  (recur (rest input) (conj stack (count stack)))
          \-  (recur (rest input) (m-pop stack))))))

(defn s [n]
  (let [modi  (if (pos? n) 1 -1)]
   (apply str
          (flatten (conj
           (repeat (* modi (bit-shift-left n 1 )) "-")
           (repeat (* modi (inc (bit-shift-left n 1))) \|)
                    )))))

; (bit-shift-left 3 1)
(stick-stack (s 9730))
(s 9730)
;:return'|'*(n*2+1)+'-'*n*2 if n>=0 else'|'*(n*-2)+'-'*(n*-2-1)

(stick-stack "||--")
(stick-stack "|||--||--")
(stick-stack "|||||-|||-|-|-|------")

(time (stick-stack "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||-|||||||||||||||||||||-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|--------------------------------------------------------------------------------------------------|-")
      )
