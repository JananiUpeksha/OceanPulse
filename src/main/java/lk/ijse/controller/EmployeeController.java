package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Appinitializer;
import lk.ijse.db.Dbconnection;
import lk.ijse.dto.BoatDto;
import lk.ijse.dto.DriverDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.tm.BoatTm;
import lk.ijse.dto.tm.EmployeeTm;
import lk.ijse.model.EmployeeModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TableView employeeTbl;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    @FXML
    private Button backbtn;

    @FXML
    private Button customerbtn;

    @FXML
    private Button itembtn;

    @FXML
    private Button logoutbtn;

    @FXML
    private Button reservationbtn;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Button shedulebtn;

    @FXML
    private Button staffbtn;

    public EmployeeModel model = new EmployeeModel();

    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/staff&others.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void customerbtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customerMng.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Customer Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void itembtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/itemMng.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Item Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void logoutbtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Login Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void reservationbtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/reservation.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Reservation Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void shedulebtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/shedule.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Shedule Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void staffbtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/staff&others.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Staff & Others Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        boolean isValid = validateEmployee();
        if (isValid){
            String id = txtId.getText();
            String name = txtName.getText();
            String contact = txtContact.getText();
            String address = txtAddress.getText();
            var dto = new EmployeeDto(id,name,contact,address);

            try {
                boolean isSaved = model.saveEmployee(dto);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                    loadAllCustomer();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private boolean validateEmployee() {
        String id = txtId.getText();
        boolean matches1 = Pattern.matches("[E][0-9]{3,}", txtId.getText());
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            return false;
        }

        String name = txtId.getText();
        boolean matches2 = Pattern.matches("^[A-Za-z ]{4,}$", txtName.getText());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid Name").show();
            return false;
        }
        String address = txtAddress.getText();
        boolean matches3 = Pattern.matches("[A-Za-z]{4,}", txtAddress.getText());
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Invalid Address").show();
            return false;
        }
        String contact = txtContact.getText();
        boolean matches5 = Pattern.matches("\\d{10}", txtContact.getText());
        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"Invalid Contact").show();
            return false;
        }

        return true;
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        var dto = new EmployeeDto(id,name,contact,address);


        try {
            boolean isUpdated = model.updateEmployee(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                loadAllCustomer();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {try {
        String id = txtId.getText();
        EmployeeDto employeeDto = model.SearchAll(id);
        if (employeeDto != null){
            txtId.setText(employeeDto.getId());
            txtName.setText(employeeDto.getName());
            txtAddress.setText(employeeDto.getAddress());
            txtContact.setText(employeeDto.getContact());
        }
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }

    }
    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            boolean isDeleted = model.deleteEmployee(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void clearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }
    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();
    }

    private void loadAllCustomer() {
        ObservableList<EmployeeTm> oblist = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> dtoList = model.getAll();
            for (EmployeeDto dto : dtoList) {
                oblist.add(new EmployeeTm(dto.getId(),dto.getName(),dto.getAddress(),dto.getContact()));
            }
            employeeTbl.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }
}
