package com.unipi.core;

public class TelecommunicationCompany {

    private static int ID;
    private final int UNIQUEID;
    private String name;
    private String phoneNum;
    private String email;
    // counts how many plans point to this company
    private int associations = 0;

    public TelecommunicationCompany(String name, String phoneNum, String email) {
        // assigns unique ID
        ID++;
        this.UNIQUEID = ID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    } // constructor with every field too
    
    // these 2 methods are used in Plan
    public void incrementAssociations () {
        this.associations++;
    }
    
    public void decrementAssociations () {
        this.associations--;
    }

    // normal getters/setters
    public int getAssociations() {
        return associations;
    }

    public int getUNIQUEID() {
        return UNIQUEID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
