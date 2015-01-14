(ns maths.simple.find1)

; http://stackoverflow.com/questions/27613545/clojure-find-locations-of-1-in-a-string-and-print-out-them-in-the-format-of-i

(->> "00101110101110"
  (map-indexed vector)                         ;; ([0 \0] [1 \0] [2 \1]...
  (partition-by second)                        ;; (([0 \0] [1 \0]) ([2 \1]) ([3 \0]) ([4 \1] [5 \1] [6 \1]) ...
  (filter #(= \1 (-> % first second)))         ;; (([2 \1]) ([4 \1] [5 \1] [6 \1])...
  (map #(map (comp inc first) %))              ;; ((3) (5 6 7) (9) (11 12 13))
  (map #(if (next %) [(first %) (last %)] %))  ;; ((3) [5 7] (9) [11 13])
  (map #(clojure.string/join "-" %))           ;; ("3" "5-7" "9" "11-13")
  (clojure.string/join ", "))
;; => "3, 5-7, 9, 11-13"
