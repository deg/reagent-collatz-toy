(ns reframe-toy1.handlers
  (:require [re-frame.core :as re-frame]
            [reframe-toy1.db :as db]
            [reframe-toy1.calc :refer [calc]]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   (calc (assoc db/default-db :generations 3))))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/register-handler
 :generations
 (fn [db [_ n]]
   (calc (assoc db :generations n))))
