package system.domain.mediator;

import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;

public class ServerConnectionThread extends Thread
   {
      private static final int PORT = 14305;
      private ServerModelManager smodel;
      private ServerSocket ssocket;

      public ServerConnectionThread(ServerModelManager model) throws IOException {
         ssocket = new ServerSocket(PORT);
         this.smodel = model;
         this.start();
      }

      public void run() {
         System.out.println("ServerConnectionThread was started successfully.");
         try {
            // create welcoming socket at specified port

            Thread.sleep(800);

            while (true) {
               System.out.println("Waiting for a client...");

               // blokada soketa az do czasu nawiazania polaczenia
               Socket clientSocket = ssocket.accept();

               // Start a thread with the client communication
               new ServerCommunicationThread(clientSocket, smodel);
            }
         }
         catch (Exception e) {
            System.out.println("Exception in connection to client: " + e.getMessage());
            // e.printStackTrace();
         }
      }
   }
