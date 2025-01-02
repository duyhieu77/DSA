package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.Optional;

public class DetailsController {
    private BinaryTree financeTree;
    private VBox incomeLayout;
    private VBox expenseLayout;

    public void setFinanceTree(BinaryTree financeTree) {
        this.financeTree = financeTree;
    }

    @FXML
    public void showDetails() {
        Stage detailsStage = new Stage();
        GridPane detailsLayout = new GridPane();
        detailsLayout.setPadding(new Insets(20));
        detailsLayout.setHgap(20);
        detailsLayout.setVgap(20);
        detailsLayout.setAlignment(Pos.CENTER);

        Text title = new Text("Income and Expense Details");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        GridPane.setHalignment(title, javafx.geometry.HPos.CENTER);

        ComboBox<String> monthComboBox = createMonthComboBox();
        monthComboBox.setValue("Select Month");
        monthComboBox.setOnAction(event -> {
            String selectedMonth = monthComboBox.getValue();
            if (!selectedMonth.equals("Select Month")) {
                showDetailsForSelectedMonth(selectedMonth);
            }
        });

        incomeLayout = createLabeledBox("Income:");
        expenseLayout = createLabeledBox("Expenses:");

        detailsLayout.add(title, 0, 0, 2, 1);
        detailsLayout.add(monthComboBox, 0, 1, 2, 1);
        GridPane.setHalignment(monthComboBox, javafx.geometry.HPos.CENTER);
        detailsLayout.add(incomeLayout, 0, 2);
        detailsLayout.add(expenseLayout, 1, 2);

        HBox actionButtons = createActionButtons(monthComboBox);
        detailsLayout.add(actionButtons, 0, 3, 2, 1);
        actionButtons.setAlignment(Pos.CENTER);

        Scene detailsScene = new Scene(detailsLayout, 600, 400);
        detailsStage.setScene(detailsScene);
        detailsStage.setTitle("Income and Expense Details");
        detailsStage.show();
    }

    private ComboBox<String> createMonthComboBox() {
        ComboBox<String> monthComboBox = new ComboBox<>();
        monthComboBox.getItems().add("Select Month");
        monthComboBox.getItems().addAll("January", "February", "March", "April", "May",
                "June", "July", "August", "September",
                "October", "November", "December");
        return monthComboBox;
    }

