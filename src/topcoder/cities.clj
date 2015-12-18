(ns trigperf.core (:gen-class))

(deftype city [^String name ^double latr ^double lonr
               ^double sinlat ^double coslat ^double sinlon ^double coslon])

(defn dist [^city city1 ^city city2]
  (let [st1 (double (.sinlat city1)) st2 (double (.sinlat city2))
        ct1 (double (.coslat city1)) ct2 (double (.coslat city2))
        ln1 (double (.lonr city1))   ln2 (double (.lonr city2))]
    (Math/acos
     (+
      (* st1 st2)
      (*
       ct1
       ct2
       (Math/cos (Math/abs (- ln1 ln2)))
       )))))

(defn cost [route]
  (reduce + (map dist route (rest route))))

(defn test-data []
  (take 100 (repeatedly
             #(let [latr (/ (* (- (rand 180) 90) Math/PI) 180)
                    lonr (/ (* (- (rand 360) 180) Math/PI) 180)]
                (city. "" latr lonr
                       (Math/sin latr) (Math/cos latr)
                       (Math/sin lonr) (Math/cos lonr))))))

(defn run-test []
  (let [data (test-data)]
    (time
     (dotimes [i 99999] (cost data))
;     (loop [i 0]
;       (if (< i 100000)
;         (do
;           (cost data)
;           (recur (inc i)))))

          )))

(defn -main [& args]
  (run-test))

;(set! *unchecked-math* true)
(set! *unchecked-math* :warn-on-boxed)
(run-test)
