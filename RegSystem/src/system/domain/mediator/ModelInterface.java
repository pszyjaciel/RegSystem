package system.domain.mediator;

import java.io.IOException;

import system.domain.model.AbstractMessage;

public interface ModelInterface {
   public void addCommand(AbstractMessage message) throws IOException;

}
