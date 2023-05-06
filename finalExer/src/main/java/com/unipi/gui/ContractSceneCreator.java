package com.unipi.gui;

import com.unipi.core.Client;
import com.unipi.core.Contract;
import com.unipi.core.LandlinePlan;
import com.unipi.core.MobilePlan;
import com.unipi.core.Plan;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

// h hmeromhnia prepei na einai ths morfhs ddmmyyyy, kamia /, kollhmena ola, 2 pshfia oi meres, 2 oi mhnew, 4 ta xronia

public class ContractSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {

    // declare nodes
    GridPane rootGridPane, fieldGridPane;
    TableView<Contract> contractTableView;
    TextField phoneNumField, afmField, planIDField, beginDateField, contractLengthField, typeField, paymentField, numSearchField, isActiveField;
    Label phoneNumLbl, afmLbl, planIDLbl, beginDateLbl, contractLengthLbl, typeLbl, paymentLbl, numSearchLbl, typeSearchLbl, isActiveLbl, planTypeLbl;
    FlowPane btnFlowPane, backBtnFlowPane, searchRadioBtnFlowPane, searchPlanTypeBtnFlowPane;
    Button newBtn, backBtn, searchBtn, cancelBtn;
    int nameSearchState = 1, typeSearchState;
    String selectedContractID;
    RadioButton numSearchRaBtn, typeSearchRaBtn, landlineSearchRaBtn, mobileSearchRaBtn;
    ToggleGroup searchGroup, searchPlanTypeGroup;

    @Override
    public Scene createScene(double width, double height) {

        initialiseNodes();
        
        attachEvents();

        customiseNodes();

        // returns completed scene
        return new Scene(rootGridPane, width, height);
    }

    private void initialiseNodes() {

        rootGridPane = new GridPane();
        fieldGridPane = new GridPane();

        contractTableView = new TableView<>();

        phoneNumField = new TextField();
        afmField = new TextField();
        planIDField = new TextField();
        beginDateField = new TextField();
        contractLengthField = new TextField();
        typeField = new TextField();
        paymentField = new TextField();
        numSearchField = new TextField();
        isActiveField = new TextField();
        
        phoneNumLbl = new Label("Phone Num.: ");
        afmLbl = new Label("AFM: ");
        planIDLbl = new Label("PlanID: ");
        beginDateLbl = new Label("Begin Date: ");
        contractLengthLbl = new Label("Length: ");
        typeLbl = new Label("Type: ");
        paymentLbl = new Label("Payment: ");
        numSearchLbl = new Label("Name Search: ");
        typeSearchLbl = new Label("Type Search: ");
        isActiveLbl = new Label("Is Active: ");
        planTypeLbl = new Label("Plan type: ");

        btnFlowPane = new FlowPane();
        searchRadioBtnFlowPane = new FlowPane();
        searchPlanTypeBtnFlowPane = new FlowPane();
        
        newBtn = new Button("New");
        backBtn = new Button("Back");
        searchBtn = new Button("Search");
        cancelBtn = new Button("Cancel");

        numSearchRaBtn = new RadioButton("Number");
        typeSearchRaBtn = new RadioButton("Type");
        landlineSearchRaBtn = new RadioButton("Landline");
        mobileSearchRaBtn = new RadioButton("Mobile");

        searchGroup = new ToggleGroup();
        searchPlanTypeGroup = new ToggleGroup();
    
        // auxiliary nodes for formatting
        backBtnFlowPane = new FlowPane();

    }

    private void attachEvents() {

        newBtn.setOnMouseClicked(this);
        backBtn.setOnMouseClicked(this);
        searchBtn.setOnMouseClicked(this);
        contractTableView.setOnMouseClicked(this);
        cancelBtn.setOnMouseClicked(this);

    }

