(ns todo.core
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [secretary.core :as secretary :refer-macros [defroute]])
  (:import goog.History))


(enable-console-print!)

(def app-root (dom/getElement "app"))

(defn set-html! [content]
  (set! (.-innerHTML app-root) content))



(secretary/set-config! :prefix "#")

(let [history (History .)]
  (events/listen history "navigate"
    (fn [e] 
      (secretary/dispatch! (.-token e))))
  (.setEnabled history true))

(js/alert "asdajss")
