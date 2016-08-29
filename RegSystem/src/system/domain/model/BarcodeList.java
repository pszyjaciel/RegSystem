package system.domain.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BarcodeList extends DefaultHandler implements Serializable
   {
      ArrayList<Barcode> bl = new ArrayList<>();
      private Barcode barcode;
      private Product product;
      private Company company;
      String value;

      public BarcodeList() {
         this.bl = new ArrayList<>();
      }

      public Barcode getBarcode() {
         System.out.println(barcode);
         return barcode;
      }

      public void setBarcode(Barcode barcode) {
         this.barcode = barcode;
      }

      public Product getProduct() {
         return product;
      }

      public void setProduct(Product product) {
         this.product = product;
      }

      public Company getCompany() {
         return company;
      }

      public void setCompany(Company company) {
         this.company = company;
      }

      public Barcode getBarcode(int index) {
         for (int i = 0; i < getSize(); i++) {
            if (bl.get(i).getIndex() == index) {
               return bl.get(i);
            }
         }
         // System.out.println(index + " not found.");
         return null;
      }

      public Barcode getBarcodeByProduct(Product product) {
         for (int i = 0; i < getSize(); i++) {
            if (bl.get(i).getProduct() == product) {
               return bl.get(i);
            }
         }
         // System.out.println(product + " not found.");
         return null;
      }

      ArrayList<Barcode> found = new ArrayList<>();

      public ArrayList<Barcode> getBarcodeByType(String type) {
         for (int i = 0; i < getSize(); i++) {
            if (bl.get(i).getProduct().getType().equals(type)) {
               found.add(bl.get(i));
            }
         }
         return found;
      }

      public Barcode getBarcode(Company company) {
         for (int i = 0; i < getSize(); i++) {
            if (bl.get(i).getCompany() == company) {
               return bl.get(i);
            }
         }
         // System.out.println(company + " not found.");
         return null;
      }

      public void removeBarcode(int index) {
         for (int i = 0; i < getSize(); i++) {
            if (bl.get(i).getIndex() == index)
               bl.remove(i); // a co jesli z jednej firmy przyjdzie 5 pudel o takiej saamej wadze....?
         }
      }

      public void addBarcode(Barcode barcode) {
         if (!bl.contains(barcode)) // SPRAWDZA CZY ISTNIEJE BARCODE ALE CO GDY JEST 5 TAKICH SAMYCH BARKODOW ? 
                                       // BO JEST 5 TAKICH SAMYCH PUDEL?????
            bl.add(barcode);
         else
            System.out.println("Cannot add, because the barcode exists");
      }

      public int getSize() {
         return bl.size();
      }

      public String toString() {
         String s = "List of Barcodes: ";
         for (int i = 0; i < getSize(); i++) {
            s += "\n" + bl.get(i);
         }
         return s;
      }

      public Iterator createIterator() {
         return bl.iterator();
      }

      public Barcode readBarcodeFromXML(String xmlFileName) {
         SAXParserFactory factory = SAXParserFactory.newInstance();
         try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlFileName, this);
            // System.out.println("tutaj: " + barcode);
         }
         catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
         }
         catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
         }
         catch (IOException e) {
            System.out.println("IO error");
         }
         return new Barcode(barcode.getIndex(), product, company, barcode.getAmount());
      }

      // metoda wywolywana przy napotkaniu taga otwierajacego
      @Override
      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

         if (qName.equalsIgnoreCase("barcode")) { // gdy znajdzie taga <guest>
            barcode = new Barcode(0, null, null, 0);
            product = new Product(0, null, null);
            company = new Company(0, null);
            attributes.getValue("id");
         }
      }

      // metoda wywolywana przy napotkaniu taga zamykajacego
      @Override
      public void endElement(String uri, String localName, String qName) throws SAXException {
         if (qName.equalsIgnoreCase("index")) {
            barcode.setIndex(Integer.parseInt(value));
         }
         if (qName.equalsIgnoreCase("pid")) {
            product.setIndex(Integer.parseInt(value));
         }
         if (qName.equalsIgnoreCase("type")) {
            product.setType(value);
         }
         if (qName.equalsIgnoreCase("pname")) {
            product.setName(value);
         }
         if (qName.equalsIgnoreCase("cid")) {
            company.setIndex(Integer.parseInt(value));
         }
         if (qName.equalsIgnoreCase("cname")) {
            company.setName(value);
         }
         if (qName.equalsIgnoreCase("amount")) {
            barcode.setAmount(Integer.parseInt(value));
         }
      }

      @Override
      public void characters(char[] ch, int start, int length) throws SAXException {
         value = new String(ch, start, length);
         // System.out.println(value);
      }

   }