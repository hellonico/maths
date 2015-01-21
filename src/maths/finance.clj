(ns maths.finance
  (:require incanter.distributions))

(defn d1
  [spot timeleft strike riskfree sigma]
  (/
   (+ (Math/log (/ spot strike))
      (* (+ riskfree (/ (* sigma sigma) 2))
         timeleft))
   (* sigma (Math/sqrt timeleft))))
(defn d2
  [spot timeleft strike riskfree sigma]
  (- (d1 spot timeleft strike riskfree sigma) (* sigma (Math/sqrt timeleft))))

(defn- n [val] (incanter.distributions/cdf (incanter.distributions/normal-distribution) val))

(defn call
  "Returns the theoretic value of a European call option on the given underlying based on the Black-Scholes model.
  * spot - spot price of the underlying
  * timeleft - time to expiration, in years
  * strike - option strike price
  * riskfree - annual continuous risk-free interest rate
  * sigma - volatility of the underlying
  "
  [spot timeleft strike riskfree sigma]
  (- (* spot (n (d1 spot timeleft strike riskfree sigma)))
     (* strike
        (Math/exp (* (- riskfree) timeleft))
        (n (d2 spot timeleft strike riskfree sigma)))))


(call 100.0 1.0 105.0 0.05 0.2)
(put 100.0 1.0 105.0 0.05 0.2)


(defn put
  "Returns the theoretic value of a European put option on the given underlying based on the Black-Scholes model.
  * spot - spot price of the underlying
  * timeleft - time to expiration, in years
  * strike - option strike price
  * riskfree - annual continuous risk-free interest rate
  * sigma - volatility of the underlying
  "
  [spot timeleft strike riskfree sigma]
  (- (* strike
        (Math/exp (* (- riskfree) timeleft))
        (n (- (d2 spot timeleft strike riskfree sigma))))
     (* spot (n (- (d1 spot timeleft strike riskfree sigma))))))
(defn call-delta
  [spot timeleft strike riskfree sigma]
  (n (d1 spot timeleft strike riskfree sigma)))

(defn put-delta
  [spot timeleft strike riskfree sigma]
  (- (n (d1 spot timeleft strike riskfree sigma)) 1))

(defn- nprime [val] (incanter.distributions/pdf (incanter.distributions/normal-distribution) val))

(defn gamma
  [spot timeleft strike riskfree sigma]
  (/ (nprime (d1 spot timeleft strike riskfree sigma))
     (* spot sigma (Math/sqrt timeleft))))
(defn vega
  [spot timeleft strike riskfree sigma]
  (* spot (nprime (d1 spot timeleft strike riskfree sigma)) (Math/sqrt timeleft)))

(defn call-theta
  [spot timeleft strike riskfree sigma]
  (-
   (- (/ (* spot (nprime (d1 spot timeleft strike riskfree sigma)) sigma)
         (* 2 (Math/sqrt timeleft))))
   (* riskfree strike (Math/exp (* (- riskfree) timeleft)) (n (d2 spot timeleft strike riskfree sigma)))))

(defn put-theta
  [spot timeleft strike riskfree sigma]
  (-
   (- (/ (* spot (nprime (d1 spot timeleft strike riskfree sigma)) sigma)
         (* 2 (Math/sqrt timeleft))))
   (* riskfree strike (Math/exp (* (- riskfree) timeleft)) (n (- (d2 spot timeleft strike riskfree sigma))))))

(defn call-rho
  [spot timeleft strike riskfree sigma]
  (* strike timeleft (Math/exp (* (- riskfree) timeleft)) (n (d2 spot timeleft strike riskfree sigma))))

(defn put-rho
  [spot timeleft strike riskfree sigma]
  (* (- strike) timeleft (Math/exp (* (- riskfree) timeleft)) (n (- (d2 spot timeleft strike riskfree sigma)))))
