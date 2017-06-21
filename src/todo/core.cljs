(ns todo.core
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [secretary.core :as secretary :refer-macros [defroute]])
  (:import goog.History))


(enable-console-print!)

(def app-root (dom/getElement "app"))

(defn set-html! [content]
  (set! (.-innerHTML app-root) content))

(defroute home "/" [] 
  (set-html! "<h1>Bem vindo!</h1>"))

(defroute perfil "/perfil/:user" [user] 
  (set-html! (str "<h1> Olá " user "</h1>")))

(secretary/set-config! :prefix "#")

(let [history (History.)]
  (events/listen history "navigate"
    (fn [e] 
      (secretary/dispatch! (.-token e))))
  (.setEnabled history true))

