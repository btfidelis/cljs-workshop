(ns todo.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [cljs.core.async :refer [<! put! chan]]
            [secretary.core :as secretary :refer-macros [defroute]])
  (:import goog.History
           goog.Uri
           goog.net.Jsonp))


(enable-console-print!)

(def app-root (dom/getElement "app"))
(defonce fixer-uri "http://api.fixer.io/latest")
(def home-view 
  (str "<h1>Taxas de intercâmbio</h1>"
       "<div id=\"results\"></div>"))

(defn set-html! 
  ([content] (set! (.-innerHTML app-root) content))
  ([content el] (set! (.-innerHTML el) content)))

(defn get-from-fixer 
  [path]
  (let [out (chan) 
        req (Jsonp. (Uri. (str fixer-uri path)))]
    (.send req nil (fn [res] (put! out res))) out))

(defn render-results [res el]
  (let [results (js->clj res)]
    (set-html! 
      (str "<p> Tipo: " (get results "base") "</p>"
           "<p> Atualizado em: " (get results "date") "</p>"
            (reduce 
              (fn [acc curr]
                (let [[coin value] curr]
                  (str acc "<div>" coin ":" value "</div>"))) 
                    "" (get results "rates"))) el)))



(defn render-fix [path]
  (go 
    (let [results (<! (get-from-fixer path))]
      (render-results results (dom/getElement "results")))))

(defroute home "/" [] 
  (set-html! home-view)
  (render-fix ""))

(defroute perfil "/moeda/:type" [type] 
  (set-html! (set-html! home-view))
  (render-fix (str "?base=" type)))

(secretary/set-config! :prefix "#")

(let [history (History.)]
  (events/listen history "navigate"
    (fn [e] 
      (secretary/dispatch! (.-token e))))
  (.setEnabled history true))

