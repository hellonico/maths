(ns maths.cosine)

(set! *unchecked-math* true)
(set! *warn-on-reflection* true)

(defn cosine-similarity [^doubles va ^doubles vb]
    (loop [p 0.0
           na 0.0
           nb 0.0
           i  (dec (count va))]
      (if (> 0 i)
        (/ p (* (Math/sqrt na) (Math/sqrt nb)))
        (let [a  (aget va i)
              b  (aget vb i)]
          (recur (+ p (* a b))
                 (+ na (* a a))
                 (+ nb (* b b))
                 (dec i))))))
