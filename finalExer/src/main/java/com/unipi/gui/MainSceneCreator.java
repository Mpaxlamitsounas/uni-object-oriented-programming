package com.unipi.gui;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MainSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {
    
    // declare nodes
    GridPane rootGridPane;
    Button companyBtn, planBtn, customerBtn, contractBtn;

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
        companyBtn = new Button("Companies");
        planBtn = new Button("Plans");
        customerBtn = new Button("Customers");
        contractBtn = new Button("Contracts");

    }

    private void attachEvents() {

        // attach events to buttons
        companyBtn.setOnMouseClicked(this);
        planBtn.setOnMouseClicked(this);
        customerBtn.setOnMouseClicked(this);
        contractBtn.setOnMouseClicked(this);

    }

    private void customiseNodes() {

        //customise buttons
        companyBtn.setMinWidth(80);
        planBtn.setMinWidth(80);
        customerBtn.setMinWidth(80);
        contractBtn.setMinWidth(80);

        // add buttons to grid pane
        rootGridPane.setAlignment(Pos.CENTER);
        rootGridPane.setHgap(20);
        rootGridPane.setVgap(20);
        rootGridPane.setMinWidth(120);
        rootGridPane.add(companyBtn, 0, 0);
        rootGridPane.add(planBtn, 1, 0);
        rootGridPane.add(customerBtn, 0, 1);
        rootGridPane.add(contractBtn, 1, 1);
        
    }

    // implement button functionality
    @Override
    public void handle(MouseEvent event) {

        // checks event source
        if      (event.getSource() == companyBtn) {
           switchToCompany();
        }
        else if (event.getSource() == planBtn) {
            switchToPlan();
        }
        else if (event.getSource() == customerBtn) {
            switchToCustomer();
        }
        else if (event.getSource() == contractBtn) {
            switchToContract();
        }
    }

}
