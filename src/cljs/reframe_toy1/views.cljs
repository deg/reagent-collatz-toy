(ns reframe-toy1.views
    (:require [re-frame.core :refer [subscribe dispatch]]
              [re-com.core :as re-com]))



;; --------------------
(defn footer-panel []
  [re-com/v-box
   :children ["A Degel toy application"
              [re-com/h-box
               :justify :start
               :gap "2em"
               :children ["Written by David Goldfarb"
                          [re-com/hyperlink-href
                           :label "deg@degel.com"
                           :href "mailto:deg@degel.com"]]]
              "Copyright (C) 2015, Degel Software Ltd."]])

;; --------------------
(defn home-title []
  (let [name (subscribe [:name])]
    (fn []
      [re-com/title
       :label (str "Hello from " @name ". This is the Home Page.")
       :level :level1])))

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn home-panel []
  (let [generations (subscribe [:generations])
        max-generations (subscribe [:max-generations])
        values (subscribe [:values])]
    (fn []
      [re-com/v-box
       :gap "1em"
       :children [[home-title]
                  [re-com/slider
                   :model generations
                   :min 1
                   :max 30
                   :on-change #(dispatch [:generations %])]
                  "before"
                  (str @values)
                  "after"
                  [link-to-about-page]
                  [footer-panel]]])))

;; --------------------
(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "go to Home Page"
   :href "#/"])  

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title]
              [link-to-home-page]
              [footer-panel]]])

;; --------------------
(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [(panels @active-panel)]])))
