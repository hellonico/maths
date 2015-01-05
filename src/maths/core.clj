(ns maths.core)
; http://stackoverflow.com/questions/4732134/can-i-refer-another-namespace-and-expose-its-functions-as-public-for-the-current

(defmacro pull [ns vlist]
  `(do ~@(for [i vlist]
           `(def ~i ~(symbol (str ns "/" i))))))

(pull maths.fastfib (fib-2))

(fib-2 1000)

;(ns maths.core2)
;(use '(maths.core))
;(maths.core/fib-2 1000)
