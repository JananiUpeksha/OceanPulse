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

import java.io.IOException;

public class NewController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPasswordconfirm;

    @FXML
    private TextField txtUserName;
    private static final String UserName = "user";
    private static String Password = "1234";

    @FXML
    void loginOnAcion(ActionEvent event) throws IOException {
        String enteredUserName = txtUserName.getText();
        String enteredPassword = txtPassword.getText();
        String confirmedPassword = txtPasswordconfirm.getText();

        if (enteredUserName.equals(UserName)) {
            if (enteredPassword.equals(confirmedPassword)) {

                Password = enteredPassword;
                try {
                    Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
                    Scene scene = new Scene(rootNode);
                    Stage stage = (Stage) this.rootNode.getScene().getWindow();
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Entered credentials are invalid.").show();
        }
    }
}
