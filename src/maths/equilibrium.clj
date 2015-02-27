(ns maths.equilibrium)

(defn equilibrium [lst]
  (loop [acc '(), i 0, left 0, right (apply + lst), lst lst]
     (if (empty? lst)
	 (reverse acc)
	 (let [[x & xs] lst
	       right    (- right x)
	       acc      (if (= left right) (cons i acc) acc)]
	   (recur acc (inc i) (+ left x) right xs)))))

(equilibrium [-7, 1, 5, 2, -4, 3, 0])

