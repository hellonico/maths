(defproject maths "0.1.0-SNAPSHOT"
  :description "Some, possibly fast, implementations of known algorithms"
  ;  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ["-Xmx16g" "-server" "-XX:-UseGCOverheadLimit"]
  :profiles {
    :dev {:dependencies [
                         [org.clojure/tools.namespace "0.2.8"]
                         [criterium "0.4.3"]
                         [clj-stacktrace "0.2.4"]]}
  }
  :dependencies [; [org.apache.commons/commons-math3 "3.0"]
                 [org.clojure/data.priority-map "0.0.5"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/clojure "1.7.0-alpha4"]])
