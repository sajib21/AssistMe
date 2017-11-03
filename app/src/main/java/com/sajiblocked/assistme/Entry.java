package com.sajiblocked.assistme;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class Entry {
    public int _id;
    public String tag;
    public String type;
    public String description;
    public int    amount;

    public Entry() {

    }

    public Entry(int id, String tag, String type, int amount) {
        _id = id;
        this.tag = tag;
        this.type = type;
        this.amount = amount;
    }
    public Entry(long tag, String type, String description, int amount) {
//        _id = Integer.parseInt( id );
        this.tag = Long.toString(tag);
        this.type = type;
        this.description = description;
        this.amount = amount;
    }
    public Entry(long tag, String type, String description, String amount) {
//        _id = Integer.parseInt( id );
        this.tag = Long.toString(tag);
        this.type = type;
        this.description = description;
        this.amount = Integer.parseInt(amount);
    }
    public Entry(String tag, String type, String description, int amount) {
//        _id = Integer.parseInt( id );
        this.tag = tag;
        this.type = type;
        this.description = description;
        this.amount = amount;
    }
    public Entry(String tag, String type, String description, String amount) {
        this.tag = tag;
        this.type = type;
        this.description = description;
        this.amount = Integer.parseInt(amount);
    }

    public String getTag() {
        return tag;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }
}
