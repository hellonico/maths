(ns maths.barber.async
  (:require [clojure.core.async :as async]))

; barber problem solution using core.async

(defn customer-wait-time []
  (+ 10 (rand-int 21)))

(defn customers []
  (let [c (async/chan)]
    (async/go-loop [i 1]
                   (async/<! (async/timeout (customer-wait-time)))
                   (when (async/>! c (str "Customer" i))
                     (recur (inc i))))
    c))

(defn waiting-room [customer-channel]
  (let [waiting-channel (async/chan (async/dropping-buffer 3))]
    (async/pipe customer-channel waiting-channel)
    waiting-channel))

(def *time-to-cut-hair* 100)
(defn barber [waiting-channel]
  (async/go-loop [processed-customers []]
                 (if-let [customer (async/<! waiting-channel)]
                   (do
                     (async/<! (async/timeout *time-to-cut-hair*))
                     (println (str customer))
                     (recur (conj processed-customers customer)))
                   processed-customers)))

(defn solve[]
  (let [customers-channel (customers)
      customers-mult-channel (async/mult customers-channel)
      all-customers-channel (async/tap customers-mult-channel (async/chan))
      saloon-customers-channel (async/tap customers-mult-channel (async/chan))
      saloon (-> saloon-customers-channel
                 waiting-room
                 barber)
      customer-counter (async/reduce (fn [i c] (inc i)) 0 all-customers-channel)]
  (async/<!! (async/timeout 1000))
  (async/close! customers-channel)
  (println
    "Haircuts for "
    (count (async/<!! saloon))
    " customers out of "
    (async/<!! customer-counter)
    " customers")))
