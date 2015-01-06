(ns maths.100doors)

(defn doors-1 []
  (reduce (fn [doors toggle-idx] (update-in doors [toggle-idx] not))
          (into [] (repeat 100 false))
          (for [pass   (range 1 101)
                i      (range (dec pass) 100 pass) ]
            i)))

(defn doors-2 []
	(reduce (fn [doors idx] (assoc doors idx true))
	        (into [] (repeat 100 false))
	        (map #(dec (* % %)) (range 1 11))))

(defn open-doors [func]
  (for [[d n] (map vector (func) (iterate inc 1)) :when d] n))

(defn print-open-doors [func]
   (println
    "Open doors after 100 passes:"
    (apply str (interpose ", " (open-doors func)))))

(time (print-open-doors doors-1))
; "Elapsed time: 28.886517 msecs"

(time (print-open-doors doors-2))
; "Elapsed time: 2.216951 msecs"
