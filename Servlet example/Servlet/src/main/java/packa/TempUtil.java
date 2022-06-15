package packa;

import java.util.ArrayList;

public class TempUtil {
    public double[] findMax(ArrayList<Double> list) {
        double[] maxValues = {0, 0, 0};
        double a = 0, b = 0, c = 0;
        for (Double d : list) {
            if (d > a) {
                c = b;
                b = a;
                a = d;
            } else if (d > b) {
                c = b;
                b = d;
            } else if (d > c) {
                c = d;
            }
        }
        maxValues[0] = a;
        maxValues[1] = b;
        maxValues[2] = c;
        return maxValues;
    }
    public int highDays(ArrayList<Double> list) {
        int quantity = 0;
        for (Double d : list) {
            if (calculateAvg(list)<d)quantity++;
        }
        return quantity;
    }
    public int lowDays(ArrayList<Double> list) {
        int quantity = 0;
        for (Double d : list) {
            if (calculateAvg(list)>d)quantity++;
        }
        return quantity;
    }

    public double calculateAvg(ArrayList<Double> list) {
        double sum = 0;
        for (Double d : list) {
            sum += d;
        }
        return sum / list.size();
    }
}
