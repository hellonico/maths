(ns maths.barber3
  (:require [clojure.core.async :refer
    [put! dropping-buffer timeout chan go-loop <! chan ]]))

(def shop (chan (dropping-buffer 3)))

(defn customer [i shop]
  (put! shop i (fn [v] (println "customer" i "going to the barber"))))

(defn barber [shop]
 (go-loop []
  (let [v (<! shop)]
    (println "cutting hair2 of customer " v)
    (<! (timeout (+ 100 (rand-int 600)))))
    (recur)))

(defn solve[]
  (barber shop)
  (doseq [n (range 1 20)]
     (Thread/sleep (+ 100 (rand-int 200)))
     (customer n shop)))

; (solve)
