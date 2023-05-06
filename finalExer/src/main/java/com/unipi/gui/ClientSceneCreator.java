package com.unipi.gui;

import com.unipi.core.Client;

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

public class ClientSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {

    // adt anaferetai ws "id" sthn class

    // declare nodes
    GridPane rootGridPane, fieldGridPane;
    TableView<Client> clientTableView;
    TextField idField, afmField, nameField, propertyField, addressField, phoneField, emailField, searchTermField;
    Label idLbl, afmLbl, nameLbl, propertyLbl, addressLbl, phoneLbl, emailLbl, searchTermLbl, searchByLbl;
    FlowPane btnFlowPane, backBtnFlowPane, searchBtnFlowPane;
    Button newBtn, updateBtn, deleteBtn, backBtn, searchBtn;
    int searchState = 1;
    String selectedClientAFM;
    RadioButton afmSearchRaBtn, idSearchRaBtn;
    ToggleGroup searchGroup;

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

        clientTableView = new TableView<>();

        idField = new TextField();
        afmField = new TextField();
        nameField = new TextField();
        propertyField = new TextField();
        addressField = new TextField();
        phoneField = new TextField();
        emailField = new TextField();
        searchTermField = new TextField();
        
        idLbl = new Label("ID: ");
        afmLbl = new Label("AFM: ");
        nameLbl = new Label("Full name: ");
        propertyLbl = new Label("Property: ");
        addressLbl = new Label("Address: ");
        phoneLbl = new Label("Phone: ");
        emailLbl = new Label("Email: ");
        searchTermLbl = new Label("Search: ");
        searchByLbl = new Label ("Search by: ");

        btnFlowPane = new FlowPane();
        searchBtnFlowPane = new FlowPane();
        
        newBtn = new Button("New");
        updateBtn = new Button("Update");
        deleteBtn = new Button("Delete");
        backBtn = new Button("Back");
        searchBtn = new Button("Search");

        // auxiliary nodes for formatting
        backBtnFlowPane = new FlowPane();

        afmSearchRaBtn = new RadioButton("AFM");
        idSearchRaBtn = new RadioButton("ID");

