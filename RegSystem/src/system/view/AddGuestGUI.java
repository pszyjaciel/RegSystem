package system.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.JButton;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddGuestGUI extends JDialog {
   private JPanel mainPanel;

   private JButton showTextDialogOkBtn;
   private JButton showTextDialogCancelBtn;

   private String showTextDialogOkBtnName = "showTextDialogOkBtn";
   private String showTextDialogCancelBtnName = "showTextDialogCancelBtn";

   private JTextField textField1;
   private JTextField textField2;
   private JTextField textField3;
   private JTextField textField4;
   private JTextField textField5;
   private JTextField textField6;

   private JFormattedTextField ftf1;

   // constructor showTextDialog()
   public AddGuestGUI(String textToShow, ClientView owner, boolean modality) {
      super(owner, modality);
      setTitle("Add a new guest");

      setPreferredSize(new Dimension(380, 225));
      setLocationRelativeTo(null); // center of the screen
      setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
      setResizable(false);

      // create the panel that will be the container
      mainPanel = new JPanel();

      // create the buttons
      showTextDialogOkBtn = new JButton("OK");
      showTextDialogOkBtn.setMnemonic(KeyEvent.VK_O);
      showTextDialogCancelBtn = new JButton("Cancel");
      showTextDialogCancelBtn.setMnemonic(KeyEvent.VK_C);

      // create the text field
      textField1 = new JTextField(textToShow, 34);
      textField1.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      textField2 = new JTextField(textToShow, 34);
      textField2.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      Date value1 = java.util.Calendar.getInstance().getTime();
      ftf1 = new JFormattedTextField(value1);
      ftf1.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      textField3 = new JTextField(textToShow, 34);
      textField3.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      textField4 = new JTextField(textToShow, 34);
      textField4.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      textField5 = new JTextField(textToShow, 34);
      textField5.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      textField6 = new JTextField(textToShow, 34);
      textField6.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));

      // add text field
      JLabel firstName = new JLabel("First name: ");
      firstName.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(firstName);
      mainPanel.add(textField1);

      JLabel lastName = new JLabel("Last name:  ");
      lastName.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(lastName);
      mainPanel.add(textField2);

      JLabel birthday = new JLabel("Birthday:                           ");
      birthday.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(birthday);
      mainPanel.add(ftf1);

      JLabel phone = new JLabel("Phone:      ");
      phone.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(phone);
      mainPanel.add(textField3);
      
      JLabel address = new JLabel("Address:    ");
      address.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(address);
      mainPanel.add(textField4);
      
      JLabel nationality = new JLabel("Nationality:");
      nationality.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(nationality);
      mainPanel.add(textField5);

      JLabel cpr = new JLabel("CPR/ID:     ");
      cpr.setFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 12));
      mainPanel.add(cpr);
      mainPanel.add(textField6);

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
      String firstName = new String(textField1.getText());
      return firstName;
   }

   public String getTextField2() {
      String lastName = new String(textField2.getText());
      return lastName;
   }

   public String getFormatedField1() {
      String birthday = ftf1.getText();
      return birthday;
   }

   public int getTextField3() {
      try {
         int phone = Integer.parseInt(textField3.getText());
         return phone;
      }
      catch (NumberFormatException e) {
         return 0;
      }
   }

   public String getTextField4() {
      String address = new String(textField4.getText());
      return address;
   }

   public String getTextField5() {
      String nationality = new String(textField5.getText());
      return nationality;
   }

   public int getTextField6() {
      try {
         int cpr = Integer.parseInt(textField6.getText());
         return cpr;
      }
      catch (NumberFormatException e) {
         return 0;
      }
   }
}
