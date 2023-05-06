package com.unipi.gui;

import java.util.ArrayList;

import com.unipi.core.*;

import javafx.scene.Scene;
import javafx.scene.control.Alert;

public abstract class SceneCreator {

    public abstract Scene createScene(double width, double height);

    // declared here since multiple classes require them
    public static ArrayList<TelecommunicationCompany> companyArrayList = new ArrayList<>();
    public static ArrayList<Plan> planArrayList = new ArrayList<>();
    public static ArrayList<Client> clientArrayList = new ArrayList<>();
    public static ArrayList<Contract> contractArrayList = new ArrayList<>();

    // universal 
    public static TelecommunicationCompany getCompanyFromID(int companyID) {
        for (TelecommunicationCompany c : companyArrayList) {
            if (companyID == c.getUNIQUEID())
                return c;
        }

        return null; // Den uparxei periptwsh na gurisei null, apla den araisei sthn java to mono return na einai mesa se if

    } // returns object TeleCompany from its unique ID, used for making code more readable

    public static Plan getPlanFromID(int planID) {
        for (Plan p : planArrayList) {
            if (planID == p.getUNIQUEID())
                return p;
        }

        return null;

    } // same as above

    public static Client getClientFromAfm(String afm) {
        for (Client c : clientArrayList) {
            if (afm.equals(c.getAfm())) 
                return c;
        }

        return null;

    } // same as above

    public static Contract getContractFromID(String contractID) {
        for (Contract c : contractArrayList) {
            if (contractID.equals(c.getUNIQUEID())) {
                return c;
            }
        }

        return null;

    } // same as above

    static boolean invalidPhoneLength(String phoneNum) {

        if (phoneNum.length() != 10 ) 
            return true;
        else
            return false;

    } // used in many classes, so declared here
      // koitaei an o arithmos einai 10 pshfia
   
    static void quickAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    } // gia readability, efoson xrhsimopoiountai pantou ta alerts

    // ftiagmena gia readability kai reuse
    static void switchToMain() {
        App.mainStage.setTitle("Main Window");
        App.mainStage.setScene(App.mainScene);
    }

    static void switchToCompany() {
        App.mainStage.setTitle("Manage Companies");
        App.mainStage.setScene(App.companyScene);
    }

    static void switchToPlan() {
        App.mainStage.setTitle("Manage Plans");
        App.mainStage.setScene(App.planScene);
    }

    static void switchToCustomer() {
        App.mainStage.setTitle("Manage Clients");
        App.mainStage.setScene(App.clientScene);
    }

    static void switchToContract() {
        App.mainStage.setTitle("Manage Contracts");
        App.mainStage.setScene(App.contractScene);
    }

}
