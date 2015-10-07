(ns ^:figwheel-always reagent-project-example.core
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require
    [reagent-project-example.components :as components]
    [reagent-project-example.data :as data :refer [app-state]]
    [reagent-project-example.routes]
    [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)


(println "Edits to this text should show up in your developer console.")


;(re-frame/dispatch [:initialise-db])


(defn header
  []
  (reagent/create-class
    {:reagent-render (fn []

                       [:div
                        [:h1 "Reagent + re-frame + secretary"]
                        (into [:h3]
                              (interpose " "
                                         (map (fn [[url title]]
                                                [:a {:href url} title]) [["#/" "Home"]
                                                                         ["#/public" "Public"]
                                                                         ["#/private" "Private"]])))])}))



(defn hello-world []
  (reagent/create-class
    {:componentWillMount (fn []
                           (reset! data/current-page-cursor #'components/home)
                           )
     :reagent-render     (let [current-page (reaction @data/current-page-cursor)
                               ]
                           (fn []
                             [:div
                              [header]
                              (when @current-page
                                [@current-page])
                              [:p "app-state: " (str @app-state)]]))}))


(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

