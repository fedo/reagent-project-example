(ns reagent-project-example.components
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require
    [reagent-project-example.data :as data]
    [reagent.core :as reagent]))



(defn home
  []
  (reagent/create-class
    {:reagent-render (let [items (reaction @data/items-cursor)]
                       (fn []
                         [:div [:div "Home"]
                          [:strong "List:"]
                          (when @items
                            (into [:ul]
                                  (mapv (fn [item]
                                          [:li (str item)]) @items)))]))}))


(defn public
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div "Public"])}))


(defn private
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div "Private"])}))
