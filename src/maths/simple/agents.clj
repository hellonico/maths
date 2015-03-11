(ns maths.simple.agents)

(def counter (agent 0))
(send counter inc)

@counter

(doseq [_ (range 10)]
  (send counter #(+ % 2)))

@counter
