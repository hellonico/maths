(ns maths.caesar)

(defn encrypt-character [offset c]
  (if (Character/isLetter c)
    (let [v (int c)
          base (if (>= v (int \a))
                 (int \a)
                 (int \A))
          offset (mod offset 26)] ;works with negative offsets too!
      (char (+ (mod (+ (- v base) offset) 26)
               base)))
    c))

(defn encrypt [offset text]
  (apply str (map #(encrypt-character offset %) text)))

(defn decrypt [offset text]
  (encrypt (- 26 offset) text))