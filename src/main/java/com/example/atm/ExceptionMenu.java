package com.example.atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ExceptionMenu implements Initializable {
    @FXML
    Label errorLabel;
    @FXML
    Button OK;
    static String exc;

    public ExceptionMenu(){

    }
    public ExceptionMenu(String exception){
        exc = "Error: " + exception;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText(exc);
    }

    public void onOkButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) OK.getScene().getWindow();
        stage.close();
    }
}
