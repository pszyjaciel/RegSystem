import system.controller.ServerController;
import system.domain.mediator.ServerConnectionThread;
import system.domain.mediator.ServerModelManager;
import system.view.ServerView;

public class MyServer
   {
      public static void main(String argv[]) throws Exception {
         ServerModelManager smodel = new ServerModelManager();
         ServerView sview = new ServerView();
         smodel.addObserver(sview);
         new ServerConnectionThread(smodel);
         ServerController controller = new ServerController(smodel, sview);
         sview.start(controller);
      }
   }