    private void customiseNodes() {

        // reasoning in ContractSceneCreator
        backBtnFlowPane.setAlignment(Pos.BOTTOM_RIGHT);
        backBtnFlowPane.getChildren().add(backBtn);

        // customise buttons
        searchBtn.setMinWidth(75);
        newBtn.setMinWidth(75);
        cancelBtn.setMinWidth(75);
        backBtn.setMinWidth(75);

        // add buttons to flow pane
        btnFlowPane.setAlignment(Pos.BOTTOM_CENTER);
        btnFlowPane.setHgap(10);
        btnFlowPane.getChildren().addAll(newBtn, cancelBtn);

        // makes search categories mutually exclusive
        numSearchRaBtn.setToggleGroup(searchGroup);
        typeSearchRaBtn.setToggleGroup(searchGroup);

        // makes type search categories mutually excluseive
        landlineSearchRaBtn.setToggleGroup(searchPlanTypeGroup);
        mobileSearchRaBtn.setToggleGroup(searchPlanTypeGroup);

        // adds search radio buttons to flow pane
        searchRadioBtnFlowPane.setAlignment(Pos.CENTER);
        searchRadioBtnFlowPane.getChildren().addAll(numSearchRaBtn, typeSearchRaBtn);
        searchRadioBtnFlowPane.setHgap(5);
        searchRadioBtnFlowPane.setMaxWidth(135);
        numSearchRaBtn.setMinWidth(60);
        typeSearchRaBtn.setMinWidth(60);

        // adds search by plan type radio buttons to flow pane
        searchPlanTypeBtnFlowPane.setAlignment(Pos.CENTER);
        searchPlanTypeBtnFlowPane.getChildren().addAll(landlineSearchRaBtn, mobileSearchRaBtn);
        searchPlanTypeBtnFlowPane.setHgap(5);
        searchPlanTypeBtnFlowPane.setMaxWidth(135);
        landlineSearchRaBtn.setMinWidth(60);
        mobileSearchRaBtn.setMinWidth(60);
        
        // adds all nodes to grid pane
        fieldGridPane.setAlignment(Pos.CENTER);  // phoneNum, afm, planID, beginDate, contractLength, type, payment
        fieldGridPane.setVgap(10);
        fieldGridPane.setHgap(7);
        fieldGridPane.add(phoneNumLbl, 0, 0);
        fieldGridPane.add(phoneNumField, 1, 0);
        fieldGridPane.add(afmLbl, 0, 1);
        fieldGridPane.add(afmField, 1, 1);
        fieldGridPane.add(planIDLbl, 0, 2);
        fieldGridPane.add(planIDField, 1, 2);
        fieldGridPane.add(beginDateLbl, 0, 3);
        fieldGridPane.add(beginDateField, 1, 3);
        fieldGridPane.add(contractLengthLbl, 0, 4);
        fieldGridPane.add(contractLengthField, 1, 4);
        fieldGridPane.add(typeLbl, 0, 5);
        fieldGridPane.add(typeField, 1, 5);
        fieldGridPane.add(paymentLbl, 0, 6);
        fieldGridPane.add(paymentField, 1, 6);
        fieldGridPane.add(isActiveLbl, 0, 7);
        fieldGridPane.add(isActiveField, 1, 7);
        fieldGridPane.add(typeSearchLbl, 0, 9);
        fieldGridPane.add(searchRadioBtnFlowPane, 1, 9);
        fieldGridPane.add(numSearchLbl, 0, 10);
        fieldGridPane.add(numSearchField, 1, 10);
        fieldGridPane.add(planTypeLbl, 0, 11);
        fieldGridPane.add(searchPlanTypeBtnFlowPane, 1, 11);
        fieldGridPane.add(searchBtn, 1, 12);

        // create table columns
        TableColumn<Contract, String> uidColumn = new TableColumn<>("UID");
        uidColumn.setMinWidth(235);
        uidColumn.setCellValueFactory(new PropertyValueFactory<>("UNIQUEID"));
        contractTableView.getColumns().add(uidColumn);

        TableColumn<Contract, String> phoneNumColumn = new TableColumn<>("Phone");
        phoneNumColumn.setMinWidth(75);
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        contractTableView.getColumns().add(phoneNumColumn);

        TableColumn<Contract, String> afmColumn = new TableColumn<>("AFM");
        afmColumn.setMinWidth(155);
        afmColumn.setCellValueFactory(new PropertyValueFactory<>("afm"));
        contractTableView.getColumns().add(afmColumn);

        TableColumn<Contract, String> planIDColumn = new TableColumn<>("PlanID");
        planIDColumn.setMinWidth(65);
        planIDColumn.setCellValueFactory(new PropertyValueFactory<>("planID"));
        contractTableView.getColumns().add(planIDColumn);

        TableColumn<Contract, String> beginDateColumn = new TableColumn<>("BeginDate");
        beginDateColumn.setMinWidth(65);
        beginDateColumn.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        contractTableView.getColumns().add(beginDateColumn);

        TableColumn<Contract, String> contractLengthColumn = new TableColumn<>("Length");
        contractLengthColumn.setMinWidth(65);
        contractLengthColumn.setCellValueFactory(new PropertyValueFactory<>("contractLength"));
        contractTableView.getColumns().add(contractLengthColumn);

        TableColumn<Contract, String> finalCostColumn = new TableColumn<>("Cost");
        finalCostColumn.setMinWidth(65);
        finalCostColumn.setCellValueFactory(new PropertyValueFactory<>("finalCost"));
        contractTableView.getColumns().add(finalCostColumn);

        TableColumn<Contract, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(65);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        contractTableView.getColumns().add(typeColumn);

        TableColumn<Contract, String> paymentColumn = new TableColumn<>("Payment");
        paymentColumn.setMinWidth(85);
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        contractTableView.getColumns().add(paymentColumn);

        TableColumn<Contract, String> isActiveColumn = new TableColumn<>("Active");
        isActiveColumn.setMinWidth(65);
        isActiveColumn.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        contractTableView.getColumns().add(isActiveColumn);

        // customise table
        contractTableView.setMinWidth(800);
        
        // adds elements to root grid pane and formatting arguments
        GridPane.setVgrow(fieldGridPane, Priority.ALWAYS);
        GridPane.setHgrow(fieldGridPane, Priority.ALWAYS);
        rootGridPane.add(contractTableView, 0, 0);
        rootGridPane.add(fieldGridPane, 1, 0);
        rootGridPane.add(btnFlowPane, 0, 1);
        rootGridPane.add(backBtnFlowPane, 1, 1);

        tableSync();
    }

