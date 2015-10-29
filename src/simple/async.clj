(ns simple.async
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
; [42 42 42]


(defn do-channel [f ch]
  (go-loop []
           (when-let [v (<! ch)]
             (f v)
             (recur))))

(do-channel println
            (to-chan (repeat 50 "print")))


; http://www.braveclojure.com/core-async/


; https://github.com/clojure/core.async/blob/master/examples/walkthrough.clj

(let [c (chan)]
  (thread (dotimes [i 10] (>!! c "hello2\n")))
  (print (<!! c))
    (print (<!! c))
  (close! c))

(def f (future (Thread/sleep 10000) (println "done") 100))

@f

(def f2 (future (Thread/sleep 10000) (println "done") 100))
