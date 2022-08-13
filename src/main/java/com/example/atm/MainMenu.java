package com.example.atm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @FXML
    Label greetingText;
    @FXML
    Button exitButton, sendButton, charityButton, balanceButton;

    static String loginFromWindow;
    static String additionalString;
    HelloApplication helloApplication = new HelloApplication();

    public MainMenu() {
    }

    public MainMenu(String login, String addedString) {
        loginFromWindow = login;
        additionalString = addedString;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        greetingText.setText("Welcome, " + loginFromWindow + ", " + additionalString + "!");
    }

    public void onBalanceButtonClicked() throws SQLException, ClassNotFoundException {
        DataBaseConnect dataBaseConnect = new DataBaseConnect();
        System.out.println(loginFromWindow);
        int balance = dataBaseConnect.getBalance(loginFromWindow);
        greetingText.setText("Your balance is " + balance);
    }

    public void onSendButtonClicked() throws IOException {
        Stage stage = (Stage) sendButton.getScene().getWindow();
        helloApplication.sendMenu(stage, loginFromWindow, "send-menu.fxml", "Send menu");
    }

    public void onCharityButtonClicked() throws IOException {
        Stage stage = (Stage) charityButton.getScene().getWindow();
        helloApplication.charityMenu(stage, loginFromWindow, "charity-menu.fxml", "Charity menu");
    }

    public void onExitButtonClicked() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
