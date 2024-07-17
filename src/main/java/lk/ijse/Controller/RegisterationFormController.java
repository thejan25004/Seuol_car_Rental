package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RegisterationBo;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dao.custom.impl.UserDaoImpl;
import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterationFormController {


    public AnchorPane AnchopaneRegisteration;
    public TextField txtuserId;
    public TextField txtusername;
    public TextField txtpassword;

  //  UserDao userDao = new UserDaoImpl();

    RegisterationBo registerationBo = (RegisterationBo) BOFactory.getBo(BOFactory.BOTypes.REGISTERATION);

    public void btnRegisterartion(ActionEvent actionEvent) {
        String userId = txtuserId.getText();
        String name = txtusername.getText();
        String password = txtpassword.getText();

        try {
            boolean isSaved = registerationBo.saveUser(userId, name, password);
            if(isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnActionBackToLoginFrm(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/log_in_form.fxml"));
        Stage stage = (Stage) AnchopaneRegisteration.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("logIn Form");
        stage.centerOnScreen();
    }
}
