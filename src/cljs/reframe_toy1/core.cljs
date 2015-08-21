(ns reframe-toy1.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [reframe-toy1.handlers]
              [reframe-toy1.subs]
              [reframe-toy1.routes :as routes]
              [reframe-toy1.views :as views]))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
