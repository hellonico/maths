(ns simple.coolness
  (:use clojure.test))

; http://deliberate-software.com/unusual-reasons-why-clojure-is-a-delight/

; mocking

(declare get-current-id)
(defn next-id [connection]
  (+ 1 (get-current-id connection)))

(deftest nextid
(testing "next-id"
  ;; bind get-current-id to a function that always returns 4
  (with-redefs [get-current-id (fn [_] 4)]
    (is (= (next-id "fake connection"))))))


; polymorphismn
; dispatch on keys of a map

(defmulti speak :animal)
(defmethod speak :dog [this] (str "woof says " (:name this)))
(defmethod speak :cat [this] (str "mow says " (:name this)))

(speak {:animal :dog :id 1 :name "Spike"})
;; => "woof says Spike"
(speak {:animal :cat :id 2 :name "Mr Cat"})
;; => "mow says Mr Cat"

(defmulti odds? (comp odd? :id))
(defmethod odds? true [d] "odd id")
(defmethod odds? false [c] "even id")

(odds? {:animal :dog :id 1 :name "Spike"})
;; => "odd id"
(odds? {:animal :cat :id 2 :name "Mr Cat"})
;; => "even id"


; inheritance

(derive ::cat ::mammal)
(derive ::dog ::mammal)
(derive ::dog ::hairy)
(derive ::poodle ::dog)


(isa? ::poodle ::dog)
;; => true
(isa? ::poodle ::mammal)
;; => true
(isa? ::poodle ::hairy)
;; => true
(isa? ::poodle ::cat)
;; => false
(isa? ::mammal ::hairy)


(defmulti speak :animal)
(defmethod speak ::poodle [d] "chirps")
(defmethod speak ::mammal [c] "breathes")

(speak {:animal ::poodle :id 1 :name "Spike"})
;; => "chirps"
(speak {:animal ::dog :id 2 :name "Mr Dog"})
;; => "breathes"

(defmulti shave :animal)
(defmethod shave ::poodle [d] "shivers")
(defmethod shave ::hairy [c] "stuggles")
(defmethod shave ::mammal [c] "maybe cant be shaved!")
(prefer-method shave ::hairy ::mammal)

(shave {:animal ::poodle :id 1 :name "Spike"})
;; => "shivers"
(shave {:animal ::dog :id 2 :name "Rufs"})
;; => "stuggles"


; mostly monadic
(get {:id 5} :id)
;; => 5
(get nil :id)
;; => nil

(first [3 2 1])
;; => 3
(first nil)
;; => nil
(count nil)
;; => 0


; These are a few simple features that keep me coming back to Clojure,
; even from languages like F# and Haskell.
; While Clojure is a bit more wordy than the ML family,
; and not as type safe,
; the simplicity of these features keep me coming back for more!
