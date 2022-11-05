(ns components.index) 

(def styles
  [:body
    {:display "flex"
     :align-items "center"
     :justify-content "center"
     :min-width "100vw"
     :min-height "100vh"}
    [:.index
     {:width "530px"
      :position "relative"
      :padding-top "20px"
      :padding-bottom "20px"}
     [".photo:before"
      {:display "block"
       :content "''"
       :width "calc(100% - 2px)"
       :height "calc(100% - 2px)"
       :background "linear-gradient(rgba(0,0,0,.05) 50%, rgba(255,255,255,.1) 50%)"
       :border "1px solid rgba(255,255,255,.2)"
       :border-radius "100%"
       :transform "rotate(-20deg)"
       :z-index "10"
       :position "absolute"
       :top "0"
       :left "0"}]
     [:.photo
      {:display "block"
       :width "75px"
       :overflow "hidden"
       :margin-bottom "30px"} 
      [:img
        {:display "block"
         :width "75px"
         :border-radius "100%"
         :box-shadow "0px 3px 8px rgba(0,0,0,.3)"}]]
     [:.name
      {:font-size "18px"
       :font-weight "800"
       :margin-bottom "10px"}]
     [:.description
      {:font-size "16px"
       :color "#eee"}]
     [:.links
      {:margin-top "50px"}
      [:li
       {:display "inline-block"
        :color "rgba(255,255,255,.3)"
        :list-style "none"}]
      ["li:after"
       {:display "inline-block"
        :content "','"
        :margin-right "4px"}]
      ["li:last-child:after"
       {:content "'.'"
        :margin-right "0"}]]]])

(defn- ->description-job [jobs]
  (when-let [current (->> jobs (filter #(= (:to (val %)) "present")) first last)]
    [:span
     "Currently working for "
     [:a {:href (:url current)} (:company current)]
     (when (:position current)
       [:span 
        " as a "
        (:position current)])
     ". "]))

(defn- ->description-contact [email]                      
  [:span
   "You can get in touch via "
   [:a {:href (str "mailto:" email)} email]
   "."])

(defn ->links [links]
  [:ul
   (for [link links]
     [:li
      [:a {:href (:url (val link))} (:label (val link))]])])

(defn render [{:keys [about jobs links]}]
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
      (->description-contact (:email about)))]
   (when links
     [:div.links
      [:h2 "Elsewhere"]
      (->links links)])])
