(ns reagent-project-example.components
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent-project-example.layouts :as layouts]
            [reagent-project-example.data :as data]
            [reagent.core :as reagent]))


(defn home-content
  []
  (reagent/create-class
    {:reagent-render
     (let [items (reaction @data/items-cursor)]
       (fn []
         [:div [:div "Home"]
          [:strong "List:"]
          (when @items
            (into [:ul]
                  (map-indexed (fn [id item]
                                 (println "id=" id "item=" item)
                                 [:li [:a {:href (str "#/items/" id)} (str item)]]) @items)))]))}))


(defn home-page
  []
  (reagent/create-class
    {:reagent-render (fn [] [layouts/standard home-content])}))


(defn item-content
  []
  (reagent/create-class
    {:reagent-render
     (let [params (reaction @data/params-cursor)
           items (reaction @data/items-cursor)
           item-index (js/parseInt (get @params :item-id))
           _ (println item-index)]
       (fn [] (fn []
                [:div [:h3 "Item"]
                 (when (and (seq @items) (> item-index -1))
                   (let [item (nth @items item-index)]
                     [:div "item=" item]))])))}))

(defn item-page
  []
  (reagent/create-class
    {:reagent-render (fn [] [layouts/standard item-content])}))


(defn public-content
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div [:h3 "Item"]
                        [:div [:a {:href "#/"
                                   } "Go to home"]]])}))


(defn public-page
  []
  (reagent/create-class
    {:reagent-render (fn [] [layouts/full-screen public-content])}))


(defn private-content
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div "Private"])}))


(defn private-page
  []
  (reagent/create-class
    {:reagent-render (fn [] [layouts/standard private-content])}))
