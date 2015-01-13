(ns maths.crfm
 (:require [clojure.core.matrix :as m]
          [primitive-math :as pm]))

; Counterfactual Regret Minimization is an algorithm that
; can be used to find the Nash Equilibrium for games of incomplete information.

; http://codereview.stackexchange.com/questions/45796/translating-cfrm-algorithm-from-java-to-clojure-and-improving-performance?rq=1


(set! *warn-on-reflection* true)
(m/set-current-implementation :vectorz)

(defn create-utility-fn
  "Given a payoff-matrix creates a utility function for the game. The utility
  function accepts two strategy vectors as its arguments and returns the utility
  for the first player in question."
  [m]
  (fn ^double [sa sb]
    (m/mget m sa sb)))

(defn regret
  "Given a utility function and three strategy vectors, returns the regret for
  player having played his strategy `sa' instead of `sb' against his opponents `so'"
  [uf sa sb so]
  (pm/- ^double (uf sb so) ^double (uf sa so)))

(defn regret-profile
  "Given a utility function and strategies for both players, this function
  returns the regret for all the pure-strategies the first player could have
  played, including the strategy he did play."
  [uf sa so ns]
  (map #(regret uf sa % so) (range ns)))

(defn normalise-strategies
  [nsum strat]
  (if (pm/> ^double nsum 0.0)
    (map #(pm/div ^double % ^double nsum) strat)
    (repeat (m/ecount strat) (pm/div (m/ecount strat)))))

(defn new-strategy
  "Creates a new strategy based on the regrets experienced by the player."
  [rgr]
  (let [n     (m/ecount rgr)
        strat (map #(if (pos? (m/mget rgr %)) (m/mget rgr %) 0) (range n))
        nsum  (reduce + strat)]
    (normalise-strategies nsum strat)))

(defn cumulative-probabilities
  "Takes a collection of probabilities (that sum up to one) and turns it into a
  sequence of cumulative probabilities."
  [coll]
  (reduce #(conj %1 (+ %2 (last %1))) [0] coll))

(defn choose-action
  "Given a strategy vector, chooses an action to play based on its probability."
  [^doubles strat]
  (let [cp    (cumulative-probabilities strat)
        r     (rand)]
    (pm/dec ^long (m/ecount (take-while #(pm/> ^double r ^double %) cp)))))

(defn avarage-strategy
  "Given a vector where each index maps to how often a certain strategy has been
  played, returns the frequency of each strategy as a part of the total."
  [ssum]
  (let [nsum (reduce + ssum)]
    (normalise-strategies nsum ssum)))

(defn cfrm-be
  "Given a utility function, number of iterations and a strategy for the
  opponent, performs the Counterfactual Regret Minimization algorithm to find
  the best response to the strategy in question."
  [m n sb]
  (let [n         (int n)
        uf        (create-utility-fn m)
        reg-a     (m/array [0.0 0.0 0.0])
        ssum      (m/array [0.0 0.0 0.0])
        [sca scb] (m/shape m)]
    (loop [i (int 0)]
      (if (pm/== i n)
        (avarage-strategy ssum)
        (let [strat-a (choose-action (new-strategy reg-a))
              strat-b sb]
          (m/add! reg-a (regret-profile uf strat-a strat-b sca))
          (m/add! ssum strat-a)
          (recur (pm/inc i)))))))

(defn cfrm-ne
  "Given a utility function and a number of iterations to perform, performs the
  Counterfactual Regret Minimization algorithm to find an approximation of the
  Nash Equilibrium for the game."
  [m n]
  (let [n         (int n)
        uf        (create-utility-fn m)
        reg-a     (m/array [0.0 0.0 0.0])
        reg-b     (m/array [0.0 0.0 0.0])
        ssum      (m/array [0.0 0.0 0.0])
        [sca scb] (m/shape m)]
    (loop [i (int 0)]
      (if (pm/== i n)
        (avarage-strategy ssum)
        (let [strat-a (choose-action (new-strategy reg-a))
              strat-b (choose-action (new-strategy reg-b))]
          (m/add! reg-a (regret-profile uf strat-a strat-b sca))
          (m/add! reg-b (regret-profile uf strat-b strat-a scb))
          (m/add! ssum strat-a)
          (recur (pm/inc i)))))))

(comment
 (def rps
    [[0, -1, 1]
     [1, 0, -1]
     [-1, 1, 0]])

  (cfrm-ne rps 100000)


)


 (def rps
    [[0, -1, 1]
     [1, 0, -1]
     [-1, 1, 0]])

(time  (cfrm-ne rps 100000))
