(ns reframe-toy1.db
  (:require [cljs.reader]
            [schema.core  :as s :include-macros true]))

(def schema
  {:name s/Str
   :display {:num-rows s/Int
             :num-columns s/Int}
   :max-generations s/Int
   :generations s/Int
   :values {s/Int s/Int}})

(def default-db
  {:name "Collatz Conjecture Reagent Toy"
   :description [:div
                 "For details, see the "
                 [:a {:href "https://en.wikipedia.org/wiki/Collatz_conjecture"} "Wikipedia page"]]
   :generations 3
   :display {:num-rows 20 :num-columns 10}
   :values {1 1}})
