(ns september
  (:require
   [clojure.java.io :as io]
   [clojure.string :as string]
   [clojure.walk :refer [keywordize-keys]]
   [design]
   [components.index :as index]
   [garden.core :refer [css]]
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

(defn- doc-head [{:keys [title styles responsive-styles]}]
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport" :content "width=device-width"}]
   [:title title]
   [:style (str "@import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap');" 
                (css design/styles
                     design/responsive-styles
                     styles
                     responsive-styles))]]) 

(defn- doc-body [content]
  [:body content])

(defn- copy-image [photo]
  (let [photo-from-path (->path root-dir photo)
        photo-to-path (->path root-dir "public" photo)]
    (when (and photo (.exists (io/file photo-from-path)))
      (io/copy (io/file photo-from-path) (io/file photo-to-path)))))

(defn- compose-index [config]
  (let [write-path (->path root-dir "public" "index.html")
               content (-> config index/render hc/html)
        head (doc-head {:title (-> config :about :name)
                        :styles index/styles
                        :responsive-styles index/responsive-styles})
        body (doc-body content)
        document (hp/html5 head body)]  
    (io/make-parents write-path)
    (copy-image (-> config :about :photo))
    (spit write-path document)))

(defn -main [& _]
  (let [config (read-config)]
    (compose-index config)))
  
      
