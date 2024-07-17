package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReturnViewBo;
import lk.ijse.dto.ReturnDTO;
import lk.ijse.entity.ReturnDetailsView;
import lk.ijse.Tdm.ReturnDetailsTm;
import lk.ijse.Tdm.ReturnTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class ReturnViewController {

    @FXML
    private TableView<ReturnDetailsTm> DetailsTable;

    @FXML
    private TableColumn<?, ?> colAfterRideMilage;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colLateReturnCharge;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableColumn<?, ?> colReturnId;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private Label lblDate;

    @FXML
    private AnchorPane main2Anchopane;

    @FXML
    private TableView<ReturnTm> tableReturn;

    ReturnViewBo returnViewBo = (ReturnViewBo) BOFactory.getBo(BOFactory.BOTypes.RETURNVIEW);

 //   ReturnViewDao returnViewDao = new ReturnViewDaoImpl();
  //  ReturnDao returnDao = new ReturnDaoImpl();


    public void initialize(){
        setCellValueFactory();
        loadReturn();
        loadReturnDetails();
        setDate();


    }

    private void setDate() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        lblDate.setText(String.valueOf(localDate));
    }

    private void loadReturnDetails() {
        ObservableList<ReturnDetailsTm> obList = FXCollections.observableArrayList();
       // ReturnDetailsDao returnDetailsDao = new ReturnDetailsDaoImpl();


        try {
            List<ReturnDetailsView> returnList = returnViewBo.getAllReturnView();
            for (ReturnDetailsView aReturn :returnList ){
                ReturnDetailsTm tm = new ReturnDetailsTm(
                        aReturn.getReturnId(),
                        aReturn.getVehicleId()


                );
                obList.add(tm);
            }
            DetailsTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadReturn() {
        ObservableList<ReturnTm> obList = FXCollections.observableArrayList();


        try {
            List<ReturnDTO> returnList = returnViewBo.getAllReturns();
            for (ReturnDTO aReturn :returnList ){
                ReturnTm tm = new ReturnTm(
                        aReturn.getReturnId(),
                        aReturn.getAfterRideMilage(),
                        aReturn.getLateReturnCharge(),
                        aReturn.getTotalCost(),
                        aReturn.getCustomerld(),
                        aReturn.getReservationId()


                );
                obList.add(tm);
            }
            tableReturn.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colReturnId.setCellValueFactory(new PropertyValueFactory<>("returnId"));
        colAfterRideMilage.setCellValueFactory(new PropertyValueFactory<>("afterRideMilage"));
        colLateReturnCharge.setCellValueFactory(new PropertyValueFactory<>("lateReturnCharge"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerld"));
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
    }

    @FXML
    void btnBackReturnForm(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/return_form.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/return_form.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }

    @FXML
    void btnBackToDashBoard(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) main2Anchopane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }
}
