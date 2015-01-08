(ns maths.quickselect)

; http://petrustheron.com/posts/clojure-top-k-quickselect.html

(defn top-k [coll k]
  (let [pivot (rand-nth coll)
        {:keys [left middle right]} (group-by
                                     #(cond (= % pivot) :middle (< % pivot) :left :else :right) coll)
        left-cnt (count left)]
    (cond
     (= left-cnt k) left
     (= (inc left-cnt) k) (concat left middle)
     (> left-cnt k) (top-k left k)
     :else ; left-cnt < k
     (concat left middle (top-k right (- k left-cnt 1))))))

; (top-k [4 5 3 2 8 3 8 9 0 9] 3)
