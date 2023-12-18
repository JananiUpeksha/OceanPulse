package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Appinitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));

        Scene scene = new Scene(rootNode);
        //scene.getStylesheets().add(Appinitializer.class.getResource("/style/login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Page");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
