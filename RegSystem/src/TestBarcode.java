
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import system.domain.mediator.ServerModelManager;
import system.domain.model.Barcode;
import utility.SimpleDataProvider;

public class TestBarcode
   {

      public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException {

         new SimpleDataProvider();

         ServerModelManager smm = new ServerModelManager();

         String b2 = new String("0001400180012");
         Barcode b3 = smm.parseBarcode(b2);
         System.out.println(b3);

         b2 = new String("0000701100100");
         b3 = smm.parseBarcode(b2);
         System.out.println(b3);

         b2 = new String("0001901301001");
         b3 = smm.parseBarcode(b2);
         System.out.println(b3);

         SimpleDataProvider.getCompanyList();
         
      }

   }
