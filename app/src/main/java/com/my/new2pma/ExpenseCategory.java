package com.my.new2pma;

/**
 * Created by ashwini on 1/17/2018.
 */

public class ExpenseCategory {
    String name;
    int amount;
    int imageSourceId;

    public ExpenseCategory(String name, int amount, int imageSourceId) {
        this.name = name;
        this.amount = amount;
        this.imageSourceId = imageSourceId;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getImageSourceId() {
        return imageSourceId;
    }
}
