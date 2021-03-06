(ns reagent-project-example.routes
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent-project-example.components :as components]
            [reagent-project-example.data :as data]
            [reagent-project-example.layouts :as layouts]
            [secretary.core :as secretary :refer-macros [defroute]])
  (:import goog.History))


(enable-console-print!)


(secretary/set-config! :prefix "#")


(defroute "/public" []
          (reset! data/current-layout-cursor #'layouts/full-screen)
          (reset! data/current-page-cursor #'components/public)
          )


(defroute "/private" []
          (reset! data/current-layout-cursor #'layouts/compact)
          (reset! data/current-page-cursor #'components/private)
          )

(defroute "/items/:item-id" {:as params}
          (reset! data/params-cursor params)
          (reset! data/current-layout-cursor #'layouts/standard)
          (reset! data/current-page-cursor #'components/item)
          )


;; nop
(defroute "/" []
          (reset! data/current-layout-cursor #'layouts/standard)
          (reset! data/current-page-cursor #'components/home)
          )
;; page not found
(defroute "*" []
          (println "[routes] *"))


;; Quick and dirty history configuration.
(let [h (History.)]
  (reset! data/history-cursor h)
  (events/listen h EventType/NAVIGATE (fn [%]
                                        (if-let [token (.-token %)]
                                          (do (secretary/dispatch! token)
                                              (set! (.. js/document -body -scrollTop) 0))
                                          (do (println "EventType/NAVIGATE")))))
  (doto h (.setEnabled true)))