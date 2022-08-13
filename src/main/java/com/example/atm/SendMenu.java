package com.example.atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SendMenu implements Initializable {
    @FXML
    TextField recipientField;
    @FXML
    TextField sumField;

    @FXML
    Button exitButton;
    @FXML
    Button sendButton;
    static String usernameLogin;

    DataBaseConnect dataBaseConnect = new DataBaseConnect();

    public SendMenu() {

    }

    public SendMenu(String login) {
        usernameLogin = login;
    }

    public void onSendButtonClicked(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        HelloApplication helloApplication = new HelloApplication();
        char[] summ = sumField.getText().toCharArray();
        boolean isSumCorrect = true;
        for (char c : summ) {
            if (!Character.isDigit(c)) {
                isSumCorrect = false;
            }
        }
        if (recipientField.getText() == null) {
            helloApplication.errorWindow("Sender name is not text", "exception-menu.fxml", "Error menu");
        }
        if (summ == null || !isSumCorrect) {
            helloApplication.errorWindow("Sum field is not correct", "exception-menu.fxml", "Error menu");
        }
        if (dataBaseConnect.setBalance(usernameLogin, recipientField, sumField) == false) {
            helloApplication.errorWindow("This user isn't exist", "exception-menu.fxml", "Error menu");
        } else {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            helloApplication.openMainMenu(stage, usernameLogin, "main-menu.fxml", "Main menu", ", sum is sended");
        }
    }


    public void onExitButtonClicked(ActionEvent actionEvent) {
        Stage window = (Stage) exitButton.getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
