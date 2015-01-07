(ns maths.utils)

(defn load-test-me [times myfunc inputs]
   (dotimes [_ times]
    (time
     (apply myfunc inputs))))

(defn str->int
  ([string]
   (str->int string 0))
  ([str default]
   (if
     (and (not (empty? str)) (re-matches (re-pattern "\\d+") str))
     (read-string str)
     default)))

