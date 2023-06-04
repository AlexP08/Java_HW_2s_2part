package com.TrainFX.controller;

import com.TrainFX.App;
import com.TrainFX.model.Route;
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
import java.util.Date;

public class RouteOverviewController {

    @FXML
    private TableView<Route> routeTable;
    @FXML
    private TableColumn<Route, String> flightNumberColumn;
    @FXML
    private TableColumn<Route, String> departureCityColumn;
    @FXML
    private TableColumn<Route, String> arrivalCityColumn;
    @FXML
    private TableColumn<Route, Date> departureDateColumn;
    @FXML
    private TableColumn<Route, Date> departureTimeColumn;
    @FXML
    private TableColumn<Route, Date> arrivalTimeColumn;
    @FXML
    private Label routeNumberLabel;
    @FXML
    private Label departureCityLabel;
    @FXML
    private Label arrivalCityLabel;
    @FXML
    private Label departureDateLabel;
    @FXML
    private Label departureTimeLabel;
    @FXML
    private Label arrivalTimeLabel;

    private App main;
    private Stage stage;

    @FXML
    private void initialize() {
        try {
            routeTable.setItems(API.getAllRoutes());
            flightNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getRouteNumberProperty());
            departureCityColumn.setCellValueFactory(cellData -> cellData.getValue().getDepartureCityProperty());
            arrivalCityColumn.setCellValueFactory(cellData -> cellData.getValue().getArrivalCityProperty());

            showRouteOverviewDetails(null);
            routeTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showRouteOverviewDetails(newValue)
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
    void handleNewRoute() {
        showRouteEdit(new Route(), true);
    }

    @FXML
    void handleEditRoute() {
        Route route = routeTable.getSelectionModel().getSelectedItem();
        if (route != null) {
            showRouteEdit(route, false);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("Route not selected");
            alert.setContentText("You need to select a route in the table before changing!");

            alert.showAndWait();
        }
    }

    private void showRouteOverviewDetails(Route route) {
        if (route != null) {
            routeNumberLabel.setText(route.getRouteNumber());
            departureCityLabel.setText(route.getDepartureCity());
            arrivalCityLabel.setText(route.getArrivalCity());
            departureDateLabel.setText(route.getDepartureDate().toString());
            departureTimeLabel.setText(route.getDepartureTime().toString());
            arrivalTimeLabel.setText(route.getArrivalTime().toString());
        } else {
            routeNumberLabel.setText("");
            departureCityLabel.setText("");
            arrivalCityLabel.setText("");
            departureDateLabel.setText("");
            departureTimeLabel.setText("");
            arrivalTimeLabel.setText("");
        }
    }

    private void showRouteEdit(Route route, boolean creation) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.getClass().getResource("views/routeManaging.fxml"));
            AnchorPane page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Route");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getPrimaryStage());
            Scene scene = new Scene(page);
            stage.setScene(scene);
            RouteManagingController controller = loader.getController();
            controller.setStage(stage);
            controller.setRoute(route);
            controller.setMain(this.main);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
