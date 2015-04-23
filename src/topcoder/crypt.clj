(ns topcoder.crypt)

(defn crypt[input]
  (if (some #{0} input) 0
  (last
   (sort
    (map
     #(/ (*' (apply *' input) (inc %)) %)
     input)))))
