package system.domain.mediator;

import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Observable;

import system.domain.model.AbstractMessage;

public class ClientModelManager extends Observable {

   // private MessageList list;

   // Class ClientModelManager delegates the work to the proxy, i.e.
   // o method add(…) calls the method with the same name in class Proxy (which sends this to the server)

   // Observable are:
   // - ClientModelManager
   // - ServerModelManager

   public ClientModelManager() throws UnknownHostException, IOException {
   }

   public void addCommand(AbstractMessage message) {
      super.setChanged();
      super.notifyObservers(message);

      // jak naciskam przycisk w GUI to ClientController poprzez addCommand informuje Observery
      // ze sie zmienila MessageList

      // w tym samym czasie Observery poprzez metode update w swoich klasach dostana to sama komende, wtedy:
      // ClientCommunicationThread nic nie powinien zrobic
      // ServerCommunicationThread pobierze ta komende i wykona
      // ClientView wyswietli ta komende
      // SystemView tez wyswietli ta komende

   }

}