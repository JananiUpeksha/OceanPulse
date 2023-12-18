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
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.InsructorDto;
import lk.ijse.dto.tm.BoatTm;
import lk.ijse.dto.tm.CustomerTm;
import lk.ijse.model.CustomerModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerMngController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtNic;
    public TableView customerTbl;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colNic;
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

    public CustomerModel model = new CustomerModel();

    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/reservation.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Dashboard Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void customerbtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customerMng.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/customer.css").toExternalForm());
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
        Stage.setTitle("Staff & Others Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void logoutbtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(rootNode);
        // scene.getStylesheets().add(appinitializer.class.getResource("/style/item.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Driver Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void reservationbtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/reservation.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/reservation.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("reservation Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void shedulebtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/shedule.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/shedule.css").toExternalForm());
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

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtNic.setText("");
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            boolean isDeleted = model.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String NIC = txtNic.getText();
        var dto = new CustomerDto(id,name,address,contact,NIC);

        try {
            boolean isUpdated = model.updateCustomer(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                loadAllCustomer();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        boolean isValid = validateCustomer();
        if (isValid){
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String contact = txtContact.getText();
            String NIC = txtNic.getText();
            var dto = new CustomerDto(id,name,address,contact,NIC);

            try {
                boolean isSaved = model.saveCustomer(dto);
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

    private boolean validateCustomer() {
        String id = txtId.getText();
        boolean matches1 = Pattern.matches("[C][0-9]{3,}", txtId.getText());
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

        String Nic = txtNic.getText();
        boolean matches4 = Pattern.matches("^\\d+(v)?$", txtNic.getText());
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Invalid Experience").show();
            return false;
        }
        return true;
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            CustomerDto customerDto = model.SearchAll(id);
            if (customerDto != null){
                txtId.setText(customerDto.getId());
                txtName.setText(customerDto.getName());
                txtAddress.setText(customerDto.getAddress());
                txtContact.setText(customerDto.getContact());
                txtNic.setText(customerDto.getNIC());

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
        ObservableList<CustomerTm> oblist = FXCollections.observableArrayList();
        try {
            List<CustomerDto> dtoList = model.getAll();
            for (CustomerDto dto : dtoList) {
                oblist.add(new CustomerTm(dto.getId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getNIC()));
            }
            customerTbl.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
    }

}

