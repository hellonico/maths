(ns maths.quotes
  (:require [clojure.java.io :as io]))

(defn build-url [sym]
  (str "http://ichart.finance.yahoo.com/table.csv?s=" sym "&ignore=.csv"))

(defn download-historical-quotes [sym]
  (let [url (build-url sym)
        filename (str "target/" sym ".csv")]
    (with-open [rdr (io/reader url)
                wrt (io/writer filename)]
      (doseq [line (line-seq rdr)]
        (.write wrt (str line "\n"))))))

(download-historical-quotes "goog")
(download-historical-quotes "aapl")