(ns simple.sortmap)

(let [results {:A 1 :B 2 :C 2 :D 5 :E 1 :F 1}]
  (into (sorted-map-by (fn [key1 key2]
                         (compare (get results key2)
                                  (get results key1))))
        results))


(into
 (sorted-map-by
  (fn [key1 key2]
    (compare key1 key2)))
       {:A 1 :B 2 :F 1 :D 5 :C 2 :E 1 })