        searchGroup = new ToggleGroup();
        
    }

    private void attachEvents() {

        newBtn.setOnMouseClicked(this);
        updateBtn.setOnMouseClicked(this);
        deleteBtn.setOnMouseClicked(this);
        backBtn.setOnMouseClicked(this);
        searchBtn.setOnMouseClicked(this);
        clientTableView.setOnMouseClicked(this);

    }

    private void customiseNodes() {

        // reasoning in ClientSceneCreator
        backBtnFlowPane.setAlignment(Pos.BOTTOM_RIGHT);
        backBtnFlowPane.getChildren().add(backBtn);

        // customise buttons
        searchBtn.setMinWidth(75);
        newBtn.setMinWidth(75);
        updateBtn.setMinWidth(75);
        deleteBtn.setMinWidth(75);
        backBtn.setMinWidth(75);

        // add buttons to flow pane and customises
        btnFlowPane.setAlignment(Pos.BOTTOM_CENTER);
        btnFlowPane.setHgap(10);
        btnFlowPane.getChildren().addAll(newBtn, updateBtn, deleteBtn);

        // makes radio buttons mutually exclusive
        afmSearchRaBtn.setToggleGroup(searchGroup);
        idSearchRaBtn.setToggleGroup(searchGroup);

        // adds radio buttons to flow pane
        searchBtnFlowPane.setAlignment(Pos.CENTER);
        searchBtnFlowPane.getChildren().addAll(idSearchRaBtn, afmSearchRaBtn);
        searchBtnFlowPane.setHgap(5);
        searchBtnFlowPane.setMaxWidth(125);
        idSearchRaBtn.setMinWidth(60);
        afmSearchRaBtn.setMinWidth(60);
        
        // adds labels and text fields to grid pane
        fieldGridPane.setAlignment(Pos.CENTER);
        fieldGridPane.setVgap(10);
        fieldGridPane.setHgap(7);
        fieldGridPane.add(idLbl, 0, 0);
        fieldGridPane.add(idField, 1, 0);
        fieldGridPane.add(afmLbl, 0, 1);
        fieldGridPane.add(afmField, 1, 1);
        fieldGridPane.add(nameLbl, 0, 2);
        fieldGridPane.add(nameField, 1, 2);
        fieldGridPane.add(propertyLbl, 0, 3);
        fieldGridPane.add(propertyField, 1, 3);
        fieldGridPane.add(addressLbl, 0, 4);
        fieldGridPane.add(addressField, 1, 4);
        fieldGridPane.add(phoneLbl, 0, 5);
        fieldGridPane.add(phoneField, 1, 5);
        fieldGridPane.add(emailLbl, 0, 6);
        fieldGridPane.add(emailField, 1, 6);
        fieldGridPane.add(searchTermLbl, 0, 8);
        fieldGridPane.add(searchTermField, 1, 8);
        fieldGridPane.add(searchByLbl, 0, 9);
        fieldGridPane.add(searchBtnFlowPane, 1, 9);
        fieldGridPane.add(searchBtn, 1, 10);

        // create table columns
        TableColumn<Client, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(90);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientTableView.getColumns().add(idColumn);

        TableColumn<Client, String> afmColumn = new TableColumn<>("AFM");
        afmColumn.setMinWidth(90);
        afmColumn.setCellValueFactory(new PropertyValueFactory<>("afm"));
        clientTableView.getColumns().add(afmColumn);

        TableColumn<Client, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(130);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        clientTableView.getColumns().add(nameColumn);

        TableColumn<Client, String> propertyColumn = new TableColumn<>("Property");
        propertyColumn.setMinWidth(50);
        propertyColumn.setCellValueFactory(new PropertyValueFactory<>("property"));
        clientTableView.getColumns().add(propertyColumn);

        TableColumn<Client, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(175);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        clientTableView.getColumns().add(addressColumn);

        TableColumn<Client, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setMinWidth(50);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        clientTableView.getColumns().add(phoneColumn);

        TableColumn<Client, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(154);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        clientTableView.getColumns().add(emailColumn);


        // customise table
        clientTableView.setMinWidth(800);
        
        // adds elements to root grid pane and formatting argument
        GridPane.setVgrow(fieldGridPane, Priority.ALWAYS);
        GridPane.setHgrow(fieldGridPane, Priority.ALWAYS);
        rootGridPane.add(clientTableView, 0, 0);
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

            if (isBlank())
                quickAlert(AlertType.WARNING, "Invalid input", "Please provide all necessary information");
            else {
                // get values of fields
                String id = idField.getText();
                String afm = afmField.getText();
                String name = nameField.getText();
                String property = propertyField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                clientArrayList.add(new Client(id, afm, name, property, address, phone, email));
                clearFieldText(); 
            }

            tableSync();

        }
        else if (event.getSource() == updateBtn) {

            String name = nameField.getText();
            String property = propertyField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            if      (isBlank()) // checks for name too, despite it not being updated
                quickAlert(AlertType.WARNING, "Invalid input", "Please provide all necessary information");
            else if (invalidPhoneLength(phone))
                quickAlert(AlertType.WARNING, "Invalid input", "Invalid phone number length.");
            else {

                // runs through "list" and compares selected client afm, updates if match
                for (Client c : clientArrayList) {
                    if (c.getAfm().equals(selectedClientAFM)) {
                        c.setName(name);
                        c.setProperty(property);
                        c.setAddress(address);
                        c.setPhone(phone);
                        c.setEmail(email);
                    }
                }

                clearFieldText(); 
            }

            tableSync();
    
        }
        else if (event.getSource() == deleteBtn) {
            
            // runs through clientlist
            for (int i = 0; i < clientArrayList.size(); i++) {

                Client c = clientArrayList.get(i); // readability
                if (c.getAfm().equals(selectedClientAFM)) { // checks if AFM match
                    if (c.hasHadContracts() == false) { // if client has never done a contract before, removes them
                        clientArrayList.remove(i);
                        break;
                    }
                    else 
                        quickAlert(AlertType.INFORMATION, "Unable to delete client", "The client has had contracts associated with them.");
                }
            }

            tableSync();
            clearFieldText();

        }
        else if (event.getSource() == searchBtn) {
            
            // same principle as all other scene searches
            switch(searchState) {

                case 1:
                    searchBtn.setText("End Search");
                    String searchTerm = searchTermField.getText();
                    clientTableView.getItems().clear(); // clears tableview

                    if (afmSearchRaBtn.isSelected()) { // adds matches (se authn thn periptwsh afm)
                        for (Client c : clientArrayList) {
                            if (c.getAfm().equals(searchTerm)){
                                clientTableView.getItems().add(c);
                            }
                        }
                    }
                    else if (idSearchRaBtn.isSelected()) { // enw se authn ADT
                        for (Client c : clientArrayList) {

                            if (c.getId().equalsIgnoreCase(searchTerm)){
                                clientTableView.getItems().add(c);
                            }
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
            
            searchState++;
            searchState %= 2;

        }
        else if (event.getSource() == clientTableView) {

            Client selectedClient = clientTableView.getSelectionModel().getSelectedItem();
            if (selectedClient != null) {

                idField.setText(selectedClient.getId());
                afmField.setText(selectedClient.getAfm());
                nameField.setText(selectedClient.getName());
                propertyField.setText(selectedClient.getProperty());
                addressField.setText(selectedClient.getAddress());
                phoneField.setText(selectedClient.getPhone());
                emailField.setText(selectedClient.getEmail());
                selectedClientAFM = selectedClient.getAfm(); 
                
            }

        } // gets data from ArrayList and fills in the text fields

    }
        
    private void clearFieldText() {

        idField.setText("");
        afmField.setText("");
        nameField.setText("");
        propertyField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
        searchTermField.setText("");

    } // clears fields

    private void tableSync() {

        clientTableView.getItems().clear();
        for (Client c : clientArrayList) {
            clientTableView.getItems().add(c);
        }

    } // copies everything to a first cleared tableview

    private boolean isBlank() {
        return idField.getText().equals("") || afmField.getText().equals("") || nameField.getText().equals("") || 
        propertyField.getText().equals("") || addressField.getText().equals("") || phoneField.getText().equals("") || emailField.getText().equals("");
    } // checks if any of required fields is empty

}
