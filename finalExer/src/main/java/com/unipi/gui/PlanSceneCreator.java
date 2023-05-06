package com.unipi.gui;

import com.unipi.core.LandlinePlan;
import com.unipi.core.MobilePlan;
import com.unipi.core.Plan;
import com.unipi.core.TelecommunicationCompany;

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

public class PlanSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {

    // declare nodes
    GridPane rootGridPane, fieldGridPane;
    Button backBtn, newBtn, updateBtn, deleteBtn, nameSearchBtn, typeSearchBtn;
    Label providerLbl, timeLbl, costLbl, searchNameLbl, searchTypeLbl, speedLbl, typeLbl, smsLbl, dataLbl, searchByLbl;
    TextField providerField, timeField, costField, searchNameField, speedField, typeField, smsField, dataField;
    RadioButton landlineRaBtn, mobileRaBtn, searchLandlineRaBtn, searchMobileRaBtn;
    ToggleGroup radioBtnGroup, searchRadioBtnGroup; // used for radio buttons
    FlowPane btnFlowPane, backBtnFlowPane, radioBtnFlowPane, searchBtnFlowPane, searchRadioBtnFlowPane;
    int selectedPlanID, searchNameState = 1, searchTypeState = 1;
    TableView<Plan> planTableView;

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

        btnFlowPane = new FlowPane();
        backBtnFlowPane = new FlowPane();
        // used for formatting
        radioBtnFlowPane = new FlowPane();
        searchBtnFlowPane = new FlowPane();
        searchRadioBtnFlowPane = new FlowPane();

        backBtn = new Button("Back");
        newBtn = new Button("New");
        updateBtn = new Button("Update");
        deleteBtn = new Button("Delete");
        nameSearchBtn = new Button("Name"); 
        typeSearchBtn = new Button("Type");

        searchByLbl = new Label("Search by: ");
        providerLbl = new Label("Provider: ");
        timeLbl = new Label("Free time: ");
        costLbl = new Label("Cost: ");
        searchNameLbl = new Label("Name Search: ");
        searchTypeLbl = new Label("Type Search: ");
        speedLbl = new Label("Speed: ");
        typeLbl = new Label("Type: "); 
        smsLbl = new Label("SMS: ");
        dataLbl = new Label("Data: ");
        
        providerField = new TextField();
        timeField = new TextField();
        costField = new TextField();
        searchNameField = new TextField();
        speedField = new TextField();
        typeField = new TextField();
        smsField = new TextField();
        dataField = new TextField();

        landlineRaBtn = new RadioButton("LandLine");
        mobileRaBtn = new RadioButton("Mobile");
        searchLandlineRaBtn = new RadioButton("Landline");
        searchMobileRaBtn = new RadioButton("Mobile");

        radioBtnGroup = new ToggleGroup();
        searchRadioBtnGroup = new ToggleGroup();

