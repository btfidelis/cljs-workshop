(ns todo.core
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [clojure.string :as string]))

(enable-console-print!)

(def app-root (dom/getElement "app"))
(def app-item-btn (dom/getElement "sendItem"))
(def app-nitem (dom/getElement "newItem"))

(defonce app-state 
  (atom {:todos ["Baixar JDK" "Baixar Leiningem"] }))

(defn set-state! [new-state]
  (swap! app-state conj new-state))


(defn list-el [list]
  (str "<ul>" 
    (string/join "" 
      (map (fn [x] (str "<li>" x "</li>")) list)) "</ul>"))

(defn render-list []
  (set! (.-innerHTML app-root) (list-el (:todos @app-state))))

(defn add-item-to-list [e]
  (set-state! {:todos (conj (:todos @app-state) (.-value app-nitem))}))

(add-watch app-state :on-text-change render-list)

(render-list)

(events/listen app-item-btn "click" add-item-to-list)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
