package com.example.atm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    static DataBaseConnect dataBaseConnect = new DataBaseConnect();

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void openMainMenu(Stage authMenu, String login, String resourceName, String title, String addString) throws IOException {
        new MainMenu(login, addString);
        ShowWindow(resourceName, title);
        authMenu.hide();
    }

    public void sendMenu(Stage mainMenu, String login, String resourceName, String title) throws IOException {
        new SendMenu(login);
        ShowWindow(resourceName, title);
        mainMenu.hide();
    }

    public void charityMenu(Stage mainMenu, String login, String resourceName, String title) throws IOException {
        new CharityMenu(login);
        ShowWindow(resourceName, title);
        mainMenu.hide();
    }

    public void errorWindow(String error, String resourceName, String title) throws IOException {
        new ExceptionMenu(error);
        ShowWindow(resourceName, title);
    }

    public Stage FXMLload(String resourceName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 320, 240);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        return stage;
    }

    public void ShowWindow(String resourceName, String title) throws IOException {
        Stage stage = FXMLload(resourceName, title);
        stage.show();
    }

    public static void main(String[] args) {

        dataBaseConnect.connectDataBase();
        launch();
    }

    public boolean checkAuth(TextField textField, PasswordField passwordField) throws SQLException, ClassNotFoundException {
        boolean isPasswordCorrect = false;
        String password = dataBaseConnect.getCredentials(textField.getText());
        if ((passwordField.getText()).equals(password))
            isPasswordCorrect = true;
        return isPasswordCorrect;
    }
}