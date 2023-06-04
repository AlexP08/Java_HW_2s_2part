package com.TrainFX.controller;

import com.TrainFX.App;
import com.TrainFX.model.Train;
import com.TrainFX.exception.NoConnectionException;
import com.TrainFX.service.API;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
public class TrainOverviewController {

    @FXML
    private TableView<Train> trainTable;
    @FXML
    private TableColumn<Train, String> trainNumberColumn;
    @FXML
    private TableColumn<Train, String> trainTypeColumn;
    @FXML
    private TableColumn<Train, String> trainRouteColumn;
    @FXML
    private Label trainNumberLabel;
    @FXML
    private Label trainTypeLabel;
    @FXML
    private Label trainRouteLabel;

    private App main;
    private Stage stage;

    @FXML
    private void initialize() {
        try {
            trainTable.setItems(API.getAllTrains());
            trainNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getTrainNumberProperty());
            trainTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getTrainTypeProperty());
            trainRouteColumn.setCellValueFactory(cellData -> cellData.getValue().getTrainRouteProperty());

            showTrainOverviewDetails(null);
            trainTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showTrainOverviewDetails(newValue)
            );
        } catch (NoConnectionException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("No connection");
            alert.setHeaderText("No server connection");
            alert.setContentText("No server connection. Please try again later.");
            alert.showAndWait();
        }
    }

    /**
     * Устанавливает значение параметру main
     * @param main App
     */
    public void setMain(App main) {
        this.main = main;
    }

    /**
     * Устанавливает значение параметру stage
     * @param stage Stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void handleNewTrain() {
        showTrainEdit(new Train(), true);
    }

    @FXML
    void handleEditTrain() {
        Train train = trainTable.getSelectionModel().getSelectedItem();
        if (train != null) {
            showTrainEdit(train, false);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("Train not selected");
            alert.setContentText("You need to select a train in the table before changing!");

            alert.showAndWait();
        }
    }

    private void showTrainOverviewDetails(Train train) {
        if (train != null) {
            trainNumberLabel.setText(train.getTrainNumber());
            trainTypeLabel.setText(train.getTrainType());
            trainRouteLabel.setText(train.getTrainRoute());
        } else {
            trainNumberLabel.setText("");
            trainTypeLabel.setText("");
            trainRouteLabel.setText("");
        }
    }

    private void showTrainEdit(Train train, boolean creation) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.getClass().getResource("views/trainManaging.fxml"));
            AnchorPane page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Train");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getPrimaryStage());
            Scene scene = new Scene(page);
            stage.setScene(scene);
            TrainManagingController controller = loader.getController();
            controller.setStage(stage);
            controller.setTrain(train);
            controller.setMain(this.main);
            controller.setCreation(creation);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}