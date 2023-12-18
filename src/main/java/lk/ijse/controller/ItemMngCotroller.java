package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Appinitializer;
import lk.ijse.db.Dbconnection;
import lk.ijse.dto.BoatDto;
import lk.ijse.dto.InsructorDto;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.tm.BoatTm;
import lk.ijse.dto.tm.ItemTm;
import lk.ijse.model.ItemMngModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemMngCotroller {
    public AnchorPane rootNode;

    @FXML
    private Label lblFins;

    @FXML
    private Label lblMak;

    @FXML
    private Label lblRegulator;

    public TextField txtQty;
    public TextField txtId;
    public TextField txtBrand;
    public TextField txtSize;
    public TextField txtName;
    public TextField txtPrice;
    public Button clearbtn;
    public Button updatebtn;
    public Button savebtn;
    public ImageView deletebtn;

    public ItemMngModel model = new ItemMngModel();

    public void logoutbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.centerOnScreen();
        Stage.show();
    }

    public void customerbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customerMng.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/customer.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Customer Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void itembtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/itemMng.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.centerOnScreen();
        Stage.show();
    }

    public void staffbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/staff&others.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Staff & Others Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void shedulebtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/shedule.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/reservation.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.centerOnScreen();
        Stage.show();
    }

    public void reservationbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/reservation.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/reservation.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("reservation Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void clearbtnOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtBrand.setText("");
        txtSize.setText("");
        txtPrice.setText("");
        txtQty.setText("");
    }

    public void savebtnOnACtion(ActionEvent actionEvent) {
        boolean isValid = validateItem();
        if (isValid){
            String id = txtId.getText();
            String name = txtName.getText();
            String brand = txtBrand.getText();
            String size = txtSize.getText();

            try {
                double price = Double.parseDouble(txtPrice.getText());
                int qty = Integer.parseInt(txtQty.getText());

                var dto = new ItemDto(id, name, brand, size, price, qty);

                boolean isSaved = model.saveItem(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                    System.out.println("Done");
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid price or quantity input. Please enter numeric values.").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error saving item: " + e.getMessage()).show();
            }
        }
       /* String id = txtId.getText();
        String name = txtName.getText();
        String brand = txtBrand.getText();
        String size = txtSize.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());


        var dto = new ItemDto(id,name,brand,size,Double.parseDouble(String.valueOf(price)), Integer.parseInt(String.valueOf(qty)));

        try {
            boolean isSaved = model.saveItem(dto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                System.out.println("Done");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
    }

    private boolean validateItem() {
       String id = txtId.getText();
        Pattern compile = Pattern.compile("[I][0-9]{3,}");
        Matcher matcher = compile.matcher(id);
        boolean matches = matcher.matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid Item ID").show();
            return false;
        }
        String name = txtName.getText();
        boolean matches1 = Pattern.matches("^[A-Za-z ]{4,}$", name);
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid Name").show();
            return false;
        }
        String brand = txtBrand.getText();
        boolean matches2 = Pattern.matches("^[A-Za-z ]{3,}$",txtBrand.getText());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid Brand").show();
            return false;
        }
        String size = txtSize.getText();
        boolean matches3 = Pattern.matches("^[A-Za-z ]{4,}$", txtSize.getText());
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Invalid size").show();
            return false;
        }
        double price = Double.parseDouble(txtPrice.getText());
        boolean matches4 = Pattern.matches("^[+-]?\\d+$", txtPrice.getText());
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Invalid Price").show();
            return false;
        }
        int qty = Integer.parseInt(txtQty.getText());
        boolean matches5 = Pattern.matches("\\d+", txtQty.getText());
        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"Invalid qty").show();
            return false;
        }
        return true;

    }

    public void backbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.centerOnScreen();
        Stage.show();
    }
    
    public void updateBtnOnAction(ActionEvent actionEvent) {
       /* String id = txtId.getText();
        String name = txtName.getText();
        String brand = txtBrand.getText();
        String size = txtSize.getText();
        String price = txtSize.getText();
        String qty = txtQty.getText();


        var dto = new ItemDto(id,name,brand,size,Double.parseDouble(price), Integer.parseInt(qty));

        try {
            boolean isUpdated = model.updateItem(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                System.out.println("Done");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
        String id = txtId.getText();
        String name = txtName.getText();
        String brand = txtBrand.getText();
        String size = txtSize.getText();

        try {
            double price = Double.parseDouble(txtPrice.getText());
            int qty = Integer.parseInt(txtQty.getText());

            var dto = new ItemDto(id, name, brand, size, price, qty);

            boolean isUpdated = model.updateItem(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                System.out.println("Done");
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid price or quantity input. Please enter numeric values.").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error saving item: " + e.getMessage()).show();
        }
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            boolean isDeleted = model.deleteItem(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void deletebtnOnAction(MouseEvent mouseEvent) {
    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();
        try {
            ItemDto itemDto = model.SearchAll(id);
            if (itemDto != null){
                txtId.setText(itemDto.getId());
                txtName.setText(itemDto.getName());
                txtBrand.setText(itemDto.getBrand());
                txtSize.setText(itemDto.getSize());
                txtPrice.setText(String.valueOf(itemDto.getPrice()));
                txtQty.setText(String.valueOf(itemDto.getQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    /*public void initialize() {
        loadAllCustomer();
    }

    private void loadAllCustomer() {
        ObservableList<ItemTm> oblist = FXCollections.observableArrayList();
        try {
            List<ItemDto> dtoList = ItemMngModel.getAll();
            for (ItemDto dto : dtoList) {
                oblist.add(new ItemTm(dto.getId(),dto.getName(),dto.getBrand(),dto.getSize(),dto.getPrice(),dto.getQty()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    public void initialize(){
        setMarsks();
        setFins();
        setRegulator();
    }

    private void setFins() {
        try {
            String itemId = "I002";
            ItemDto itemDto = model.SearchAll(itemId);

            if (itemDto != null) {
                int qtyOnHand = itemDto.getQty();
                lblFins.setText(String.valueOf(qtyOnHand));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setMarsks() {

        try {
            String itemId = "I002";
            ItemDto itemDto = model.SearchAll(itemId);

            if (itemDto != null) {
                int qtyOnHand = itemDto.getQty();
                lblMak.setText(String.valueOf(qtyOnHand));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setRegulator(){
        try {
            String itemId = "I003";
            ItemDto itemDto = model.SearchAll(itemId);

            if (itemDto != null) {
                int qtyOnHand = itemDto.getQty();
                lblRegulator.setText(String.valueOf(qtyOnHand));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void reportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/ItemReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport, //compiled report
                        null,
                        Dbconnection.getInstance().getConnection() //database connection
                );

        JasperViewer.viewReport(jasperPrint, false);


    }
}