----
##### `/Users/niko/projects/maths/src/maths/100doors.clj:5`
Consider using:
```clojure
  (vec (repeat 100 false))
```
instead of:
```clojure
  (into [] (repeat 100 false))
```

----
##### `/Users/niko/projects/maths/src/maths/100doors.clj:12`
Consider using:
```clojure
  (vec (repeat 100 false))
```
instead of:
```clojure
  (into [] (repeat 100 false))
```

----
##### `/Users/niko/projects/maths/src/maths/100doors.clj:21`
Consider using:
```clojure
  (clojure.string/join ", " (open-doors func))
```
instead of:
```clojure
  (apply str (interpose ", " (open-doors func)))
```

----
##### `/Users/niko/projects/maths/src/maths/ackermann.clj:17`
Consider using:
```clojure
  (zero? m)
```
instead of:
```clojure
  (== m 0)
```

----
##### `/Users/niko/projects/maths/src/maths/ackermann.clj:17`
Consider using:
```clojure
  (zero? n)
```
instead of:
```clojure
  (== n 0)
```

----
##### `/Users/niko/projects/maths/src/maths/aliquot.clj:6`
Consider using:
```clojure
  (zero? (rem number potential-factor))
```
instead of:
```clojure
  (= 0 (rem number potential-factor))
```

----
##### `/Users/niko/projects/maths/src/maths/aliquot.clj:9`
Consider using:
```clojure
  (inc number)
```
instead of:
```clojure
  (+ 1 number)
```

----
##### `/Users/niko/projects/maths/src/maths/astar2.clj:22`
Consider using:
```clojure
  (dec x)
```
instead of:
```clojure
  (- x 1)
```

----
##### `/Users/niko/projects/maths/src/maths/astar2.clj:23`
Consider using:
```clojure
  (dec y)
```
instead of:
```clojure
  (- y 1)
```

----
##### `/Users/niko/projects/maths/src/maths/astar2.clj:70`
Consider using:
```clojure
  (if-not (contains? open edge)
    (assoc open edge (conj (cost edge start end) coord))
    (let [[_ pg] (open edge) [nf ng nh] (cost edge start end)]
      (if (< ng pg) (assoc open edge (conj [nf ng nh] coord)) open)))
```
instead of:
```clojure
  (if (not (contains? open edge))
    (assoc open edge (conj (cost edge start end) coord))
    (let [[_ pg] (open edge) [nf ng nh] (cost edge start end)]
      (if (< ng pg) (assoc open edge (conj [nf ng nh] coord)) open)))
```

----
##### `/Users/niko/projects/maths/src/maths/astar2.clj:82`
Consider using:
```clojure
  (set (time (search area start end)))
```
instead of:
```clojure
  (into #{} (time (search area start end)))
```

----
##### `/Users/niko/projects/maths/src/maths/barber/agents.clj:12`
Consider using:
```clojure
  (clojure.string/join (repeat (- 35 (count msg)) \space))
```
instead of:
```clojure
  (apply str (repeat (- 35 (count msg)) \space))
```

----
##### `/Users/niko/projects/maths/src/maths/binaryperm.clj:5`
Consider using:
```clojure
  (clojure.string/join
    (take n (map #(bit-and 1 %) (iterate #(bit-shift-right % 1) x))))
```
instead of:
```clojure
  (apply
    str
    (take n (map #(bit-and 1 %) (iterate #(bit-shift-right % 1) x))))
```

----
##### `/Users/niko/projects/maths/src/maths/carpet.clj:11`
Consider using:
```clojure
  (clojure.string/join
    \newline
    (for
      [x (range (Math/pow 3 n))]
      (apply
        str
        (for [y (range (Math/pow 3 n))] (if (in-carpet? x y) "*" " ")))))
```
instead of:
```clojure
  (apply
    str
    (interpose
      \newline
      (for
        [x (range (Math/pow 3 n))]
        (apply
          str
          (for
            [y (range (Math/pow 3 n))]
            (if (in-carpet? x y) "*" " "))))))
```

