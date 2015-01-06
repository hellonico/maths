(ns maths.sudoku
  (:use [clojure.pprint :only (cl-format)]))

(defn- compatible? [m x y n]
  (let [n= #(= n (get-in m [%1 %2]))]
    (or (n= y x)
      (let [c (count m)]
        (and (zero? (get-in m [y x]))
             (not-any? #(or (n= y %) (n= % x)) (range c))
             (let [zx (* c (quot x c)), zy (* c (quot y c))]
               (every? false?
                 (map n= (range zy (+ zy c)) (range zx (+ zx c))))))))))

(defn solve [m]
  (let [c (count m)]
    (loop [m m, x 0, y 0]
      (if (= y c) m
        (let [ng (->> (range 1 c)
                      (filter #(compatible? m x y %))
                      first
                      (assoc-in m [y x]))]
          (if (= x (dec c))
            (recur ng 0 (inc y))
            (recur ng (inc x) y)))))))

(cl-format true "~{~{~a~^ ~}~%~}"
 (solve [[3 9 4 0 0 2 6 7 0]
         [0 0 0 3 0 0 4 0 0]
         [5 0 0 6 9 0 0 2 0]
         [0 4 5 0 0 0 9 0 0]
         [6 0 0 0 0 0 0 0 7]
         [0 0 7 0 0 0 5 8 0]
         [0 1 0 0 6 7 0 0 8]
         [0 0 9 0 0 8 0 0 0]
         [0 2 6 4 0 0 7 3 5]]))

(solve [[3 9 4 0 0 2 6 7 0]
         [0 0 0 3 0 0 4 0 0]
         [5 0 0 6 9 0 0 2 0]
         [0 4 5 0 0 0 9 0 0]
         [6 0 0 0 0 0 0 0 7]
         [0 0 7 0 0 0 5 8 0]
         [0 1 0 0 6 7 0 0 8]
         [0 0 9 0 0 8 0 0 0]
         [0 2 6 4 0 0 7 3 5]])
