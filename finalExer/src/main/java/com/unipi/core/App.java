package com.unipi.core;

import com.unipi.gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Comments
// Praktika oles oi klaseis den xrhsimopoioun ton constructor me ola ta pedia, einai ekei apokleistika epeidh apaiteitai.
// Ola (H toulaxiston perisotera) ta boolean checks exoun apotelesma "1->Problhma", "0->Kanena problhma",
// giauto kai exoun auto poy thelw na apofygw sto onoma (px isBlank, invalidPhoneLengths).
// Sygnwmh eksarxhs gia ta greeklish comments, den aresan ta Ellhnika sto IDE ths C sto prohgoumeno eksamhno kai thelw na apofygw unreadable comments,
// giauto the ta grafw me greeklish h agglika.

public class App extends Application {

    // declare stages and scenes as public and static to make them accesible
    public static Scene mainScene, companyScene, planScene, clientScene, contractScene ;
    public static Stage mainStage;

    @Override
    public void start(Stage stage) {

        mainStage = stage;
        mainStage.setTitle("Main Window");

        initialiseScenes();
        
        // shows main scene
        mainStage.setScene(mainScene);
        mainStage.show();

    }

    private void initialiseScenes() {

        // initialise scenes, create scenes
        SceneCreator mainWindowCreator = new MainSceneCreator();
        mainScene = mainWindowCreator.createScene(1280, 720);

        SceneCreator companyWindowCreator = new CompanySceneCreator();
        companyScene = companyWindowCreator.createScene(1280, 720);

        SceneCreator planWindowCreator = new PlanSceneCreator();
        planScene = planWindowCreator.createScene(1280, 720);

        SceneCreator clientWindowCreator = new ClientSceneCreator();
        clientScene = clientWindowCreator.createScene(1280, 720);

        SceneCreator contractWindowCreator = new ContractSceneCreator();
        contractScene = contractWindowCreator.createScene(1280, 720);

    }
    public static void main(String[] args) {
        launch();
    }

}