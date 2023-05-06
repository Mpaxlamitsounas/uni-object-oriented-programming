package com.unipi.core;

public class LandlinePlan extends Plan {

    private String speed;
    private String type;
    
    public LandlinePlan(String provider, int freeTime, double cost, String speed, String type, int companyID) {
        super(provider, freeTime, cost, companyID);
        this.speed = speed;
        this.type = type;
    }

    public LandlinePlan(String provider, int freeTime, double cost, String speed, String type) {
        super(provider, freeTime, cost);
        this.speed = speed;
        this.type = type;
    } // constructor with every field, unused

    // ta TableColumn apaitoun enan getter tou argument poy dinetai alliws exw uncaught exception
    public String getFreeSMS() {
        return "-";
    }

    public String getFreeData() {
        return "-";
    }

    // normal getters/setters
    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
