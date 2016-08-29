package system.domain.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CompanyList extends DefaultHandler
   {

      ArrayList<Company> cl = new ArrayList<>();
      private Company company;
      String value;

      public CompanyList() {
         this.cl = new ArrayList<>(0);
      }

      public Company getCompanyByIndex(int index) {
         for (int i = 0; i < getSize(); i++) {
            if (cl.get(i).getIndex() == index) {
               return cl.get(i);
            }
         }
         //      System.out.println(index + " not found.");
         return null;
      }

      public Company getCompanyByName(String name) {
         for (int i = 0; i < getSize(); i++) {
            if (cl.get(i).getName().equals(name)) {
               return cl.get(i);
            }
         }
         //      System.out.println(name + " not found.");
         return null;
      }

      public void addCompany(Company company) {
         if (!cl.contains(company))
            cl.add(company);
         else
            System.out.println("Cannot add, because the company exists");
      }

      public void removeCompany(int id, String name) {
         // TODO Auto-generated method stub
      }

      public int getSize() {
         return cl.size();
      }

      public String toString() {
         String s = "List of companies: ";
         for (int i = 0; i < getSize(); i++) {
            s += "\n" + cl.get(i);
         }
         return s;
      }

      public Company readCompanyFromXML(String xmlFileName) {
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
         return company;
      }

      // metoda wywolywana przy napotkaniu taga otwierajacego
      @Override
      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

         if (qName.equalsIgnoreCase("company")) { // gdy znajdzie taga <guest>
            company = new Company(0, "");
            attributes.getValue("id");
         }
      }

      // metoda wywolywana przy napotkaniu taga zamykajacego
      @Override
      public void endElement(String uri, String localName, String qName) throws SAXException {
         if (qName.equalsIgnoreCase("index")) {
            company.setIndex(Integer.parseInt(value));
         }
         if (qName.equalsIgnoreCase("name")) {
            company.setName(value);
         }
      }

      @Override
      public void characters(char[] ch, int start, int length) throws SAXException {
         value = new String(ch, start, length);
      }

   }
