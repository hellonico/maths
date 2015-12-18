(ns bugs.block
  (:import
   [java.net URL]
   [java.io File]))

(defn file? [f]
  (instance? File f))

(defn url? [f]
  (instance? URL f))

(defn ^String filename [^File f]
  (.getName f))

(defn last-modified [src]
  (cond
    (file? src) (.lastModified ^File src)
    (url? src)
    (let [conn (.openConnection ^URL src)]
      (try
        (.getLastModified conn)
        (finally
          (let [ins (.getInputStream conn)]
            (when ins
              (.close ins))))))
    :else
    (throw
      (IllegalArgumentException. (str "Cannot get last modified for " src)))))


