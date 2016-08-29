package system.domain.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProductList extends DefaultHandler
   {

      ArrayList<Product> pl = new ArrayList<>();
      private Product product;
      String value;

      public ProductList() {
         this.pl = new ArrayList<>(0);
      }

      public Product getProductByIndex(int index) {
         for (int i = 0; i < getSize(); i++) {
            if (pl.get(i).getIndex() == index) {
               return pl.get(i);
            }
         }
         //      System.out.println(index + " not found.");
         return null;
      }

      public void addProduct(Product product) {
         if (!pl.contains(product))
            pl.add(product);
         else
            System.out.println("Cannot add, because the product exists");
      }

      public void removeProduct(int product, String type, String name) {
         // TODO Auto-generated method stub

      }

      public int getSize() {
         return pl.size();
      }

      public String toString() {
         String s = "List of products: ";
         for (int i = 0; i < getSize(); i++) {
            s += "\n" + pl.get(i);
         }
         return s;
      }

      public Product readProductFromXML(String xmlFileName) {
         SAXParserFactory factory = SAXParserFactory.newInstance();
         try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlFileName, this);
            // System.out.println(company);
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
         return product;
      }

      // metoda wywolywana przy napotkaniu taga otwierajacego
      @Override
      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

         if (qName.equalsIgnoreCase("product")) { // gdy znajdzie taga <guest>
            product = new Product(0, "", "");
            attributes.getValue("id");
         }
      }

      // metoda wywolywana przy napotkaniu taga zamykajacego
      @Override
      public void endElement(String uri, String localName, String qName) throws SAXException {
         if (qName.equalsIgnoreCase("index")) {
            product.setIndex(Integer.parseInt(value));
         }
         if (qName.equalsIgnoreCase("type")) {
            product.setType(value);
         }
         if (qName.equalsIgnoreCase("name")) {
            product.setName(value);
         }
      }

      @Override
      public void characters(char[] ch, int start, int length) throws SAXException {
         value = new String(ch, start, length);
      }

   }
