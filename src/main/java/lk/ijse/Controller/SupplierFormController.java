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
import lk.ijse.bo.custom.SupplierBo;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Supplier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.input.MouseEvent;
import lk.ijse.Tdm.SupplierTm;

public class SupplierFormController {
    @FXML
    private AnchorPane MainAnchopane;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSpecialCar;

    @FXML
    private TableColumn<?, ?> colSpecialCarCost;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private AnchorPane main2AnchopaneSupplier;

    @FXML
    private TableView<SupplierTm> supplierTable;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtSpecialCar;

    @FXML
    private TextField txtSpecialCarCost;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtVehicleId;

    @FXML
    private TextField txtname;

  //  SupplierDao supplierDao = new SupplierDaoImpl();
  //  VehicleDao vehicleDao = new VehicleDaoImpl();

    SupplierBo supplierBo = (SupplierBo) BOFactory.getBo(BOFactory.BOTypes.SUPPLIER);


    public void initialize(){
        setCellValueFactory();
        loadAllSuppliers();
    }

    private void loadAllSuppliers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> supplierList = supplierBo.getAll();
            for (SupplierDTO supplier :supplierList ){
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
            supplierTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSpecialCar.setCellValueFactory(new PropertyValueFactory<>("specialCar"));
        colSpecialCarCost.setCellValueFactory(new PropertyValueFactory<>("specialCarCost"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
    }

    @FXML
    void btnBackToDashBoard(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) main2AnchopaneSupplier.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtSupplierId.setText("");
        txtname.setText("");
        txtContact.setText("");
        txtSpecialCar.setText("");
        txtSpecialCarCost.setText("");
        txtVehicleId.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();

        try {
            boolean isDeleted = supplierBo.delete(supplierId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String supplierId = txtSupplierId.getText();
        String name = txtname.getText();
        String contact = txtContact.getText();
        String specialCar = txtSpecialCar.getText();
        Double specialCarCost = Double.valueOf(txtSpecialCarCost.getText());
        String vehicleId = txtVehicleId.getText();

        Supplier supplier = new Supplier(supplierId,name,contact,specialCar,specialCarCost,vehicleId);

      /*  try {
            boolean isSaved = SupplierRepo.save(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier saved!").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } */

        if (isValied()) {
            boolean isSaved = supplierBo.save(new SupplierDTO(supplierId, name, contact, specialCar, specialCarCost, vehicleId));

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
        String supplierId = txtSupplierId.getText();
        String name = txtname.getText();
        String contact = txtContact.getText();
        String specialCar = txtSpecialCar.getText();
        Double specialCarCost = Double.valueOf(txtSpecialCarCost.getText());
        String vehicleId = txtVehicleId.getText();

        SupplierDTO supplier = new SupplierDTO(supplierId,name,contact,specialCar,specialCarCost,vehicleId);

        try {
            boolean isUpdated = supplierBo.update(supplier);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {

        String supplierId = txtSupplierId.getText();

        SupplierDTO supplier = supplierBo.searchById(supplierId);
        if (supplier != null){
            txtSupplierId.setText(supplier.getSupplierId());
            txtname.setText(supplier.getName());
            txtContact.setText(supplier.getContact());
            txtSpecialCar.setText(supplier.getSpecialCar());
            txtSpecialCarCost.setText(String.valueOf(supplier.getSpecialCarCost()));
            txtVehicleId.setText(supplier.getVehicleId());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
        }


    }

    @FXML
    void vehicleSearchOnAction(ActionEvent event) {
        String vehicleId = txtVehicleId.getText();
        try {
            VehicleDTO vehicle = supplierBo.searchByVehicleId(vehicleId);
            if(vehicle != null) {
                txtSpecialCar.setText(vehicle.getModelName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void mouseClickOnAction(MouseEvent event) {
        Integer index = supplierTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtSupplierId.setText(colSupplierId.getCellData(index).toString());
        txtname.setText(colName.getCellData(index).toString());
        txtContact.setText(colContact.getCellData(index).toString());
        txtSpecialCar.setText(colSpecialCar.getCellData(index).toString());
        txtSpecialCarCost.setText(colSpecialCarCost.getCellData(index).toString());
        txtVehicleId.setText(colVehicleId.getCellData(index).toString());
    }


    @FXML
    void txtSupplierContactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.CONTACT,txtContact);

    }

    @FXML
    void txtSupplierIDOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.ID,txtSupplierId);

    }

    @FXML
    void txtSupplierNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.NAME,txtname);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(TexetField.ID,txtSupplierId)) return false;
        if (!Regex.setTextColor(TexetField.NAME,txtname)) return false;

        if (!Regex.setTextColor(TexetField.CONTACT,txtContact)) return false;

        return true;
    }
    }