        planTableView = new TableView<>();

    }

    private void attachEvents() {

        backBtn.setOnMouseClicked(this);
        newBtn.setOnMouseClicked(this);
        updateBtn.setOnMouseClicked(this);
        deleteBtn.setOnMouseClicked(this);
        nameSearchBtn.setOnMouseClicked(this);
        typeSearchBtn.setOnMouseClicked(this);
        planTableView.setOnMouseClicked(this);

    }

    private void customiseNodes() {

        // make radio btns mutually exclusive in groups
        landlineRaBtn.setToggleGroup(radioBtnGroup);
        mobileRaBtn.setToggleGroup(radioBtnGroup);

        searchLandlineRaBtn.setToggleGroup(searchRadioBtnGroup);
        searchMobileRaBtn.setToggleGroup(searchRadioBtnGroup);

        // reasoning in CompanySceneCreator
        backBtnFlowPane.setAlignment(Pos.BOTTOM_RIGHT);
        backBtnFlowPane.getChildren().add(backBtn); 
        
        // customise buttons
        btnFlowPane.setHgap(10);
        newBtn.setMinWidth(75);
        updateBtn.setMinWidth(75);
        deleteBtn.setMinWidth(75);
        backBtn.setMinWidth(75); 
        nameSearchBtn.setMinWidth(60);
        typeSearchBtn.setMinWidth(60);

        // add buttons to flow pane
        btnFlowPane.setAlignment(Pos.BOTTOM_CENTER);
        btnFlowPane.getChildren().addAll(newBtn, updateBtn, deleteBtn);
        
        // adds search buttons to flow pane and customises
        searchBtnFlowPane.getChildren().addAll(nameSearchBtn, typeSearchBtn);
        searchBtnFlowPane.setHgap(5);
        searchBtnFlowPane.setMaxWidth(125);

        // adds radio buttons for search to their flowpane
        searchRadioBtnFlowPane.setAlignment(Pos.CENTER);
        searchRadioBtnFlowPane.getChildren().addAll(searchLandlineRaBtn, searchMobileRaBtn);
        searchRadioBtnFlowPane.setMaxWidth(200);
        searchRadioBtnFlowPane.setHgap(5);

        // adds creation radio buttons to flowpane
        radioBtnFlowPane.setAlignment(Pos.CENTER);
        radioBtnFlowPane.getChildren().addAll(landlineRaBtn, mobileRaBtn);
        radioBtnFlowPane.setMaxWidth(200);
        radioBtnFlowPane.setHgap(5);

        // adds labels, and text fields, and panes to grid pane
        fieldGridPane.setAlignment(Pos.CENTER);
        fieldGridPane.setVgap(10);
        fieldGridPane.setHgap(7);
        fieldGridPane.add(providerLbl, 0, 0);
        fieldGridPane.add(providerField, 1, 0);
        fieldGridPane.add(timeLbl, 0, 1);
        fieldGridPane.add(timeField, 1, 1);
        fieldGridPane.add(speedLbl, 0, 2);
        fieldGridPane.add(speedField, 1, 2);
        fieldGridPane.add(typeLbl, 0, 3);
        fieldGridPane.add(typeField, 1, 3);
        fieldGridPane.add(smsLbl, 0, 4);
        fieldGridPane.add(smsField, 1, 4);
        fieldGridPane.add(dataLbl, 0, 5);
        fieldGridPane.add(dataField, 1, 5);
        fieldGridPane.add(costLbl, 0, 6);
        fieldGridPane.add(costField, 1, 6);
        fieldGridPane.add(radioBtnFlowPane, 1, 7);
        fieldGridPane.add(searchNameLbl, 0, 9);
        fieldGridPane.add(searchNameField, 1, 9);
        fieldGridPane.add(searchTypeLbl, 0, 10);
        fieldGridPane.add(searchRadioBtnFlowPane, 1, 10);
        fieldGridPane.add(searchByLbl, 0, 11);
        fieldGridPane.add(searchBtnFlowPane, 1, 11);

        // create table columns
        TableColumn<Plan, String> UIDColumn = new TableColumn<>("ID");
        UIDColumn.setMinWidth(25);
        UIDColumn.setCellValueFactory(new PropertyValueFactory<>("UNIQUEID"));
        planTableView.getColumns().add(UIDColumn);
        
        TableColumn<Plan, String> providerColumn = new TableColumn<>("Provider");
        providerColumn.setMinWidth(218);
        providerColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
        planTableView.getColumns().add(providerColumn);

        TableColumn<Plan, String> timeColumn = new TableColumn<>("Free Time");
        timeColumn.setMinWidth(80);
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("freeTime"));
        planTableView.getColumns().add(timeColumn);

        TableColumn<Plan, String> speedColumn = new TableColumn<>("Speed");
        speedColumn.setMinWidth(80);
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        planTableView.getColumns().add(speedColumn);

        TableColumn<Plan, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(80);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        planTableView.getColumns().add(typeColumn);

        TableColumn<Plan, String> smsColumn = new TableColumn<>("SMS");
        smsColumn.setMinWidth(80);
        smsColumn.setCellValueFactory(new PropertyValueFactory<>("freeSMS"));
        planTableView.getColumns().add(smsColumn);

        TableColumn<Plan, String> dataColumn = new TableColumn<>("Data");
        dataColumn.setMinWidth(80);
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("freeData"));
        planTableView.getColumns().add(dataColumn);

        TableColumn<Plan, String> costColumn = new TableColumn<>("Cost");
        costColumn.setMinWidth(100);
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        planTableView.getColumns().add(costColumn);

        // customise tableview
        planTableView.setMinWidth(800);

       // adds elements to root grid pane and formatting arguments
       GridPane.setVgrow(fieldGridPane, Priority.ALWAYS);
       GridPane.setHgrow(fieldGridPane, Priority.ALWAYS);
       rootGridPane.add(planTableView, 0, 0);
       rootGridPane.add(fieldGridPane, 1, 0);
       rootGridPane.add(btnFlowPane, 0, 1);
       rootGridPane.add(backBtnFlowPane, 1, 1); 

    }

    @Override
    public void handle(MouseEvent event) {
        
        if      (event.getSource() == backBtn) {
            switchToMain();
        }
        else if (event.getSource() == newBtn) {

            // get values of fields while checking for parse errors
            try {
                String provider = providerField.getText();
                int freeTime = Integer.parseInt(timeField.getText()); // String -> Int
                double cost = Double.parseDouble(costField.getText());  // String -> Double

                int companyUID = getCompanyUIDFromName(provider); // returns company unique ID
                if (companyUID != -1) { // -1 means company doesn't exist
                    if      (landlineRaBtn.isSelected()) {

                        String speed = speedField.getText();
                        String type = typeField.getText();

                        if (isBlankLandline())
                            quickAlert(AlertType.WARNING, "Invalid input", "Please provide all necessary information");
                        else if (areNegative(freeTime, cost))
                            quickAlert(AlertType.WARNING, "Invalid input", "One or more arguments are negative");
                        else {
                            planArrayList.add(new LandlinePlan(provider, freeTime, cost, speed, type, companyUID));
                            clearFieldText(); // clears fields only if creation was successful
                        }
                            
                    }
                    else if (mobileRaBtn.isSelected()) {

                        int SMS = Integer.parseInt(smsField.getText());
                        int data = Integer.parseInt(dataField.getText());
                        if (isBlankMobile())
                            quickAlert(AlertType.WARNING, "Invalid input", "Please provide all necessary information");
                        else if (areNegativeMobile(freeTime, cost, SMS, data))
                            quickAlert(AlertType.WARNING, "Invalid input", "One or more arguments are negative");
                        else {
                            planArrayList.add(new MobilePlan(provider, freeTime, cost, SMS, data, companyUID));
                            clearFieldText(); // clears fields only if creation was successful 
                        } 

                    }
                    else 
                        quickAlert(AlertType.WARNING, "Missing information", "Please provide a plan type");

                }
                else 
                    quickAlert(AlertType.WARNING, "Non matching company name", "Unable to match name provided with any existing company name. Has the company been created?");

            }
            catch (NumberFormatException e) {
                quickAlert(AlertType.WARNING, "Invalid input data", "One of the inputs was of invalid type.");
            }

            tableSync();

        }
        else if (event.getSource() == updateBtn) {

            // get data of fields
            int freeTime = Integer.parseInt(timeField.getText());
            double cost = Double.parseDouble(costField.getText()); 
            
            for (Plan p : planArrayList) { // runs through list 
                if (p.getUNIQUEID() == selectedPlanID) { // checking for the unique ID of the plan selected

                    if (p instanceof LandlinePlan) { // checks for what kind of plan it is
                        String speed = speedField.getText();
                        String type = typeField.getText();

                        if (isBlankLandline())
                            quickAlert(AlertType.WARNING, "Invalid input", "Please provide all necessary information");
                        else if (areNegative(freeTime, cost))
                            quickAlert(AlertType.WARNING, "Invalid input", "One or more arguments are negative");
                        else {
                            LandlinePlan llp = (LandlinePlan) p; // narrows and sets fields of subclass
                            llp.setFreeTime(freeTime);
                            llp.setCost(cost);
                            llp.setSpeed(speed);
                            llp.setType(type);
                            clearFieldText(); 
                        }
                    }
                    else if (p instanceof MobilePlan) {
                        int SMS = Integer.parseInt(smsField.getText());
                        int data = Integer.parseInt(dataField.getText());

                        if (isBlankMobile())
                            quickAlert(AlertType.WARNING, "Invalid input", "Please provide all necessary information");
                        else if (areNegativeMobile(freeTime, cost, SMS, data))
                            quickAlert(AlertType.WARNING, "Invalid input", "One or more arguments are negative");
                        else {
                            MobilePlan mp = (MobilePlan) p; // narrows and sets fields of subclass
                            mp.setFreeTime(freeTime);
                            mp.setCost(cost);
                            mp.setFreeSMS(SMS);
                            mp.setFreeData(data);
                            clearFieldText(); 
                        }
                    }
                }
            }

            tableSync();

        }
        else if (event.getSource() == deleteBtn) {
            
            // runs through planArrayList
            for (int i = 0; i < planArrayList.size(); i++) {

                Plan p = planArrayList.get(i); // assigns to variable for readability

                if (p.getUNIQUEID() == selectedPlanID) { // checks if the plan selected matches with object
                    if (p.getAssociations() == 0) { // if it does, checks that no contract points to it
                        Plan.decrementCompanyAssociations(getCompanyUIDFromName(p.getProvider())); // lowers plans pointing to corresponding company by 1
                        planArrayList.remove(i); // removes plan from list
                        break;
                    }
                    else {
                        quickAlert(AlertType.INFORMATION, "Unable to delete plan", "The plan has existing contracts associated with it.");
                        
                    }
                }
            }

            tableSync();
            clearFieldText();

        }
        else if (event.getSource() == nameSearchBtn) {
            
            // similar methodologys to search in CompanySceneCreator
            switch(searchNameState) {

                case 1:
                    typeSearchBtn.setOpacity(0); // "hides" the other button
                    nameSearchBtn.setText("End");
                    String searchTerm = searchNameField.getText();
                    planTableView.getItems().clear();
                    // gets name to search and clears tableview

                    for (Plan p : planArrayList) {

                        if (p.getProvider().equalsIgnoreCase(searchTerm)){
                            planTableView.getItems().add(p);
                        }
                    } // adds matches to tableview
                    break;

                case 0:
                    typeSearchBtn.setOpacity(100); // "unhides" the other button
                    nameSearchBtn.setText("Name");
                    tableSync(); // adds everything back
                    clearFieldText();

            }
            
            searchNameState++;
            searchNameState %= 2;

        }
        else if (event.getSource() == typeSearchBtn) {
            
            // similar to search by name
            switch(searchTypeState) {

                case 1:
                    nameSearchBtn.setOpacity(0);
                    typeSearchBtn.setText("End");

                    planTableView.getItems().clear();

                    if (searchLandlineRaBtn.isSelected()) {
                        for (Plan p : planArrayList) {

                            if (p instanceof LandlinePlan){
                                planTableView.getItems().add(p);
                            }
                        } // checks object type
                        
                    }
                    else if (searchMobileRaBtn.isSelected()) {
                        for (Plan p : planArrayList) {

                            if (p instanceof MobilePlan){
                                planTableView.getItems().add(p);
                            }
                        } // checks object type
                    }

                    break;

                case 0:
                    nameSearchBtn.setOpacity(100);
                    typeSearchBtn.setText("Type");
                    tableSync();
                    clearFieldText();

            }
            
            searchTypeState++;
            searchTypeState %= 2;

        }
        else if (event.getSource() == planTableView) {

            clearFieldText();
            Plan selectedPlan = planTableView.getSelectionModel().getSelectedItem();
            if (selectedPlan != null) {

                // fills universal text fields with data from object
                providerField.setText(selectedPlan.getProvider());
                timeField.setText(Integer.toString(selectedPlan.getFreeTime()));
                costField.setText(Double.toString(selectedPlan.getCost()));
                selectedPlanID = selectedPlan.getUNIQUEID();

                // fills object specific text fields with data from subobject
                if (selectedPlan instanceof LandlinePlan) {
                    LandlinePlan llp = (LandlinePlan) selectedPlan;
                    speedField.setText(llp.getSpeed());
                    typeField.setText(llp.getType());
                }
                else if (selectedPlan instanceof MobilePlan) {
                    MobilePlan mp = (MobilePlan) selectedPlan;
                    smsField.setText(Integer.toString(mp.getFreeSMS()));
                    dataField.setText(Integer.toString(mp.getFreeData()));
                }
            }

        }

    }
        
    private void clearFieldText() { //Label providerLbl, timeLbl, costLBl, searchTermLbl, speedLbl, typeLbl, smsLbl, dataLbl;

        providerField.setText("");
        timeField.setText("");
        costField.setText("");
        speedField.setText("");
        typeField.setText("");
        smsField.setText("");
        dataField.setText("");
        searchNameField.setText("");

    }   
        
    private int getCompanyUIDFromName(String name) {

        int UID = -1;
        for (TelecommunicationCompany c : companyArrayList) { // runs through company list, checks if name is listed, returns its UID, else returns -1
            if (c.getName().equals(name)) {
                return c.getUNIQUEID();
            }
        }

        return UID;

    }

    private void tableSync() {

        planTableView.getItems().clear();
        for (Plan c : planArrayList) {
            planTableView.getItems().add(c);
        }

    } // clears tableview and copies everything

    private boolean isBlank() {
        return providerField.getText().equals("") || timeField.getText().equals("") || costField.getText().equals("");
    } // checks if any of fields required by both plans are empty
 
    private boolean isBlankLandline() {
        return  isBlank() || typeField.getText().equals("") || speedField.getText().equals("");
    } // checks if any of base Fields or landline plan required fields are empty

    private boolean isBlankMobile() {
        return isBlank() || smsField.getText().equals("") || dataField.getText().equals("");
    } // checks if any of base Fields or mobile plan required fields are empty

    private boolean areNegative(int num1, double num2) {
        return num1 < 0 || num2 < 0;
    } // checks if any of base field numbers (freeTime and cost) are negative

    private boolean areNegativeMobile(int num1, double num2, int num3, int num4) {
        return areNegative(num1, num2) || num3 < 0 || num4 < 0;
    } // additionally checks SMS and data

}
