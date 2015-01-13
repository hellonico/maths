
(ns maths.neural
  (:refer-clojure :exclude [+ - * == /])
  (:use clojure.core.matrix)
  (:use clojure.core.matrix.operators))

(set-current-implementation :vectorz)

(defn activation-fn [x] (Math/tanh x))
(defn dactivation-fn [y] (- 1.0 (* y y)))

(defn get-layers
  [network]
  (conj (apply (partial conj [(:inputs network)]) (:hidden network)) (:outputs network)))

(defn generate-layer
  [neurons next-neurons]
  (let [values  (vec (repeat neurons 1))
        weights (vec (for [i (range neurons)] (vec (repeatedly next-neurons rand))))]
    {:values values :weights weights}))

(defn generate-network
  [& {:keys [inputs hidden outputs]}]
  (if (empty? hidden)
    {:inputs (generate-layer (inc inputs) outputs) :outputs (generate-layer outputs 1)} ; add one to inputs for a extra bias neuron
    (loop [current-layer (first hidden)
           next-layer    (first (rest hidden))
           others        (rest (rest hidden))
           network       {:inputs (generate-layer (inc inputs) (first hidden))}] ; add one to inputs for extra bias neuron
      (if (nil? next-layer)
        (-> network
             (update-in [:hidden] #(conj % (generate-layer current-layer outputs)))
             (assoc :outputs (generate-layer outputs 1)))
        (recur next-layer (first others) (rest others) (update-in network [:hidden] #(conj % (generate-layer current-layer next-layer))))))))

(defn activate-layer
  [{:keys [values weights]}]
  (->> (transpose weights)   ; group weights by neuron they point to
       (mapv #(* values %))
       (mapv #(reduce + %))
       (mapv activation-fn)))

(defn forward-propagate
  [network inputs]
  (let [network (assoc-in network [:inputs :values] (conj inputs 1)) ; add one to the inputs for the bias neuron
        layers  (get-layers network)]
    (loop [current-layer (first layers)
           layers        (rest layers)
           all-layers    []]
      (if (empty? layers) ; we are at the output layer. Stop forward propagating
        {:inputs (first all-layers) :hidden (rest all-layers) :outputs current-layer}
        (let [layers (assoc-in (vec layers) [0 :values] (activate-layer current-layer))] ; sets the layer aboves values
          (recur (first layers) (rest layers) (conj all-layers current-layer)))))))

(defn threshold-outputs
  [network]
  (update-in network [:outputs :values] (partial mapv #(if (< % 0.1) 0 (if (> % 0.9) 1 %)))))

(defn output-deltas
  [network expected]
  (let [outputs (get-in network [:outputs :values])]
    (assoc-in network [:outputs :deltas] (* (mapv dactivation-fn outputs) (- expected outputs)))))

(defn layer-deltas
  [layer layer-above]
  (assoc layer :deltas (* (mapv dactivation-fn (:values layer)) (mapv #(reduce + %)
                                                                      (* (:deltas layer-above) (:weights layer))))))

(defn adjust-layer-weights
  [layer layer-above rate]
  (assoc layer :weights (+ (:weights layer) (* rate (mapv #(* (:deltas layer-above) %) (:values layer))))))

(defn back-propagate
  [network expected rate]
  (let [layers (get-layers (output-deltas network expected))]
    (loop [layer       (last (butlast layers))
           layer-above (last layers)
           layers      (butlast layers)
           all-layers  [layer-above]]
      (if (nil? layer)
        {:inputs (last all-layers) :hidden (reverse (rest (butlast all-layers))) :outputs (first all-layers)}
        (let [updated-layer (-> layer
                             (layer-deltas layer-above)
                             (adjust-layer-weights layer-above rate))]

        (recur (last (butlast layers)) updated-layer (butlast layers) (conj all-layers updated-layer)))))))

(defn train
  [network data times rate]
  (loop [i   0
         net network]
    (if (< i times)
      (recur (inc i) (reduce (fn [network sample] (-> network
                                                      (forward-propagate (:inputs sample))
                                                      (back-propagate (:outputs sample) rate))) net data))
      net)))

(def xor-data [{:inputs [1 0] :outputs [1]}
           {:inputs [0 1] :outputs [1]}
           {:inputs [1 1] :outputs [0]}
           {:inputs [0 0] :outputs [0]}])

(-> (generate-network :inputs 2 :hidden [2] :outputs 1)
    (train xor-data 500 0.2)
    (forward-propagate [1 0]))
