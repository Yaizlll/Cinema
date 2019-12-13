package sample;

import Seances.ListeSeances;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    public static void changeWindow(ActionEvent e, String path){
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Scene signInScene = new Scene(root, 1300, 700);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../xml/LoginScreen.fxml"));
        Scene scene = new Scene(root, 1300, 700);
        primaryStage.setTitle("MyCinema");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("../res/icon.png")));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Timeline updateSeances = new Timeline(new KeyFrame(Duration.hours(1), new EventHandler() {
            @Override
            public void handle(Event event) {
                ListeSeances.updateSeance();
            }
        }));
        updateSeances.setCycleCount(Timeline.INDEFINITE); updateSeances.play();

        launch(args);
    }
}