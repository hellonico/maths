(ns simple.async2
    (:require [clojure.core.async :refer :all :as async] ))



 (def in (async/chan))
  (def out (async/chan))
;  (def io-chan (io in out)) ;; io or whatever the solution is

  (async/put! in "test")
  (async/take! out (fn [x] (println x))) ;; should print "test"
  (async/put! out) ;; put into ch is equivalent to putting into `out`

  (defn io-chan [in-ch out-ch]
  (let [io (chan)]
    (go-loop []
      (>! out-ch (<! io ))
      (>! io (<! in-ch))
      (recur))
    io))
