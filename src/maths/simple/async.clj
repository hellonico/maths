(ns maths.simple.async
  (:require [clojure.core.async :refer :all]))

(def a-channel (chan 1))
(>!! a-channel "Hello, world!")
(<!! a-channel)


(<!! (let [chans [(chan) (chan) (chan)]]
                   (doseq [c chans]
                     (go (>! c 42)))
                   (go-loop [[f & r] chans result []]
                     (if f
                       (recur r (conj result (<! f)))
                       result))))

