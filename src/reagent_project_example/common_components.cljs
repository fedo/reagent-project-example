(ns reagent-project-example.common-components
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent]))


(defn compact-header
  []
  (reagent/create-class
    {:reagent-render
     (fn []
       [:div {:style {:background-color "#7986CB"}}
        [:h3 "Reagent + re-frame + secretary"]
        (into [:h4]
              (interpose " "
                         (map (fn [[url title]]
                                [:a {:href url} title]) [["#/" "Home"]
                                                         ["#/public" "Public"]
                                                         ["#/private" "Private"]])))])}))


(defn header
  []
  (reagent/create-class
    {:reagent-render
     (fn []
       [:div {:style {:background-color "#7986CB"}}
        [:h1 "Reagent + re-frame + secretary"]
        (into [:h3]
              (interpose " "
                         (map (fn [[url title]]
                                [:a {:href url} title]) [["#/" "Home"]
                                                         ["#/public" "Public"]
                                                         ["#/private" "Private"]])))])}))


(defn footer
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div {:style {:background-color "#C5CAE9"}}
                        "Footer"])}))

