package com.unipi.core;

import com.unipi.gui.SceneCreator;

public class Contract {
    
    private final String UNIQUEID;
    private String phoneNum;
    private String afm;
    private int planID;
    private String beginDate;
    private int contractLength;
    private double discount;
    private double finalCost;
    private String type;
    private String payment;
    private double cancellationCost;
    private boolean isActive;
    private final String PLANTYPE;

    public Contract(String phoneNum, String afm, int planID, String beginDate, int contractLength, String type, String payment, boolean isActive, String PLANTYPE) {
        this.UNIQUEID = beginDate + afm + PLANTYPE; // creation of UID per instructions
        this.phoneNum = phoneNum;
        this.afm = afm;
        this.planID = planID;
        this.beginDate = beginDate;
        this.contractLength = contractLength;
        this.type = type;
        this.payment = payment;
        this.isActive = isActive;
        this.PLANTYPE = PLANTYPE;
        incrementPlanAssociations(planID);
        this.setDiscount(SceneCreator.getClientFromAfm(afm).getProperty(), SceneCreator.getPlanFromID(planID).getFreeTime(), payment, PLANTYPE);
        this.setFinalCost(SceneCreator.getPlanFromID(planID).getCost(), discount);
        this.setCancellationCost();
    }
    // den eimai sigouros ti na kanw me to isActive
    // apo thn mia mporei na shmaine oti to contract den exei akyrwthei, opote tha htan apo default true
    // alla mporei na ermhneutei kai ws "einai en drasei", dhladh eimaste mesa sthn hmeromhnia opou isxuei
    // kai den exei logikh ena contract pou tha htan energo se 1 xrono px na einai "Active"
    // exw ulopoihsei to katwtero sthn class ContractSceneCreator, thetodas thn "shmerinh hmeromhnia" ws 01/07/2021

    public Contract(String UNIQUEID, String phoneNum, String afm, int planID, String beginDate, int contractLength,
            double discount, double finalCost, String type, String payment, double cancellationCost, boolean isActive,
            String PLANTYPE) {
        this.UNIQUEID = beginDate + afm + PLANTYPE;
        this.phoneNum = phoneNum;
        this.afm = afm;
        this.planID = planID;
        this.beginDate = beginDate;
        this.contractLength = contractLength;
        this.discount = discount;
        this.finalCost = finalCost;
        this.type = type;
        this.payment = payment;
        this.cancellationCost = cancellationCost;
        this.isActive = isActive;
        this.PLANTYPE = PLANTYPE;
    } // constructor with all fields, den xrhsimopoietai

    private static void incrementPlanAssociations(int planID) {
        SceneCreator.getPlanFromID(planID).incrementAssociations(); 
    } // tells the plan increase its associations by 1, disallowing its deletion

    public void setDiscount(String property, int freeTime, String paymentMethod, String planType) {

        int finalDiscount = 0;

        if (property.equalsIgnoreCase("Professional") || property.equalsIgnoreCase("Eπαγγελματίας") || property.equalsIgnoreCase("Eπαγγελματiας"))
            finalDiscount += 10;
        else if (property.equalsIgnoreCase("Student") || property.equalsIgnoreCase("Φοιτητής") || property.equalsIgnoreCase("Φοιτητης"))
            finalDiscount += 15;
        // Κοιταέι για αγγλικά, ελληνικά με ή χωρίς τόνους

        if (freeTime > 1000 ) {
            if (planType.equals("Landline"))
                finalDiscount += 8;
            else if (planType.equals("Mobile"))
                finalDiscount += 11;
        }

        if (paymentMethod.equalsIgnoreCase("Credit card") || paymentMethod.equalsIgnoreCase("Πιστωτική κάρτα") || paymentMethod.equalsIgnoreCase("Πιστωτικη καρτα"))
            finalDiscount += 5;
        else if (paymentMethod.equalsIgnoreCase("Bank account") || paymentMethod.equalsIgnoreCase("Ηλεκτρονικό λογαριασμό") || paymentMethod.equalsIgnoreCase("Ηλεκτρονικο λογαριασμο")) {
            finalDiscount += 2;
        } // Κοιταέι για αγγλικά, ελληνικά με ή χωρίς τόνους
        
        this.discount = (double) finalDiscount / 100;
    }

    private void setFinalCost(double cost, double discount) {
        finalCost = cost * (1-discount);
    }

    private void setCancellationCost() {
        cancellationCost = getFinalCost() * 0.1;
    } // kostos akyroshs, to 10% tou final cost

    // regular getters/setters from hereon
    public String getPLANTYPE() {
        return PLANTYPE;
    }

    public String getUNIQUEID() {
        return UNIQUEID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(int contractLength) {
        this.contractLength = contractLength;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public double getCancellationCost() {
        return cancellationCost;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
}
