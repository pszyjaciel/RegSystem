package utility;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.rmi.RemoteException;
import javax.xml.transform.TransformerException;

import system.domain.model.Barcode;
import system.domain.model.BarcodeList;
import system.domain.model.Company;
import system.domain.model.CompanyList;
import system.domain.model.Product;
import system.domain.model.ProductList;

public class SimpleDataProvider
   {
      static CompanyList cl = new CompanyList();
      static ProductList pl = new ProductList();
      static BarcodeList bl = new BarcodeList();
      private static Company company;
      private static Product product;

      public static CompanyList getCompanyList() throws TransformerException, ParserConfigurationException {

         File actFile = new File("");
         String folder = actFile.getAbsolutePath();
         folder += "\\resources\\companies\\";

         File file = new File(folder);
         String[] dir = file.list();
         if (dir == null) {
            System.out.println("Create a resource folder first..");
            return cl;
         }
         for (int i = 0; i < dir.length - 1; i++) {
            company = cl.readCompanyFromXML(folder + dir[i]);
            cl.addCompany(company);
         }
         return cl;
      }

      public static ProductList getProductList() throws RemoteException, TransformerException,
            ParserConfigurationException
      {

         File actFile = new File("");
         String folder = actFile.getAbsolutePath();
         folder += "\\resources\\products\\";
         File file = new File(folder);

         String[] dir = file.list();
         if (dir == null) {
            System.out.println("Create a resource folder first..");
            return pl;
         }
         for (int i = 0; i < dir.length - 1; i++) {
            product = pl.readProductFromXML(folder + dir[i]);
            pl.addProduct(product);
         }
         return pl;
      }

      public static BarcodeList getBarcodeList() throws RemoteException, TransformerException,
            ParserConfigurationException
      {
         Product p = pl.getProductByIndex(1);
         Company c = cl.getCompanyByIndex(1);
         Barcode b = new Barcode(1, p, c, 100);
         bl.addBarcode(b);

         p = pl.getProductByIndex(2);
         c = cl.getCompanyByIndex(2);
         b = new Barcode(2, p, c, 200);
         bl.addBarcode(b);

         p = pl.getProductByIndex(3);
         c = cl.getCompanyByIndex(3);
         b = new Barcode(3, p, c, 300);
         bl.addBarcode(b);

         p = pl.getProductByIndex(4);
         c = cl.getCompanyByIndex(4);
         b = new Barcode(4, p, c, 400);
         bl.addBarcode(b);

         p = pl.getProductByIndex(5);
         c = cl.getCompanyByIndex(5);
         b = new Barcode(5, p, c, 50);
         bl.addBarcode(b);

         p = pl.getProductByIndex(6);
         c = cl.getCompanyByIndex(6);
         b = new Barcode(6, p, c, 40);
         bl.addBarcode(b);

         p = pl.getProductByIndex(12);
         c = cl.getCompanyByIndex(5);
         b = new Barcode(7, p, c, 110);
         bl.addBarcode(b);

         p = pl.getProductByIndex(13);
         c = cl.getCompanyByIndex(4);
         b = new Barcode(8, p, c, 140);
         bl.addBarcode(b);

         p = pl.getProductByIndex(14);
         c = cl.getCompanyByIndex(3);
         b = new Barcode(9, p, c, 20);
         bl.addBarcode(b);

         p = pl.getProductByIndex(12);
         c = cl.getCompanyByIndex(2);
         b = new Barcode(10, p, c, 25);
         bl.addBarcode(b);

         // barcode = bl.readBarcodeFromXML("resources/barcodes/barcode4.xml");
         // bl.addBarcode(barcode);

         return bl;
      }

   }
