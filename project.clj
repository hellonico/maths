(defproject maths "0.1.0-SNAPSHOT"
  :description "Some, possibly fast, implementations of known algorithms"
  ;  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ["-Xmx16g" "-server" "-XX:-UseGCOverheadLimit"]
  :profiles {
    :dev {:plugins [[lein-midje "3.0.0"]]
          :dependencies [[primitive-math "0.1.4"]
                         [org.clojure/tools.trace "0.7.8"]
                         [midje "1.6.3"]
                         [org.clojure/tools.namespace "0.2.8"]
                         [criterium "0.4.3"]
                         [clj-stacktrace "0.2.4"]]
          }
  }
  :dependencies [[org.clojure/data.priority-map "0.0.5"]
                 [net.mikera/core.matrix "0.32.1"]
                 [net.mikera/vectorz-clj "0.28.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/clojure "1.7.0-alpha5"]])
