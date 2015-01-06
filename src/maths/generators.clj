(ns maths.generators)

(defn ^doubles rand-double-arr [n m]
  (double-array
   (take n (repeatedly #(rand m)))))

(defn ^longs rand-long-array []
  (let [rnd (java.util.Random.)]
    (long-array (repeatedly 100000 #(.nextLong rnd)))))

(def ^:static alphabet "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(defn make-random-string [len]
  (apply str
    (take len
      (repeatedly #(rand-nth alphabet)))))

(defn rand-string [characters n]
  (->> (fn [] (rand-nth characters))
       repeatedly
       (take n)
       (apply str)))
; (rand-string "nicolas" 10)

(defn random-numbers [size max]
   (take size (repeatedly #(rand-int max))))
(defn rand-int-array [size max]
   (int-array (random-numbers size max)))

(defn fixed-length-password
        ([] (fixed-length-password 8))
        ([n]
           (let [chars (map char (range 33 127))
                 password (take n (repeatedly #(rand-nth chars)))]
             (reduce str password))))
; (fixed-length-password 15)
