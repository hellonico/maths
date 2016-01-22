(ns simple.trans
  (:require [clojure.core.async :refer :all]))

(defn count-xf [rf]
  (let [ctr (volatile! 0)]
    (completing
     (fn [result _]
       (rf result (vswap! ctr inc))))))

(let [ch (chan 1 count-xf)]
  (onto-chan ch (repeat 10 true))
  (<!! (clojure.core.async/into [] ch)))
