package com.example.asm;

public class ExpenseRecord {
    private String income;
    private String expense;

    public ExpenseRecord(String income, String expense) {
        this.income = income;
        this.expense = expense;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }
}