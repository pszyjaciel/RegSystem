package system.view;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddReservationGUI extends JDialog {
   private JPanel mainPanel;

   private JButton showTextDialogOkBtn;
   private JButton showTextDialogCancelBtn;

   private String showTextDialogOkBtnName = "showTextDialogOkBtn";
   private String showTextDialogCancelBtnName = "showTextDialogCancelBtn";

   private JTextField textField1;
   private JTextField textField2;
   private JTextField textField4;

   private JFormattedTextField ftf1;
   private JFormattedTextField ftf2;

   // constructor showTextDialog()
   public AddReservationGUI(String textToShow, ClientView owner, boolean modality) {
      super(owner, modality);
      setTitle("Create a reservation");

      setPreferredSize(new Dimension(380, 160));
      setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
      setResizable(false);

      // create the panel that will be the container
      mainPanel = new JPanel();

      // create the buttons
      showTextDialogOkBtn = new JButton("OK");
      showTextDialogCancelBtn = new JButton("Cancel");

      // create the text area
      textField1 = new JTextField(textToShow, 34);
      textField1.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      textField2 = new JTextField(textToShow, 34);
      textField2.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      // Create a formatted text field.
      Date value1 = java.util.Calendar.getInstance().getTime();
      ftf1 = new JFormattedTextField(value1);
      ftf1.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      Date value2 = java.util.Calendar.getInstance().getTime();
      ftf2 = new JFormattedTextField(value2);
      ftf2.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      textField4 = new JTextField(textToShow, 34);
      textField4.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      // add text field
      JLabel firstName = new JLabel("First name:");
      firstName.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(firstName);
      mainPanel.add(textField1);

      JLabel lastName = new JLabel("Last name: ");
      lastName.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(lastName);
      mainPanel.add(textField2);

      JLabel from = new JLabel("From:      ");
      from.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(from);
      mainPanel.add(ftf1);

      JLabel til = new JLabel("    Til:    ");
      til.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(til);
      mainPanel.add(ftf2);

      JLabel whichRoom = new JLabel("Room:      ");
      whichRoom.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(whichRoom);
      mainPanel.add(textField4);

      // add buttons
      mainPanel.add(showTextDialogOkBtn, BorderLayout.SOUTH);
      mainPanel.add(showTextDialogCancelBtn, BorderLayout.SOUTH);

      // add the components to the frame, specify placement, and arrange
      add(mainPanel);

      setLocationRelativeTo(owner);

      pack();

   } // end constructor showTextDialog()

   // method showTextActionListeners is called by the controller to add its
   // action listener to this class' buttons.
   public void showTextActionListeners(ActionListener showTextBl) {
      showTextDialogOkBtn.setActionCommand(showTextDialogOkBtnName);
      showTextDialogOkBtn.addActionListener(showTextBl);
      showTextDialogCancelBtn.setActionCommand(showTextDialogCancelBtnName);
      showTextDialogCancelBtn.addActionListener(showTextBl);
   }

   // getter getTextField() returns the string entered by the user
   public String getTextField1() {
      String text = new String(textField1.getText());
      return text;
   }

   public String getTextField2() {
      String text = new String(textField2.getText());
      return text;
   }

   public String getTextField3() {
      return ftf1.getText();
   }

   public String getTextField4() {
      return ftf2.getText();
   }

   public int getTextField5() {
      try {
         int roomNo = Integer.parseInt(textField4.getText());
         return roomNo;
      }
      catch (NumberFormatException e) {
         return 0;
      }
   }
}
