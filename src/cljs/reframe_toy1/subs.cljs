(ns reframe-toy1.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :num-rows
 (fn [db]
   (reaction (-> @db :display :num-rows))))

(re-frame/register-sub
 :num-columns
 (fn [db]
   (reaction (-> @db :display :num-columns))))

(re-frame/register-sub
 :generations
 (fn [db]
   (reaction (:generations @db))))

(re-frame/register-sub
 :max-generations
 (fn [db]
   (reaction (:max-generations @db))))

(re-frame/register-sub
 :values
 (fn [db]
   (reaction (map first (:values @db)))))

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))
