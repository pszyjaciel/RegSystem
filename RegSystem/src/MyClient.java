import java.io.IOException;
import system.controller.ClientController;

import system.domain.mediator.ClientCommunicationThread;
import system.domain.mediator.ClientModelManager;
import system.view.ClientView;

public class MyClient
   {
      public static void main(String[] args) throws IOException {

         ClientView cview = new ClientView();
         ClientModelManager cmodel = new ClientModelManager();
//         cmodel.addObserver(cview);
         new ClientCommunicationThread(cmodel);
         ClientController controller = new ClientController(cview, cmodel);
         cview.start(controller);

      }
   }

// 14Thu_Exercises-SDJI2-X-S13.pdf

