(ns components)

(def colors
  {:background "#202024"
   :semi-background "#303034"})

(def general-styles
  [:body
   {:background (:background colors)
    :padding "0px"
    :margin "0px"}
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
     :color "#fff"
     :letter-spacing "1px"}]
   [:a 
    {:color "#fff"}]])
        
