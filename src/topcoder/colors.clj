(ns topcoders.colors
  (:require [clojure.java.io :as io]))

(defn- read-line-ints
  ([] (read-line-ints (read-line)))
  ([input-line]
   (if-let [line input-line]
     (->> line
          (#(clojure.string/split %1 #" "))
          (map #(Integer/parseInt %1))))))

(defn- make-paper
  ([w h] (make-paper 0 0 0 w h))
  ([color x y w h]
   {:color color
    :x1 x
    :y1 y
    :w w
    :h h
    :x2 (dec (+ x w))
    :y2 (dec (+ y h))}))

(defn- parse-inputs
  []
  (let [[canvas-width canvas-height] (read-line-ints)]
    (loop [papers (list (make-paper canvas-width canvas-height))]
      (if-let [[color x y w h] (read-line-ints)]
        (recur (conj papers (make-paper color x y w h)))
        {:papers (into [] papers)
         :canvas-width canvas-width
         :canvas-height canvas-height}))))

(defn- read-input-file
  [file-name]
  (with-open
   [rdr (io/reader (io/resource file-name))]
    (binding [*in* rdr]
      (parse-inputs))))

(def ^:private input-files
  ["100rects100x100.in"
   "100rects10Kx10K.in"])

(defn- covered?
  [[canvas-x canvas-y] {:keys [x1 y1 x2 y2]}]
  (and (<= x1 canvas-x x2) (<= y1 canvas-y y2)))

(defn- visible-color
  [coord papers]
  (some #(when (covered? coord %1) (:color %1))
        papers))

(defn- visible-color-frequencies
  [{:keys [canvas-width canvas-height papers]}]
  (persistent!
   (reduce
    (fn [acc coord]
      (if-let [color (visible-color coord papers)]
        (assoc! acc color (inc (get acc color 0)))
        acc))
    (transient {})
    (for [y (range canvas-height)
          x (range canvas-width)]
      [x y]))))

(defn- solve
  [input-file]
  (let [input (read-input-file input-file)
        color-map (visible-color-frequencies input)
        sorted (sort-by key color-map)]
    (doseq [line sorted]
      (println (key line) (val line)))))

(defn -main
  ([] (-main 0))
  ([index]
   (time (solve (input-files (Integer/parseInt index))))))

(-main "0")
