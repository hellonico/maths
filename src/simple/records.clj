(ns simple.records)

; start by creating a record with fields
(defrecord Recipe
  [name ;; string
   author ;; recipe creator
   description ;; string
   ingredients ;; list of ingredients
   steps ;; sequence of string
   servings ;; number of servings
   ])

; here we create the dependencies
(defrecord Person
  [fname ;; first name
   lname ;; last name
])

; create our atom, just like a regular object
(def toast (atom (->Recipe "Toast"
(->Person "Nicolas" "Modrzyk")
  "Crispy bread"
["Slice of bread"]
["Toast bread in toaster"] 1)))

; perform atomic operation, no need for dosync
 (swap! toast assoc-in [:author :fname] "Nico")
 (swap! toast assoc :description "Updated description")
 (swap! toast assoc-in [:author :lname] "Chris2")
 (swap! toast assoc-in [:author :zname] "Mitchell2")

@toast

