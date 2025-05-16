package com.app.PayTogether.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private Long id;
    private String name;
    private BigDecimal amount;
    private LocalDate dueDate;
    private Group group;

    public Expense() {
    }

    public Expense(Long id, String name, BigDecimal amount, LocalDate dueDate, Group group) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}