----
##### `/Users/niko/projects/maths/src/maths/carpet.clj:15`
Consider using:
```clojure
  (clojure.string/join
    (for [y (range (Math/pow 3 n))] (if (in-carpet? x y) "*" " ")))
```
instead of:
```clojure
  (apply
    str
    (for [y (range (Math/pow 3 n))] (if (in-carpet? x y) "*" " ")))
```

----
##### `/Users/niko/projects/maths/src/maths/collatz.clj:54`
Consider using:
```clojure
  (zero? (mod n 2))
```
instead of:
```clojure
  (= (mod n 2) 0)
```

----
##### `/Users/niko/projects/maths/src/maths/collatz.clj:56`
Consider using:
```clojure
  (inc (* n 3))
```
instead of:
```clojure
  (+ (* n 3) 1)
```

----
##### `/Users/niko/projects/maths/src/maths/collatz.clj:67`
Consider using:
```clojure
  (inc (* 3 n))
```
instead of:
```clojure
  (+ 1 (* 3 n))
```

----
##### `/Users/niko/projects/maths/src/maths/collatz.clj:75`
Consider using:
```clojure
  (inc (* 3 n))
```
instead of:
```clojure
  (+ 1 (* 3 n))
```

Check failed -- skipping rest of file
java.lang.RuntimeException: EOF while reading, starting at line 82
----
##### `/Users/niko/projects/maths/src/maths/countcoins.clj:6`
Consider using:
```clojure
  (zero? amount)
```
instead of:
```clojure
  (= amount 0)
```

----
##### `/Users/niko/projects/maths/src/maths/countcoins.clj:7`
Consider using:
```clojure
  (neg? amount)
```
instead of:
```clojure
  (< amount 0)
```

----
##### `/Users/niko/projects/maths/src/maths/divisors.clj:11`
Consider using:
```clojure
  (zero? (rem num d))
```
instead of:
```clojure
  (== 0 (rem num d))
```

----
##### `/Users/niko/projects/maths/src/maths/expt.clj:6`
Consider using:
```clojure
  (zero? n)
```
instead of:
```clojure
  (= n 0)
```

----
##### `/Users/niko/projects/maths/src/maths/expt.clj:14`
Consider using:
```clojure
  (zero? n)
```
instead of:
```clojure
  (= n 0)
```

----
##### `/Users/niko/projects/maths/src/maths/generators.clj:6`
Consider using:
```clojure
  (repeatedly n #(rand m))
```
instead of:
```clojure
  (take n (repeatedly #(rand m)))
```

----
##### `/Users/niko/projects/maths/src/maths/generators.clj:16`
Consider using:
```clojure
  (clojure.string/join (take len (repeatedly #(rand-nth alphabet))))
```
instead of:
```clojure
  (apply str (take len (repeatedly #(rand-nth alphabet))))
```

----
##### `/Users/niko/projects/maths/src/maths/generators.clj:17`
Consider using:
```clojure
  (repeatedly len #(rand-nth alphabet))
```
instead of:
```clojure
  (take len (repeatedly #(rand-nth alphabet)))
```

----
##### `/Users/niko/projects/maths/src/maths/generators.clj:28`
Consider using:
```clojure
  (repeatedly size #(rand-int max))
```
instead of:
```clojure
  (take size (repeatedly #(rand-int max)))
```

----
##### `/Users/niko/projects/maths/src/maths/generators.clj:37`
Consider using:
```clojure
  (repeatedly n #(rand-nth chars))
```
instead of:
```clojure
  (take n (repeatedly #(rand-nth chars)))
```

----
##### `/Users/niko/projects/maths/src/maths/generators.clj:42`
Consider using:
```clojure
  (repeatedly size #(-> r .nextGaussian (* 0.5) (+ 1.0)))
```
instead of:
```clojure
  (take size (repeatedly #(-> r .nextGaussian (* 0.5) (+ 1.0))))
```

----
##### `/Users/niko/projects/maths/src/maths/hofstadter.clj:8`
Consider using:
```clojure
  (dec n)
```
instead of:
```clojure
  (- n 1)
```

----
##### `/Users/niko/projects/maths/src/maths/hofstadter.clj:11`
Consider using:
```clojure
  (nth (iterate qs []) n)
```
instead of:
```clojure
  (-> (iterate qs []) (nth n))
```

