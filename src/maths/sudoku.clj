(ns sudoku
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic])
  (:require [clojure.core.logic.fd :as fd]))


(defn init-board [vars puzzle]
  (matche [vars puzzle]
          ([[] []]
             succeed)
          ([[_ . more-vars] [0 . more-puzzle]]
             (init-board more-vars more-puzzle))
          ([[num . more-vars] [num . more-puzzle]]
             (init-board more-vars more-puzzle))))


(defn solve [puzzle]
  (let [sdnum (fd/domain 1 2 3 4 5 6 7 8 9)
        board (repeatedly 81 lvar)
        rows (into [] (map vec (partition 9 board)))
        cols (apply map vector rows)

        get-square (fn [x y]
                     (for [x (range x (+ x 3))
                           y (range y (+ y 3))]
                       (get-in rows [x y])))

        squares (for [x (range 0 9 3)
                      y (range 0 9 3)]
                  (get-square x y))]

    (run* [q]
         (== q board)
         (everyg #(fd/in % sdnum) board)
         (init-board board puzzle)
         (everyg fd/distinct rows)
         (everyg fd/distinct cols)
         (everyg fd/distinct squares))))


(def puzzle1
  [0 0 0 0 0 9 0 6 0
   0 3 8 0 0 5 0 0 4
   0 2 0 0 6 0 0 7 0
   0 0 0 0 0 0 3 9 0
   0 0 0 9 2 6 0 0 0
   0 9 7 0 0 0 0 0 0
   0 4 0 0 7 0 0 3 0
   5 0 0 4 0 0 2 1 0
   0 7 0 8 0 0 0 0 0])

(def puzzle2
  [0 3 0 5 0 0 0 0 0
   7 0 0 0 6 0 9 0 0
   8 2 9 0 0 0 0 0 0
   9 6 0 0 3 0 1 0 0
   3 0 0 6 0 4 0 0 2
   0 0 4 0 8 0 0 7 6
   0 0 0 0 0 0 7 5 3
   0 0 7 0 5 0 0 0 9
   0 0 0 0 0 9 0 1 0])

;; http://www.sudokudragon.com/unsolvable.htm
(def puzzle3
  [0 8 0 0 0 9 7 4 3
   0 5 0 0 0 8 0 1 0
   0 1 0 0 0 0 0 0 0
   8 0 0 0 0 5 0 0 0
   0 0 0 8 0 4 0 0 0
   0 0 0 3 0 0 0 0 6
   0 0 0 0 0 0 0 7 0
   0 3 0 5 0 0 0 8 0
   9 7 2 4 0 0 0 5 0])

(comment
  (partition 9 (first (solve puzzle1)))
  ((7 1 4 2 8 9 5 6 3)
   (6 3 8 7 1 5 9 2 4)
   (9 2 5 3 6 4 1 7 8)
   (8 6 1 5 4 7 3 9 2)
   (4 5 3 9 2 6 7 8 1)
   (2 9 7 1 3 8 4 5 6)
   (1 4 9 6 7 2 8 3 5)
   (5 8 6 4 9 3 2 1 7)
   (3 7 2 8 5 1 6 4 9))

  (partition 9 (first (solve puzzle2)))
  ((4 3 6 5 9 1 2 8 7)
   (7 5 1 8 6 2 9 3 4)
   (8 2 9 4 7 3 5 6 1)
   (9 6 8 2 3 7 1 4 5)
   (3 7 5 6 1 4 8 9 2)
   (2 1 4 9 8 5 3 7 6)
   (6 9 2 1 4 8 7 5 3)
   (1 8 7 3 5 6 4 2 9)
   (5 4 3 7 2 9 6 1 8))

  (count (solve puzzle3))
  8

)


