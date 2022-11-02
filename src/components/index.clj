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
          :padding-top "20px"
          :padding-bottom "20px"}
         [:.photo
          {:width "70px"
           :border-radius "100%"
           :margin-left "-8px"
           :margin-bottom "35px"}]
         [:.name
          {:font-size "19px"
           :font-weight "600"
           :margin-bottom "20px"}]
         [:.description
          {:font-size "16px"
           :color "#666"}]]]))

(defn render [{:keys [about]}]
  [:div.index
   (when (:photo about)
     [:img.photo {:src (str "/" (:photo about))}])
   (when (:name about)
     [:h1.name (:name about)])
   (when (:description about)
     [:p.description (:description about)])])
