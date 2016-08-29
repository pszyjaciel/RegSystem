package system.domain.model;

import java.util.Calendar;

public class MyDate
   {
      private int day;
      private int month;
      private int year;

      /**
       * This constructor initializes the day, month and year.
       */

      public MyDate(int day, int month, int year) {
         this.day = day;
         this.month = month;
         this.year = year;
      }

      /**
       * This method calculate the time in miliseconds
       */
      public long getTimeInMilisec() {
         Calendar now = Calendar.getInstance();
         long milisec = now.getTimeInMillis();
         return milisec; // zwraca msec od poczatku wieku
      }

      public int days;

      /**
       * This method gets the current date and returns as an integer in current
       * millenium.
       */
      public int dateNow() {
         Calendar now = Calendar.getInstance();
         int day = now.get(Calendar.DAY_OF_MONTH);
         int month = now.get(Calendar.MONTH) + 1;
         int year = now.get(Calendar.YEAR);

         days = ((52 * 7) * (year - 1)) + (month * 30) + day;
         if (isLeapYear() == true)
            days = days - 4;

         return days;
      }

      /**
       * This method converts the date object into days in the current milenium
       */

      public int toDays(MyDate date) {
//         System.out.println("MyDate: " + date.year + "/" + date.month + "/" + date.day);

         int days = ((52 * 7) * (date.year)) + (date.month * 31) + date.day;
         if (isLeapYear() == true)
            days = days - 4;

         return days;
      }

      public void set(int day, int month, int year) // constructor
      {
         if (year < 0) // zainicjalizuj rok
         {
            year = -year;
         }
         this.year = year;

         if (month < 0) // zainicjalizuj miesiac
         {
            month = 1;
         }
         else if (month > 12) {
            month = 12;
         }

         // String miesiac = getMonthForInt(month);
         this.month = month;

         if (day < 1) // zainicjalizuj dzien
         {
            day = 1;
         }
         else if (day > numberOfDaysInMonth()) {
            day = numberOfDaysInMonth();
         }
         this.day = day;

      }

      /**
       * This method checks if the date is valid, the year should not be less
       * than 0, the month should not be less than 0 and bigger than 12.
       */
      public boolean isValid() {

         if (year < 0) // zainicjalizuj rok
         {
            return false;
         }

         if (month < 0 || month > 12) {
            return false;
         }

         if (day < 1 || day > numberOfDaysInMonth()) // zainicjalizuj dzien
         {
            return false;
         }

         return true;

      }

      /**
       * This method sets the number of days for each month.
       */
      public int numberOfDaysInMonth() // kolejna metoda
      {
         switch (month)
            {
               case 4:
               case 6:
               case 9:
               case 11:
                  return 30;

               case 2:
                  if (isLeapYear()) // czy rok przestepny
                  {
                     return 29;
                  }
                  return 28;
            }
         return 31;
      }

      /**
       * This method checks to see if it is a leap year or not.
       */
      public boolean isLeapYear() // kolejna metoda
      {
         if (year % 400 == 0)
            return true; // rok jest przestepny

         else if (year % 100 == 0)
            return false; // co 100 lat rok zwykly

         else if (year % 4 == 0)
            return true;

         else
            return false; // rok zwykly
      }

      /**
       * This method sets the name to the months.
       */
      public String getMonthName() // kolejna metoda
      {
         switch (month)
            {
               case 1:
                  return "january";
               case 2:
                  return "february";
               case 3:
                  return "marts";
               case 4:
                  return "april";
               case 5:
                  return "may";
               case 6:
                  return "juni";
               case 7:
                  return "juli";
               case 8:
                  return "august";
               case 9:
                  return "september";
               case 10:
                  return "october";
               case 11:
                  return "november";
               case 12:
                  return "december";

               default:
                  return "Wrong month format";

            }
      }

      public void stepOneDayForward() {
         day++;
         if (day > numberOfDaysInMonth()) {
            day = 1;
            month++;
            if (month > 12) {
               month = 1;
               year++;
            }
         }
      }

      // getters
      public int getDay() {
         return day;
      }

      public int getMonth() {
         return month;
      }

      public int getYear() {
         return year;
      }

      public int yearsBetween(MyDate other) {
         int yearsBetween = 0;
         if (this.isBefore(other)) {
            yearsBetween = other.year - this.year;
            if (other.month < this.month || (other.month == this.month && other.day < this.day))
               yearsBetween--;
         }
         else {
            yearsBetween = this.year - other.year;
            if (this.month < other.month || (this.month == other.month && this.day < other.day))
               yearsBetween--;
         }
         return yearsBetween;
      }

      public int daysBetween(MyDate other) { // pobiera obiekt jako zmienna
         int daysBetween = 0;
         if (isBefore(other)) {
            MyDate counterDate = this.copy();
            while (counterDate.isBefore(other)) {
               daysBetween++;
               counterDate.stepOneDayForward();
            }
         }
         else {
            MyDate counterDate = other.copy();
            while (counterDate.isBefore(this)) {
               daysBetween++;
               counterDate.stepOneDayForward();
            }
         }
         return daysBetween;
      }

      // odwolanie do zmiennej date w klasie MyDate
      public boolean isBefore(MyDate other) {
         if (year < other.year)
            return true;
         if (year > other.year)
            return false;
         if (month < other.month)
            return true;
         if (month > other.month)
            return false;
         return day < other.day;
      }

      public MyDate copy() // kopia obiektu
      {
         return new MyDate(day, month, year);
      }

      @Override
      public String toString() // zmiana formatu wyswietlania
      {
         String s = "";
         if (day < 10) {
            s += "0"; // dodaj zero do pojedynczej cyfry
         }
         s += day + "/";

         if (month < 10) {
            s += "0"; // dodaj zero do pojedynczej cyfry
         }

         s += month + "/" + year;

         return s;
      }
   }