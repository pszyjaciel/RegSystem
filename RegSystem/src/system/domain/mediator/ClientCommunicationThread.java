// $date
package system.domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

import system.domain.model.AbstractMessage;
import system.domain.model.PublicMessage;
import system.domain.model.SimpleDate;

public class ClientCommunicationThread extends Thread implements Observer
   {
      private static final String HOSTNAME = "127.0.0.1";
      // private static final String HOSTNAME = "192.168.178.36";

      private static final int PORT = 14305;

      private ClientModelManager cmodel;
      private ObjectInputStream inFromServer; // to samo trzeba uzyc w cliencie inaczej InvalidStreamHeader
      private ObjectOutputStream outToServer;
      private Socket connectionSocket;
      private String serverIP;
      private int number;
      private String server;

      public ClientCommunicationThread(ClientModelManager model) throws IOException { // parametry ok
         connectionSocket = new Socket(HOSTNAME, PORT);

         this.cmodel = model;

         outToServer = new ObjectOutputStream(connectionSocket.getOutputStream()); // will freeze if missing flush()
         outToServer.flush();
         inFromServer = new ObjectInputStream(connectionSocket.getInputStream());

         serverIP = connectionSocket.getInetAddress().getHostName();
         if (!serverIP.equals(connectionSocket.getInetAddress().getHostAddress()))
            serverIP += " / " + connectionSocket.getInetAddress().getHostAddress();
         if (serverIP.equals("127.0.0.1"))
            serverIP = "localhost";

         this.start();
      }

      // klient ma czytac od servera jakies wartosci list itp, a nie logowac go, hehe
      // klient ma za zadanie wyswietlic wszystko co przyjdzie od servera i kropka.

      // Observers are:
      // - ClientCommunicationThread
      // - ServerCommunicationThread
      // - ClientView
      // - ServerView

      public void run() {
         System.out.println("ClientCommunicationThread was started successfully.");

         try {
            // wszystko co sie zmieni w cmodelu (observable) ma byc zanotowane do observera (cview)
            cmodel.addObserver(this);
            System.out.println("Observers: " + cmodel.countObservers());
            number = cmodel.countObservers();

            Thread.sleep(1000);

            while (true) {
               Object inObject = inFromServer.readObject();
               AbstractMessage message = (AbstractMessage) inObject;
               AbstractMessage pl;

               // co przyszlo od servera to wyswietl i bez wyjatku

               pl = new PublicMessage(message.getId(), "Client: ", message.getBody());
               cmodel.addCommand(pl);
               break;
            }
         }

         catch (SocketException e) {
            // ok
         }
         catch (Exception e) {
            e.printStackTrace();
         }
      }

      public void login() throws IOException {
         outToServer.writeUTF("EHLO");
      }

      public void logout() throws IOException {
         outToServer.close();
         inFromServer.close();
         connectionSocket.close();
      }

      // metoda zauwazy kazda zmiane w ServerModelManager(Observable)
      @Override
      public void update(Observable obs, Object arg1) {
         try {
            System.out.println("ClientCommunicationThread.update: " + arg1);
            sendCommand((AbstractMessage) arg1); // jak nic nie wysle to server nic nie dostanie, proste
         }
         catch (Exception e) {
            // no client connection
            System.out.println("Exception for client broadcast to " + server + " - " + e.getMessage());
            cmodel.deleteObserver(this);
            AbstractMessage pl = new PublicMessage(server, "Client> " + server + " disconnected "
                  + new SimpleDate().getTime());
            System.out.println(pl);
            cmodel.addCommand(pl);
         }
      }

      public void sendCommand(AbstractMessage command) throws IOException {
         outToServer.writeObject(command);
         outToServer.flush();
      }

   }
