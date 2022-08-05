package com.miniproject.grocery.model;


public class Budget {
    private int budget;

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budget=" + budget +
                '}';
    }
}
