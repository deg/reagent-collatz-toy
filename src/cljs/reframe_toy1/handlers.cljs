(ns reframe-toy1.handlers
  (:require [re-frame.core :refer [debug trim-v after register-handler]]
            [reframe-toy1.db :as db]
            [reframe-toy1.calc :refer [calc]]
            [schema.core :as s]))

(defn check-and-throw
      "throw an exception if db doesn't match the schema."
      [a-schema db]
      (if-let [problems (s/check a-schema db)]
        (throw (js/Error. (str "schema check failed: " problems)))))

(def check-schema-toy (after (partial check-and-throw db/schema)))

(def toy-middleware [check-schema-toy debug trim-v])

(register-handler
 :initialize-db
 toy-middleware
 (fn  [_]
   (calc (assoc db/default-db :generations 3))))

(register-handler
 :set-active-panel
 toy-middleware
 (fn [db [active-panel]]
   (assoc db :active-panel active-panel)))

(register-handler
 :generations
 toy-middleware
 (fn [db [n]]
   (calc (assoc db :generations n))))

(register-handler
 :num-columns
 toy-middleware
 (fn [db [n]]
   (calc (assoc-in db [:display :num-columns] n))))

