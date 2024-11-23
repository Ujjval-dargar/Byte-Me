package com.application.assignment4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Select_User.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        HelloController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("ByteMe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
