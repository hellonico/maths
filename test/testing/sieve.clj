(ns testing.sieve
  (:use maths.sieve))


(sieve 100000)


(time (nth (primes) 10000))

(time (last (prime-sieve 100000)))

