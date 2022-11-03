(ns components
  (:require
    [garden.core :refer [css]]))

(def general-styles
  (str "@import url('https://fonts.googleapis.com/css2?family=JetBrains+Mono:ital,wght@0,400;0,600;0,800;1,400;1,600&display=swap');"
       (css [:body
             [:*
               {:font-family "JetBrains Mono"
                :font-size "13px"
                :line-height "1.75"
                :box-sizing "border-box"
                :padding "0"
                :margin "0"}]
             [:h1
              {:text-transform "uppercase"
               :font-weight "800"
               :color "#111"
               :letter-spacing "1px"}]])))
              
