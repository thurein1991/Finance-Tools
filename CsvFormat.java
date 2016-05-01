import java.util.ArrayList;

/**
 * Created by Josue on 4/23/2016.
 * Commented by Khanh Ho 4/26/2016.
 */

public class CsvFormat {
    private ArrayList<String> dates = new ArrayList<>();// date list.
    private ArrayList<String> closingPricing = new ArrayList<>();// closing pricing per date.

    public CsvFormat(ArrayList<String> rowList) {
        for (String row : rowList) {
            String[] rowData = Engulfing.splitRow(row);// Data per row then split it into columns
            dates.add(rowData[Lables.DATE.val()]);//  Increments our datelist getting the right position with lable enumeration.
            closingPricing.add(rowData[Lables.CLOSE.val()]);//  Gets the closing pricing per day.
        }
    }

    public ArrayList<String> getDates() {
        return this.dates;// Gets the full list of dates.
    }

    public ArrayList<String> getClosingPricing() {
        return this.closingPricing; // Gets the full list of pricing per day
    }

    public String cvsOutputPercentage(ArrayList<String> percentageOfList) {
        String date = this.dates.get(0);// to get currently date.
        percentageOfList.set(0, date);//  % listing changes the first value (N/A) for the current date date

        String formatString = ""; // Empty String to store the CSV string
        for (int day = 1; day < 11; day++) { // We have 10 days plus day 0 which is N/A then 11 values
            if (day != 11-1) { // If it is not the last date
                formatString += percentageOfList.get(day) + ",";// add a coma "," at the end
            } else {
                formatString += percentageOfList.get(day) + "\n"; // add a coma new line
            }
        }
        return formatString;// Return value String Value format of (N/A,day1val,day1va2,....,day1val0)
    }

    /**
     * * @param prevString value to create table categories
     * * @return table categories format in string
     **/
    public static String daysCvsFormat(String prevString) {
        String dayCvsFormat = prevString + ",";//  CustomValue as first csv value
        for (int day = 1; day < 11; day++) {
            String dayString = "Day " + day;// append or adds Day number i.e(Day1,Day2,..,Day10)
            if (day != 11 - 1) {
                dayCvsFormat += dayString + ","; // adds a comma if it is not the last element
            } else {
                dayCvsFormat += dayString + "\n"; // if last element then new line
            }
        }

        return dayCvsFormat;// returns the category header
    }

    public void printTable(ArrayList<String> dates, ArrayList<String> closingPricing, ArrayList<String> percentageOfList) {
        String charLimits = "| %-15s";// Allow 15 characters only to print
        String titleDate = " date of engulfing ---> " + dates.get(0) + "\n";// String "date of engulfing ---> i.e. (2010-12-22)

        System.out.println(titleDate); // Prints Date value
        printDelimeters(); // Delimeters to separate rows
        System.out.format(charLimits, "**************");// Prints an empty category
        printDays(); // prints category days i.e (Day1,Day2,...,Day10)
        printDelimeters();
        System.out.format(charLimits, "Date");//  output Date category
        printRowCategory(dates);//  print Date Category
        System.out.format(charLimits, "Price");//  output pricing category
        printRowCategory(closingPricing);//  print closing pricing category
        System.out.format(charLimits, "Profit(%)");//presnt for day of profit category
        printRowCategory(percentageOfList);//  PRINTS ALL VALUES per date
        printDelimeters();
    }

    private void printRowCategory(ArrayList<String> category) {
        String charLimits = "| %-15s";// means allow 15 char only.
        for (String aCategory : category) {
            System.out.format(charLimits, aCategory);
        }
        System.out.print("|\n");//  output for print category list.
    }

    private void printDelimeters() {
        for (int day = 1; day < 11; day++) {
            System.out.print("+----------------");// Simulates the length of 11 cases/colums
        }
        System.out.print("+\n");
    }

    private void printDays() {
        String charLimits = "| %-15s";// allow 15 char only to print.

        for (int day = 1; day < 11; day++) {
            String dayString = "Day " + day; // allow 10 days only
            System.out.format(charLimits, dayString);
        }
        System.out.print("|\n"); // print 10 days only
    }

    /**
     * *@param list ArrayList to convert into csv format
     * *@return a string in csv format
     **/
    private String listToCSVformat(ArrayList<String> list) {
        String formattedString = "";
        for (int count = 0; count < list.size(); count++) {
            if (count == list.size() - 1) {
                formattedString += list.get(count) + "\n";// presnt csv format for listing.
            } else {
                formattedString += list.get(count) + ",";
            }
        }
        return formattedString;// end of listing.
    }
}
