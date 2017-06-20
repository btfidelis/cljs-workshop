(ns todo.core
  (:require ))

(enable-console-print!)

;; https://clojure.org/reference/atoms
(defonce app-state (atom {:text "Hello world!"}))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
