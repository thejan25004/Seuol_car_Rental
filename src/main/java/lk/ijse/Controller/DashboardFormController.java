package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashboardBo;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.Tdm.CustomerTm;
import lk.ijse.Tdm.SupplierTm;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.time.ZoneId;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class DashboardFormController {

    @FXML
    private AnchorPane main2Anchopane;

    @FXML
    private AnchorPane MainAnchopane;

    @FXML
    private AnchorPane iconOfAnchorpane;

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblSupplierCount;

    @FXML
    private Label lblVehicleCount;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBailVehicleNm;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;


    @FXML
    private TableColumn<?, ?> colSupplierContatct;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableColumn<?, ?> colSupplierSpecialCar;

    @FXML
    private TableColumn<?, ?> colSupplierSpecialCarCost;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private TableView<CustomerTm> customerTable;

    @FXML
    private TableView<SupplierTm> tableSupplier;

    @FXML
    private Label lblDate;

    private int customerCount;
    private int vehicleCount;
    private int SupplirCount;

    DashboardBo dashboardBo = (DashboardBo) BOFactory.getBo(BOFactory.BOTypes.DASHBOARD);

    public void initialize(){
        setDate();
        setCellValueFactory();
        loadAllCustomers();
        setCellValueFactoryForSuppliers();
        loadAllSuppliers();
        try {
            customerCount = getCustomerCount();
            vehicleCount = getVehicleCount();
            SupplirCount = getSupplierCount();
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setCustomerCount(customerCount);
        setVehicleCount(vehicleCount);
        setSupplierCount(SupplirCount);
    }

    private void setDate() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        lblDate.setText(String.valueOf(localDate));
    }

    private void loadAllSuppliers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> supplierList = dashboardBo.getAll();
            for (SupplierDTO supplier : supplierList){
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierId(),
                        supplier.getName(),
                        supplier.getContact(),
                        supplier.getSpecialCar(),
                        supplier.getSpecialCarCost(),
                        supplier.getVehicleId()
                );

                obList.add(tm);
            }
            tableSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactoryForSuppliers() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierContatct.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSupplierSpecialCar.setCellValueFactory(new PropertyValueFactory<>("specialCar"));
       // colSupplierSpecialCarCost.setCellValueFactory(new PropertyValueFactory<>("specialCarCost"));
       // colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
    }


    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDTO> CustomerList = dashboardBo.getAllDesc();
            for (CustomerDTO customer : CustomerList){
                CustomerTm tm = new CustomerTm(
                        customer.getCustomerld(),
                        customer.getName(),
                        customer.getContact(),
                        customer.getEmail(),
                        customer.getAddress(),
                        customer.getNic(),
                        customer.getBailVehicleNm()
                );

                obList.add(tm);
            }
            customerTable.setItems(obList);
        } catch (SQLException e) {

        }
    }

    private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerld"));
        colName.setCellValueFactory(new  PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new  PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new  PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new  PropertyValueFactory<>("nic"));
        colBailVehicleNm.setCellValueFactory(new  PropertyValueFactory<>("bailVehicleNm"));

    }

    private void setSupplierCount(int supplirCount) {
        lblSupplierCount.setText(String.valueOf(vehicleCount));
    }

    private int getSupplierCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS supplier_count FROM Suppliers";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("supplier_count");
        }
        return 0;
    }

    private void setVehicleCount(int vehicleCount) {
        lblVehicleCount.setText(String.valueOf(vehicleCount));
    }

    private int getVehicleCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS vehicle_count FROM Vehicles";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("vehicle_count");
        }
        return 0;
    }

    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    private int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS customer_count FROM customer";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("customer_count");
        }

        return 0;
    }

    @FXML
    void customerOptionbtn(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }

    }

    @FXML
    void vehicleOptionbtn(ActionEvent event)  throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/vehicle_form.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/vehicle_form.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }

    }

    @FXML
    void employeeOptionbtn(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }

    }


    @FXML
    void returnsOptionbtn(ActionEvent event) throws IOException {
       AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/return_form.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/return_form.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }


    @FXML
    void suppliersOprtionbtn(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Supplier-form.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/Supplier-form.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }

    }

    @FXML
    void reservationOptionbtn(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/reservation_form.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/reservation_form.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

      }

    }


    @FXML
    void UserSetting(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/checkIdAndPassword.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("User Setting ");
        stage.show();
    }




    @FXML
    void dashBoardtoolAnchopane(MouseEvent event) {


    }
    @FXML
    void btnLogOutAOnAction(ActionEvent event) throws IOException {

        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to log out?", yes, no).showAndWait();

        if(type.orElse(no) == yes) {
            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/log_in_form.fxml"));

            Scene scene = new Scene(rootNode);

            Stage stage = (Stage) this.MainAnchopane.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        }
    }

    @FXML
    void EmailOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/email_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Send Emails");
        stage.show();

    }


    @FXML
    void aboutUsOnAction(ActionEvent event) throws IOException {
    Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/aboutUs_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("SAbout Us");
        stage.show();
    }







}
