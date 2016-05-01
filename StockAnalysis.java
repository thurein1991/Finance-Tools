import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Josue on 4/24/2016.
 */
public class StockAnalysis {
    private ArrayList<String> csvPercentageData;
    private boolean isCorrectFormat;

    public StockAnalysis(ArrayList<String> csvPercentageData) throws Exception {
        if (csvPercentageData == null)
            throw new Exception("Null List");
        else {
            this.csvPercentageData = csvPercentageData;
            isCorrectFormat = isPercentageFormat();
            if (!this.isCorrectFormat) throw new Exception("Format of the data is not correct.");
            getAveragePerDay();
        }
    }

    private boolean isPercentageFormat() {
        return this.csvPercentageData.get(1).split(",").length == 11;
    }

    public String getAveragePerDay() {
        // we subtract because the first value are the categories
        String averagePerDay = Settings.companyName + "," +
                String.valueOf(this.csvPercentageData.size() - 1) + ",";
        ArrayList<Double> doubleDayData = new ArrayList<>();

        for (int day = 1; day < 11; day++) {
            doubleDayData.add(0.0);
        }

        // We skip first value due to we do not need it.
        for (int rowIndex = 1; rowIndex < this.csvPercentageData.size(); rowIndex++) {
            // Split arrow into columns to get value of each day
            String[] stringDayData = this.csvPercentageData.get(rowIndex).split(",");
            // Sum each day by the previous value
            for (int dayIndex = 0; dayIndex < doubleDayData.size(); dayIndex++) {
                doubleDayData.set(dayIndex, (doubleDayData.get(dayIndex) + Double.parseDouble(stringDayData[dayIndex + 1])));
            }
        }
        // divide all sums with the volume or total size of dates
        for (int sumDayIndex = 0; sumDayIndex < doubleDayData.size(); sumDayIndex++) {
            DecimalFormat dec = new DecimalFormat("0.00");
            doubleDayData.set(sumDayIndex, Double.valueOf(dec.format((doubleDayData.get(sumDayIndex) /
                    (double) this.csvPercentageData.size()))));

            // Converting into csvString
            if (sumDayIndex != doubleDayData.size() - 1){
                averagePerDay += doubleDayData.get(sumDayIndex) + ",";
            }else {
                averagePerDay += doubleDayData.get(sumDayIndex);
            }
        }
        return averagePerDay;
    }
}
