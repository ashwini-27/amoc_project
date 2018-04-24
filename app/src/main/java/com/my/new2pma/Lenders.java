package com.my.new2pma;

/**
 * Created by ajay on 1/18/2018.
 */

public class Lenders {
    String Name;
    int amount;
    int ImageresourceId;

    public Lenders(String name, int amount) {
        Name = name;
        this.amount = amount;
        ImageresourceId=R.drawable.nit_surat;
    }

    public int getImageresourceId() {
        return ImageresourceId;
    }

    public String getName() {
        return Name;
    }

    public int getAmount() {
        return amount;
    }


}
