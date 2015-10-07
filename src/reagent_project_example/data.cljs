(ns reagent-project-example.data
  (:require [reagent.core :as r]
            [reagent.ratom :as rv :refer [atom]]))


(def app-state (atom {:url-params         nil
                      :current-layout nil
                      :current-page   nil
                      :items          ["One" "Two" "Three"]
                      :text           "Text from the app-state!"
                      :history        nil}))



(def current-page-cursor (r/cursor app-state [:current-page]))


(def current-layout-cursor (r/cursor app-state [:current-layout]))


(def items-cursor (r/cursor app-state [:items]))


(def params-cursor (r/cursor app-state [:url-params]))


(def history-cursor (r/cursor app-state [:history]))

