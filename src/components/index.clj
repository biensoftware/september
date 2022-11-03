(ns components.index 
  (:require
   [components :refer [colors]]
   [garden.selectors :as gs]))
   

(def styles
  [:body
    {:display "flex"
     :align-items "center"
     :justify-content "center"
     :min-width "100vw"
     :min-height "100vh"}
    [:.index
     {:width "500px"
      :position "relative"
      :padding-top "20px"
      :padding-bottom "20px"}
     [".photo:before"
      {:display "block"
       :content "''"
       :width "calc(100% - 2px)"
       :height "calc(100% - 2px)"
       :border "1px solid rgba(255,255,255,.2)"
       :border-radius "5px"
       :position "absolute"
       :top "0"
       :left "0"}]
     [:.photo
      {:overflow "hidden"
       :position "absolute"
       :margin-top "3px"
       :left "-125px"}
      [:img
        {:display "block"
         :width "92px"
         :border-radius "5px"
         :box-shadow "0px 3px 8px rgba(0,0,0,.3)"}]]
     [:.name
      {:font-weight "800"
       :margin-bottom "10px"}]
     [:.description
      {:color "#eee"}]]])

(defn- ->description-job [jobs]
  (when-let [current (->> jobs (filter #(= (:to (val %)) "present")) first last)]
    [:span
     "I'm currently spending my days at "
     [:a {:href (:url current)} (:company current)]
     " as a "
     (:position current)
     ". "]))

(defn- ->description-contact [email]                      
  [:span
   "You can get in touch with me via "
   [:a {:href (str "mailto:" email)} email]
   "."])

(defn render [{:keys [about jobs]}]
  [:div.index
   (when (:photo about)
     [:div.photo
       [:img {:src (str "/" (:photo about))}]])
   (when (:name about)
     [:h1.name (:name about)])
   [:p.description
    (when (:description about) 
      [:span (:description about) " "])
    (when jobs
      (->description-job jobs))
    (when (:email about)
      (->description-contact (:email about)))]])
