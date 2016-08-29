package system.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import system.domain.mediator.ClientModelManager;
import system.domain.model.PublicMessage;
import system.view.ClientView;

public class ClientController
   {

      private ClientView cview;
      private ClientModelManager cmodel;
      private PublicMessage message;

      public ClientController(ClientView view, ClientModelManager model) {
         this.cview = view;
         this.cmodel = model;
         new PublicMessage("Client", "");
         this.cmodel.addObserver(view);

      }

      public void execute(String action) throws TransformerException, ParserConfigurationException, IOException {

         String act = action.toLowerCase();

         switch (act)

            {
               case "login":
                  message = new PublicMessage(0, "Client", "login");
                  cmodel.addCommand(message);
                  cview.show("\n" + message.toString());
                  break;

               case "logout":
                  message = new PublicMessage(1, "Client", "logout");
                  cmodel.addCommand(message);
                  cview.show("\n" + message.toString());
                  break;

               case "getlist":
                  message = new PublicMessage(2, "Client", "getlist");
                  cmodel.addCommand(message);
                  cview.show("\n" + message.toString());
                  break;

               case "barcode":
                  String input = cview.getBarcode();
                  if (input == null) {
                     String msg2 = "\nBarcode empty, or wrong format; try again..";
                     cview.show(msg2.toString());
                     break;
                  }
                  else {
                     message = new PublicMessage(3, "Client", input);
                     cmodel.addCommand(message);
                     cview.show("\n" + message.toString());
                     break;
                  }

               case "search":
                  message = new PublicMessage(4, "Client", "search");
                  cmodel.addCommand(message);
                  cview.show("\n" + message.toString());
                  break;

               case "remove":
                  message = new PublicMessage(5, "Client", "remove");
                  cmodel.addCommand(message);
                  cview.show("\n" + message.toString());
                  break;

               case "quit":
                  System.exit(0);
                  break;
            }
      }
   }
