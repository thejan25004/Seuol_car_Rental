package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserSettingBo;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dao.custom.impl.UserDaoImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class UserSettingFormController {
    public TextField txtUserId;
    public TextField txtUserName;
    public TextField txtPassword;
    @FXML
    private AnchorPane UserSettingAnchopane;


    @FXML
    private AnchorPane mainAnchopane;

  //  UserDao userDao = new UserDaoImpl();

    UserSettingBo userSettingBo = (UserSettingBo) BOFactory.getBo(BOFactory.BOTypes.USERSETTING);

    public void btnSave(ActionEvent actionEvent) throws SQLException {
        String userId = txtUserId.getText();
        String name = txtUserName.getText();
        String password = txtPassword.getText();


        UserDTO user = new UserDTO(userId,name,password);

        try {
            boolean isUpdated = userSettingBo.update(user);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "User are  updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    @FXML
    void btnOnActionDelete(ActionEvent event) {
        String userId = txtUserId.getText();

        try {
            boolean isDeleted = userSettingBo.delete(userId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }






}
