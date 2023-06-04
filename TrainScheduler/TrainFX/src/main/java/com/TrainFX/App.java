package com.TrainFX;

import com.TrainFX.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Запускаемое приложение и контроллер сцены JavaFX
 */
public class App extends Application {

    private Stage primaryStage;
    private AnchorPane anchorPane;
    private BorderPane rootLayout;

    /**
     * возвращает значение параметра primaryStage
     * @return Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * возвращает значение параметра anchorPane
     * @return AnchorPane
     */
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    /**
     * возвращает значение параметра return rootLayout;
     * @return BorderPane
     */
    public BorderPane getRootLayout() {
        return rootLayout;
    }

    /**
     * инициализирует стартовое окно
     */
    public void initLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("views/login.fxml"));
            anchorPane = loader.load();
            LoginController loginController = loader.getController();
            loginController.setMain(this);
            Scene scene = new Scene((anchorPane));
            primaryStage.setScene(scene);
            loginController.setStage(primaryStage);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запускает показ интерфейса
     * @param primaryStage Stage
     */
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ReadMe");

        initLogin();
    }

    /**
     * инициализирцет главное окно с поездами
     */
    public void handleTrainList(){
        initRoot();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("views/ListTrains.fxml"));
            AnchorPane personOverview = loader.load();
            rootLayout.setCenter(personOverview);
            TrainOverviewController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * инициализирцет главное окно с рейсами
     */
    public void handleRouteList(){
        initRoot();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("views/ListRoutes.fxml"));
            AnchorPane personOverview = loader.load();
            rootLayout.setCenter(personOverview);
            RouteOverviewController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Инициализирует меню-бар
     */
    public void initRoot(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("views/menuBar.fxml"));
            rootLayout = loader.load();
            MenuBarController controller = loader.getController();
            controller.setStage(primaryStage);
            controller.setMain(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * точка входа в программу
     * @param args String[]
     */
    public static void main(String[] args) {
        launch(args);
    }
}
