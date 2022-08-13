package com.example.atm;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    Button enter;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        Stage stage = (Stage) enter.getScene().getWindow();
        if (password.getText().length() != 0) {
            try {
                if (helloApplication.checkAuth(login, password) == true) {
                    helloApplication.openMainMenu(stage, login.getText(), "main-menu.fxml", "Main menu", "choose a button");
                }
            } catch (Exception e) {
                helloApplication.errorWindow("Unknown exception", "exception-menu.fxml", "Error menu");
            }
        } else helloApplication.errorWindow("No password", "exception-menu.fxml", "Error menu");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}