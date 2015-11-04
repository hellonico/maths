(map-indexed
 #(vector %2 %1)
 [:a :b :c])


(map-indexed (fn [idx itm] [idx itm]) ["item"])
(map-indexed #(vector %1 %2) [:a :b :c])
