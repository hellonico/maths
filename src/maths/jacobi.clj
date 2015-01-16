(ns maths.jacobi)

; http://fornext1119.hatenablog.com/entry/2014/09/15/204605

(def N 4)

(def a [[9.0 2.0 1.0 1.0]
        [2.0 8.0 -2.0 1.0]
        [-1.0 -2.0 7.0 -2.0]
        [1.0 -1.0 -2.0 6.0]])

(def b  [20.0 16.0 8.0 17.0])

(def x0 [0.0 0.0 0.0 0.0])

(defn disp_vector [row]
    (doseq [col row]
        (print (format "%14.10f\t" col)))
    (println))

(defn disp_matrix [matrix]
    (doseq [row matrix]
        (doseq [col row]
            (print (format "%14.10f\t" col)))
        (println)))

(defn row_loop [row a b x0 x1]
    (def a1 (map (fn [a b] [a b]) (nth a row) x0))
    (def a2 (concat (take row a1) (drop (+ row 1) a1)))
    (def a3 (map (fn [x] (* (first x) (second x))) a2))
    (def s  (apply + a3))
    (def x  (/ (- (nth b row) s) (nth (nth a row) row)))
    (def xs (cons x x1))

    (if (>= row (- N 1))
        (reverse xs)
        (row_loop (inc row) a b x0 xs)))

(defn jacobi [a b x0 xs]
    (def x1 (row_loop 0 a b x0 []))

    (def cnt (count
        (filter (fn [x] (>= x 0.0000000001))
            (map (fn [x] (. Math abs (- (first x) (second x))))
                (map (fn [a b] [a b]) x0 x1)))))

    (if (< cnt 1)
        (vector (reverse xs) x1)
        (jacobi a b x1 (cons x1 xs))))

(def xs (jacobi a b x0 []))

(disp_matrix (first xs))
(println "X")
(disp_vector (second xs))
