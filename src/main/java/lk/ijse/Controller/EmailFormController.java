package lk.ijse.Controller;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashboardBo;
import lk.ijse.bo.custom.EmailBo;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;


import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;


public class EmailFormController {
    @FXML
    private Label lblStatus;


    @FXML
    private Label lblEmail;

    @FXML
    private ComboBox<String> cmbtxtCustomerId;




    @FXML
    private AnchorPane MainAnchopane;


    @FXML
    private TextField txtEmail;

    @FXML
    private TextArea txtMassage;

    @FXML
    private TextField txtSubject;

    EmailBo emailBo = (EmailBo) BOFactory.getBo(BOFactory.BOTypes.EMAIL);


    public void initialize(){
        getCustomerValues();
    }

    private void getCustomerValues() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> cusList = emailBo.getIds();

            for(String customerld : cusList) {
                obList.add(customerld);
            }

            cmbtxtCustomerId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EmailOnAction(ActionEvent event) {
        txtSubject.requestFocus();
    }


    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) MainAnchopane.getScene().getWindow();

        Scene scene=new Scene(rootNode);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        boolean isvalid = validation();

        if (isvalid) {


            lblStatus.setText("sending...");

            Mail mail = new Mail(); //creating an instance of Mail class
            mail.setMsg(txtMassage.getText());//email message
            mail.setTo(txtEmail.getText()); //receiver's mail
            mail.setSubject(txtSubject.getText()); //email subject

            Thread thread = new Thread(mail);
            thread.start();


            lblStatus.setText("sent successfully");
            clearFields();        }

    }

    private void clearFields() {
        txtEmail.clear();
        txtMassage.clear();
        txtSubject.clear();
        lblEmail.setText("");
    }

    public static class Mail implements Runnable{
        private String msg;
        private String to;
        private String subject;
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public void setTo(String to) {
            this.to = to;
        }
        public void setSubject(String subject) {
            this.subject = subject;
        }

        public boolean outMail() throws MessagingException {
            String from = "seoulcarrental@gmail.com"; //sender's email address
            String host = "localhost";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("seoulcarrental@gmail.com", "pefh pfoa lsbf sspo");  // have to change some settings in SMTP
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(this.subject);
            mimeMessage.setText(this.msg);
            Transport.send(mimeMessage);
            return true;
        }
        public void run() {
            if (msg != null) {
                try {
                    outMail();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("not sent. empty msg!");
            }
        }
    }

    private boolean validation() {
        String email=txtEmail.getText();
        boolean isMatch = Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",email);
        if (!isMatch) {
            new Alert(Alert.AlertType.ERROR,"invalid email").show();
            return false;
        }
        return true;
    }

    @FXML
    void subjectOnAction(ActionEvent event) {
        txtMassage.requestFocus();
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String customerIdValue = cmbtxtCustomerId.getValue();
        try {
            CustomerDTO customer = emailBo.searchById(customerIdValue);

            if (customer != null) {
                lblEmail.setText(customer.getEmail());
            } else {
                lblEmail.setText("No email found for this customer.");
                // Optionally, show an alert
                new Alert(Alert.AlertType.WARNING, "Customer not found").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error retrieving customer data").show();
        }
    }


}
