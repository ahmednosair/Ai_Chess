package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 1000, 650));
        showStartAlert(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);

    }

    private void showStartAlert(Stage primaryStage) {
        Alert startAlert = new Alert(Alert.AlertType.CONFIRMATION);
        startAlert.setTitle("Chess");
        startAlert.setHeaderText("Welcome to Chess game");
        startAlert.setContentText("Please choose one of the options below");
        ButtonType newSingle = new ButtonType("New game (Single Player)");
        ButtonType newMulti = new ButtonType("New game (Multi Player)");
        ButtonType exit = new ButtonType("Exit");
        startAlert.getButtonTypes().setAll(newSingle, newMulti, exit);
        Optional<ButtonType> outcome = startAlert.showAndWait();
        if (outcome.isPresent() && outcome.get() == newSingle) {
            Controller.setGameMode(1);
            primaryStage.show();
        } else if (outcome.isPresent() && outcome.get() == newMulti) {
            Controller.setGameMode(2);
            primaryStage.show();
        } else if (outcome.isPresent() && outcome.get() == exit) {
            Platform.exit();
        }

    }

}
