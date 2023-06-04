package com.example.bookerfx.view;

import com.example.bookerfx.ScreenBounds;
import com.example.bookerfx.controller.CreateBookingFormController;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateBookingFormView {
    private Stage stage;
    private TextField roomIdField;
    private TextField clientIdField;
    private TextField checkInDateField;
    private TextField checkOutDateField;
    private TextField totalCostField;
    private CreateBookingFormController controller;
    public void setController(CreateBookingFormController controller) {
        this.controller = controller;
    }

    public void display(Stage primaryStage) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.setTitle("Create a reservation");

        Label headlineLabel = new Label("Create new reservation");
        headlineLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label roomIdLabel = new Label("room_id:");
        Label clientIdLabel = new Label("client_id:");
        Label checkInDateLabel = new Label("check_in_date:");
        Label checkOutDateLabel = new Label("check_out_date:");
        Label totalCostLabel = new Label("total_cost:");

        roomIdField = new TextField();
        clientIdField = new TextField();
        checkInDateField = new TextField();
        checkOutDateField = new TextField();
        totalCostField = new TextField();

        Button createButton = new Button("Enter the data");
        createButton.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        createButton.setOnAction(e -> controller.createBooking());

        // Disable the create button until all fields are filled
        createButton.disableProperty().bind(Bindings.createBooleanBinding(() ->
                        roomIdField.getText().isEmpty() ||
                                clientIdField.getText().isEmpty() ||
                                checkInDateField.getText().isEmpty() ||
                                checkOutDateField.getText().isEmpty() ||
                                totalCostField.getText().isEmpty(), // Disable if any field is empty
                roomIdField.textProperty(),
                clientIdField.textProperty(),
                checkInDateField.textProperty(),
                checkOutDateField.textProperty(),
                totalCostField.textProperty()));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new javafx.geometry.Insets(10, 20, 10, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(headlineLabel, 0, 0, 2, 1);
        gridPane.add(roomIdLabel, 0, 1);
        gridPane.add(roomIdField, 1, 1);
        gridPane.add(clientIdLabel, 0, 2);
        gridPane.add(clientIdField, 1, 2);
        gridPane.add(checkInDateLabel, 0, 3);
        gridPane.add(checkInDateField, 1, 3);
        gridPane.add(checkOutDateLabel, 0, 4);
        gridPane.add(checkOutDateField, 1, 4);
        gridPane.add(totalCostLabel, 0, 5);
        gridPane.add(totalCostField, 1, 5);
        gridPane.add(createButton, 0, 6, 2, 1);
        GridPane.setHalignment(createButton, javafx.geometry.HPos.RIGHT);

        javafx.scene.layout.ColumnConstraints column1 = new javafx.scene.layout.ColumnConstraints();
        column1.setPercentWidth(30);
        javafx.scene.layout.ColumnConstraints column2 = new javafx.scene.layout.ColumnConstraints();
        column2.setPercentWidth(70);
        gridPane.getColumnConstraints().addAll(column1, column2);

        javafx.scene.layout.RowConstraints rowConstraints = new javafx.scene.layout.RowConstraints();
        rowConstraints.setPrefHeight(30);
        gridPane.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints, rowConstraints, rowConstraints, rowConstraints);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);

        stage.setHeight(ScreenBounds.getWindowHeight(0.35));
        stage.setMinHeight(ScreenBounds.getWindowHeight(0.35));
        stage.setWidth(ScreenBounds.getWindowWidth(0.25));
        stage.setMinWidth(ScreenBounds.getWindowWidth(0.25));

        stage.showAndWait();
    }

    public Integer getRoomId() {
        return Integer.parseInt(roomIdField.getText());
    }

    public Integer getClientId() {
        return Integer.parseInt(clientIdField.getText());
    }

    public String getCheckInDate() {
        return checkInDateField.getText();
    }

    public String getCheckOutDate() {
        return checkOutDateField.getText();
    }

    public Double getTotalCost() {
        return Double.parseDouble(totalCostField.getText());
    }

    public void close() {
        stage.close();
    }

    public void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
