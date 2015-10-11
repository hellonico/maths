(ns simple.testing
  (:require [clojure.test :refer :all]
            ))

(defn foo [i]
  (constantly 1))


(def ^:dynamic *a* 4)

(defn setup [f]
  (binding [*a* 42]
    (with-redefs [foo (fn [v] (constantly 42))]
      f)))

(use-fixtures :once setup)

(deftest a-test
  (testing "testing the number 42"
    (is (= *a* (foo 75)))))

(a-test)

(println (foo 75))
