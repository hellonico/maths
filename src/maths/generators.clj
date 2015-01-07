(ns maths.generators
  (:import [java.util Random]))

(defn ^doubles rand-double-arr [n m]
  (double-array
   (take n (repeatedly #(rand m)))))

(defn ^longs rand-long-array
  ([] (rand-long-array 100000))
  ([n]
   (let [rnd (java.util.Random.)]
    (long-array (repeatedly n #(.nextLong rnd))))))

(def ^:static alphabet "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(defn make-random-string [len]
  (apply str
    (take len
      (repeatedly #(rand-nth alphabet)))))

(defn rand-string [characters n]
  (->> (fn [] (rand-nth characters))
       repeatedly
       (take n)w
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

(defn normals[size]
  (let [r (Random.)]
    (take size (repeatedly #(-> r .nextGaussian (* 0.5) (+ 1.0))))))
