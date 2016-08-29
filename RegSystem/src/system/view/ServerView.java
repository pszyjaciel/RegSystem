// 26-05-2013 - 17:16:38
package system.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import system.controller.ServerController;
import system.domain.model.AbstractMessage;

// o method update(…) notifies the observers (in this case the view). The method will be called from ClientReceiverThread every time there is a new message
// received from the server.

public class ServerView extends JFrame implements Observer, ActionListener
   {
      private ServerController scontroller;

      private JTextArea textArea;

      public ServerView() throws TransformerException, ParserConfigurationException, IOException {
         super("Server V10.7 by Group3");

         initializeWindow();
      }

      public void initializeWindow() {
         // Place text area on the frame
         textArea = new JTextArea();
         textArea.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
         textArea.setForeground(Color.WHITE);
         textArea.setLineWrap(true);
         textArea.setWrapStyleWord(true); // dzielenie wyrazow
         textArea.setBackground(Color.BLACK);
         textArea.setEditable(false);
         add(new JScrollPane(textArea), BorderLayout.CENTER);
         setSize(500, 380); // set frame size
         setLocationRelativeTo(null); // center of the screen
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setResizable(true);
      }

      public void start(ServerController controller) {
         setVisible(true);
      }

      public void show(String value) {
         // textArea.append(value);
         textArea.append(value);
         textArea.setCaretPosition(textArea.getDocument().getLength());
      }

      @Override
      public void update(Observable arg0, Object arg1) {
         AbstractMessage msg = (AbstractMessage) arg1;
         textArea.append("\n" + msg.getFrom() + " > " + msg.getBody());
         textArea.setCaretPosition(textArea.getDocument().getLength());
         //         textArea.append("\nClientView.update()");
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         scontroller.execute((((JTextComponent) e.getSource())).getText());
      }
   }