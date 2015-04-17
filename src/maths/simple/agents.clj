(ns maths.simple.agents)

; example 1: agent
(def counter (agent 0))
(send counter inc)

@counter

(doseq [_ (range 10)]
  (send counter #(+ % 2)))

@counter


; example 2: atom and add-watch
(def a (atom {}))

(add-watch a :watcher
  (fn [key atom old-state new-state]
    (prn "-- Atom Changed --")
    (prn "key" key)
    (prn "atom" atom)
    (prn "old-state" old-state)
    (prn "new-state" new-state)))

(reset! a {:foo "bar"})

; example 3: atom, desctructuring and add-watch
(let [account (atom {:name "pending"
                     :funds 100.50
                     :profit-loss 23.45})
      label-account-name (atom "no-name-yet")]
   (add-watch account :listener-one #(reset! label-account-name (:name %4)))
   (println "Before swap:" @label-account-name)
   (swap! account assoc :name "CFD")
   (println "After swap:" @label-account-name))
