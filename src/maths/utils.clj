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

(defmacro time-ns
  [x expr]
   `(let [start# (. System (nanoTime))
        ret# (dotimes [y# ~x] ~expr)
        end# (/ (double (- (. System (nanoTime)) start#)) ~x)]
   end#))

(defmacro time-ms
  [x expr]
   `(let [start# (. System (nanoTime))
        ret# (dotimes [y# ~x] ~expr)
        ela# (double (- (. System (nanoTime)) start#))
        avg# (/ ela  ~x 1000.0)]
   avg#))

(defmacro time-s
  [x expr]
   `(let [start# (. System (nanoTime))
        ret# (dotimes [y# ~x] ~expr)
        end# (/ (double (- (. System (nanoTime)) start#)) (* ~x 1000000.0))]
   end#))
