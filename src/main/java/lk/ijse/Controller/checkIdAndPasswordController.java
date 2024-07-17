package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CheckIdAndPasswordBO;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dao.custom.impl.UserDaoImpl;

import java.io.IOException;
import java.sql.SQLException;

public class checkIdAndPasswordController {

    @FXML
    private AnchorPane checkAnchopane;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserPassword;

    CheckIdAndPasswordBO checkIdAndPasswordBO = (CheckIdAndPasswordBO) BOFactory.getBo(BOFactory.BOTypes.CHECK);

    @FXML
    void btnGo(ActionEvent event) throws SQLException, IOException {
        String userId = txtUserId.getText();
        String pw = txtUserPassword.getText();

        checkCredential(userId, pw);
    }

    private void checkCredential(String userId, String pw) throws SQLException, IOException {
        String dbPw = checkIdAndPasswordBO.checkCreditianal(userId, pw);

        if (dbPw != null && pw.equals(dbPw)) {
            navigateUserAccount();
        } else {
            if (dbPw == null) {
                new Alert(Alert.AlertType.INFORMATION, "Sorry! User ID can't be found!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry! Password is incorrect!").show();
            }
        }
    }

    private void navigateUserAccount() throws IOException {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/user_setting_form.fxml"));
            checkAnchopane.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        new Alert(alertType, message).show();
    }
}

































//package lk.ijse.Controller;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import lk.ijse.dao.custom.UserDao;
//import lk.ijse.dao.custom.impl.UserDaoImpl;
//import lk.ijse.db.DbConnection;
//
//import javax.swing.text.html.ImageView;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class checkIdAndPasswordController {
//
//    @FXML
//    private AnchorPane checkAnchopane;
//
//    @FXML
//    private ImageView checkFormAnchopane;
//
//    @FXML
//    private TextField txtUserId;
//
//    @FXML
//    private TextField txtUserPassword;
//
//    @FXML
//    void btnGo(ActionEvent event) throws SQLException, IOException {
//        String   userId = txtUserId.getText();
//        String pw = txtUserPassword.getText();
//
//        checkCreditianal(userId,pw);
//
//    }
//
//    private void checkCreditianal(String userId, String pw) throws SQLException, IOException {
////        String sql = "SELECT userId, password FROM users WHERE userId = ?";
////
////        Connection connection = DbConnection.getInstance().getConnection();
////        PreparedStatement pstm = connection.prepareStatement(sql);
////        pstm.setObject(1,userId);
////
////        ResultSet resultSet = pstm.executeQuery();
////        if (resultSet.next()){
////            String dbPw = resultSet.getString("password");
//
//            UserDao userDao = new UserDaoImpl();
//
//    String dbPw = userDao.checkCreditianal(userId , pw);
//
//            if (pw.equals(dbPw)){
//                navigateUserAccount();
//            } else {
//                new Alert(Alert.AlertType.ERROR,"sorry! password is incorrect!").show();
//            }
//        } else {
//            new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!").show();
//        }
//    }
//
//    private void navigateUserAccount() throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/user_setting_form.fxml"));
//        checkAnchopane.getChildren().setAll(anchorPane);
//        try {
//            checkAnchopane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/user_setting_form.fxml")));
//        } catch (IOException e ){
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//
//        }
//    }
//
//}

