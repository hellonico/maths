(ns maths.traverse)

(defn paths [separator tree]
  (let [finished? (fn [[_ v]] ((complement map?) v))]
    (loop [finished-paths nil
           path-trees (seq tree)]
      (let [new-paths (mapcat
                        (fn [[path children]]
                          (map
                            (fn [[k v]]
                              (vector (str path separator k) v))
                            children))
                        path-trees)
            finished (->> (filter finished? new-paths)
                              (map
                                (fn [[k v]]
                                  (str k separator v)))
                              (concat finished-paths))
            remaining-paths (remove finished? new-paths)]
        (if (seq remaining-paths)
          (recur finished remaining-paths)
          finished)))))

(paths "/"
       {"foo" {"bar" {"bosh" "1" "bash" "3"} "baz" "2"}})
