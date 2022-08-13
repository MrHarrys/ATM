package com.example.atm;

import javafx.scene.control.TextField;

import java.sql.*;

public class DataBaseConnect {

    static String login = "root";
    static String password = "jQToaL98NRA";

    public static void connectDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Connection succesfull!");
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static String getCredentials(String login) throws SQLException, ClassNotFoundException {
        ResultSet Credentials;
        String out = "";
        Statement statement = getStatement();
        String getPassword = "SELECT password, id from auth where login = '" + login + "';";

        Credentials = statement.executeQuery(getPassword);
        while (Credentials.next()) {
            out = Credentials.getString("password");
        }

        return out;
    }

    public static Integer getBalance(String login) throws SQLException, ClassNotFoundException {
        ResultSet balance;
        int loginBalance = 0;
        Statement statement = getStatement();
        String getBalance = "SELECT balance FROM balance where login ='" + login + "';";
        balance = statement.executeQuery(getBalance);

        while (balance.next()) {
            loginBalance = balance.getInt("balance");
        }
        return loginBalance;
    }

    public static Integer getBalance(TextField login) throws SQLException, ClassNotFoundException {
        return getBalance(login.getText());
    }

    public static boolean setBalance(String senderLogin, TextField recipientLogin, TextField sum) throws SQLException, ClassNotFoundException {
        int odd = Integer.parseInt(sum.getText());
        if(!isUserNoExist(recipientLogin)) {
            removeBalance(senderLogin, odd);
            addBalance(recipientLogin, odd);
            return true;
        } else return false;
    }

    public static void addBalance(TextField login, int odd) throws SQLException, ClassNotFoundException {
        int recipientBalance = getBalance(login);
        String updateBalanceQuerry = "UPDATE balance SET balance = " + (recipientBalance + odd) + " where login = '" + login.getText() + "'; ";

        Statement statement = getStatement();
        statement.executeUpdate(updateBalanceQuerry);
    }

    public static void removeBalance(String login, int odd) throws SQLException, ClassNotFoundException {
        int senderBalance = getBalance(login);
        String sendBalanceQuerry = "UPDATE balance SET balance = " + (senderBalance - odd) + " where login = '" + login + "'";
        Statement statement = getStatement();
        statement.executeUpdate(sendBalanceQuerry);
    }

    public static boolean isUserNoExist(TextField login) throws SQLException, ClassNotFoundException {
        Statement statement = getStatement();
        if (!statement.executeQuery("SELECT * FROM auth where login = '" + login.getText() + "';").next()){
            return true;
        }
        else return false;
    }
    public static Statement getStatement() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/atm", login, password);
        return connection.createStatement();
    }
}
