(ns maths.palyndrome
  (:use [maths.generators])
  (:require [clojure.string :as str]))


(defn palindrome? [n]
  (let [b (str n)]
    (= b (str/join "" (reduce conj '() b)))))

(defn palindrome? [string]
  (.equalsIgnoreCase string (str/reverse string)))
; (rand-string "nicolas" 10)

(defn search-palyndrome[input size]
  (loop [input input acc 0]
    (if (and (not (empty? (str/trim input))) (palindrome? input))
      (println input ":[" acc "]" )
      (recur (rand-string input size) (inc acc)))))

(search-palyndrome "le chemin du bain est le meilleur" 10)

