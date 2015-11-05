(defn generatePermutations [^chars a n]
  (if (zero? n)
    ();(println (apply str a))
    (doseq [i (range 0 (inc n))]
      (generatePermutations a (dec n))
      (let [j (if (even? n) i 0) oldn (aget a n) oldj (aget a j)]
        (aset-char a n oldj) (aset-char a j oldn)))))

(comment

  (if (not= (count *command-line-args*) 1)
  (do
    (println "Exactly one argument is required")
    ;(System/exit 1)
    )
  (let [word (-> *command-line-args* first vec char-array)]
    (time (generatePermutations word (dec (count word))))))

)
