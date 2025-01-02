package com.example.assignment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private BarChart<String, Number> barChart;

    private BinaryTree financeTree = new BinaryTree();
    private boolean showIncome = true;
    private boolean showExpenses = true;

    private DetailsController detailsController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        showChart();
        detailsController = new DetailsController();
        detailsController.setFinanceTree(financeTree);
    }

    private void loadData() {
        Finance[] finances = new Finance[]{
                new Finance("January", "7000", "Salary", "5000", "1000", "2000", "2000"),
                new Finance("February", "10000", "Salary", "8000", "1500", "2500", "4000"),
                new Finance("March", "4000", "Salary", "2700", "900", "800", "1000"),
                new Finance("April", "6000", "Salary", "2500", "700", "1300", "500"),
                new Finance("May", "2000", "Salary", "1500", "400", "600", "500"),
                new Finance("June", "7700", "Salary", "6000", "1200", "1800", "3000"),
                new Finance("July", "5000", "Salary", "4000", "1000", "1300", "1700"),
                new Finance("August", "9000", "Salary", "7000", "2000", "2500", "2500"),
                new Finance("September", "15000", "Salary", "12000", "2500", "5000", "4500"),
                new Finance("October", "3000", "Salary", "2500", "800", "1000", "700"),
                new Finance("November", "8000", "Salary", "6000", "900", "1100", "4000"),
                new Finance("December", "13000", "Salary", "10000", "3000", "5000", "2000")
        };

        BubbleSort.sort(finances);
        for (Finance finance : finances) {
            financeTree.insert(finance);
        }
    }

    @FXML
    private void showChart() {
        barChart.getData().clear();

        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
        incomeSeries.setName("Income");

        XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
        expenseSeries.setName("Expenses");

        String[] months = {"January", "February", "March", "April", "May",
                "June", "July", "August", "September",
                "October", "November", "December"};

        for (String month : months) {
            Finance finance = financeTree.find(month);
            if (finance != null) {
                if (showIncome) {
                    incomeSeries.getData().add(new XYChart.Data<>(month, parseCurrency(finance.getIncome())));
                }
                if (showExpenses) {
                    expenseSeries.getData().add(new XYChart.Data<>(month, parseCurrency(finance.getExpense())));
                }
            }
        }

        if (showIncome) {
            barChart.getData().add(incomeSeries);
        }
        if (showExpenses) {
            barChart.getData().add(expenseSeries);
        }
    }

    private double parseCurrency(String amount) {
        return Double.parseDouble(amount.replace(".", "").replace(",", "."));
    }

    @FXML
    private void toggleIncome() {
        showIncome = !showIncome;
        showChart();
    }

    @FXML
    private void toggleExpenses() {
        showExpenses = !showExpenses;
        showChart();
    }

    @FXML
    private void showDetails() {
        detailsController.showDetails();
    }
}