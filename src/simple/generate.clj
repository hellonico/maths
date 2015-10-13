(defn generate
  [f & more]
  (letfn [(recurse
            [coll args]
            (let [next-val (apply f args)]
              (lazy-cat coll (recurse [next-val] (conj (butlast args) next-val)))))]
    (recurse more (reverse more))))

(defn generate' [f h & r]
  (cons h
    (lazy-seq
      (apply generate' f
        (reverse (cons (apply f h r) (reverse r)))))))

(take 10
      (generate' * 2 1))