    // implement button (and tableview) functionality
    @Override
    public void handle(MouseEvent event) {

        if      (event.getSource() == backBtn) {
            switchToMain();
        }
        else if (event.getSource() == newBtn) {

            // try to get values of fields
            try {
                String phoneNum = phoneNumField.getText();
                String afm = afmField.getText();
                int planID = Integer.parseInt(planIDField.getText());
                String beginDate = beginDateField.getText();
                int length = Integer.parseInt(contractLengthField.getText());
                String type = typeField.getText();
                String paymentMethod = paymentField.getText();
                String planType = getPlanType(planID);
                
                if (errorExists(phoneNum, planType, planID, afm, beginDate, length)) {}
                else {
                    boolean isActive = exampleSetActive(beginDate, length); // see Contract Constructor Comments
                    contractArrayList.add(new Contract(phoneNum, afm, planID, beginDate, length, type, paymentMethod, isActive, planType));
                    setHadContracts(afm); // makes the client undeletable now that they have contracts on their number
                    clearFieldText(); 
                }
            }
            catch (NumberFormatException e) { // catch possible input error
                quickAlert(AlertType.WARNING, "Invalid input data", "One of the inputs was of invalid type.");
            }

            tableSync();

        }
        else if (event.getSource() == searchBtn) {
            
            // similar to search function in every other class
            if (typeSearchRaBtn.isSelected()) {
                switch(typeSearchState) {

                    case 1:
                        searchBtn.setText("End");
                        contractTableView.getItems().clear(); // clears tableview

                        if (landlineSearchRaBtn.isSelected()) { // adds objects according to what object type we're sorting by
                            for (Contract c : contractArrayList) {
                                if (c.getPLANTYPE().equals("Landline"))
                                    contractTableView.getItems().add(c);
                            }
                        }
                        else if (mobileSearchRaBtn.isSelected()) {
                            for (Contract c : contractArrayList) {
                                if (c.getPLANTYPE().equals("Mobile"))
                                    contractTableView.getItems().add(c);
                            }
                        }
                        else
                            quickAlert(AlertType.WARNING, "Missing argument", "Please select search criteria.");

                        break;

                    case 0:
                        searchBtn.setText("Search");
                        tableSync();
                        clearFieldText();
                }

                typeSearchState++;
                typeSearchState %= 2;

            }
            else if (numSearchRaBtn.isSelected()) {
                switch(nameSearchState) {

                    case 1:
                        searchBtn.setText("End");
                        contractTableView.getItems().clear();

                        for (Contract c : contractArrayList) { // searches by phoneNum here instead
                            if (c.getPhoneNum().equals(numSearchField.getText()))
                                contractTableView.getItems().add(c);
                        }


                        break;

                    case 0:

                    searchBtn.setText("Search");
                    tableSync();
                    clearFieldText();

                }

                nameSearchState++;
                nameSearchState %= 2;

            }
        }
        else if (event.getSource() == contractTableView) { // fills in text fields and gets unique ID of selected contract

            Contract selectedContract = contractTableView.getSelectionModel().getSelectedItem();
            if (selectedContract != null) {
                phoneNumField.setText(selectedContract.getPhoneNum());
                afmField.setText(selectedContract.getAfm());
                planIDField.setText(Integer.toString(selectedContract.getPlanID()));
                beginDateField.setText(selectedContract.getBeginDate());
                contractLengthField.setText(Integer.toString(selectedContract.getContractLength()));
                typeField.setText(selectedContract.getType());
                paymentField.setText(selectedContract.getPayment());
                isActiveField.setText(Boolean.toString(selectedContract.getIsActive()));
                selectedContractID = selectedContract.getUNIQUEID();
            }

        }
        else if (event.getSource() == cancelBtn) {

            if (isActiveField.getText().equals("false")) { // checks if user has done the required true->false change
                Contract selectedContract = getContractFromID(selectedContractID); // gets contract
                selectedContract.setIsActive(false); // sets it to inactive
                if (overlap(DDMMYYYYToYYYYMMDD(selectedContract.getBeginDate()), (3*10000)/12, DDMMYYYYToYYYYMMDD("01072021"), 0)) // checks if it is within 3 months since startDate
                    quickAlert(AlertType.CONFIRMATION, "Contract cancelled successfully", "Cancellation cost is: " + selectedContract.getCancellationCost() + ".");
                else
                    quickAlert(AlertType.CONFIRMATION, "Contract cancelled successfully", "Cancellation is free.");
            }
            tableSync();
            clearFieldText();

        }

    }
    
