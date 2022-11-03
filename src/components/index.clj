(ns components.index
  (:require
    [garden.core :refer [css]]))

(def styles
  (css [:body
        {:display "flex"
         :align-items "center"
         :justify-content "center"
         :min-width "100vw"
         :min-height "100vh"}
        [:.index
         {:width "525px"
          :position "relative"
          :padding-top "20px"
          :padding-bottom "20px"}
         [:.photo
          {:width "90px"
           :border-radius "10px"
           :box-shadow "0px 1px 0px #fff, 0px 2px 0px #999, 0px 3px 0px #fff, 0px 4px 0px #999, 0px 3px 8px rgba(0,0,0,.25)"
           :position "absolute"
           :margin-top "3px"
           :left "-125px"}]
         [:.name
          {:font-weight "800"
           :margin-bottom "10px"}]
         [:.description
          {:color "#666"}]]]))

(defn render [{:keys [about]}]
  [:div.index
   (when (:photo about)
     [:img.photo {:src (str "/" (:photo about))}])
   (when (:name about)
     [:h1.name (:name about)])
   (when (:description about)
     [:p.description (:description about)])])
