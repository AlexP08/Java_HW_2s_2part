package com.TrainFX.controller;

import com.TrainFX.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * контроллер сцены JavaFX
 * @author Alexander Pavlov
 */
public class LoginController {

    private App main;
    private Stage stage;

    /**
     * Инициализатор класса
     */
    public LoginController() {
    }

    /**
     * Устанавливает значение параметру main
     *
     * @param main App
     */
    public void setMain(App main) {
        this.main = main;
    }

    /**
     * Устанавливает значение параметру stage
     *
     * @param stage Stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

