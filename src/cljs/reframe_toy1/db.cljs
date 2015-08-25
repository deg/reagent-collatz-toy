(ns reframe-toy1.db
  (:require [cljs.reader]
            [schema.core  :as s :include-macros true]))

(def schema-hiccup (s/either s/Str [s/Any]))
(def schema-current s/Int)
(def schema-value [(s/one s/Int "previous") (s/one s/Int "generation")])
(def schema-value-map {schema-current schema-value})

(def schema-panels (s/enum :home-panel :about-panel))

(def schema
  {:name schema-hiccup
   :description schema-hiccup
   :active-panel s/Any
   :display {:num-rows s/Int
             :num-columns s/Int}
   :generations s/Int
   :generation-values {s/Int {s/Int schema-value}}
   :values schema-value-map})


(def default-db
  {:name "Collatz Conjecture Reagent Toy"
   :description [:div
                 "For details, see the "
                 [:a {:href "https://en.wikipedia.org/wiki/Collatz_conjecture"}
                  "Wikipedia page"]]
   :active-panel :home-panel
   :generations 3
   :display {:num-rows 20 :num-columns 10}
   :values {1 [1 0]}})
