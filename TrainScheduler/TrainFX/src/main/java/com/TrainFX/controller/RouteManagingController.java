package com.TrainFX.controller;

import com.TrainFX.App;
import com.TrainFX.model.Route;
import com.TrainFX.model.Train;
import com.TrainFX.exception.NoConnectionException;
import com.TrainFX.service.API;
import com.TrainFX.utils.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;

import java.sql.Date;
import java.sql.Time;

public class RouteManagingController {

    @FXML
    private TextField routeNumber;

    @FXML
    private TextField departureCity;

    @FXML
    private TextField arrivalCity;

    @FXML
    private DatePicker departureDate;

    @FXML
    private TextField departureTime;

    @FXML
    private TextField arrivalTime;

    @FXML
    private ComboBox<Train> trainComboBox;

    private Stage stage;
    private App main;
    private Route route;
    private boolean creation;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMain(App main) {
        this.main = main;
    }

    public void setRoute(Route route) {
        this.route = route;
        if (route != null) {
            creation = false;
            routeNumber.setText(route.getRouteNumber());
            departureCity.setText(route.getDepartureCity());
            arrivalCity.setText(route.getArrivalCity());
            departureDate.setValue(route.getDepartureDate());
            departureTime.setText(DateUtil.format(route.getDepartureTime()));
            arrivalTime.setText(route.getArrivalTime().toString());
            trainComboBox.setValue(route.getTrain());
        } else {
            creation = true;
        }
    }

    @FXML
    void handleOk() {
        if (isInputValid()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("routeNumber", routeNumber.getText());
            jsonObject.put("departureCity", departureCity.getText());
            jsonObject.put("arrivalCity", arrivalCity.getText());
            jsonObject.put("departureDate", Date.valueOf(departureDate.getValue()));
            jsonObject.put("departureTime", Time.valueOf(departureTime.getText()));
            jsonObject.put("arrivalTime", Time.valueOf(arrivalTime.getText()));
            jsonObject.put("train", trainComboBox.getValue().getId());
            if (creation) {
                try {
                    API.addRoute(jsonObject);
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
                    jsonObject.put("id", route.getId());
                    API.updateRoute(jsonObject);
                } catch (NoConnectionException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(stage);
                    alert.setTitle("No connection");
                    alert.setHeaderText("No server connection");
                    alert.setContentText("No server connection. Please try again later.");
                    alert.showAndWait();
                }

            }
            main.handleRouteList();
            stage.close();
        }
    }

    @FXML
    void handleCancel() {
        stage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (routeNumber.getText() == null || routeNumber.getText().length() == 0) {
            errorMessage += "Enter route number!\n";
        }

        if (departureCity.getText() == null || departureCity.getText().length() == 0) {
            errorMessage += "Enter your departure city!\n";
        }

        if (arrivalCity.getText() == null || arrivalCity.getText().length() == 0) {
            errorMessage += "Enter your city of arrival!\n";
        }

        if (departureDate.getValue() == null) {
            errorMessage += "Choose a departure date!\n";
        }

        if (departureTime.getText() == null || departureTime.getText().length() == 0) {
            errorMessage += "Enter departure time!\n";
        } else {
            try {
                Time.valueOf(departureTime.getText());
            } catch (IllegalArgumentException e) {
                errorMessage += "The format is invalid. Format should be HH:MM:SS!\n";
            }
        }

        if (arrivalTime.getText() == null || arrivalTime.getText().length() == 0) {
            errorMessage += "Enter the time of arrival!\n";
        } else {
            try {
                Time.valueOf(arrivalTime.getText());
            } catch (IllegalArgumentException e) {
                errorMessage += "The format is invalid. Format should be HH:MM:SS!\n";
            }
        }

        if (trainComboBox.getValue() == null) {
            errorMessage += "Choose your train!\n";
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
