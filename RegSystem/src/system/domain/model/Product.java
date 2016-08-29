package system.domain.model;

public class Product
   {

      private int index;
      private String type;
      private String name;

      public Product(int id, String type, String name) {
         index = id;
         this.type = type;
         this.name = name;
      }

      public int getIndex() {
         return index;
      }

      public void setIndex(int id) {
         index = id;
      }

      public String getType() {
         return type;
      }

      public void setType(String type) {
         this.type = type;
      }

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }

      @Override
      public String toString() {
         return "Product [index=" + index + ", type=" + type + ", name=" + name + "]";
      }

      public Product getProduct(int idProduct) {
         setIndex(101);
         setType("typ_produktu");
         setName("nazwa_dostafcy");
         return new Product(getIndex(), getType(), getName());
      }
   }
