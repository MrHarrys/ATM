package com.example.atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharityMenu implements Initializable {
    @FXML
    TextField sumField;
@FXML
Button sendButton;
    @FXML
    Button exitButton;
    static String usernameLogin;

    DataBaseConnect dataBaseConnect = new DataBaseConnect();

    public CharityMenu() {

    }

    public CharityMenu(String login) {
        usernameLogin = login;
    }

    public void onSendButtonClicked(ActionEvent actionEvent) throws IOException {

        HelloApplication helloApplication = new HelloApplication();
        char[] summ = sumField.getText().toCharArray();
        boolean isSumCorrect = true;
        for (char c : summ) {
            if (!Character.isDigit(c)) {
                isSumCorrect = false;
            }
        }
        if (summ == null || !isSumCorrect) {
            helloApplication.errorWindow("Sum field is not correct", "exception-menu.fxml", "Error menu");
        } else {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            helloApplication.openMainMenu(stage, usernameLogin, "main-menu.fxml", "Main menu", "charity is complete");
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
