(defproject reframe-toy1 "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.48"]
                 [reagent "0.5.0"]
                 [re-frame "0.4.1"]
                 [re-com "0.5.4"]
                 [secretary "1.2.3"]
                 [prismatic/schema "0.4.4"]]

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.7" :exclusions [cider/cider-nrepl]]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target" "test/js"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]

                        :figwheel {:on-jsload "reframe-toy1.core/mount-root"}

                        :compiler {:main reframe-toy1.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true}}

                       {:id "test"
                        :source-paths ["src/cljs" "test/cljs"]
                        :notify-command ["phantomjs" "test/unit-test.js" "test/unit-test.html"]
                        :compiler {:optimizations :whitespace
                                   :pretty-print true
                                   :output-to "test/js/app_test.js"
                                   :warnings {:single-segment-namespace false}}}

                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler {:main reframe-toy1.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]})
