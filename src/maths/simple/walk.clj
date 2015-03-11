(ns maths.simple.walk)

(clojure.walk/prewalk
 (fn [m]
   (if (and (map? m) (:foo m))
     (update-in m [:foo] #(str % "/bar"))
     m))
 {:key "value"
  :anotherKey "anotherValue"
  :foo "test"
  :others [{:foo "test2"} {:foo "test3"}]
  :deeper {:nesting {:foo "test4"}}})


(def input {:profile_data {:ber {:value 11}, :event_type {:value "foo"}}, :conservatories [{:area {:value 10}, :height {:value 11}} {:area {:value 2}, :height {:value 1}}], :timestamp {:value 1}})

(def input {:profile_data {:ber {:value 11}, :event_type {:value "foo"}}, :conservatories [{:area {:value 10}, :height {:value 11}} {:area {:value 2}, :height {:value 1}}], :timestamp {:value 1} :other-stuff {:value nil}})

(clojure.walk/prewalk #(do
                         (println %)
                         (if (and (map? %)
                                  (contains? % :value))
                           (:value %) %)) input)

(clojure.walk/postwalk #(do
                          (println %)
                          (if (and (map? %)
                                   (contains? % :value))
                            (:value %) %)) input)
