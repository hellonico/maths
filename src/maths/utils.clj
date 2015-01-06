(ns maths.utils)

(defn load-test-me [times myfunc inputs]
  ;(time
   (dotimes [_ times]
    (time
     (apply myfunc inputs)))
  ; )
  )

; (load-test-me 10 #(* 2 %) [1])
