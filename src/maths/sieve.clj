(ns maths.sieve)

(defn sieve [n]
  "Returns a BitSet with bits set for each prime up to n"
  (let [bs (new java.util.BitSet n)]
    (.flip bs 2 n)
    (doseq [i (range 4 n 2)] (.clear bs i))
    (doseq [p (range 3 (Math/sqrt n))]
      (if (.get bs p)
        (doseq [q (range (* p p) n (* 2 p))] (.clear bs q))))
    bs))

(time (do (sieve 1e6) nil))
; (sieve 1e6)

(defn primes
  "postponed primes"
  []
  (letfn [(prime-help
            [foo bar]
            (loop [D foo
                   q bar]
              (if (nil? (get D q))
                (cons q (lazy-seq
                         (prime-help
                          (persistent! (assoc! (transient D) (* q q) (list q)))
                          (inc q))))
                (let [factors-of-q (get D q)
                      key-val (interleave
                               (map #(+ % q) factors-of-q)
                               (map #(cons % (get D (+ % q) (list)))
                                    factors-of-q))]
                  (recur (persistent!
                          (dissoc!
                           (apply assoc! (transient D) key-val)
                           q))
                         (inc q))))))]
    (prime-help {} 2)))


(first (primes))
(second (primes))
(nth (primes) 100)
(take 5 (primes))
(time (nth (primes) 10000))

;;; sieve

(defn clense
  "Walks through the sieve and nils out multiples of step"
  [primes step i]
  (if (<= i (count primes))
    (recur
      (assoc! primes i nil)
      step
      (+ i step))
    primes))

(defn sieve-step
  "Only works if i is >= 3"
  [primes i]
  (if (< i (count primes))
    (recur
      (if (nil? (primes i)) primes (clense primes (* 2 i) (* i i)))
      (+ 2 i))
    primes))

(defn prime-sieve
  "Returns a lazy list of all primes smaller than x"
  [^long x]
  (drop 2
    (filter (complement nil?)
    (persistent! (sieve-step
      (clense (transient (vec (range x))) 2 4) 3)))))