----
##### `/Users/niko/projects/maths/src/maths/lcs.clj:19`
Consider using:
```clojure
  (let [match-len (inc (aget prev j))]
    (aset-int curr (inc j) match-len)
    (recur (inc j) (max max-len match-len)))
```
instead of:
```clojure
  (let [match-len (inc (aget prev j))]
    (do
      (aset-int curr (inc j) match-len)
      (recur (inc j) (max max-len match-len))))
```

----
##### `/Users/niko/projects/maths/src/maths/lcs.clj:32`
Consider using:
```clojure
  (clojure.string/join (repeatedly n #(rand-nth pool)))
```
instead of:
```clojure
  (apply str (repeatedly n #(rand-nth pool)))
```

----
##### `/Users/niko/projects/maths/src/maths/lcs.clj:33`
Consider using:
```clojure
  (repeatedly 10000 #(get-random-id 5))
```
instead of:
```clojure
  (take 10000 (repeatedly #(get-random-id 5)))
```

----
##### `/Users/niko/projects/maths/src/maths/lcs.clj:34`
Consider using:
```clojure
  (repeatedly 10000 #(get-random-id 5))
```
instead of:
```clojure
  (take 10000 (repeatedly #(get-random-id 5)))
```

----
##### `/Users/niko/projects/maths/src/maths/lcs.clj:99`
Consider using:
```clojure
  (let [match-len (inc (aget prev j))]
    (aset curr (inc j) match-len)
    (recur (inc j) (max max-len match-len)))
```
instead of:
```clojure
  (let [match-len (inc (aget prev j))]
    (do
      (aset curr (inc j) match-len)
      (recur (inc j) (max max-len match-len))))
```

----
##### `/Users/niko/projects/maths/src/maths/mandelbrot.clj:20`
Consider using:
```clojure
  (pos? j)
```
instead of:
```clojure
  (> j 0)
```

----
##### `/Users/niko/projects/maths/src/maths/mandelbrot.clj:80`
Consider using:
```clojure
  (when (< y outlen)
    (put-line y (aget out y))
    (recur (.getAndIncrement yct)))
```
instead of:
```clojure
  (when (< y outlen)
    (do (put-line y (aget out y)) (recur (.getAndIncrement yct))))
```

----
##### `/Users/niko/projects/maths/src/maths/mandelbrot.clj:86`
Consider using:
```clojure
  (Long/parseLong (nth args 0) 10)
```
instead of:
```clojure
  (. Long parseLong (nth args 0) 10)
```

----
##### `/Users/niko/projects/maths/src/maths/maze.clj:17`
Consider using:
```clojure
  (dec (count grid))
```
instead of:
```clojure
  (- (count grid) 1)
```

----
##### `/Users/niko/projects/maths/src/maths/maze.clj:18`
Consider using:
```clojure
  (dec (count (nth grid y)))
```
instead of:
```clojure
  (- (count (nth grid y)) 1)
```

----
##### `/Users/niko/projects/maths/src/maths/maze.clj:19`
Consider using:
```clojure
  (zero? (get-in grid [x y]))
```
instead of:
```clojure
  (= 0 (get-in grid [x y]))
```

----
##### `/Users/niko/projects/maths/src/maths/movingaverage.clj:26`
Consider using:
```clojure
  (when-not (empty? values-new)
    (let [num-out (first values-old)
          num-in (first values-new)
          new-sum (+ last-sum (- num-out) num-in)]
      (lazy-seq
        (cons
          new-sum
          (gen new-sum (next values-old) (next values-new))))))
```
instead of:
```clojure
  (if (empty? values-new)
    nil
    (let [num-out (first values-old)
          num-in (first values-new)
          new-sum (+ last-sum (- num-out) num-in)]
      (lazy-seq
        (cons
          new-sum
          (gen new-sum (next values-old) (next values-new))))))
```

----
##### `/Users/niko/projects/maths/src/maths/movingaverage.clj:36`
Consider using:
```clojure
  (when-not (< (count (take period values)) period)
    (map
      #(/ % period)
      (gen
        (apply + (take (dec period) values))
        (cons 0 values)
        (drop (dec period) values))))
```
instead of:
```clojure
  (if (< (count (take period values)) period)
    nil
    (map
      #(/ % period)
      (gen
        (apply + (take (dec period) values))
        (cons 0 values)
        (drop (dec period) values))))
```

