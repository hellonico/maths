(ns maths.movingaverage)

;
; reduce and map
;
(defn average [lst] (/ (reduce + lst) (count lst)))

(defn moving-average-0 [window lst]
  (map average (partition window 1 lst)))

;
; my rewrite of the above
;
(defn moving-average-1 [window lst]
  (map
   #(/ (reduce + %) (count %))
   (partition window 1 lst)))

; lazyseq
(defn moving-average-2
  "Calculates the moving average of values with the given period.
  Returns a lazy seq, works with infinite input sequences.
  Does not include initial zeros in the output."
  [period values]
  (let [gen (fn gen [last-sum values-old values-new]
              (if (empty? values-new)
                nil
                (let [num-out (first values-old)
                      num-in  (first values-new)
                      new-sum (+ last-sum (- num-out) num-in)]
                  (lazy-seq
                    (cons new-sum
                          (gen new-sum
                               (next values-old)
                               (next values-new)))))))]
    (if (< (count (take period values)) period)
      nil
      (map #(/ % period)
           (gen (apply + (take (dec period) values))
                (cons 0 values)
                (drop (dec period) values))))))


;
; map and lazyseq
;
(defn partialsums [start lst]
  (lazy-seq
    (if-let [lst (seq lst)]
          (cons start (partialsums (+ start (first lst)) (rest lst)))
          (list start))))

(defn moving-average-3 [window lst]
  (map #(/ % window)
       (let [start   (apply + (take window lst))
             diffseq (map - (drop window lst) lst)]
         (partialsums start diffseq))))

; loop and recur
(defn moving-average-4 [period values]
  (loop [values values, period period, acc []]
    (let [first (take period values)]
      (if (= (count first) period)
        (recur (rest values) period (conj acc (/ (reduce + first) period)))
        acc))))

; To get the Exponent,
; take the number 2 and divide it by Days+1.
; For example the Exponent for a 200 day moving average would be:
; 2÷201 = 0.01

; implementation of an exponentially weighted moving-average function
; https://gist.github.com/MerelyAPseudonym/5273905
(defn ema [f values]
  (reductions
   (fn [running v]
    (let [one-minus-F (- 1 f)] ;naming intermediate results can help with the readability of non-associative operators.
     (+ (* f v)
     (* one-minus-F running))))
  values))
