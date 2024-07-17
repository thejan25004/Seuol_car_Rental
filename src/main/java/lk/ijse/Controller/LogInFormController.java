package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LogInBo;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dao.custom.impl.UserDaoImpl;
import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LogInFormController {
    public TextField txtuserId;
    @FXML
    private PasswordField txtpassword;
    public AnchorPane loginFormAnchopane;

  //  UserDao userDao = new UserDaoImpl();

    LogInBo logInBo = (LogInBo) BOFactory.getBo(BOFactory.BOTypes.LOGIN);


    public void btnLogIn(ActionEvent actionEvent) throws IOException {

        String userId = txtuserId.getText();
        String pw = txtpassword.getText();

        try {
            checkCredential(userId, pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

   private void  checkCredential(String userId , String pw ) throws SQLException, IOException {

        String dbPw = logInBo.checkCreditianal(userId,pw) ;

       if (dbPw != null && pw.equals(dbPw)) {
           navigateToTheDashboard();
       } else {
           if (dbPw == null) {
               new Alert(Alert.AlertType.INFORMATION, "Sorry! User ID can't be found!").show();
           } else {
               new Alert(Alert.AlertType.ERROR, "Sorry! Password is incorrect!").show();
           }
       }
   }

   private void navigateToTheDashboard() throws IOException {
       AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

       Scene scene = new Scene(rootNode);

       Stage stage = (Stage) this.loginFormAnchopane.getScene().getWindow();
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.setTitle(" SEOUL CAR RENTAL Dashboard Form");
   }

    public void btnActionRegisteration(ActionEvent actionEvent) throws IOException {
       Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/registeration_form.fxml"));

       Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

       stage.setTitle("Registeration Form For Users");
       stage.show();
    }
}
