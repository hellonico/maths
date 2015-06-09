(ns maths.simple.redefs)

;
(ns http)
(defn post [url]
  {:body "Hello world"})

(ns app
  (:require [clojure.test :refer [deftest is run-tests]]))

(deftest is-a-fn
  (with-redefs-fn {#'http/post (fn [url] {:body "Hello world again"})}
    #(is (= {:body "Hello world again"} (http/post "http://service.com/greet")))))

(run-tests) ;; test is passing
