package system.view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import system.controller.ClientController;

public class ButtonHandler implements ActionListener {
   private ClientController con;

   public ButtonHandler(ClientController con) {
      this.con = con;
   }

   @Override
   public void actionPerformed(ActionEvent e) {  // to samo co w ClientController !!!!
      if (!(e.getSource() instanceof JButton))
         return;

      String action = ((JButton) e.getSource()).getText();
      int index = action.indexOf(" ");
      if (index > -1) {
         action = action.substring(0, index);
      }
      // System.out.println(action);
      try {
         con.execute(action);
      }
      catch (TransformerException | ParserConfigurationException | IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
   }
}