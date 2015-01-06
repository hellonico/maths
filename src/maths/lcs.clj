; http://stackoverflow.com/questions/14949705/clojure-performance-for-expensive-algorithms

; algorithm to calculate the longest contiguous common subsequence (

(defn lcs
  [#^"[Ljava.lang.String;" a1 #^"[Ljava.lang.String;" a2]
  (let [a1-len (alength a1)
        a2-len (alength a2)
        prev (int-array (inc a2-len))
        curr (int-array (inc a2-len))]
    (loop [i 0 max-len 0 prev prev curr curr]
      (if (< i a1-len)
        (recur (inc i)
               (loop [j 0 max-len max-len]
                 (if (< j a2-len)
                   (if (= (aget a1 i) (aget a2 j))
                     (let [match-len (inc (aget prev j))]
                       (do
                         (aset-int curr (inc j) match-len)
                         (recur (inc j) (max max-len match-len))))
                     (do
                       (aset-int curr (inc j) 0)
                       (recur (inc j) max-len)))
                   max-len))
               curr
               prev)
        max-len))))

(def pool "ABC")
(defn get-random-id [n] (apply str (repeatedly n #(rand-nth pool))))
(def a1 (into-array (take 10000 (repeatedly #(get-random-id 5)))))
(def a2 (into-array (take 10000 (repeatedly #(get-random-id 5)))))
(time (lcs a1 a2))
; "Elapsed time: 35632.160109 msecs"

(defn my-lcs
  [#^"[Ljava.lang.String;" a1 #^"[Ljava.lang.String;" a2]
  ; [^objects a1 ^objects a2]
  (first
    (let [n (inc (alength a1))]
      (areduce a1 i
        [max-len ^ints prev ^ints curr] [0 (int-array n) (int-array n)]
        [(areduce a2 j max-len (unchecked-long max-len)
           (let [match-len
                 (if (.equals (aget a1 i) (aget a2 j))
                   (unchecked-inc (aget prev j))
                   0)]
             (aset curr (unchecked-inc j) match-len)
             (if (> match-len max-len)
               match-len
               max-len)))
         curr prev]))))

(time (my-lcs a1   a2))


(deftype F [^:unsynchronized-mutable ^ints curr
            ^:unsynchronized-mutable ^ints prev]
  clojure.lang.IFn
  (invoke [_ a1 a2]
    (let [^objects a1 a1
          ^objects a2 a2]
      (areduce a1 i max-len 0
        (let [m (areduce a2 j max-len (unchecked-long max-len)
                  (let [match-len
                        (if (.equals (aget a1 i) (aget a2 j))
                          (unchecked-inc (aget prev j))
                          0)]
                    (aset curr (unchecked-inc j) (unchecked-int match-len))
                    (if (> match-len max-len)
                      match-len
                      max-len)))
              bak curr]
          (set! curr prev)
          (set! prev bak)
          m)))))

(defn my-lcs2 [^objects a1 a2]
  (let [n (inc (alength a1))
        f (F. (int-array n) (int-array n))]
    (f a1 a2)))

(time (my-lcs a1   a2))

(defn lcs-2
  [^objects a1 ^objects a2]
  (let [a1-len (alength a1)
        a2-len (alength a2)
        prev (int-array (inc a2-len))
        curr (int-array (inc a2-len))]
    (loop [i 0 max-len 0 prev prev curr curr]
      (if (< i a1-len)
        (recur (inc i)
               (long (loop [j 0 max-len max-len]
                 (if (< j a2-len)
                   (if (= (aget a1 i) (aget a2 j))
                     (let [match-len (inc (aget prev j))]
                       (do
                         (aset curr (inc j) match-len)
                         (recur (inc j) (max max-len match-len))))
                     (do
                       (aset curr (inc j) 0)
                       (recur (inc j) max-len)))
                   max-len)))
               curr
               prev)
        max-len))))

 (time (lcs-2 a1 a2))
 ; "Elapsed time: 6617.180975 msecs"
