(ns simple.fileloadbatch
  (require [clojure.java.io :as io]))


(def book "http://norvig.com/big.txt")

(defn do-something [ batch ]
  (println (count batch)))

(defn parse-line [line]
  line)
; http://www.beaconhill.com/solutions/kb/clojure/reading-files-in-clojure2.html

(with-open [reader (io/reader book)]
  (->>
     (line-seq reader)
     (map parse-line)
     (partition-all 1000)
     (#(doseq [batch %]
       (do-something batch)))))
