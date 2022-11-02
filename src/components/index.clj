(ns components.index)

(defn render [{:keys [about]}]
  [:div
   [:h1 (:name about)]])
