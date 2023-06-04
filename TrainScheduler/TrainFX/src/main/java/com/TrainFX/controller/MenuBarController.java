package com.TrainFX.controller;


import com.TrainFX.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * контроллер сцены JavaFX
 * @author Alexander Pavlov
 */
public class MenuBarController {
    private App main;
    private Stage stage;
    @FXML
    private Label labelInfo;

    /**
     * Инициализатор класса
     */
    public MenuBarController() {
    }

    /**
     * Устанавливает значение параметру main
     *
     * @param main App
     */
    public void setMain(App main) {
        this.main = main;
    }

    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает значение параметру stage
     *
     * @param stage Stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @FXML
    private void handleTrainList() {
        main.handleTrainList();
    }

    @FXML
    private void handleRouteList() {
        main.handleRouteList();
    }

    @FXML
    private void handleClose() {
        main.getPrimaryStage().close();
    }

    @FXML
    private void handleExit() throws Exception {
        main.getPrimaryStage().close();
        main.start(new Stage());
    }
}


