package packa;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    TempUtil tempUtil = new TempUtil();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<Double> tempList = new ArrayList<>();
            double step = 0;
            for (int i = 1; i < 31; i++) {
                step += i * 0.05;
                tempList.add(15 + step);
                if (i % 7 == 0) step -= 1.4;
                if (i % 20 == 0) step -= 10;
            }
            int choice = Integer.parseInt(request.getParameter("operation"));
            HttpSession session = request.getSession(true);
            switch (choice) {
                case 1:
                    double avg = Double.parseDouble(String.format("%.1f", tempUtil.calculateAvg(tempList)).replaceAll(",", "."));
                    session.setAttribute("res", avg);
                    break;
                case 2:
                    int quantity = tempUtil.highDays(tempList);
                    session.setAttribute("res", quantity);
                    break;
                case 3:
                    int quantityLow = tempUtil.lowDays(tempList);
                    session.setAttribute("res", quantityLow);
                    break;
                case 4:
                    double[] maxValues;
                    maxValues = tempUtil.findMax(tempList);
                    int id1 = tempList.indexOf(maxValues[0]) + 1;
                    int id2 = tempList.indexOf(maxValues[1]) + 1;
                    int id3 = tempList.indexOf(maxValues[2]) + 1;
                    String res = id1 + " - " + Double.parseDouble(String.format("%.1f", maxValues[0]).replaceAll(",", ".")) + " degrees, " + id2 + " - " + Double.parseDouble(String.format("%.1f", maxValues[1]).replaceAll(",", ".")) + " degrees, " + id3 + " - " + Double.parseDouble(String.format("%.1f", maxValues[2]).replaceAll(",", ".")) + " degrees.";
                    session.setAttribute("res", res);
                    break;
                case 5:
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < 30; i++) {
                        stringBuilder.append(" | " + (i + 1) + ". - \n" + Double.parseDouble(String.format("%.1f", tempList.get(i)).replaceAll(",", "."))+" | ");
                    }
                    session.setAttribute("res", String.valueOf(stringBuilder));
            }
            response.sendRedirect("main.jsp");

        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
}