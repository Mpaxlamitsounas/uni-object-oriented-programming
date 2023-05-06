package com.unipi.core;

public class MobilePlan extends Plan {

    private int freeSMS;
    private int freeData;
    
    public MobilePlan(String provider, int freeTime, double cost, int freeSMS, int freeData, int companyID) {
        super(provider, freeTime, cost, companyID);
        this.freeSMS = freeSMS;
        this.freeData = freeData;
    }

    public MobilePlan(String provider, int freeTime, double cost, int freeSMS, int freeData) {
        super(provider, freeTime, cost);
        this.freeSMS = freeSMS;
        this.freeData = freeData;
    } // constructor with every field, not used

    // hthelan kati ta table columns, opote auto einai anagkaio gia na mhn petaei uncaught exceptions
    public String getSpeed() {
        return "-";
    }

    public String getType() {
        return "-";
    }

    // normal getters/setters
    public int getFreeSMS() {
        return freeSMS;
    }

    public void setFreeSMS(int freeSMS) {
        this.freeSMS = freeSMS;
    }

    public int getFreeData() {
        return freeData;
    }

    public void setFreeData(int freeData) {
        this.freeData = freeData;
    }

}
