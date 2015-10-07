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

(defn home
  []
  (reset! data/current-layout-cursor #'layouts/full-screen)
  (reset! data/current-content-cursor #'home-content)
  (reagent/create-class
    {:component-will-mount (fn []
                             (println "mount public component")
                             )
     :reagent-render
                           (let []
                             (fn [] (fn []
                                      [:div (when @data/current-layout-cursor
                                              [@data/current-layout-cursor])])))}))


(defn item
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

(defn public-content
  []
  (reagent/create-class
    {:reagent-render       (fn []
                             [:div [:h3 "Item"]
                              [:div [:a {:href "#/"
                                         } "Go to home"]]])}))

(defn public
  []
  (reagent/create-class
    {:component-will-mount (fn []
                             (println "mount public component")
                             (reset! data/current-layout-cursor #'layouts/full-screen)
                             (reset! data/current-content-cursor #'public-content))
     :reagent-render
     (let []
       (fn [] (fn []
                [:div (when @data/current-layout-cursor
                        [@data/current-layout-cursor])])))}))




(defn private
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div "Private"])}))