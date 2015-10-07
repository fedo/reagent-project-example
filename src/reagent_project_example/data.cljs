(ns reagent-project-example.data
  (:require [reagent.core :as r]
            [reagent.ratom :as rv :refer [atom]]))


(def app-state (atom {:current-page nil
                      :items        ["One" "Two" "Three"]
                      :text         "Text from the app-state!"}))



(def current-page-cursor (r/cursor app-state [:current-page]))


(def items-cursor (r/cursor app-state [:items]))
