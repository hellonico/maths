(ns maths.maxes)

; https://codereview.stackexchange.com/questions/74012/getting-rid-of-extra-test-during-initialization-of-loop-recursion

 (defn maxes
      "Returns a sequence of elements from s, each with the maximum value of (f element)."
      ([f s]
       (maxes f s Double/NEGATIVE_INFINITY '()))
      ([f s best-val collected]
      (if (empty? s)
        collected
        (let [new-elt (first s)
              new-val (f new-elt)]
          (cond (>  new-val best-val) (recur f (rest s) new-val  [new-elt])
                (== new-val best-val) (recur f (rest s) best-val (conj collected new-elt))
                :else                 (recur f (rest s) best-val collected))))))

(defn test1[x]
  (+ (- (* x x x)) (* -2 x x ) (* 3 x) -14))
(test1 -0.2)
(maxes test1  [-0.2 -10 0.1] )
