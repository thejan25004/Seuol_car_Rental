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
import lk.ijse.bo.custom.ReservationViewBo;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.RedDetailsView;
import lk.ijse.Tdm.ReservationDetailsTm;
import lk.ijse.Tdm.ReservationTm;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


public class ReservationViewController {
    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colReservationDate;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableColumn<?, ?> colReturnDate;


    @FXML
    private Label lblDate;

    @FXML
    private AnchorPane main2Anchopane;

    @FXML
    private AnchorPane mainAnchopane;

    @FXML
    private TableView<ReservationTm> tblReservation;

    @FXML
    private TableColumn<?, ?> colReID2;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private TableView<ReservationDetailsTm> DetailsTable;

    ReservationViewBo reservationViewBo = (ReservationViewBo) BOFactory.getBo(BOFactory.BOTypes.RESERVATIONVIEW);


 //   ReservationViewDao reservationViewDao = new ReservationViewDaoImpl();
 //   ReservationDao reservationDao = new ReservationDaoImpl();


    public void initialize(){
        setCellValueFactory();
        loadReservation();
        loadReservationDetails();
        setDate();


    }

    private void setDate() {
        Date date = new Date();
           LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           lblDate.setText(String.valueOf(localDate));
    }

    private void loadReservationDetails() {
        ObservableList<ReservationDetailsTm> obList = FXCollections.observableArrayList();

        try {
            List<RedDetailsView> redDetailsViews = reservationViewBo.getAll();
            for (RedDetailsView reservation :redDetailsViews ){
                ReservationDetailsTm tm = new ReservationDetailsTm(
                        reservation.getVehicleId(),
                        reservation.getReservationId()



                );
                obList.add(tm);
            }
            DetailsTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadReservation() {
        ObservableList<ReservationTm> obList = FXCollections.observableArrayList();


        try {
            List<ReservationDTO> ReservationLIst = reservationViewBo.getAllReservations();
            for (ReservationDTO reservation :ReservationLIst ){
                ReservationTm tm = new ReservationTm(
                        reservation.getReservationId(),
                        reservation.getReservationDate(),
                        reservation.getReturnDate(),
                        reservation.getCustomerld()


                );
                obList.add(tm);
            }
            tblReservation.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colReservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerld"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));

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
    void btnBackReservationForm(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/reservation_form.fxml"));
        main2Anchopane.getChildren().setAll(anchorPane);
        try {
            main2Anchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/reservation_form.fxml")));
        } catch (IOException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }


}
