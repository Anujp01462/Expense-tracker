package com.example.expensetracker.model;

import java.time.LocalDate;

public class Expense {
    private Long id;
    private String title;
    private Double amount;
    private String category; // Food, Transport, Utilities, Entertainment, Salary, Other
    private String type;     // EXPENSE or INCOME
    private LocalDate date = LocalDate.now();

    public Expense() {
    }

    public Expense(Long id, String title, Double amount, String category, String type, LocalDate date) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.type = (type != null) ? type.toUpperCase() : "EXPENSE";
        this.date = (date != null) ? date : LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = (type != null) ? type.toUpperCase() : "EXPENSE";
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
