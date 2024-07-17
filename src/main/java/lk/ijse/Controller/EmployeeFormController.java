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
import lk.ijse.bo.custom.EmployeeBo;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;
import lk.ijse.Tdm.EmployeeTm;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {
    @FXML
    private AnchorPane EmployeeMain2Anchopane;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeType;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colsalary;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private AnchorPane employeeMainAnchopane;

    @FXML
    private TableView<EmployeeTm> employeeTable;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeType;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;


    @FXML
    private TextField txtUserId;

   // EmployeeDao employeeDao = new EmployeeDaoImpl();

    EmployeeBo employeeBo = (EmployeeBo) BOFactory.getBo(BOFactory.BOTypes.EMPLOYEE);
    public void initialize(){
        setCellValueFactory();
        loadAllEmployees();
    }

    private void loadAllEmployees() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDTO> employeeList = employeeBo.getAll();
            for (EmployeeDTO employee :employeeList ){
                EmployeeTm tm = new EmployeeTm(
                      employee.getEmployeeId(),
                      employee.getName(),
                      employee.getEmployeeType(),
                      employee.getSalary(),

                        employee.getUserId()

                );
                obList.add(tm);
            }
            employeeTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeType.setCellValueFactory(new PropertyValueFactory<>("employeeType"));
        colsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));


    }

    @FXML
    void btnBackToDashBoard(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) employeeMainAnchopane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearFields();
    }

    private void clearFields() {
        txtEmployeeId.setText("");
        txtName.setText("");
        txtEmployeeType.setText("");
        txtSalary.setText("");
        txtUserId.setText("");

    }

    @FXML
    void btnDeketeOnAction(ActionEvent event) {
        String EmployeeId = txtEmployeeId.getText();

        try {
            boolean isDeleted = employeeBo.delete(EmployeeId);
            if(isDeleted) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String EmployeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String employeeType = txtEmployeeType.getText();
        Double salary = Double.valueOf(txtSalary.getText());
        String userId = txtUserId.getText();


        Employee employee = new Employee(EmployeeId,name,employeeType,salary,userId);
        if (isValied()) {
            boolean isSaved = employeeBo.save(new EmployeeDTO(EmployeeId, name, employeeType, salary, userId));

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                clearFields();
                initialize();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Details is Invalied !").show();
            }
        }




    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String EmployeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String employeeType = txtEmployeeType.getText();
        Double salary = Double.valueOf(txtSalary.getText());
        String userId = txtUserId.getText();


        EmployeeDTO employee = new EmployeeDTO(EmployeeId,name,employeeType,salary,userId);

        try {
            boolean isUpdated = employeeBo.update(employee);
            if(isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String EmployeeId = txtEmployeeId.getText();

        EmployeeDTO employee = employeeBo.searchById(EmployeeId);
        if (employee != null){
            txtEmployeeId.setText(employee.getEmployeeId());
            txtName.setText(employee.getName());
            txtEmployeeType.setText(employee.getEmployeeType());
            txtSalary.setText(String.valueOf(employee.getSalary()));
            txtUserId.setText(employee.getUserId());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "employee not found!").show();
        }

    }



    @FXML
    void mouseClickOnAction(MouseEvent event) {
        Integer index = employeeTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtEmployeeId.setText(colEmployeeId.getCellData(index).toString());
        txtName.setText(colName.getCellData(index).toString());
        txtEmployeeType.setText(colEmployeeType.getCellData(index).toString());
        txtSalary.setText(colsalary.getCellData(index).toString());
        txtUserId.setText(colUserId.getCellData(index).toString());

    }

    @FXML
    void txtEmployeeIDOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.ID,txtEmployeeId);
    }

    @FXML
    void txtEmployeeNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.NAME,txtName);
    }
    public boolean isValied(){
        if (!Regex.setTextColor(TexetField.ID,txtEmployeeId)) return false;
        if (!Regex.setTextColor(TexetField.NAME,txtName)) return false;

        return true;
    }

}
