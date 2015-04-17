(ns maths.simple.morechannels
  (:require [clojure.core.async :refer [go chan <! >! timeout]]))

(defn prn-with-thread-id [s]
  (prn (str s " - Thread id: " (.getId (Thread/currentThread)))))

(defn producer [c]
  (go (prn-with-thread-id "Taking a nap ")
      (<! (timeout 5000))
      (prn-with-thread-id "Now putting a name in que queue...")
      (>! c "Leo")))

(defn consumer [c]
  (go (prn-with-thread-id "Attempting to take value from queue now...")
      (prn-with-thread-id (str "Got it. Hello " (<! c) "!"))))
(def c (chan))

(consumer c)

(producer c)
