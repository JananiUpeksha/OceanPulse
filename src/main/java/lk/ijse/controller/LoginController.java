package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Appinitializer;

import java.io.IOException;


public class LoginController {
    public AnchorPane anchorPane2;

    //@FXML
    /*void loginOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/dashboard.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Dash Board Form");
        Stage.centerOnScreen();
        Stage.show();
    }*/

    @FXML
    private AnchorPane rootNode;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    private final String UserName = "user";
    private final String Password = "1234";


    public void loginOnAcion(ActionEvent actionEvent) throws IOException {
        String enteredUserName = txtUserName.getText();
        String enteredPassword = txtPassword.getText();

        // Check if the entered username and password match the admin's
        if (enteredUserName.equals(UserName) && enteredPassword.equals(Password)) {
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
            Scene scene = new Scene(rootNode);
            scene.getStylesheets().add(Appinitializer.class.getResource("/style/dashboard.css").toExternalForm());
            Stage Stage = (Stage)this.rootNode.getScene().getWindow();
            Stage.setScene(scene);
            Stage.setTitle("Dash Board Form");
            Stage.centerOnScreen();
            Stage.show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Enterd Credentials Are Invalid ").show();
        }


    }
    @FXML
    void passwordOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/new.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.centerOnScreen();
        Stage.show();

    }

}
