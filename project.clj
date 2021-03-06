(defproject maths "0.2.0-SNAPSHOT"
  :description "Some, possibly fast, implementations of known algorithms"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ["-Xmx12g" "-server" "-XX:-UseGCOverheadLimit""-XX:+TieredCompilation" "-XX:TieredStopAtLevel=1"] ;"–XX:+UseG1GC"
  ;:aliases {["suite" ["midje" ":autotest" "test/testing/suite.clj"]]}
  :bootclasspath true
  :repositories [["hellonico.org" "http://nexus.hellonico.org/nexus/content/repositories/snapshots/"]]
  :profiles {
    :dev {
           :injections
           [(require 'flare.clojure-test)
            (flare.clojure-test/install!)]
          :plugins [
                    ; documentation
                    ;[lein-marginalia "0.8.1-SNAPSHOT"]
                    ; [lein-marginalia "0.8.1-SNAPSHOT"]
                    ; interactive web REPL
                    ;[lein-gorilla "0.3.4"]
                    ; static code analysis
                    [lein-kibit "0.1.2"]
                    ; test framework
                    [lein-midje "3.0.0"]]
          :dependencies [
                         ; tracing functions
                         [org.clojure/tools.trace "0.7.8"]
                         ; testing
                         [flare "0.2.8"]
                         [midje "1.6.3"]
                         ; (re-)loading namespace
                         [org.clojure/tools.namespace "0.2.10"]
                         ; test framework
                         [criterium "0.4.3"]
                         ; better display of stack traces
                         [clj-stacktrace "0.2.8"]]}
  }
  :dependencies [

                 ;[tesser.all "0.1.0-SNAPSHOT"]

                 ;
                 [hellonico/wujuko-common "0.1.3"]
                 ; statistical library
                 [incanter/incanter-core "1.5.6"]
                 [org.clojure/data.priority-map "0.0.7"]
                 [net.mikera/core.matrix "0.34.0"]
                 [primitive-math "0.1.4"]
                 [org.clojure/core.logic "0.8.10"]
                 [net.mikera/vectorz-clj "0.29.0"]
<<<<<<< HEAD
                 [org.clojure/clojure "1.8.0-RC4"]
                 [org.clojure/core.async "0.2.371"]
                ; [org.clojure/clojure "1.7.0"]
=======
;                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/core.async "0.2.374"]
                 ;[org.clojure/clojure "1.8.0-RC3"]
;                [org.clojure/clojure "1.7.0"]
                 [org.clojure/clojure "1.8.0-master-SNAPSHOT"]
>>>>>>> new
                 ])
