package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TexetField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.VehicleBo;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Vehicle;
import lk.ijse.Tdm.VehicleTm;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VehicleFormController {
    @FXML
    private AnchorPane MainAnchopane;


    @FXML
    private TableColumn<?, ?> colBookingCost;

    @FXML
    private TableColumn<?, ?> colModelName;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private TableColumn<?, ?> colNumberPlate;

    @FXML
    private TableColumn<?, ?> colCurrentMilage;

    @FXML
    private TableColumn<?, ?> colAfter100KmCharge;

    @FXML
    private TableColumn<?, ?> colFirst100KmCharge;




    @FXML
    private TextField txtBookingCost;

    @FXML
    private TextField txtModelName;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtVehicleId;


    @FXML
    private TextField txtCurrentMilage;

    @FXML
    private TextField txtNmberPlate;

    @FXML
    private TextField txtAfter100Km;

    @FXML
    private TextField txtFirst100Km;

    @FXML
    private AnchorPane vehicleAnchopane;

    @FXML
    private TableView<VehicleTm> vehicleTable;

  //  VehicleDao vehicleDao = new VehicleDaoImpl();

    VehicleBo vehicleBo = (VehicleBo) BOFactory.getBo(BOFactory.BOTypes.VEHICLE);

    public void initialize(){
        setCellValueFactory();
        loadAllVehicles();
    }

    private void loadAllVehicles() {
        ObservableList<VehicleTm> obList = FXCollections.observableArrayList();

        try {
            List<VehicleDTO> vehicleList = vehicleBo.getAll();
            for (VehicleDTO vehicle : vehicleList){
                VehicleTm tm = new VehicleTm(
                        vehicle.getVehicleId(),
                        vehicle.getModelName(),
                        vehicle.getBookingCost(),
                        vehicle.getNumberPlate(),
                        vehicle.getCurrentMilage(),
                        vehicle.getFirst_100Km_1km_charge(),
                        vehicle.getAfter_100Km_1km_charge(),
                        vehicle.getUserId()
                );
                obList.add(tm);
            }
            vehicleTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colModelName.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        colBookingCost.setCellValueFactory(new PropertyValueFactory<>("bookingCost"));
        colNumberPlate.setCellValueFactory(new PropertyValueFactory<>("numberPlate"));
        colCurrentMilage.setCellValueFactory(new PropertyValueFactory<>("currentMilage"));
        colFirst100KmCharge.setCellValueFactory(new PropertyValueFactory<>("first_100Km_1km_charge"));
        colAfter100KmCharge.setCellValueFactory(new PropertyValueFactory<>("after_100Km_1km_charge"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    @FXML
    void btnBackToDashBoard(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) vehicleAnchopane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtVehicleId.setText("");
        txtModelName.setText("");
        txtBookingCost.setText("");
        txtNmberPlate.setText("");
        txtCurrentMilage.setText("");
        txtFirst100Km.setText("");
        txtAfter100Km.setText("");
        txtUserId.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String vehicleId = txtVehicleId.getText();

        try {
            boolean isDeleted = vehicleBo.delete(vehicleId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "vehicle deleted!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String vehicleId = txtVehicleId.getText();
        String modelName = txtModelName.getText();
        Double bookingCost = Double.valueOf(txtBookingCost.getText());
        String numberPlate = txtNmberPlate.getText();
        Double currentMilage = Double.valueOf(txtCurrentMilage.getText());
        Double first_100Km_1km_charge = Double.valueOf(txtFirst100Km.getText());
        Double after_100Km_1km_charge = Double.valueOf(txtAfter100Km.getText());
        String userId = txtUserId.getText();

        Vehicle vehicle  = new Vehicle( vehicleId,modelName,bookingCost,numberPlate,currentMilage,first_100Km_1km_charge,after_100Km_1km_charge,userId);
        if (isValied()) {
            boolean isSaved = vehicleBo.save(new VehicleDTO(vehicleId, modelName, bookingCost, numberPlate, currentMilage, first_100Km_1km_charge,after_100Km_1km_charge,userId));

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                initialize();
                clearFields();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Details is Invalied !").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String vehicleId = txtVehicleId.getText();
        String modelName = txtModelName.getText();
        Double bookingCost = Double.valueOf(txtBookingCost.getText());
        String numberPlate = txtNmberPlate.getText();
        Double currentMilage = Double.valueOf(txtCurrentMilage.getText());
        Double first_100Km_1km_charge = Double.valueOf(txtFirst100Km.getText());
        Double after_100Km_1km_charge = Double.valueOf(txtAfter100Km.getText());
        String userId = txtUserId.getText();

        VehicleDTO vehicle  = new VehicleDTO( vehicleId,modelName,bookingCost,numberPlate,currentMilage,first_100Km_1km_charge,after_100Km_1km_charge,userId);

        try {
            boolean isUpdated = vehicleBo.update(vehicle);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "vehicle updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String vehicleId = txtVehicleId.getText();

        VehicleDTO vehicle = vehicleBo.searchById(vehicleId);
        if (vehicle != null){
            txtVehicleId.setText(vehicle.getVehicleId());
            txtModelName.setText(vehicle.getModelName());
            txtBookingCost.setText(String.valueOf(vehicle.getBookingCost()));
            txtNmberPlate.setText(vehicle.getNumberPlate());
            txtCurrentMilage.setText(String.valueOf(vehicle.getCurrentMilage()));
            txtFirst100Km.setText(String.valueOf(vehicle.getFirst_100Km_1km_charge()));
            txtAfter100Km.setText(String.valueOf(vehicle.getAfter_100Km_1km_charge()));
            txtUserId.setText(vehicle.getUserId());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Vehicle  not found!").show();
        }

    }

    @FXML
    void mouseClickOnAction(MouseEvent event) {
        Integer index = vehicleTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtVehicleId.setText(colVehicleId.getCellData(index).toString());
        txtModelName.setText(colModelName.getCellData(index).toString());
        txtBookingCost.setText(colBookingCost.getCellData(index).toString());
        txtNmberPlate.setText(colNumberPlate.getCellData(index).toString());
        txtCurrentMilage.setText(colCurrentMilage.getCellData(index).toString());
        txtFirst100Km.setText(colFirst100KmCharge.getCellData(index).toString());
        txtAfter100Km.setText(colAfter100KmCharge.getCellData(index).toString());
        txtUserId.setText(colUserId.getCellData(index).toString());

    }

    @FXML
    void txtVehicleIDOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.ID,txtVehicleId);
    }

    @FXML
    void txtVehicleModelNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.NAME,txtModelName);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(TexetField.ID,txtVehicleId)) return false;
        if (!Regex.setTextColor(TexetField.NAME,txtModelName)) return false;

        return true;
    }
}


