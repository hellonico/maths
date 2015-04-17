(ns maths.simple.reduce)

(def *roster* '((Lucas male 30)(Juliana female 25)(Gabriel male 8)))

(defn ismale [x] (if (= (compare (second x) 'male) 0) true nil))

  (reduce +
          (map (fn [x] (nth x 2))
               (filter ismale *roster*)))


