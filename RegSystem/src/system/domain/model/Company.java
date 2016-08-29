package system.domain.model;

public class Company
   {
      private int index;
      private String name;

      public Company(int id, String name) {
         this.index = id;
         this.name = name;
      }

      public int getIndex() {
         return index;
      }

      public void setIndex(int id) {
         index = id;
      }

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }

      @Override
      public String toString() {
         return "Company [index=" + index + ", name=" + name + "]";
      }
   }
