(ns maths.tailer
  (:require [clojure.core.async :as async])
  (:import [java.io RandomAccessFile FileNotFoundException])
  (:gen-class))

(def new-line-timeout 1000)
(def file-not-found-timeout 5000)

(defn alts-run? [stop ch]
  (let [[_ res-port] (async/alts!! [stop ch])]
    (not (= res-port stop))))

(defn create-rac [filename]
  (RandomAccessFile. filename "r"))

(defn seek [rac offset]
  (let [checked-offset (if (>= (.length rac) offset) offset 0)]
    (doto rac (.seek checked-offset))))

(defn read-lines [rac out stop]
  (let [line (.readLine rac)
        offset (.getFilePointer rac)]
    (if line
      (when (alts-run? stop [out line])
        (recur rac out stop))
      offset)))

(defn read-file [filename offset out stop]
  (let [rac (seek (create-rac filename) offset)
        offset (read-lines rac out stop)]
    (.close rac)
    ; (println "sleep")
    (Thread/sleep new-line-timeout)
    (when offset
      (recur filename offset out stop))))

(defn safe-read-file [filename offset out stop]
  (try
    (read-file filename offset out stop)
    (catch FileNotFoundException e
      (println "file not found")
      (when (alts-run? stop (async/timeout file-not-found-timeout))
        #(safe-read-file filename offset out stop)))))

(defn tailer [filename]
  (let [stop (async/chan)
        out (async/chan)
        tailer-map {:out out
                    :stop-handler #(dorun (map async/close! [stop out]))}]
    (async/thread (trampoline safe-read-file filename 0 out stop))
    tailer-map))

(defn -main [& args]
  (let [{:keys [out stop-handler]} (tailer (first args))]

    (async/thread
      (Thread/sleep 100000)
      (println "stop invoked")
      (stop-handler))

    (loop [line (async/<!! out)]
      (when line
        (println line)
        (recur (async/<!! out))))

    ))
