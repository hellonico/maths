(ns maths.frequencies)

(def ^:static alphabet "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(defn make-random-string [len]
  (apply str
    (take len
      (repeatedly #(rand-nth  alphabet)))))

(make-random-string 12)

(defn frequencies
  "Returns a map from distinct items in coll to the number of times
  they appear."
  [coll]
  (reduce (fn [counts x]
            (assoc counts x (inc (get counts x 0))))
          {} coll))

(dotimes [x 4]
  (println "i: " (int (Math/pow 10.0 x)))
  (time
   (dotimes [_ (int (Math/pow 10.0 x))]
     (let [a (for [_ (range 1000)] (make-random-string 2))]
       (frequencies a)))))

(let [a (for [_ (range 1000)] (make-random-string 2))]
       (frequencies a))
