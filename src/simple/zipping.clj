(ns simple.zipping)

(require '[clojure.zip :as z])
(def g (z/vector-zip [:A [:B :C :D [:E  :F]]]))

(defn edges [vtree]
  (letfn [(parent-node [vz]
            (if-not (-> vz z/node vector?)
              (-> vz
                  z/up
                  z/left
                  (#(when % (z/node %))))))
          (edge-seq [vz]
            (if-not (z/end? vz)
              (if-let [p (parent-node vz)]
                (cons {:from p :to (z/node vz)}
                      (lazy-seq (edge-seq (-> vz z/next))))
                (edge-seq (-> vz z/next)))))]
    (let [vz (z/vector-zip vtree)]
      (edge-seq vz))))

(clojure.pprint/pprint
 (edges g))

(clojure.pprint/pprint
 (filter #(= :D (:from %)) (edges g)))
