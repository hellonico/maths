(ns maths.minmax)

(defn tree? [t]
  (and (seq t) (not (empty? t))))

(defn bounds2
  ([tree] (bounds2 tree 99 -99))
  ([tree minVal maxVal]
   (cond
     (number? tree) tree
     (tree? tree)
     (cond

       (nil? (bounds2 (first tree) minVal maxVal))
       (bounds2 (rest tree) minVal maxVal)

       ((complement nil?) (bounds2 (first tree) minVal maxVal))
       (cond
         (< (bounds2 (first tree) minVal maxVal) minVal)
         (recur (rest tree) (first tree) maxVal)

         (> (bounds2 (first tree) minVal maxVal) maxVal)
         (recur (rest tree) minVal (first tree)))

       (empty? tree) nil))))


; (println (bounds2 `(1 ( -2 17 (4)) -8 (6 13))))
(flatten '(1 ( -2 17 (4)) -8 (6 13)))
