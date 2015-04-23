(ns maths.luhn)

(defn luhn? [cc]
  (let [factors (flatten (repeat [1 2]))
        numbers (map #(Character/digit % 10) (seq (str cc)))
        sum (reduce + (map #(int (+ (/ %1 10) (mod %1 10)))
                        (map * (reverse numbers) factors)))]
    (zero? (mod sum 10))))
