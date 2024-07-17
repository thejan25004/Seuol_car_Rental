package lk.ijse.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReturnBo;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.ReservationDao;
import lk.ijse.dao.custom.ReturnDao;
import lk.ijse.dao.custom.VehicleDao;
import lk.ijse.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.dao.custom.impl.ReservationDaoImpl;
import lk.ijse.dao.custom.impl.ReturnDaoImpl;
import lk.ijse.dao.custom.impl.VehicleDaoImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.ReturnDetails;
import lk.ijse.entity.Vehicle;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javafx.scene.control.Button;



public class ReturnFormController {
    @FXML
    private AnchorPane MainAnchopane;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbReservationId;

    @FXML
    private ComboBox<String> cmbVehicleId;

    @FXML
    private Label lblAfter100KmCharge;

    @FXML
    private Label lblBailVehicleNm;

    @FXML
    private Label lblBookingCost;

    @FXML
    private Label lblCurrentMilage;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblFirst100KmCharge;

    @FXML
    private Label lblModelName;

    @FXML
    private Label lblReturnId;

    @FXML
    private Label lblTotlPrice;



    @FXML
    private Label lblReservationDate;

    @FXML
    private Label lblReturnDate;

    @FXML
    private Label lblNumberPlate;

    @FXML
    private AnchorPane main2Anchopane;

    @FXML
    private TextField txtAfterRideMilage;

    @FXML
    private TextField txtLateReturnCharge;

    @FXML
    private TextField txtVehicleNumberPlate;

    @FXML
    private Label lblReservationId;

    @FXML
    private Label lblReservationCustomerId;


    @FXML
    private Label lblVehicleId;


    @FXML
    private Button btnPrintBill;

    ReturnBo returnBo = (ReturnBo) BOFactory.getBo(BOFactory.BOTypes.RETURN);

  //  CustomerDao customerDao = new CustomerDaoImpl();
    //VehicleDao vehicleDao = new VehicleDaoImpl();
  //  ReservationDao reservationDao = new ReservationDaoImpl();
   //  ReturnDao returnDao = new ReturnDaoImpl();

    public void initialize(){
        setDate();
        getCurrentReturnId();
        getReservationds();
        getCustomerValues();
        getVehicleValues();
        getReturnDetailsValue();
    }

    private void getReturnDetailsValue() {


    }

    private void getVehicleValues() {

             ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> valueList = returnBo.getIdsVehicle();

            for(String numberPlate : valueList) {
                obList.add(numberPlate);
            }

            cmbVehicleId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentReturnId() {

        try {
            String currentId = returnBo.getCurrentId();

            String nextReturnId = generateNextReturnId(currentId);
            lblReturnId.setText(nextReturnId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextReturnId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("Return");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "Return" + ++idNum;
        }
        return "Return1";
    }

    private void getCustomerValues() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> valueList = returnBo.getIds();

            for(String customerld : valueList) {
                obList.add(customerld);
            }

            cmbCustomerId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getReservationds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = returnBo.getIdsForReturns();  //Reservation Repo kalin

            for(String vehicleId : idList) {
                obList.add(vehicleId);
            }

            cmbReservationId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        lblDate.setText(String.valueOf(localDate));
    }

    @FXML
    void btnBackToDashBoard(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) main2Anchopane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnNewOnAction(ActionEvent event) {
        clearFields();


    }

    private void clearFields() {
        lblReturnId.setText("");
        txtAfterRideMilage.setText("");
        txtLateReturnCharge.setText("");
        lblModelName.setText("");
        lblNumberPlate.setText("");
        lblBookingCost.setText("");
        lblCurrentMilage.setText("");
        lblFirst100KmCharge.setText("");
        lblAfter100KmCharge.setText("");
        lblReservationDate.setText("");
        lblReturnDate.setText("");
        lblBailVehicleNm.setText("");
        lblCustomerName.setText("");
        lblTotlPrice.setText("");


    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException, JRException {

        double totalCost = Double.parseDouble(lblTotlPrice.getText());
        ReturnDetails returnDetails = new ReturnDetails(lblReturnId.getText(),txtAfterRideMilage.getText(),txtLateReturnCharge.getText(),totalCost,cmbCustomerId.getValue(),cmbReservationId.getValue(),lblNumberPlate.getText());
        try {
            if (returnBo.returnComplete(returnDetails)){
                new Alert(Alert.AlertType.CONFIRMATION, "Return Successfully !!").show();


            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Return Unsuccessfully !!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String customerld = cmbCustomerId.getValue();
        try {
            CustomerDTO customer = returnBo.searchById(customerld);

            lblCustomerName.setText(customer.getName());
            lblBailVehicleNm.setText(customer.getBailVehicleNm());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbReservationOnAction(ActionEvent event) {
        String reservationId = cmbReservationId.getValue();
        try {
            ReservationDTO reservation = returnBo.searchByIdForReturns(reservationId);

            lblReservationCustomerId.setText(String.valueOf(reservation.getCustomerld()));
            lblReservationDate.setText(String.valueOf(reservation.getReservationDate()));
            lblReturnDate.setText(String.valueOf(reservation.getReturnDate()));


            if (lblDate != lblReturnDate){
                new Alert(Alert.AlertType.ERROR,"The vehicle has been kept for more than the date it was rented ").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbVehicleOnAction(ActionEvent event) {
        String numberPlate = cmbVehicleId.getValue();
        try {
            VehicleDTO vehicle = returnBo.searchBynumberPlate(numberPlate);

            lblNumberPlate.setText(vehicle.getVehicleId());
            lblModelName.setText(vehicle.getModelName());
            lblBookingCost.setText(String.valueOf(vehicle.getBookingCost()));
            lblCurrentMilage.setText(String.valueOf(vehicle.getCurrentMilage()));
            lblFirst100KmCharge.setText(String.valueOf(vehicle.getFirst_100Km_1km_charge()));
            lblAfter100KmCharge.setText(String.valueOf(vehicle.getAfter_100Km_1km_charge()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void actionEventTotalCost(ActionEvent event) {
        double current = Double.parseDouble(lblCurrentMilage.getText());
        double last = Double.parseDouble(txtAfterRideMilage.getText());
        double first100 = Double.parseDouble(lblFirst100KmCharge.getText());
        double next = Double.parseDouble(lblAfter100KmCharge.getText());
        double millage = last - current;

        double totalCost = 0;

        if(millage > 101) {
            totalCost = (first100 * 100) + ((millage - 100) * next);
        } else {
            totalCost = first100 * millage;
        }

        if (!txtLateReturnCharge.getText().equals("")) {
            double late = Double.parseDouble(txtLateReturnCharge.getText());
            //double booking = Double.parseDouble(lblBookingCost.getText());
            totalCost += late;
           // totalCost += booking;
        }

        lblTotlPrice.setText(String.valueOf(totalCost));

    }


    @FXML
    void btnReturnViewOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ReturnView.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/ReturnView.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }

    @FXML
    void btnPrintBillOnAction(ActionEvent event) throws JRException, SQLException {
 JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/RETURN-FORM.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                Map<String,Object> data = new HashMap<>();
                data.put("returnId",lblReturnId.getText());
                data.put("returnDate",lblReturnDate.getTooltip());


                JasperPrint jasperPrint =
                        JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
                JasperViewer.viewReport(jasperPrint,false);
    }
}
