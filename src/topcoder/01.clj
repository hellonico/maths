(ns topcoder.01)

(defn crypt[input]
  (if (some #{0} input) 0
  (last
   (sort
    (map
     #(/ (*' (apply *' input) (inc %)) %)
     input)))))

(crypt [1 2 3])
; 12
(crypt [1000 999 998 997 996 995])
; 986074810223904000N
(crypt [1 1 1 1])
; 2
(crypt [1 1 1 0])
; 0
