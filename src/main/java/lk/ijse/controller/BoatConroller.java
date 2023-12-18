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
import lk.ijse.dto.tm.BoatTm;
import lk.ijse.model.BoatModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoatConroller {
    public TableView boarTbl;
    public TableColumn colId;
    public TableColumn colStatus;
    public TableColumn colModel;
    public TableColumn colCapacity;
    public AnchorPane imageAnchore;
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
    private AnchorPane rootNode;

    @FXML
    private TextField txtCapacity;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModels;

    @FXML
    private TextField txtStatus;
    public BoatModel Boatmodel = new BoatModel();

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/staff&others.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
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
    void saveOnAction(ActionEvent event) {
        boolean isValid = validateBoat();
        if (isValid){
            new Alert(Alert.AlertType.CONFIRMATION,"Boat Validates");
            String id = txtId.getText();
            String capacity = txtCapacity.getText();
            String status = txtStatus.getText();
            String model = txtModels.getText();
            var dto = new BoatDto(id,capacity,status,model);

            try {
                boolean isSaved = Boatmodel.saveBoat(dto);
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

    private boolean validateBoat() {
        String id = txtId.getText();
        Pattern compile = Pattern.compile("[B][0-9]{3,}");
        Matcher matcher = compile.matcher(id);
        boolean matches = matcher.matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid Boat ID").show();
            return false;
        }
        String capacity = txtCapacity.getText();
        boolean matches1 = Pattern.matches("^[A-Za-z0-9-]+$", capacity);
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid Capacity").show();
            return false;
        }
        String status = txtStatus.getText();
        boolean matches2 = Pattern.matches("[A-Za-z]{4,}",txtStatus.getText());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid Status").show();
            return false;
        }
        String model = txtModels.getText();
        boolean matches3 = Pattern.matches("^[A-Za-z0-9-]+$", txtModels.getText());
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Invalid Model").show();
            return false;
        }
        return true;
    }

    @FXML
    void updateOnACtion(ActionEvent event) {
        String id = txtId.getText();
        String capacity = txtCapacity.getText();
        String status = txtStatus.getText();
        String model = txtModels.getText();
        var dto = new BoatDto(id,capacity,status,model);

        try {
            boolean isUpdated = Boatmodel.updateBoat(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                loadAllCustomer();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();
        try {
            BoatDto boatDto = Boatmodel.SearchAll(id);
            if (boatDto != null){
                txtId.setText(boatDto.getId());
                txtCapacity.setText(boatDto.getCapacity());
                txtStatus.setText(boatDto.getStatus());
                txtModels.setText(boatDto.getModel());


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
        txtCapacity.setText("");
        txtStatus.setText("");
        txtModels.setText("");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            boolean isDeleted = Boatmodel.deleteBoat(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
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
        ObservableList<BoatTm> oblist = FXCollections.observableArrayList();
        try {
            List<BoatDto> dtoList = Boatmodel.getAll();
            for (BoatDto dto : dtoList) {
                oblist.add(new BoatTm(dto.getId(),dto.getCapacity(),dto.getStatus(),dto.getModel()));
            }
            boarTbl.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    }

}
