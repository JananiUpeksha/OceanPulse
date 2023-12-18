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
import lk.ijse.dto.InsructorDto;
import lk.ijse.dto.tm.BoatTm;
import lk.ijse.dto.tm.InstructorTm;
import lk.ijse.model.DriverModel;
import lk.ijse.model.InstructorModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class InstructorMngController {
    public TableView instructorTbl;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colConact;
    public TableColumn colQualification;
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

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtQualifications;
    public InstructorModel model = new InstructorModel();

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/staff&others.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void customerbtnOnACtion(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customerMng.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Customer Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void itembtnOnACtion(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/itemMng.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Item Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void logoutbtnOnACtion(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(rootNode);
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
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Reservation Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void shedulebtnOnACtion(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/shedule.fxml"));
        Scene scene = new Scene(rootNode);
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
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Staff & Others Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }
    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            boolean isDeleted = model.deleteInstructor(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        boolean isValid = validateInstructor();
        if (isValid){
            String id = txtId.getText();
            String name = txtName.getText();
            String contact = txtContact.getText();
            String address = txtAddress.getText();
            String qualification = txtQualifications.getText();
            var dto = new InsructorDto(id,name,contact,address,qualification);

            try {
                boolean isSaved = model.saveInstructor(dto);
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

    private boolean validateInstructor() {
       String id = txtId.getText();
        boolean matches1 = Pattern.matches("^Ins[0-9]{3,}$", txtId.getText());
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

        String qualification = txtQualifications.getText();
        boolean matches4 = Pattern.matches("\\b[A-Za-z0-9]+ L\\d+\\b", txtQualifications.getText());
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Invalid Qualification").show();
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
        String qualification = txtQualifications.getText();
        var dto = new InsructorDto(id,name,contact,address,qualification);

        try {
            boolean isUpdated = model.updateInstructor(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                loadAllCustomer();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            InsructorDto insructorDto = model.SearchAll(id);
            if (insructorDto != null){
                txtId.setText(insructorDto.getId());
                txtName.setText(insructorDto.getName());
                txtAddress.setText(insructorDto.getAddress());
                txtContact.setText(insructorDto.getContact());
                txtQualifications.setText(insructorDto.getQualification());

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
        txtQualifications.setText("");
    }
    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();
    }

    private void loadAllCustomer() {
        ObservableList<InstructorTm> oblist = FXCollections.observableArrayList();
        try {
            List<InsructorDto> dtoList = model.getAll();
            for (InsructorDto dto : dtoList) {
                oblist.add(new InstructorTm(dto.getId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getQualification()));
            }
            instructorTbl.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colConact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
    }

}
