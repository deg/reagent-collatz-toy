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
       :label (str @name)
       :level :level1])))

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn unpack-values [values]
  (letfn [(helper [candidate remaining]
            (cond (not (seq remaining))
                  nil
                  (= candidate (first remaining))
                  (cons [candidate true] (lazy-seq (helper (inc candidate) (rest remaining))))
                  :else
                  (cons [candidate false] (lazy-seq (helper (inc candidate) remaining)))))]
    (helper 1 values)))

(defn numbers-panel []
  (let [num-rows (subscribe [:num-rows])
        num-columns (subscribe [:num-columns])
        raw-values (subscribe [:values])]
    (fn []
      (let [max-val (* @num-rows @num-columns)
            values (take max-val (unpack-values @raw-values))
            divs (map (fn [[n on?]]
                        [:span {:key n
                                :class (str "numberBox " (if on? "" "un") "highlighted")}
                         (str n)])
                      values)
            rows (map (fn [spans] [re-com/h-box :children spans]) (partition @num-columns @num-columns "" divs))]
        [re-com/v-box :children rows]))))

(defn home-panel []
  (let [generations (subscribe [:generations])
        num-columns (subscribe [:num-columns])
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
                  [numbers-panel]
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
