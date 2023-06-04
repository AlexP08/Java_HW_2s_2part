package com.TrainFX.controller;

import com.TrainFX.App;
import com.TrainFX.model.Train;
import com.TrainFX.exception.NoConnectionException;
import com.TrainFX.service.API;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
public class TrainManagingController {

    @FXML
    private TextField trainNumber;

    private Stage stage;
    private Train trainEntity;
    private App main;
    private boolean creation;

    /**
     * Устанавливает значение параметру stage
     * @param stage Stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * устанавливает значение параметру main
     * @param main App
     */
    public void setMain(App main) {
        this.main = main;
    }

    /**
     * Устанавливает значение параметру trainEntity
     * @param trainEntity TrainEntity
     */
    public void setTrain(Train trainEntity) {
        this.trainEntity = trainEntity;
        trainNumber.setText(trainEntity.getTrainNumber());
    }

    /**
     * Устанавливает значение параметру creation
     * @param creation boolean
     */
    public void setCreation(boolean creation) {
        this.creation = creation;
    }


    @FXML
    void handleCancel() {
        stage.close();
    }

    @FXML
    void handleOk() {
        if (isInputValid()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("number", trainNumber.getText());
            if (creation) {
                try {
                    API.addTrain(jsonObject);
                } catch (NoConnectionException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(stage);
                    alert.setTitle("No connection");
                    alert.setHeaderText("No server connection");
                    alert.setContentText("No server connection. Please try again later.");
                    alert.showAndWait();
                }
            } else {
                try {
                    jsonObject.put("id", trainEntity.getId());
                    API.updateTrain(jsonObject);
                } catch (NoConnectionException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(stage);
                    alert.setTitle("No connection");
                    alert.setHeaderText("No server connection");
                    alert.setContentText("No server connection. Please try again later.");
                    alert.showAndWait();
                }

            }
            main.handleTrainList();
            stage.close();
        }
    }

    private boolean isInputValid(){
        String errorMessage = "";

        if (trainNumber.getText() == null || trainNumber.getText().length() == 0) {
            errorMessage += "Enter the train number!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Please fix the invalid fields!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}