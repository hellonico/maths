(ns psy.anew
  (:require [clojure.string        :as s]
            [wujuko-common.core    :as wc]
            [wujuko-common.zio     :as tio]))

; Modified version of the anew algorithm

;; Bradley, M. M., & Lang, P. J. (1999).
;; Affective norms for English words (ANEW):
;;      Instruction manual and affective ratings.
;; University of Florida: The Center for Research in Psychophysiology.
;;
;; Data file header:
;;
;; Description, Word No., Valence Mean, Valence SD, Arousal Mean,
;; Arousal SD, Dominance Mean, Dominance SD, Word Frequency

(def scores {:english (atom {})})

;; Java <= 6 Has crap support for unicode regex
(def word-re #"[\pL\pM\p{Nd}\p{Nl}\p{Pc}[\p{InEnclosedAlphanumerics}&&\p{So}]+]+")

(defn load-lexicon
  "Loads lexicon.

   filename - The name of the file to pull data from.

   Expecting a fixed format CSV file with characteristics:

   Col 0: English word
   Col 2: Valence
   Col 5: Arousal
   Col 8: Dominance"
  [filename]
  (with-open [rdr (tio/zreader filename)]
    (try
      (doseq [jls (rest (line-seq rdr))]
        (let [tabs      (s/split jls #",")
              eword      (nth tabs 1)
              wvalence   (Float/parseFloat (nth tabs 2))
              warousal   (Float/parseFloat (nth tabs 5))
              wdominance (Float/parseFloat (nth tabs 8))
              scorem     {:a warousal :d wdominance :v wvalence}]
          ;; Update our inner atoms
          (swap! (:english scores) assoc eword scorem)))
      (catch Exception e
        (println "Exception during lexicon load: " e (.getMessage e))))))

(defn tokenize
  "A wrapper around the simple regex tokenizer for
   cases when we get a string."
  [text]
  (re-seq word-re (s/lower-case text)))

(defn string-or-seq
  "Figure out whether this is a string or a sequence and
   return a sequence."
  [obj]
  (if (string? obj)
    (tokenize obj)
    ;; obj is a sequence, we hope
    obj))

(defn score-phrase
  "Scores the phrase on the ANEW 1-9 scale.

   phrase   - A string containing words.
   language - a keyword in #{:english}

   Returns a map in the form:

   {:words [ w_1 ... w_n ]
    :score {:v X :a Y :d Z} }

   :a is Arousal
   :d is Dominance
   :v is Valence
  "
  [tokens language]
  (let [;; We're just building a seq of scores, accounting for duplicates:
        matches (reduce (fn [v w]
                          (if-let [scorem (get @(language scores) w)]
                            (conj v (assoc scorem :word w))
                            v))
                        [] tokens)
        ;; match count the same across all dimensions
        matchcount (count matches)]
    {:words (reduce (fn [x y] (conj x (:word y))) [] matches)
     :score (reduce (fn [x y]
                      (merge-with +
                                  x (wc/update-vals
                                     (dissoc y :word)
                                     [:a :v :d]
                                     #(/ % matchcount))))
                    {}
                    matches)}))

(defn score-phrase-langs
  "returns a map containing
   the Arousal, Dominance, and Valence scores. The specific langauge
   models that matched are also returned.

   {:a 0.0 :d 0.0 :v 0.0
    :anew-en {:words [\"word\" \"matching\" \"lexicon\"]
              :score {:a 0.0 :d 0.0 :v 0.0}}
   }"
  [text]
  (let [anew-en (score-phrase (string-or-seq text) :english)
        ;anew-es (score-phrase (string-or-seq text) :spanish)
        ;anew-pt (score-phrase (string-or-seq text) :portuguese)
        maxscored (apply max
                         (map count
                              [(:words anew-en)]))]
    (when (> maxscored 0)
      (let [maxrecs  (into {} (filter (fn [x] (>= (count (:words (second x))) maxscored))
                                      {:eng anew-en}))
            maxrecsn (count maxrecs)
            ;; Take the maxrecs, which may be 1 to 3 elements, and
            ;; average the scores.
            anew-f   (reduce (fn [x y]
                               (let [vmap (wc/update-vals
                                           (:score y)
                                           [:a :d :v] #(* % (/ 1 maxrecsn)))]
                                 (apply merge-with + [x vmap])))
                             {} (vals maxrecs))]
        (merge maxrecs anew-f)))))

(comment
  (load-lexicon "resources/ratings.csv.bz2")
  (score-phrase-langs "I really love my dog.")
  )
