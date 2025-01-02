package com.example.assignment;

public class Finance {
    private String month;
    private String income;
    private String incomeName;
    private String expense;
    private String waterElectricityBill;
    private String foodExpense;
    private String otherExpenses;

    public Finance(String month, String income, String incomeName, String expense, String waterElectricityBill, String foodExpense, String otherExpenses) {
        this.month = month;
        this.income = income;
        this.incomeName = incomeName;
        this.expense = expense;
        this.waterElectricityBill = waterElectricityBill;
        this.foodExpense = foodExpense;
        this.otherExpenses = otherExpenses;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getWaterElectricityBill() {
        return waterElectricityBill;
    }

    public void setWaterElectricityBill(String waterElectricityBill) {
        this.waterElectricityBill = waterElectricityBill;
    }

    public String getFoodExpense() {
        return foodExpense;
    }

    public void setFoodExpense(String foodExpense) {
        this.foodExpense = foodExpense;
    }

    public String getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(String otherExpenses) {
        this.otherExpenses = otherExpenses;
    }
}