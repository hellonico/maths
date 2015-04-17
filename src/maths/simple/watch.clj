(ns maths.simple.watch)

(def r (ref 0))

 (add-watch r :label
                 (fn [label the-ref old-state new-state]
                   (println "adding that little something extra")
                   (if (< old-state 10) (dosync (commute the-ref inc)))))

 (dosync (alter r inc))
