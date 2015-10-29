(ns maths.tak)

(defn takeuchi_number [n]
  (let [
        number (atom 0)
        tak_ (fn [x y z]
              (if (<= x y)
                y
                (do
                  (dosync (swap! number inc))
                  (tak_ (tak_ (dec x) y z)
                       (tak_ (dec y) z x)
                       (tak_ (dec z) x y)))))
        ]
    (dosync (reset! number 0))
    (tak_ n 0 (inc n))
    @number))

(time
 (takeuchi_number 10))
