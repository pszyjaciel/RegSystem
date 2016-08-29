package system.domain.model;

import java.util.regex.Pattern;

public class Barcode
   {
      private Barcode barcode;
      private int index;
      private Product product;
      private Company company;
      private int amount;

      public Barcode(int index, Product product, Company company, int amount) {
         this.index = index;
         this.product = product;
         this.amount = amount;
         this.company = company;
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

      public int getAmount() {
         return amount;
      }

      public void setAmount(int amount) {
         this.amount = amount;
      }

      public int getIndex() {
         return index;
      }

      public void setIndex(int index) {
         this.index = index;
      }

      @Override
      public String toString() {
         return "Barcode [index=" + index + ", product=" + product + ", company=" + company + ", amount=" + amount
               + "]";
      }
   }
