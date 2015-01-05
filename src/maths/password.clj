(ns maths.password)

(defn fixed-length-password
        ([] (fixed-length-password 8))
        ([n]
           (let [chars (map char (range 33 127))
                 password (take n (repeatedly #(rand-nth chars)))]
             (reduce str password))))

(fixed-length-password 15)

 (defn rand-string [characters n]
  (->> (fn [] (rand-nth characters))
       repeatedly
       (take n)
       (apply str)))

 (rand-string "nicolas" 10)
