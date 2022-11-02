(ns september
  (:require
   [components.index :as index]
   [clojure.java.io :as io]
   [clojure.string :as string]
   [clojure.walk :refer [keywordize-keys]]
   [hiccup.core :as hc]
   [hiccup.page :as hp]
   [toml.core :as toml])
  (:import
   (java.io File))
  (:gen-class))

(def ^:private root-dir "/home/ano/x/ano.ee")

(defn- ->path [& parts]
  (string/join File/separatorChar parts))

(defn- read-config 
  "Reads the TOML configuration file into a consumable Clojure map."
  []
  (-> (->path root-dir "september.toml")
      slurp
      toml/read
      keywordize-keys))

(defn- doc-head [{:keys [title]}]
  [:head
   [:meta {:charset "utf-8"}]
   [:title title]])

(defn- doc-body [content]
  [:body content])

(defn- compose-index [config]
  (let [write-path (->path root-dir "public" "index.html")
        content (-> config index/render hc/html)
        head (doc-head {:title (-> config :about :name)})
        body (doc-body content)
        document (hp/html5 head body)]  
    (io/make-parents write-path)
    (spit write-path document)))

(defn -main [& _]
  (let [config (read-config)]
    (compose-index config)))
  
      
