(ns build
  (:refer-clojure :exclude [test])
  (:require 
    [org.corfield.build :as bb]
    [clojure.tools.build.api :as b]))

(def lib 'net.clojars.askonomm/september)
(def version (format "0.1.%s" (b/git-count-revs nil)))
(def main 'askonomm.september)

(defn test "Run the tests." [opts]
  (bb/run-tests opts))

(defn ci "Run the CI pipeline of tests (and build the uberjar)." [opts]
  (-> opts
      (assoc :lib lib :version version :main main)
      (bb/run-tests)
      (bb/clean)
      (bb/uber)))
