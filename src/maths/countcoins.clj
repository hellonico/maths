(ns maths.countcoins)

(def denomination-kind [1 5 10 25])

(defn- cc [amount denominations]
  (cond (= amount 0) 1
        (or (< amount 0) (empty? denominations)) 0
        :else (+ (cc amount (rest denominations))
                 (cc (- amount (first denominations)) denominations))))

(println
  (cc 37 denomination-kind))
