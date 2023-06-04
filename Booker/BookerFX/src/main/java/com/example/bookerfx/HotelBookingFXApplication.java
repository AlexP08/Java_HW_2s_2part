package com.example.bookerfx;

import com.example.bookerfx.controller.CreateBookingFormController;
import com.example.bookerfx.controller.BookerFXController;
import com.example.bookerfx.model.Booking;
import com.example.bookerfx.view.CreateBookingFormView;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class HotelBookingFXApplication extends Application {
    private Stage primaryStage;
    private BookerFXController bookerFXController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Hotel Booking App");

        TableView<Booking> bookingTableView = createBookingTable();

        bookerFXController = new BookerFXController(bookingTableView);
        bookerFXController.initialize();

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label headerLabel = new Label("Bookings");
        headerLabel.setFont(Font.font("System", FontWeight.BOLD, 18));

        HBox headerBox = new HBox(10);
        headerBox.setAlignment(Pos.CENTER_LEFT);
        headerBox.getChildren().add(headerLabel);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(buttonBox, Priority.ALWAYS);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button createBookingButton = new Button("+ Add New");
        createBookingButton.setFont(Font.font("System", FontWeight.BOLD, 14));
        createBookingButton.setOnAction(e -> openCreateBookingForm());

        buttonBox.getChildren().addAll(spacer, createBookingButton);
        headerBox.getChildren().add(buttonBox);

        vbox.getChildren().addAll(headerBox, bookingTableView);
        VBox.setVgrow(bookingTableView, Priority.ALWAYS);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);

        primaryStage.setHeight(ScreenBounds.getWindowHeight(0.45));
        primaryStage.setMinHeight(ScreenBounds.getWindowHeight(0.45));
        primaryStage.setWidth(ScreenBounds.getWindowWidth(0.4));
        primaryStage.setMinWidth(ScreenBounds.getWindowWidth(0.4));

        primaryStage.show();
    }

    private TableView<Booking> createBookingTable() {
        TableView<Booking> table = new TableView<>();

        TableColumn<Booking, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(data ->  new SimpleIntegerProperty(data.getValue().getId()).asObject());

        TableColumn<Booking, Integer> roomIdColumn = new TableColumn<>("room_id");
        roomIdColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getRoomId().getId()));

        TableColumn<Booking, Integer> clientIdColumn = new TableColumn<>("client_id");
        clientIdColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getClientId().getId()));

        TableColumn<Booking, LocalDateTime> checkInDateColumn = new TableColumn<>("check_in_date");
        checkInDateColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCheckInDate()));

        TableColumn<Booking, LocalDateTime> checkOutDateColumn = new TableColumn<>("check_out_date");
        checkOutDateColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCheckOutDate()));

        TableColumn<Booking, Double> totalCostColumn = new TableColumn<>("total_cost");
        totalCostColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getTotalCost()).asObject());

        List<TableColumn<Booking, ?>> columns = Arrays.asList(
                idColumn,
                roomIdColumn,
                clientIdColumn,
                checkInDateColumn,
                checkOutDateColumn,
                totalCostColumn);
        table.getColumns().addAll(columns);

        return table;
    }

    private void openCreateBookingForm() {
        primaryStage.getScene().getRoot().setDisable(true);

        CreateBookingFormController createBookingFormController = new CreateBookingFormController();
        CreateBookingFormView createBookingFormView = new CreateBookingFormView();

        createBookingFormController.setView(createBookingFormView);
        createBookingFormView.setController(createBookingFormController);

        createBookingFormView.display(primaryStage);
        enableMainWindow();
    }
    public void enableMainWindow() {
        primaryStage.getScene().getRoot().setDisable(false);
        primaryStage.getScene().getWindow().setOpacity(1.0);
        bookerFXController.initialize();
    }
}
