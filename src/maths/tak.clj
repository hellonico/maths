(def number (atom 0))

(defn tak[x y z]
  (if (<= x y)
    y
    (do
    (dosync (swap! number inc))
    (tak (tak (dec x) y z)
         (tak (dec y) z x)
         (tak (dec z) x y)))))

(defn takeuchi_number [n]
  (dosync (reset! number 0))
  (tak n 0 (inc n))
  @number)

(time (takeuchi_number 10))