    // formatting functions
    private void clearFieldText() {

        phoneNumField.setText("");
        afmField.setText("");
        planIDField.setText("");
        beginDateField.setText("");
        contractLengthField.setText("");
        typeField.setText("");
        paymentField.setText("");
        numSearchField.setText("");
        isActiveField.setText("");

    } // clears all text fields

    private void tableSync() {

        contractTableView.getItems().clear();
        for (Contract c : contractArrayList) {
            contractTableView.getItems().add(c);
        }

    } // copies everything to the now blank tableview

    // get data functions
    private String getPlanType(int planID) {
        for (Plan p : planArrayList) {
            if (p.getUNIQUEID() == planID) {
                if (p instanceof LandlinePlan) 
                    return "Landline";
                else if (p instanceof MobilePlan)
                    return "Mobile";
            }
        }

        return "InvalidType";

    }

    // check validity functions
    private boolean errorExists(String phoneNum, String planType, int planID, String afm, String beginDate, int length) {

        if (notValidPhone(phoneNum, planType)) {
            quickAlert(AlertType.WARNING, "Invalid input", "Non valid phone number.");
            return true;
        }
        else if (phoneDoesntExist(phoneNum)) {
            quickAlert(AlertType.WARNING, "Invalid input", "Phone number provided doesn't correspond to a registered client.");
            return true;
        }
        else if (length != 12 && length != 24) {
            quickAlert(AlertType.WARNING, "Invalid input", "Contract duration can only be 12 or 24 months.");
            return true;
        }
        else if (isBlank()) {
            quickAlert(AlertType.WARNING, "Invalid input", "Please provide all necessary information.");
            return true;
        }
        else if (planDoesntExist(planID)) {
            quickAlert(AlertType.WARNING, "Invalid input", "Plan selected doesn't exist.");
            return true;
        }
        else if (clientDoesntExist(afm)) {
            quickAlert(AlertType.WARNING, "Invalid input", "AFM provided doesn't correspond to a registered client.");
            return true;
        }
        else if (invalidDate(beginDate)) {
            quickAlert(AlertType.WARNING, "Invalid input", "Date provided is not valid.");
            return true;
        }
        else if (overlappingContractDates(beginDate, length, phoneNum)) {
            quickAlert(AlertType.WARNING, "Unable to create contract", "Contract duration overlaps with another contract.");
            return true;
        }

        return false;

    } // kanw ola ta check edw gia na mhn ginei axtarmas sto createBtn event handler

    private boolean isBlank() {
        return phoneNumField.getText().equals("") || afmField.getText().equals("") || planIDField.getText().equals("") || beginDateField.getText().equals("") || 
        contractLengthField.getText().equals("") || typeField.getText().equals("") || paymentField.getText().equals("");
    } // checks if any of required fields is blank

