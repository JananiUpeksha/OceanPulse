package lk.ijse.controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Appinitializer;
import lk.ijse.db.Dbconnection;
import lk.ijse.dto.AddDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.ScheduleDto;
import lk.ijse.dto.tm.AddTm;
import lk.ijse.dto.tm.ReservationDto;
import lk.ijse.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ReservationController {

    public TextField txtItemQty1;
    public TextField txtItemSize;
    public Button btnAdd;
    public Button btnClear;
    public Label lblTotal;
    public ComboBox comboSId;
    public TableColumn<?,?> colQty;
    public TextField txtId;
    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private ComboBox<String> comboCusId;

    @FXML
    private ComboBox<String> comboItemId;

    @FXML
    private ComboBox<?> comboItemSize;

    @FXML
    private Button customerbtn;

    @FXML
    private Button itembtn;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblId;

    @FXML
    private Label lblTime;

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
    private TableView<AddTm> tblReservation;

    @FXML
    private TextField txtCusAddress;

    @FXML
    private TextField txtCusContact;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusNic;

    @FXML
    private TextField txtItemBrand;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtItemPrice;

    @FXML
    private TextField txtItemQtnOnHand;

    @FXML
    private TextField txtItemQty;

    private CustomerModel customerModel = new CustomerModel();

    private ItemMngModel itemMngModel = new ItemMngModel();
    private OrderModel orderModel = new OrderModel();
    private ObservableList<AddTm> obList = FXCollections.observableArrayList();

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customerMng.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Customer Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void itemOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/itemMng.fxml"));
        Scene scene = new Scene(rootNode);
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
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Login Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void reservationOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/reservation.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Reservation Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void sheduleOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/shedule.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Schedule Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void staffOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/staff&others.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Staff & Others Form");
        Stage.centerOnScreen();
        Stage.show();
    }
    public void dashBoardbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.centerOnScreen();
        Stage.show();
    }
    public void initialize(){
        
        loadDateTime();
        loadCusId();
        loadItemId();
        loadSheduleId();
        loadNextReservationId();
        setCellValueFactory();
    }

    private void loadSheduleId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = SheduleModel.getAll();

            for (String id : idList) {
                obList.add(id);
            }

            comboSId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadNextReservationId() {
        try {
            String orderId = OrderModel.generateNextOrderId();
            txtId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadItemId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ItemDto> itemDto = itemMngModel.getAll();

            for (ItemDto dto : itemDto) {
                obList.add(dto.getId());
            }
            comboItemId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCusId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> idList = customerModel.getAll();

            for (CustomerDto dto : idList) {
                obList.add(dto.getId());
            }

            comboCusId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDateTime() {
        Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(d.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = comboItemId.getValue();
        String size = txtItemSize.getText();
        String name = txtItemName.getText();
        String brand = txtItemBrand.getText();
        double unitPrice = Double.parseDouble(txtItemPrice.getText());
        int qty = Integer.parseInt(txtItemQty.getText());
        Button btn = new Button("Remove");
        String o_id = txtId.getText();

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);

        var dto = new AddDto(id,size,name,brand,unitPrice,qty);
        obList.add(new AddTm(dto.getId(), dto.getSize(), dto.getName(),dto.getBrand(),dto.getUnitPrice(),dto.getQty(),btn));
        tblReservation.setItems(obList);
        if (!obList.isEmpty()) {
            for (int i = 0; i < tblReservation.getItems().size(); i++) {
                if (comboItemId.getValue().equals(id)) {
                    int col_Qty = (int) colQty.getCellData(i);
                    obList.get(i).setQty(qty);

                    calculateTotal();
                    tblReservation.refresh();
                    return;
                }
            }
        }
        var addTm = new AddTm(id,size,name,brand,unitPrice,qty,new Button());

        obList.add(addTm);

        tblReservation.setItems(obList);
        calculateTotal();
        txtItemQty.clear();
    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblReservation.getItems().size(); i++) {
            double subtotal = (double) colPrice.getCellData(i) * (int) colQty.getCellData(i);
            total += subtotal;
        }
        lblTotal.setText(String.valueOf(total));
    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblReservation.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblReservation.refresh();
                calculateTotal();
            }
        });
    }


    public void comboCusIdOnAction(ActionEvent actionEvent) {
        /*String c_id = comboCusId.getValue();

        try {
            CustomerDto customerDto = customerModel.SearchAll(c_id);
            txtCusName.setText(customerDto.getName());
            txtCusAddress.setText(customerDto.getAddress());
            txtCusContact.setText(customerDto.getContact());
            txtCusNic.setText(customerDto.getNIC());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        String c_id = comboCusId.getValue();

        try {
            CustomerDto customerDto = customerModel.SearchAll(c_id);
            if (customerDto != null) {
                txtCusName.setText(customerDto.getName());
                txtCusAddress.setText(customerDto.getAddress());
                txtCusContact.setText(customerDto.getContact());
                txtCusNic.setText(customerDto.getNIC());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comboItemIdOnAction(ActionEvent actionEvent) {
        String i_id = comboItemId.getValue();

        txtItemQty.requestFocus();
        try {
            ItemDto dto = ItemMngModel.SearchAll(i_id);
            txtItemName.setText(dto.getName());
            txtItemBrand.setText(dto.getBrand());
            txtItemSize.setText(dto.getSize());
            txtItemQtnOnHand.setText(String.valueOf(dto.getQty()));
            txtItemPrice.setText(String.valueOf(dto.getPrice()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnNewOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customerMng.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Add New Customer");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void btnreciptOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/Recipe.jrxml");
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

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        String id = comboItemId.getValue();
        LocalDate date = LocalDate.parse(lblDate.getText());
        int qty = Integer.parseInt(txtItemQty.getText());
        String c_id = comboCusId.getValue();
       // String s_id = comboSheduleId.getValue;
        String s_id = (String) comboSId.getValue();
        String o_id = txtId.getText();


        List<AddTm> List = new ArrayList<>();
        for (int i = 0; i < tblReservation.getItems().size(); i++) {
            AddTm addTm = obList.get(i);

            List.add(addTm);
        }
        var dto = new ReservationDto(id, date, qty,c_id,s_id,o_id,List);
        try {
            boolean isSuccess = ReservationModel.reservation(dto,id);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
