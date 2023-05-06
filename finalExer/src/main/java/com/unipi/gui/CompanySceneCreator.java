package com.unipi.gui;

import com.unipi.core.TelecommunicationCompany;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class CompanySceneCreator extends SceneCreator implements EventHandler<MouseEvent> {
    
    // declare all nodes
    GridPane rootGridPane, fieldGridPane;
    TableView<TelecommunicationCompany> companyTableView;
    TextField nameField, phoneNumField, emailField, searchTermField;
    Label nameLbl, phoneNumLbl, emailLbl, searchTermLbl;
    FlowPane btnFlowPane, backBtnFlowPane;
    Button newBtn, updateBtn, deleteBtn, backBtn, searchBtn;
    int selectedCompanyID, searchState = 1;

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

        companyTableView = new TableView<>();

        nameField = new TextField();
        phoneNumField = new TextField();
        emailField = new TextField();
        searchTermField = new TextField();
        
        nameLbl = new Label("Name: ");
        phoneNumLbl = new Label("Phone Number: ");
        emailLbl = new Label("Email: ");
        searchTermLbl = new Label("Search Term: ");

        btnFlowPane = new FlowPane();
        
        newBtn = new Button("New");
        updateBtn = new Button("Update");
        deleteBtn = new Button("Delete");
        backBtn = new Button("Back");
        searchBtn = new Button("Search");

        // auxiliary nodes for formatting
        backBtnFlowPane = new FlowPane();
        
    }

    private void attachEvents() {

        newBtn.setOnMouseClicked(this);
        updateBtn.setOnMouseClicked(this);
        deleteBtn.setOnMouseClicked(this);
        backBtn.setOnMouseClicked(this);
        searchBtn.setOnMouseClicked(this);
        companyTableView.setOnMouseClicked(this);

    }

    private void customiseNodes() {

        // add back btn to its flow pane, have to do it like this or it shows at the left, den exw idea giati
        backBtnFlowPane.setAlignment(Pos.BOTTOM_RIGHT);
        backBtnFlowPane.getChildren().add(backBtn);

        // customise buttons, kathara aisthtiko
        searchBtn.setMinWidth(75);
        newBtn.setMinWidth(75);
        updateBtn.setMinWidth(75);
        deleteBtn.setMinWidth(75);
        backBtn.setMinWidth(75);

        // add buttons to their flow pane
        btnFlowPane.setAlignment(Pos.BOTTOM_CENTER);
        btnFlowPane.setHgap(10);
        btnFlowPane.getChildren().addAll(newBtn, updateBtn, deleteBtn);

        // adds fields and labels to a grid pane
        fieldGridPane.setAlignment(Pos.CENTER);
        fieldGridPane.setVgap(10);
        fieldGridPane.setHgap(7);
        fieldGridPane.add(nameLbl, 0, 0);
        fieldGridPane.add(nameField, 1, 0);
        fieldGridPane.add(phoneNumLbl, 0, 1);
        fieldGridPane.add(phoneNumField, 1, 1);
        fieldGridPane.add(emailLbl, 0, 2);
        fieldGridPane.add(emailField, 1, 2);
        fieldGridPane.add(searchTermLbl, 0, 4);
        fieldGridPane.add(searchTermField, 1, 4);
        fieldGridPane.add(searchBtn, 1, 5);

        // create table columns
        TableColumn<TelecommunicationCompany, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(273);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        companyTableView.getColumns().add(nameColumn);

        TableColumn<TelecommunicationCompany, String> phoneNumColumn = new TableColumn<>("Phone Number");
        phoneNumColumn.setMinWidth(165);
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        companyTableView.getColumns().add(phoneNumColumn);

        TableColumn<TelecommunicationCompany, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(360);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        companyTableView.getColumns().add(emailColumn);

        // customise table, purely aesthetic
        companyTableView.setMinWidth(800);
        
        // adds elements to root grid pane and formatting arguments
        GridPane.setVgrow(fieldGridPane, Priority.ALWAYS);
        GridPane.setHgrow(fieldGridPane, Priority.ALWAYS);
        rootGridPane.add(companyTableView, 0, 0);
        rootGridPane.add(fieldGridPane, 1, 0);
        rootGridPane.add(btnFlowPane, 0, 1);
        rootGridPane.add(backBtnFlowPane, 1, 1);

    }

    // implement button (and tableview) functionality
    @Override
    public void handle(MouseEvent event) {

        if      (event.getSource() == backBtn) {
            switchToMain();
        }
        else if (event.getSource() == newBtn) {

            // get values of fields
            String name = nameField.getText();
            String phoneNum = phoneNumField.getText();
            String email = emailField.getText();

            if (isBlank())
                quickAlert(AlertType.WARNING, "Invalid input", "Please provide all necessary information");
            else if (invalidPhoneLength(phoneNum))
                quickAlert(AlertType.WARNING, "Invalid input", "Phone number is of invalid length.");
            else {
                companyArrayList.add(new TelecommunicationCompany(name, phoneNum, email));
                clearFieldText(); // don't clear fields if unsuccessful creation
                tableSync();
            }

        }
        else if (event.getSource() == updateBtn) {

            // gets values of fields
            String name = nameField.getText();
            String phoneNum = phoneNumField.getText();
            String email = emailField.getText();
            
            if (invalidPhoneLength(phoneNum)) // validity check
                quickAlert(AlertType.WARNING, "Invalid input", "Phone number is of invalid length.");
            else {
                // runs through "list" and compares selected company ID, updates if match
                for (TelecommunicationCompany c : companyArrayList) { // tha mporousa na xrhsimopioisw 3 fores to getCompanyFromID
                    if (c.getUNIQUEID() == selectedCompanyID) {       // alla kalytera na psaxnw mono mia fora anti gia 3
                        c.setName(name);
                        c.setPhoneNum(phoneNum);
                        c.setEmail(email);
                    }
                }
            }

            tableSync();
            clearFieldText(); 

        }
        else if (event.getSource() == deleteBtn) {
            
            // looks for unique ID match
            for (int i = 0; i < companyArrayList.size(); i++) {

                TelecommunicationCompany c = companyArrayList.get(i);
                if (c.getUNIQUEID() == selectedCompanyID) {
                    if (c.getAssociations() == 0) { // diagrafei mono an den uparxei plan sysxetismeno me to company
                        companyArrayList.remove(i);
                        break;
                    }
                    else {
                        quickAlert(AlertType.INFORMATION, "Unable to delete company", "The company has existing plans associated with it.");
                        
                    }
                }
            }

            tableSync();
            clearFieldText();

        }
        else if (event.getSource() == searchBtn) {
            
            // 2 katastaseis
            // sthn katastash 1 (opou arxizoume) kanei kanonika anazhthsh
            // sthn katastash 0 stamataei thn anazhthsh
            
            switch(searchState) {

                case 1:
                    searchBtn.setText("End Search"); // signal to user it has done search 
                    String searchTerm = searchTermField.getText(); // get search term (name)
                    companyTableView.getItems().clear(); // clears table view

                    // copies all matches to tableview
                    for (TelecommunicationCompany c : companyArrayList) {

                        if (c.getName().equalsIgnoreCase(searchTerm)){
                            companyTableView.getItems().add(c);
                        }
                    }
                    break;

                case 0:
                    searchBtn.setText("Search");
                    tableSync(); // shows everything again
                    clearFieldText();

            }
        
            // used to circle between states, 1->2->0 kai 0->1
            searchState++;
            searchState %= 2;

        }
        else if (event.getSource() == companyTableView) {

            // fills fields from selected item in tableview, also gets unique ID of item for update, delete function
            TelecommunicationCompany selectedCompany = companyTableView.getSelectionModel().getSelectedItem();
            if (selectedCompany != null) {
                nameField.setText(selectedCompany.getName());
                phoneNumField.setText(selectedCompany.getPhoneNum());
                emailField.setText(selectedCompany.getEmail());
                selectedCompanyID = selectedCompany.getUNIQUEID();
            }

        }

    }
        
    private void clearFieldText() {

        nameField.setText("");
        phoneNumField.setText("");
        emailField.setText("");
        searchTermField.setText("");

    } // simply clears fields

    // tha mporousame na eixame gleitosei olh thn antigraph an epitrepontan h apothikeush
    // twn antikeimenwn sta tableViews (einai observable Lists eksalou), alla apaitountai
    // ta array lists kai den ginetai na ta syndesw me tis gnwseis pou kserw apo to mathma
    private void tableSync() {

        companyTableView.getItems().clear(); // clears tableview
        for (TelecommunicationCompany c : companyArrayList) { // copies everything
            companyTableView.getItems().add(c);
        }

    }

    private boolean isBlank() {
        return nameField.getText().equals("") || phoneNumField.getText().equals("") || emailField.getText().equals("");
    } // checks if any of the fields is blank

}
