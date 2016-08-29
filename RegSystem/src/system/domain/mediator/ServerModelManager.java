package system.domain.mediator;

import java.util.Observable;
import java.util.regex.Pattern;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import utility.SimpleDataProvider;

import system.domain.model.AbstractMessage;
import system.domain.model.Barcode;
import system.domain.model.BarcodeList;
import system.domain.model.Company;
import system.domain.model.CompanyList;
import system.domain.model.MessageList;
import system.domain.model.Product;
import system.domain.model.ProductList;

public class ServerModelManager extends Observable implements ModelInterface {
   private BarcodeList bl;
   private CompanyList cl;
   private ProductList pl;
   private MessageList ml;

   public ServerModelManager() throws TransformerException, ParserConfigurationException, IOException {
      ml = new MessageList();
      cl = SimpleDataProvider.getCompanyList();
      pl = SimpleDataProvider.getProductList();
      bl = SimpleDataProvider.getBarcodeList();
   }

   public void addCommand(AbstractMessage message) {
      ml.add(message);
      super.setChanged();
      super.notifyObservers(message);
   }

   public BarcodeList getBarcodeList() {
      return bl;
   }

   public void setBarcodeList(BarcodeList bl) {
      this.bl = bl;
   }

   public CompanyList getCompanyList() {
      return cl;
   }

   public void setCompanyList(CompanyList cl) {
      this.cl = cl;
   }

   public ProductList getProductList() {
      return pl;
   }

   public void setProductList(ProductList pl) {
      this.pl = pl;
   }

   public void addBarcode(Barcode barcode) {
      bl.addBarcode(barcode);
   }

   public void removeBarcode(int index) {
      bl.removeBarcode(index);
   }

   public void addProduct(Product product) {
      pl.addProduct(product);
   }

   public void removeProduct(int product, String type, String name) {
      pl.removeProduct(product, type, name);
   }

   public void addCompany(Company company) {
      cl.addCompany(company);
   }

   public void removeCompany(int id, String name) {
      cl.removeCompany(id, name);
   }

   public int getNumberOfBarcodes() {
      return bl.getSize();
   }

   public int getNumberOfCompanies() {
      return cl.getSize();
   }

   public int getNumberOfProducts() {
      return pl.getSize();
   }

   public Barcode parseBarcode(String input) {
      if (Pattern.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d", input)) {
         // if (isValidCRC(input) == false) {
         // return null;
         // }

         int idProduct = Integer.parseInt((String) input.substring(0, 5));
         int idCompany = Integer.parseInt((String) input.substring(5, 8));
         int amount = Integer.parseInt((String) input.substring(8, 13));

         Company company = cl.getCompanyByIndex(idCompany);
         Product product = pl.getProductByIndex(idProduct);
         Barcode barcode = new Barcode(bl.getSize() + 1, product, company, amount);

         return barcode;
      }
      return null;
   }
}
