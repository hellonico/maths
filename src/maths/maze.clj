(ns maths.maze)

; http://stackoverflow.com/questions/24255360/idiomatic-clojure-implementation-of-maze-generation-algorithm?rq=1

(def DIRECTIONS { :N 1, :S 2, :E 4, :W 8})
(def DX { :E 1, :W -1, :N 0, :S 0})
(def DY { :E 0, :W 0, :N -1, :S 1})
(def OPPOSITE { :E 8, :W 4, :N 2, :S 1})

(defn make-empty-grid
  [w h]
  (vec (repeat w (vec (repeat h 0)))))

(defn valid-unvisited-cell?
  [x y grid]
  (and
    (<= 0 y (- (count grid) 1)) ; within a column
    (<= 0 x (- (count (nth grid y)) 1)) ; within a row
    (= 0 (get-in grid [x y])))) ; unvisited

(defn remove-walls
  [cy, cx, ny, nx, direction, grid]
  (-> grid
    (update-in [cy cx] bit-or (DIRECTIONS direction))
    (update-in [ny nx] bit-or (OPPOSITE direction))))

(defn recursive-backtracker
  [current-x current-y grid]
  (reduce (fn [grid direction]
            (let [next-x (+ current-x (DX direction))
                  next-y (+ current-y (DY direction))]
              (if (valid-unvisited-cell? next-x next-y grid)
                (recursive-backtracker next-x next-y
                                       (remove-walls current-x current-y next-x next-y
                                                     direction grid))
                grid)))
          grid, (clojure.core/shuffle [:N :S :E :W])))
