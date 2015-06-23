

(defn getNums []
  (loop [i  10
         r []]
    (if (<= i 0)
      r
      (recur (dec i)
             (cons i r)))))

(getNums)

(range 1 10)

(take 10 (iterate inc 1))

; ----
; ----
; ----

(defn exchange [v i]
  (let [[src dst] i]
    (assoc v dst (v src) src (v dst))))

(defn arrange []
  (loop [idxs [0 0]
         deck [\a \b \c \d \e]
         pts [[0 1] [2 3] [4 1]]]
    (println idxs deck pts)
  (if (empty? pts)
  deck
  (recur (first pts) (exchange deck idxs) (rest pts)))))

(arrange)
