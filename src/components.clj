(ns components
  (:require
    [garden.core :refer [css]]))

(def general-styles
  (css [:body
        {:font-family "sans-serif"
         :line-height "1.75"
         :color "#373737"}]))
