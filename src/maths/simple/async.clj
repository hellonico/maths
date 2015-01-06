(ns maths.simple.async
  (:require [clojure.core.async :refer :all]))

(def a-channel (chan 1))
(>!! a-channel "Hello, world!")
(<!! a-channel)


