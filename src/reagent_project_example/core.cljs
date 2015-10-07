(ns ^:figwheel-always reagent-project-example.core
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent-project-example.components :as components]
            [reagent-project-example.layouts :as layouts]
            [reagent-project-example.data :as data :refer [app-state]]
            [reagent-project-example.routes]
            [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)


(println "Edits to this text should show up in your developer console.")


(defn hello-world []
  (reagent/create-class
    {:reagent-render (let []
                       (fn []
                         [:div
                          (when @data/current-page-cursor
                            [@data/current-page-cursor])
                          (when @app-state
                            [:p "app-state=" (str @app-state)])]))}))


(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

