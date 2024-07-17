package lk.ijse.Controller;


//import com.google.zxing.WriterException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationBo;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.ReservationDao;
import lk.ijse.dao.custom.VehicleDao;
import lk.ijse.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.dao.custom.impl.ReservationDaoImpl;
import lk.ijse.dao.custom.impl.VehicleDaoImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.ReservationDetails;
import lk.ijse.entity.Vehicle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ReservationFormController {

    @FXML
    private DatePicker DPickerReservationDate;

    @FXML
    private DatePicker DPickerReturnDate;

    @FXML
    private ComboBox<String> cmbReservationVehicleId;

    @FXML
    private ComboBox<String> cmbReservationCustomerName;

    @FXML
    private Label lblBokkingCost;

    @FXML
    private Label lblCurrentMilage;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblModelName;

    @FXML
    private Label lblNumberPlate;

    @FXML
    private Label lblReservationCustomerIBailvehicle;

    @FXML
    private Label lblReservationCustomerId;


    @FXML
    private Label lblReservationId;

    @FXML
    private Label lblTotlPrice;

    @FXML
    private AnchorPane main2Anchopane;

    @FXML
    private Button btnPrintBill;

    ReservationBo reservationBo = (ReservationBo) BOFactory.getBo(BOFactory.BOTypes.RESERVATION);


  //  CustomerDao customerDao = new CustomerDaoImpl();
   // VehicleDao vehicleDao = new VehicleDaoImpl();
  //  ReservationDao reservationDao = new ReservationDaoImpl();


    public void initialize(){
        setDate();
        getCurrentResevationId();
        getVehicleValues();
        getCustomerValues();
    }

    private void getCustomerValues() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> cusList = reservationBo.getNameDetails();

            for(String numberPlate : cusList) {
                obList.add(numberPlate);
            }

            cmbReservationCustomerName.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getVehicleValues() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> valueList = reservationBo.getIdsForReservaton();

            for(String numberPlate : valueList) {
                obList.add(numberPlate);
            }

            cmbReservationVehicleId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentResevationId() {
        try {
            String currentId = reservationBo.getCurrentId();

            String nextReservationId = generateNextResrvationId(currentId);
            lblReservationId.setText(nextReservationId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateNextResrvationId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("Reservation");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "Reservation" + ++idNum;
        }
        return "Reservation1";
    }

    private void setDate() {
       // Date date = new Date();
     //   LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
     //   lblDate.setText(String.valueOf(localDate));
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
        lblReservationCustomerId.setText("");
        lblReservationCustomerIBailvehicle.setText("");
        lblBokkingCost.setText("");
        lblModelName.setText("");
        lblCurrentMilage.setText("");
        lblNumberPlate.setText("");
        lblTotlPrice.setText("");

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        ReservationDetails reservationDetails = new ReservationDetails(lblReservationId.getText(), Date.valueOf(DPickerReservationDate.getValue()),Date.valueOf(DPickerReturnDate.getValue()),lblReservationCustomerId.getText(),cmbReservationVehicleId.getValue());

        try {
            if (reservationBo.reservationComplete(reservationDetails)){
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation Successfully !!").show();


            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation Unsuccessfully !!").show();

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }



    }

    @FXML
    void cmbReservationOnAction(ActionEvent event) {
        String vehicleId = cmbReservationVehicleId.getValue();
        try {
            VehicleDTO vehicle = reservationBo.searchById(vehicleId);

            lblNumberPlate.setText(vehicle.getNumberPlate());
            lblModelName.setText(vehicle.getModelName());
            lblBokkingCost.setText(String.valueOf(vehicle.getBookingCost()));
            lblCurrentMilage.setText(String.valueOf(vehicle.getCurrentMilage()));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        double bookingCost = Double.parseDouble(lblBokkingCost.getText());

        double totalCost = 0;


            totalCost += bookingCost;
            // totalCost += booking;


        lblTotlPrice.setText(String.valueOf(totalCost));
    }

    @FXML
    void cmbCustomerOnActionOnAction(ActionEvent event) {
        String name = cmbReservationCustomerName.getValue();
        try {
            CustomerDTO customer = reservationBo.searchByName(name);

            lblReservationCustomerId.setText(customer.getCustomerld());
            lblReservationCustomerIBailvehicle.setText(customer.getBailVehicleNm());



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnReservationViewOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ReservationView.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/ReservationView.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }

    @FXML
    void btnPrintBillOnAction(ActionEvent event) throws JRException, SQLException, IOException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/reservation_bill.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("reservationId",lblReservationId.getText());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);


    }

}
