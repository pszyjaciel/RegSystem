//c:\eclipse\Workspaces\SDJI1Workspace\GUI\src\StudentGUI.java 
package system.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import system.controller.ClientController;
import system.domain.model.AbstractMessage;

public class ClientView extends JFrame implements Observer {
   private JTextArea textArea;
   private JButton[] buttons;

   public ClientView() {
      super("Client V10.7 by Group3");

      initialize();
      buttons = new JButton[7]; // po kazdym dodaniu batona powiekszyc licznik!
      createComponents();
      addComponentsToFrame();
   }

   private void createComponents() {
      buttons[0] = new JButton("Login");
      buttons[0].setMnemonic(KeyEvent.VK_A);
      buttons[0].setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      buttons[0].setForeground(Color.BLUE);
      // buttons[0].setBackground(Color.YELLOW);

      buttons[1] = new JButton("Logout");
      buttons[1].setMnemonic(KeyEvent.VK_O);
      buttons[1].setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      buttons[1].setForeground(Color.BLUE);

      buttons[2] = new JButton("GetList");
      buttons[2].setMnemonic(KeyEvent.VK_R);
      buttons[2].setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      buttons[2].setForeground(Color.BLUE);

      buttons[3] = new JButton("Barcode");
      buttons[3].setMnemonic(KeyEvent.VK_S);
      buttons[3].setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      buttons[3].setForeground(Color.BLUE);

      buttons[4] = new JButton("Search");
      buttons[4].setMnemonic(KeyEvent.VK_T);
      buttons[4].setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      buttons[4].setForeground(Color.BLUE);

      buttons[5] = new JButton("Remove");
      buttons[5].setMnemonic(KeyEvent.VK_G);
      buttons[5].setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      buttons[5].setForeground(Color.BLUE);

      buttons[6] = new JButton("Quit");
      buttons[6].setMnemonic(KeyEvent.VK_Q);
      buttons[6].setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      buttons[6].setForeground(Color.RED);

      textArea = new JTextArea();
      textArea.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      textArea.setForeground(Color.WHITE);
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true); // dzielenie wyrazow
      textArea.setBackground(Color.BLACK);
      textArea.setEditable(false);

   }

   public void start(ClientController controller) {
      {
         ButtonHandler listener = new ButtonHandler(controller);
         for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] != null)
               buttons[i].addActionListener(listener);
         }
         setVisible(true);
      }
   }

   private void initialize() {

      setSize(800, 380); // set frame size
      setLocationRelativeTo(null); // center of the screen
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);

   }

   private void addComponentsToFrame() {
      JPanel panelButtons = new JPanel(new GridLayout(buttons.length, 1));
      for (int i = 0; i < buttons.length; i++) {
         if (buttons[i] != null)
            panelButtons.add(buttons[i]);
      }
      add(new JScrollPane(textArea), BorderLayout.CENTER);
      add(panelButtons, BorderLayout.EAST);
   }

   public void show(String value) {
      textArea.append(value);
      textArea.setCaretPosition(textArea.getDocument().getLength());
   }

   public String getBarcode() {
      String input = JOptionPane.showInputDialog("Type a barcode");
      if (checkFormat(input) == true) // tu mozna sprawdzic format tego barkoda
         return input;
      else
         return null;
   }

   private boolean checkFormat(String input) {
      if (input.length() != 13 || input == null || input == "")
         return false;
      else
         return true;
   }

   @Override
   public void update(Observable arg0, Object arg1) {
      AbstractMessage msg = (AbstractMessage) arg1;
      textArea.append("\n" + msg.getFrom() + " > " + msg.getBody());
      // textArea.append("\nClientView.update()");
   }
}