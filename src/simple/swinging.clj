(import [javax.swing JFrame JButton])
(def frame (JFrame. "Hello Frame"))

(.setSize frame 200 200)
(.setVisible frame true)
(.revalidate  frame)

(def button (JButton. "Hello"))
(.add frame button)

(import 'javax.swing.JOptionPane)
(defn say-hello []
  (JOptionPane/showMessageDialog
    nil "Hello, World!" "Greeting"
    JOptionPane/INFORMATION_MESSAGE))

(import 'java.awt.event.ActionListener)
(def act (proxy [ActionListener] []
           (actionPerformed [event] (say-hello))))
(.addActionListener button act)
