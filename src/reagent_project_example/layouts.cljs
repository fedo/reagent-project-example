(ns reagent-project-example.layouts
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent-project-example.data :as data]
            [reagent-project-example.common-components :as common-components]
            [reagent.core :as reagent :refer [atom]]))


(defn standard [content-component]
  (reagent/create-class
    {:reagent-render (fn []
                       [:div
                        [common-components/header]
                        [content-component]
                        [common-components/footer]])}))


(defn full-screen [content-component]
  (reagent/create-class
    {:reagent-render (fn []
                       [content-component])}))


(defn compact [content-component]
  (reagent/create-class
    {:reagent-render (fn []
                       [:div
                        [common-components/compact-header]
                        [content-component]
                        [common-components/footer]])}))