----
##### `/Users/niko/projects/maths/src/maths/neural.clj:71`
Consider using:
```clojure
  (update-in
    layer
    [?key]
    +
    (* rate (mapv #(* (:deltas layer-above) %) (:values layer))))
```
instead of:
```clojure
  (assoc
    layer
    :weights
    (+
      (:weights layer)
      (* rate (mapv #(* (:deltas layer-above) %) (:values layer)))))
```

----
##### `/Users/niko/projects/maths/src/maths/palyndrome.clj:16`
Consider using:
```clojure
  (seq (str/trim input))
```
instead of:
```clojure
  (not (empty? (str/trim input)))
```

----
##### `/Users/niko/projects/maths/src/maths/philosophers.clj:null`
Consider using:
```clojure
  agent
```
instead of:
```clojure
  #(agent %)
```

----
##### `/Users/niko/projects/maths/src/maths/robber.clj:36`
Consider using:
```clojure
  (pos? (- maxW weight new-weight))
```
instead of:
```clojure
  (> (- maxW weight new-weight) 0)
```

----
##### `/Users/niko/projects/maths/src/maths/sieve.clj:61`
Consider using:
```clojure
  (remove
    nil?
    (persistent! (sieve-step (clense (transient (vec (range x))) 2 4) 3)))
```
instead of:
```clojure
  (filter
    (complement nil?)
    (persistent! (sieve-step (clense (transient (vec (range x))) 2 4) 3)))
```

----
##### `/Users/niko/projects/maths/src/maths/simple/diff.clj:16`
Consider using:
```clojure
  (vec (r/filter #(not (seq-contains? b %)) a))
```
instead of:
```clojure
  (into [] (r/filter #(not (seq-contains? b %)) a))
```

----
##### `/Users/niko/projects/maths/src/maths/simple/reducers.clj:32`
Consider using:
```clojure
  (vec (r/filter even? (r/map inc nums)))
```
instead of:
```clojure
  (into [] (r/filter even? (r/map inc nums)))
```

----
##### `/Users/niko/projects/maths/src/maths/solveit.clj:32`
Consider using:
```clojure
  (pos? its)
```
instead of:
```clojure
  (> its 0)
```

----
##### `/Users/niko/projects/maths/src/maths/sort/bubble.clj:21`
Consider using:
```clojure
  (when (pos? (cmp (.get arr i) (.get arr (inc i))))
    (swap! i (inc i))
    (reset! changed true))
```
instead of:
```clojure
  (if (pos? (cmp (.get arr i) (.get arr (inc i))))
    (do (swap! i (inc i)) (reset! changed true)))
```

----
##### `/Users/niko/projects/maths/src/maths/tailer.clj:11`
Consider using:
```clojure
  (not= res-port stop)
```
instead of:
```clojure
  (not (= res-port stop))
```

----
##### `/Users/niko/projects/maths/src/maths/types/cheaplist.clj:64`
Consider using:
```clojure
  (when-not (empty? bulk) (lazy-seq (cons (first this) (rest this))))
```
instead of:
```clojure
  (when (not (empty? bulk)) (lazy-seq (cons (first this) (rest this))))
```

----
##### `/Users/niko/projects/maths/src/maths/types/cheaplist.clj:64`
Consider using:
```clojure
  (seq bulk)
```
instead of:
```clojure
  (not (empty? bulk))
```

----
##### `/Users/niko/projects/maths/src/maths/utils.clj:13`
Consider using:
```clojure
  (seq str)
```
instead of:
```clojure
  (not (empty? str))
```

----
##### `/Users/niko/projects/maths/src/maths/zigzag.clj:17`
Consider using:
```clojure
  (remove empty? (map rest first-n))
```
instead of:
```clojure
  (filter seq (map rest first-n))
```

----
##### `/Users/niko/projects/maths/src/psy/anew.clj:107`
Consider using:
```clojure
  (pos? maxscored)
```
instead of:
```clojure
  (> maxscored 0)
```

