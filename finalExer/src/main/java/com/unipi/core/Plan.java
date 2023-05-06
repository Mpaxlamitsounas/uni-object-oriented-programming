package com.unipi.core;

import com.unipi.gui.SceneCreator;

public class Plan {
    
    private static int id; 
    private final int UNIQUEID;
    private String provider;
    private int freeTime;
    private double cost;
    private int associations = 0;


    public Plan(String provider, int freeTime, double cost, int companyID) {
        id++;
        this.UNIQUEID = id;
        incrementCompanyAssociations(companyID);
        this.provider = provider;
        this.freeTime = freeTime;
        this.cost = cost;
    }

    public Plan(String provider, int freeTime, double cost) {
        id++;
        this.UNIQUEID = id;
        this.freeTime = freeTime;
        this.cost = cost;
    } // constructor with every field, den xrhsimopoieitai

    private static void incrementCompanyAssociations(int companyID) { // tells company with ID "UID" to increment associations by 1, disallowing deletion
        SceneCreator.getCompanyFromID(companyID).incrementAssociations();
    }

    public static void decrementCompanyAssociations(int companyID) { // Same as above, but decrements by 1
        SceneCreator.getCompanyFromID(companyID).decrementAssociations();
    }

    // these 2 methods are used in Contract
    public void incrementAssociations () {
        this.associations++;
    }
    
    public void decrementAssociations () {
        this.associations--;
    }

    // regular getters/setters below
    public int getAssociations() {
        return associations;
    }

    public int getUNIQUEID() {
        return UNIQUEID;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
}
