package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TexetField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBo;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.Tdm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    @FXML
    public AnchorPane customerAnchopane;
    public TextField txtSearch;

    public AnchorPane main2Anchopane;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBailVehicleNm;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private AnchorPane dashBoardtoolAnchopane;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtbailVehicleNm;

    @FXML
    private TextField txtcontact;

    @FXML
    private TextField txtcustomerId;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtnic;

    @FXML
    private TextField txtuserId;


    @FXML
    private Button btnPrintBill;

    CustomerBo customerBo = (CustomerBo) BOFactory.getBo(BOFactory.BOTypes.CUSTOMER);

    public void initialize(){
       setCellValueFactory();
       loadAlCustomers();
   }

    private void loadAlCustomers() {

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

       try {
           List<CustomerDTO> CustomerList = customerBo.getAll();
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
           tblCustomer.setItems(obList);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new  PropertyValueFactory<>("customerld"));
        colName.setCellValueFactory(new  PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new  PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new  PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new  PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new  PropertyValueFactory<>("nic"));
        colBailVehicleNm.setCellValueFactory(new  PropertyValueFactory<>("bailVehicleNm"));
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
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }

    private void clearFields() {
        txtcustomerId.setText("");
        txtname.setText("");
        txtcontact.setText("");
        txtaddress.setText("");
        txtnic.setText("");
        txtbailVehicleNm.setText("");
        txtEmail.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String customerld = txtcustomerId.getText();

        try {
            boolean isDeleted = customerBo.delete(customerld);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String customerld = txtcustomerId.getText();
        String name = txtname.getText();
        String contact = txtcontact.getText();
        String email = txtEmail.getText();
        String address = txtaddress.getText();
        String nic = txtnic.getText();
        String bailVehicleNm = txtbailVehicleNm.getText();

        Customer customer = new Customer(customerld,name,contact,email,address,nic,bailVehicleNm);

        if (isValied()) {
            boolean isSaved = customerBo.save(new CustomerDTO(customerld, name, contact,email, address, nic, bailVehicleNm));

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
        String customerld = txtcustomerId.getText();
        String name = txtname.getText();
        String contact = txtcontact.getText();
        String email = txtEmail.getText();
        String address = txtaddress.getText();
        String nic = txtnic.getText();
        String bailVehicleNm = txtbailVehicleNm.getText();

        CustomerDTO customer = new CustomerDTO(customerld,name,contact,email,address,nic,bailVehicleNm);

        try {
            boolean isUpdated = customerBo.update(customer);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String customerld = txtcustomerId.getText();

        CustomerDTO customer = customerBo.searchById(customerld);
        if (customer != null) {
            txtcustomerId.setText(customer.getCustomerld());
            txtname.setText(customer.getName());
            txtcontact.setText(customer.getContact());
            txtEmail.setText(customer.getEmail());
            txtaddress.setText(customer.getAddress());
            txtnic.setText(customer.getNic());
            txtbailVehicleNm.setText(customer.getBailVehicleNm());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }

    }


    public void mouseClickOnAction(MouseEvent mouseEvent) {
        Integer index = tblCustomer.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtcustomerId.setText(colId.getCellData(index).toString());
        txtname.setText(colName.getCellData(index).toString());
        txtcontact.setText(colContact.getCellData(index).toString());
        txtEmail.setText(colEmail.getCellData(index).toString());
        txtaddress.setText(colAddress.getCellData(index).toString());
        txtnic.setText(colNic.getCellData(index).toString());
        txtbailVehicleNm.setText(colBailVehicleNm.getCellData(index).toString());

    }

    @FXML
    void txtCustomerIDOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.ID,txtcustomerId);
    }


    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.EMAIL,txtEmail);
    }

    @FXML
    void txtCustomerNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.NAME,txtname);

    }

    @FXML
    void txtCustomerNumberOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TexetField.CONTACT,txtcontact);

    }


    public boolean isValied(){
        
        if (!Regex.setTextColor(TexetField.ID,txtcustomerId)) return false;
        if (!Regex.setTextColor(TexetField.NAME,txtname)) return false;

        if (!Regex.setTextColor(TexetField.CONTACT,txtcontact)) return false;
        if (!Regex.setTextColor(TexetField.EMAIL,txtEmail)) return false;


        return true;
    }

}

