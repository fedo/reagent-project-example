(ns reagent-project-example.components
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent-project-example.data :as data]
            [reagent.core :as reagent]))


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


(defn home
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


(defn item
  []
  (reagent/create-class
    {:reagent-render
     (let [params (reaction @data/params-cursor)
           items (reaction @data/items-cursor)
           item-index (js/parseInt (get @params :item-id))
           _ (println item-index) ]
       (fn [] (fn []
                [:div [:h3 "Item"]
                 (when (and (seq @items) (> item-index -1))
                   (let [item (nth @items item-index)]
                     [:div "item=" item]))])))}))


(defn public
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div [:h3 "Item"]
                        [:div [:a {:href "#/"} "Go to home"]]])}))


(defn private
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div "Private"])}))


(defn footer
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div {:style {:background-color "#C5CAE9"}}
                        "Footer"])}))

