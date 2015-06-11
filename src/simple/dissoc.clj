; lazyness
(defn find-first [pred col]
  (first (filter pred col)))

; use lazyness to find the first element
; stop the processing as soon as the first element is found
(find-first
 #(> % 10)
 '(1 5 8 2 15 20 31 5 1))

; iterate creates an infinite lazy collection
(take 5
      (iterate
       #(+ % 2) 0))



