; trace an atom
(def history (atom []))

(swap!  history conj ["move" 20])

(swap!  history conj ["move" 30])

(swap!  history conj ["turn" 50])

(println @history)

