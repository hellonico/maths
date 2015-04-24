(ns sort.sleep
  (require [clojure.core.async :as async :refer [chan go <! <!! >! timeout]]))

; In general, sleep sort works by starting a separate task for each item to be sorted,
; where each task sleeps for an interval corresponding to the item's sort key,
; then emits the item. Items are then collected sequentially in time.

(defn sleep-sort
  ([l] (sleep-sort l 50))
  ([l wait]
   (let [c (chan (count l))]
    (doseq [i l]
      (go (<! (timeout (* wait i)))
          (>! c i)))
    (<!! (async/into [] (async/take (count l) c))))))