    private HBox createActionButtons(ComboBox<String> monthComboBox) {
        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> editEntry(monthComboBox.getValue()));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteEntry(monthComboBox.getValue()));

        return new HBox(10, editButton, deleteButton);
    }

    private VBox createLabeledBox(String label) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #f0f0f0;");
        box.getChildren().add(new Label(label));
        return box;
    }

    private void showDetailsForSelectedMonth(String month) {
        incomeLayout.getChildren().clear();
        expenseLayout.getChildren().clear();

        Finance finance = financeTree.find(month);
        if (finance != null) {
            addIncomeWithDeleteButton(incomeLayout, finance);

            Label totalIncomeLabel = new Label("Total Income: " + finance.getIncome() + " VNĐ");
            incomeLayout.getChildren().add(totalIncomeLabel);

            addExpenseWithDeleteButton(expenseLayout, "Expenses: " + finance.getExpense(), finance);
            addExpenseWithDeleteButton(expenseLayout, "Utility Bills: " + finance.getWaterElectricityBill(), finance);
            addExpenseWithDeleteButton(expenseLayout, "Food: " + finance.getFoodExpense(), finance);
            addExpenseWithDeleteButton(expenseLayout, "Other Expenses: " + finance.getOtherExpenses(), finance);
        }
    }

    private void addIncomeWithDeleteButton(VBox incomeLayout, Finance finance) {
        HBox incomeBox = new HBox(10);
        Label incomeLabel = new Label("Income Source: " + finance.getIncomeName() + " - " + finance.getIncome() + " VNĐ");
        Button deleteButton = new Button("-");

        deleteButton.setOnAction(e -> {
            finance.setIncome("0");
            incomeLayout.getChildren().clear();
            addIncomeWithDeleteButton(incomeLayout, finance);
        });

        incomeBox.getChildren().addAll(incomeLabel, deleteButton);
        incomeLayout.getChildren().add(incomeBox);
    }

    private void addExpenseWithDeleteButton(VBox expenseLayout, String expenseText, Finance finance) {
        HBox expenseBox = new HBox(10);
        Label expenseLabel = new Label(expenseText);
        Button deleteButton = new Button("-");

        deleteButton.setOnAction(e -> {
            if (expenseText.startsWith("Expenses: ")) {
                finance.setExpense("0");
            } else if (expenseText.startsWith("Utility Bills: ")) {
                finance.setWaterElectricityBill("0");
            } else if (expenseText.startsWith("Food: ")) {
                finance.setFoodExpense("0");
            } else if (expenseText.startsWith("Other Expenses: ")) {
                finance.setOtherExpenses("0");
            }

            updateExpenseLayout(expenseLayout, finance);
        });

        expenseBox.getChildren().addAll(expenseLabel, deleteButton);
        expenseLayout.getChildren().add(expenseBox);
    }

    private void updateExpenseLayout(VBox expenseLayout, Finance finance) {
        expenseLayout.getChildren().clear();
        addExpenseWithDeleteButton(expenseLayout, "Expenses: " + finance.getExpense(), finance);
        addExpenseWithDeleteButton(expenseLayout, "Utility Bills: " + finance.getWaterElectricityBill(), finance);
        addExpenseWithDeleteButton(expenseLayout, "Food: " + finance.getFoodExpense(), finance);
        addExpenseWithDeleteButton(expenseLayout, "Other Expenses: " + finance.getOtherExpenses(), finance);
    }

    private void deleteEntry(String month) {
        Finance finance = financeTree.find(month);
        if (finance != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete the financial information for " + month + "?");
            alert.setContentText("This information will be permanently deleted.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                finance.setIncome("0");
                finance.setExpense("0");
                finance.setWaterElectricityBill("0");
                finance.setFoodExpense("0");
                finance.setOtherExpenses("0");

                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("Notification");
                infoAlert.setContentText("Successfully deleted all financial information for " + month + ".");
                infoAlert.showAndWait();

                showDetailsForSelectedMonth(month);
            }
        }
    }

    private void editEntry(String month) {
        Finance finance = financeTree.find(month);
        if (finance != null) {
            openEntryDialog(finance);
        }
    }

    private void openEntryDialog(Finance finance) {
        Dialog<Finance> dialog = new Dialog<>();
        dialog.setTitle("Edit Information");

        TextField incomeField = new TextField(finance.getIncome());
        TextField incomeNameField = new TextField(finance.getIncomeName());
        TextField waterElectricityField = new TextField(finance.getWaterElectricityBill());
        TextField foodField = new TextField(finance.getFoodExpense());
        TextField otherField = new TextField(finance.getOtherExpenses());

        VBox dialogPane = new VBox(10);
        dialogPane.getChildren().addAll(
                new Label("Income:"), incomeField,
                new Label("Income Source:"), incomeNameField,
                new Label("Utility Bills:"), waterElectricityField,
                new Label("Food Expense:"), foodField,
                new Label("Other Expenses:"), otherField
        );
        dialogPane.setPadding(new Insets(20));
        dialog.getDialogPane().setContent(dialogPane);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                if (!isValidInput(incomeField.getText(), waterElectricityField.getText(), foodField.getText(), otherField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Amount must not be negative and must be a valid number.");
                    alert.showAndWait();
                    return null;
                }

                double newUtility = Double.parseDouble(waterElectricityField.getText());
                double newFood = Double.parseDouble(foodField.getText());
                double newOther = Double.parseDouble(otherField.getText());
                double totalExpenses = newUtility + newFood + newOther;
                double income = Double.parseDouble(incomeField.getText());

                if (totalExpenses > income) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Total expenses must not exceed income.");
                    alert.showAndWait();
                    return null;
                }

                Finance updatedFinance = new Finance(finance.getMonth(), incomeField.getText(), incomeNameField.getText(),
                        String.valueOf(totalExpenses), String.valueOf(newUtility),
                        String.valueOf(newFood), String.valueOf(newOther));

                return updatedFinance;
            }
            return null;
        });

        Optional<Finance> result = dialog.showAndWait();
        result.ifPresent(updatedFinance -> {
            finance.setIncome(updatedFinance.getIncome());
            finance.setIncomeName(updatedFinance.getIncomeName());
            finance.setExpense(updatedFinance.getExpense());
            finance.setWaterElectricityBill(updatedFinance.getWaterElectricityBill());
            finance.setFoodExpense(updatedFinance.getFoodExpense());
            finance.setOtherExpenses(updatedFinance.getOtherExpenses());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setContentText("Financial information for " + finance.getMonth() + " has been saved.");
            alert.showAndWait();

            showDetailsForSelectedMonth(finance.getMonth());
        });
    }

    private boolean isValidInput(String... values) {
        for (String value : values) {
            try {
                double number = Double.parseDouble(value);
                if (number < 0) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}