package system.domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

import system.domain.model.AbstractMessage;
import system.domain.model.Barcode;
//import system.domain.model.BarcodeList;
import system.domain.model.PublicMessage;
import system.domain.model.SimpleDate;

public class ServerCommunicationThread extends Thread implements Observer
   {
      private ObjectInputStream inFromClient;
      private ObjectOutputStream outToClient;
      private ServerModelManager smodel;
      private String client;
      private String clientIP;
      private int number;

      public ServerCommunicationThread(Socket clientSocket, ServerModelManager model) throws IOException {
         this.smodel = model;

         outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
         outToClient.flush();
         inFromClient = new ObjectInputStream(clientSocket.getInputStream());

         clientIP = clientSocket.getInetAddress().getHostName();
         if (!clientIP.equals(clientSocket.getInetAddress().getHostAddress()))
            clientIP += " / " + clientSocket.getInetAddress().getHostAddress();
         if (clientIP.equals("127.0.0.1"))
            clientIP = "localhost";

         System.out.println(clientIP);
         this.start();
      }

      public void run() {

         System.out.println("ServerCommunicationThread was started successfully.");
         smodel.addObserver(this); // dodaje siebie do listy observerow
         System.out.println("Observers: " + smodel.countObservers());
         number = smodel.countObservers();

         try {

            Thread.sleep(1000);

            while (true) {
               Object inObject = inFromClient.readObject();
               AbstractMessage message = (AbstractMessage) inObject;
               AbstractMessage pl;

               System.out.println("message.getBody(): " + message.getBody());

               switch (message.getId())
                  {
                     case 0: // login
                        client = message.getFrom() + " (" + clientIP + ")";
                        pl = new PublicMessage(message.getId(), "Server: ", "[" + number + "] " + client
                              + " logged on at " + new SimpleDate().getShortTime());
                        smodel.addCommand(pl); // przy dodaniu komendy do listy, automatycznie info do update i send()
                        break;

                     case 1: // logout
                        pl = new PublicMessage(message.getId(), "Server: ", "[" + number + "] " + client
                              + " logged off at " + new SimpleDate().getShortTime());
                        smodel.addCommand(pl);
                        System.out.println("i farvela siem rozlandczam...");
                        logout();

                        break;

                     case 2: // getlist

                        // pl = new PublicMessage(message.getId(), "Server: ", blist.toString());
                        pl = new PublicMessage(message.getId(), "Server: ", smodel.getBarcodeList().toString());

                        smodel.addCommand(pl);
                        break;

                     case 3: // barcode
                        Barcode bcode = smodel.parseBarcode(message.getBody());
                        smodel.addBarcode(bcode);
                        break;

                     case 4: // search
                        pl = new PublicMessage(message.getId(), "from Server, case search: ", "command_to_client");
                        smodel.addCommand(pl);
                        break;

                     case 5: // remove
                        pl = new PublicMessage(message.getId(), "from Server, case remove: ", "command_to_client");
                        smodel.addCommand(pl);
                        break;

                     case 6: // exit
                        pl = new PublicMessage(message.getId(), "from Server, case ...: ", "command_to_client");
                        smodel.addCommand(pl);
                        break;
                  }
            }
         }
         catch (SocketException e) {
            // ok
         }
         catch (Exception e) {
            e.printStackTrace();
         }
      }

      // update automatycznie wysyla komende, a view ja pokazuje przy addCommand/setChanged()
      // zauwazy tez gdy jakas zmiana w ClientModelManager(Observable)
      @Override
      public void update(Observable arg0, Object arg1) {
         try {
            AbstractMessage message = (AbstractMessage) arg1;
            outToClient.writeObject(message);
            outToClient.flush();
         }
         catch (Exception e) {
            // no client connection
            System.out.println("Exception for client broadcast to " + client + " - " + e.getMessage());
            smodel.deleteObserver(this);
            AbstractMessage pl = new PublicMessage(client, "Server> " + client + " disconnected "
                  + new SimpleDate().getTime());
            System.out.println(pl);
            smodel.addCommand(pl);
         }
      }

      public void logout() throws IOException {
         outToClient.close();
         inFromClient.close();
         // connectionSocket.close();
      }
   }
