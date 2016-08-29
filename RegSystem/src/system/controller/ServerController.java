package system.controller;

import system.domain.mediator.ServerModelManager;
import system.view.ServerView;

public class ServerController {
   private ServerModelManager smodel;
   private ServerView sview;

   public ServerController(ServerModelManager model, ServerView view) {
      this.smodel = model;
      this.sview = view;
      this.smodel.addObserver(view);
   }

   public void execute(String action) {
      switch (action.toLowerCase())

      {
         case "Quit":
            sview.show(action.toString());

            System.exit(0);
      }
   }

   public void show(String value) {
      sview.show(value);
   }
}