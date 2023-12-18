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
import lk.ijse.dto.BoatDto;
import lk.ijse.dto.DriverDto;
import lk.ijse.dto.tm.BoatTm;
import lk.ijse.dto.tm.DriverTm;
import lk.ijse.model.DriverModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class DriverController {
    public TableView driverTbl;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colExperience;
    @FXML
    private Button customerbtn;

    @FXML
    private Button itembtn;

    @FXML
    private Button logoutbtn;

    @FXML
    private Button reservationbtn;

    @FXML
    private Button shedulebtn;

    @FXML
    private Button staffbtn;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextArea txtExperience;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private AnchorPane rootNode;


    public DriverModel model = new DriverModel();

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
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
        Stage.setTitle("Customer Manage Form");
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
    void logoutOnAction(ActionEvent event) throws IOException {
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
    void reservationbtnOnACtion(ActionEvent event) throws IOException {
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
        Stage.setTitle("Schedule Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void staffbtnOnACtion(ActionEvent event) throws IOException {
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
    void clearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtExperience.setText("");
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        boolean isValid = validateDriver();
        if (isValid){
            String id = txtId.getText();
            String name = txtName.getText();
            String contact = txtContact.getText();
            String address = txtAddress.getText();
            String experience = txtExperience.getText();
            var dto = new DriverDto(id,name,contact,address,experience);

            try {
                boolean isSaved = model.saveDriver(dto);
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

    private boolean validateDriver() {
        String id = txtId.getText();
        boolean matches1 = Pattern.matches("[D][0-9]{3,}", txtId.getText());
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

        String experience = txtExperience.getText();
        boolean matches4 = Pattern.matches("(?i)(more than\\\\s*\\\\d+\\\\s*years)|(\\\\d+\\\\s*years)", txtExperience.getText());
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Invalid Experience").show();
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
        String experience = txtExperience.getText();
        var dto = new DriverDto(id,name,contact,address,experience);


        try {
            boolean isUpdated = model.updateDriver(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                loadAllCustomer();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            boolean isDeleted = model.deleteDriver(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) { String id = txtId.getText();
        try {
            DriverDto driverDto = model.SearchAll(id);
            if (driverDto != null){
                txtId.setText(driverDto.getId());
                txtName.setText(driverDto.getName());
                txtAddress.setText(driverDto.getAddress());
                txtContact.setText(driverDto.getContact());
                txtExperience.setText(driverDto.getContact());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();
    }

    private void loadAllCustomer() {
        ObservableList<DriverTm> oblist = FXCollections.observableArrayList();
        try {
            List<DriverDto> dtoList = model.getAll();
            for (DriverDto dto : dtoList) {
                oblist.add(new DriverTm(dto.getId(),dto.getName(),dto.getContact(),dto.getAddress(),dto.getExperience()));
            }
            driverTbl.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));
    }

}
