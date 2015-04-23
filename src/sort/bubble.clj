(ns sort.bubble
  (:import java.util.ArrayList))

; http://rosettacode.org/wiki/Sorting_algorithms/Bubble_sort#Clojure
; very slow. O(n2)

(defn bubble-sort-array
  "Sort in-place.
  arr must implement the Java List interface and should support
  random access, e.g. an ArrayList."
  ([arr] (bubble-sort-array compare arr))
  ([cmp arr]
     (letfn [(swap! [i j]
                    (let [t (.get arr i)]
                      (doto arr
                        (.set i (.get arr j))
                        (.set j t))))
             (sorter [stop-i]
                     (let [changed (atom false)]
                       (doseq [i (range stop-i)]
                         (if (pos? (cmp (.get arr i) (.get arr (inc i))))
                           (do
                             (swap! i (inc i))
                             (reset! changed true))))
                       @changed))]
       (doseq [stop-i (range (dec (.size arr)) -1 -1)
               :while (sorter stop-i)])
       arr)))

(defn- bubble-step
  "was-changed: whether any elements prior to the current first element
  were swapped;
  returns a two-element vector [partially-sorted-sequence is-sorted]"
 [less? xs was-changed]
  (if (< (count xs) 2)
    [xs (not was-changed)]
    (let [[x1 x2 & xr] xs
	  first-is-smaller   (less? x1 x2)
	  is-changed         (or was-changed (not first-is-smaller))
	  [smaller larger]   (if first-is-smaller [x1 x2] [x2 x1])
	  [result is-sorted] (bubble-step
			      less? (cons larger xr) is-changed)]
      [(cons smaller result) is-sorted])))

(defn bubble-sort
  "Takes an optional less-than predicate and a sequence.
  Returns the sorted sequence.
  Very inefficient (O(n²))"
  ([xs] (bubble-sort <= xs))
  ([less? xs]
     (let [[result is-sorted] (bubble-step less? xs false)]
       (if is-sorted
	 result
	 (recur less? result)))))

; (time (bubble-sort [10 9 8 7 6 5 4 3 2 1]))

