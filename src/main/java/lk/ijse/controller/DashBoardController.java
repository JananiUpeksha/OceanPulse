package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Appinitializer;
import lk.ijse.model.DashboardModel;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javafx.scene.chart.PieChart;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DashBoardController {
    public AnchorPane chart;
    public Label lblDate;
    public Label lblTime;
    public Label lblName;
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

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customerMng.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/customer.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Customer Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/itemMng.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Staff & Others Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void staffOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/staff&others.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/other.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Staff & Others Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void sheduleOnAction(ActionEvent actionEvent) throws IOException {
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
    void reservationOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/reservation.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/reservation.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("reservation Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void logoutbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(Appinitializer.class.getResource("/style/reservation.css").toExternalForm());
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Login Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void initialize() throws SQLException {
        displayScheduleDistributionChart();
        loadDateTime();
        setName();
    }

    private void setName() throws SQLException {
        String lastCustomerName = DashboardModel.getLastCustomerNameFromDatabase();
        lblName.setText(lastCustomerName);
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
    public void displayScheduleDistributionChart() {
        // Fetch schedule data from your database
        // Fetch schedule data from your database
        try {
            // Fetch schedule data from your model
            ObservableList<PieChart.Data> scheduleData = DashboardModel.getScheduleDataFromDatabase();

            // Create a PieChart
            PieChart pieChart = new PieChart(scheduleData);

            // Set the title with a custom font
            Text titleText = new Text("Schedule Distribution Among Instructors ID ");
            titleText.setFont(Font.font("DejaVuMathTeXGyre-Regular", 18));
            titleText.setTextAlignment(TextAlignment.CENTER);

            // Limit the title width to prevent it from going outside the AnchorPane
            titleText.setWrappingWidth(chart.getWidth() - 15); // Adjust the value as needed

            // Set the layout properties for the title
            titleText.setLayoutX((chart.getWidth() - titleText.getLayoutBounds().getWidth()) / 2);
            titleText.setLayoutY(30); // Adjust the vertical position as needed

            // Set the layout properties for the PieChart
            pieChart.setLayoutX((chart.getWidth() - pieChart.getPrefWidth()) / 2);
            pieChart.setLayoutY(titleText.getLayoutY() + titleText.getLayoutBounds().getHeight() + 30); // Adjust the vertical position as needed

            // Add the PieChart and title to the AnchorPane
            chart.getChildren().clear(); // Clear existing content
            chart.getChildren().addAll(titleText, pieChart);

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    }