    private boolean notValidPhone(String phoneNum, String planType) {

        if      (invalidPhoneLength(phoneNum))
                return true;
        else if (planType.equals("Landline") &&  !(phoneNum.charAt(0) == '2'))
                return true;
        else if (planType.equals("Mobile") && !(phoneNum.charAt(0) == '6'))
                return true;
        else 
            return false;
    } // checks for invalid phoneNum length and planType-firstDigit relationship described sthn ekfwnhsh

    private boolean planDoesntExist(int planID) {

        for (Plan p : planArrayList) {
            if (planID == p.getUNIQUEID())
                return false;
        }

        return true;

    } // runs through list and checks if planID exists, if it runs through its entirety and has not found a match, returns true

    private boolean clientDoesntExist(String afm) {

        for (Client c : clientArrayList) {
            if (afm.equals(c.getAfm()))
                return false;
        }

        return true;

    } // same methodology as above

    private boolean phoneDoesntExist(String phoneNum) {

        for (Client c : clientArrayList) {
            if (phoneNum.equals(c.getPhone()))
                return false;
        }

        return true;

    } // same methodology as above


    private boolean invalidDate(String date) {

        int day = stringPosToInt(date, 0) * 10 + stringPosToInt(date, 1);
        int month = stringPosToInt(date, 2) * 10 + stringPosToInt(date, 3);
        if (day > 31 || day == 0)
            return true;
        else if (month > 12 || month == 0)
            return true;

        return false;

    } // checks if date is invalid (like xx13xxxx, month 13)

    // data set function
    private void setHadContracts(String afm) {
        getClientFromAfm(afm).setHadContracts(true);
    }

    // transform functions
    private int stringPosToInt(String str, int pos) {
        return Integer.parseInt(Character.toString(str.charAt(pos)));
    } // gets a string, reads the char at pos position, returns it as integer, used for date conversion

    private boolean overlappingContractDates(String date, int length, String phoneNum) {

        for (Contract c : contractArrayList) {
            if (c.getPhoneNum().equals(phoneNum)) {
                if (overlap(DDMMYYYYToYYYYMMDD(c.getBeginDate()), (c.getContractLength()/12) * 10000, DDMMYYYYToYYYYMMDD(date), (length/12) * 10000))
                    return true;
            }
        }

        return false;

    }
    // checks for overlapping dates for every contract that is on the same number
    // Comparison explanation:
    // Date is provided as a String in DDMMYYYY, a comparison can be made if it was in YYYYMMDD format, since the most important digit is at the left
    // overlap checks for overlap, DDMMYYY...MMDD function gets int at pos position of string and multiplies by a weight
    // example 010712021, weight for first digit of day is 1, second digit 10 (20210700 + 10*0 + 1*1)
    // length of contract is converted to years and multiplied by weight for first digit of years
    // if overlap is found, return true, else if will go through every element in contractArrayList and return false at the end
    // Einai periploko, to kserw, alla den gnwrizw pio aplo tropo

    private boolean overlap(int begin1, int duration1, int begin2, int duration2) {
        return !(begin1 >= begin2 + duration2 || begin1 + duration1 <= begin2);
    } // Koitaei an DEN kanoun overlap kai kanei return thn arnhsh autou

    // graphic example if they don't overlap
    // b1 >= b2 + d2 or b1 + d1 <= b2
    // -------b2+d2---b1---- or ----b1+d1----d2---
    //          |-----|               |------|

    // graphic example if they do overlap
    // b1 < b2 + d2 and b1 + d1 > b2
    // --------b1-----b2--------b2+d2---b1+d1-----
    //         |       |---------|--------|
    //         |-----------------|

    private int DDMMYYYYToYYYYMMDD (String date) {
        
        int year = stringPosToInt(date, 7) + 10 * stringPosToInt(date, 6) + 100 * stringPosToInt(date, 5) + 1000 * stringPosToInt(date, 4);
        int month = stringPosToInt(date, 3) + 10 * stringPosToInt(date, 2);
        int day = stringPosToInt(date, 1) + 10 * stringPosToInt(date, 0);
        return day + month * 100 + year * 10000;

    } // methodology explained in overlappingContractDates comments

    private boolean exampleSetActive(String date, int length) {
        return overlap(DDMMYYYYToYYYYMMDD("01072021"), 0, DDMMYYYYToYYYYMMDD(date), (length/12) * 10000);
    } // Logikh sta comments Constructor tou Contract

}
