(ns reframe-toy1.calc)

(defn next-gen-of [n]
  (if (and (> n 1)
           (= 1 (mod n 3))
           (= 1 (mod (/ (dec n) 3) 2)))
    [(* n 2) (/ (dec n) 3)]
    [(* n 2)]))

(defn add-nexts-of [db n gen]
  (let [new-vals (remove #(contains? (:values db) %) (next-gen-of n))
        new-pairs (into {} (map #(vector % [n gen]) new-vals))]
    (-> db
        (assoc :values (into (:values db) new-pairs))
        (update-in [:generation-values gen]
                   #(into (or %1 (sorted-map)) %2)
                   new-pairs))))

(defn first-gen [db]
  (let [first-pair {1 [1 0]}]
    (assoc db
           :values (into (sorted-map) first-pair)
           :generation-values (sorted-map 0 first-pair))))

(defn add-gen [db gen]
  (let [dests (as-> gen $
                (dec $)
                (get-in db [:generation-values $])
                (map first $))]
    (reduce #(add-nexts-of %1 %2 gen) db dests)))
         

(defn calc [db]
  (let [cleared-db (into db (first-gen db))]
    (into db (reduce add-gen cleared-db (range 1 (:generations db))))))
