(ns reagent-project-example.layouts
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent-project-example.data :as data]
            [reagent-project-example.common-components :as common-components]
            [reagent.core :as reagent :refer [atom]]))


(defn standard []
  (reagent/create-class
    {:reagent-render (let [current-page (reaction @data/current-page-cursor)]
                       (fn []
                         [:div
                          [common-components/header]
                          (when @current-page
                            [@current-page])
                          [common-components/footer]]))}))


(defn full-screen []
  (reagent/create-class
    {:reagent-render (let [current-page (reaction @data/current-page-cursor)]
                       (fn []
                         [:div
                          (when @current-page
                            [@current-page])]))}))


(defn compact []
  (reagent/create-class
    {:reagent-render (let [current-page (reaction @data/current-page-cursor)]
                       (fn []
                         [:div
                          [common-components/compact-header]
                          (when @current-page
                            [@current-page])
                          [common-components/footer]]))}))