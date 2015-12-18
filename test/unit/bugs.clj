(ns unit.utils
  (:use clojure.test)
  (:use midje.sweet)
  (:use bugs.block))

(fact "Checking last time"
      (last-modified (java.io.File. "project.clj")) >= 1444540355000)

