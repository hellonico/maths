(ns maths.markov)

; http://diegobasch.com/markov-chains-in-clojure-part-2-scaling-up

(defn transform
  [words]
  (->> words
       (partition 2 1)
       (reduce (fn [acc [w next-w]]
                 (update-in acc
                            [w next-w]
                            (fnil inc 0)))
               {})))

(defn markers [line]
  (concat [:start]
          (clojure.string/split line #"\s+")
          [:end]))

(defn lazy-lines [file]
  (letfn [(helper [rdr]
            (lazy-seq
             (if-let [line (.readLine rdr)]
               (concat (markers line) (helper rdr))
               (do (.close rdr) nil))))]
    (helper (clojure.java.io/reader file))))

(defn wrand
  "given a vector of slice sizes, returns the index of a slice given a
  random spin of a roulette wheel with compartments proportional to
  slices."
  [slices]
  (let [total (reduce + slices)
        r (rand total)]
    (loop [i 0 sum 0]
      (if (< r (+ (slices i) sum))
        i
        (recur (inc i) (+ (slices i) sum))))))

(defn generate-sentence [data]
  (loop [ws (data :start)
         acc []]
    (let [v (vec (vals ws))
          i (wrand v)
          w (nth (keys ws) i)
          nws (data w)]
      (if (= :end w)
        (clojure.string/join " " acc)
        (recur nws (concat acc [w]))))))

(defn -main [& args]
  (let [markov (transform (lazy-lines (first args)))]
    (dotimes [_ 10]
      (prn (generate-sentence markov))
    )))

(-main "resources/headlines.txt")
