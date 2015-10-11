(def string-sample "(do (Thread/sleep 5000)  (println \"done\") 1)")

; execute immediately
(def settings (load-string string-sample))
(println settings)

; execute in the background, returns immediately
(def settings (future (load-string string-sample )))
(println @settings)

; evaluated when calling it
(def settings (delay (load-string string-sample)))
(println @settings)

