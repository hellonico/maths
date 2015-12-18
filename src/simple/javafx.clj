(import 'javafx.application.Application)
(import 'javafx.scene.Scene)
(import 'javafx.stage.Stage)
(import 'javafx.scene.layout.VBox)
(import 'com.sun.javafx.application.LauncherImpl)

(def root (proxy [Application] []
            (start [#^Stage main-stage]
                   (.setScene main-stage (VBox.))
                   (.show main-stage))))

(LauncherImpl/launchApplication
 (.getClass root)
 (into-array String ["hello"]))
