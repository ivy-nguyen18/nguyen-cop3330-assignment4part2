/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ToDoListApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ToDoList.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("ToDoList.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("MyToDoList");
            ToDoListController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
