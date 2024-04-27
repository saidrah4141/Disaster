

package edu.ucalgary.oop;

import java.sql.*;

public class Database{
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;

    private Connection dbConnect;
    private ResultSet results;

    public Database(String url, String user, String pw){


        this.DBURL = url;


        this.USERNAME = user;
        this.PASSWORD = pw;
    }



    public void initializeConnection(){

        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME,PASSWORD );
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Database connection not established successfully.");
            e.printStackTrace();
        }

    }

    String getDburl(){
        return this.DBURL;
    }

    String getUsername(){
        return this.USERNAME;
    }

    String getPassword(){
        return this.PASSWORD;
    }


    public String selectAllNames(String tableName) {
        String names = "";
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT LName, FName FROM " + tableName);
            while (results.next()) {
                names += results.getString("LName") + ", " + results.getString("FName") + "\n";
            }
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return names;
    }



    public void insertNewInquirer(String fName, String lName, String phone){

        try {
            PreparedStatement preparedStatement;
            if (lName != null) {
                preparedStatement = dbConnect.prepareStatement("INSERT INTO INQUIRER (first_name, last_name, phone) VALUES (?, ?, ?)");
                preparedStatement.setString(1, fName);
                preparedStatement.setString(2, lName);
            } else {
                preparedStatement = dbConnect.prepareStatement("INSERT INTO INQUIRER (first_name, phone) VALUES (?, ?)");
                preparedStatement.setString(1, fName);
            }
            preparedStatement.setString(lName != null ? 3 : 2, phone);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public void insertNewInquiryLog(int inquirer, java.sql.Date callDate, String details) {
        try {

            PreparedStatement preparedStatement = dbConnect.prepareStatement("INSERT INTO inquiry_log (inquirer, callDate, details) VALUES (?, ?, ?)");


            preparedStatement.setInt(1, inquirer);
            preparedStatement.setDate(2, callDate);
            preparedStatement.setString(3, details);


            preparedStatement.executeUpdate();


            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public void getAllInquiryLogs() {
        try {
            PreparedStatement preparedStatement = dbConnect.prepareStatement("SELECT * inquiry_log");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int inquirerId = resultSet.getInt("inquirer");
                Date callDate = resultSet.getDate("callDate");
                String details = resultSet.getString("details");

                System.out.println("ID: " + id + ", Inquirer ID: " + inquirerId + ", Call Date: " + callDate + ", Details: " + details);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Error retrieving inquiry logs.");
        }
    }
    public void getInquiryLogsById(int id) {
        try {

            String sql = "SELECT * FROM inquiry_log WHERE inquirer = ?";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql);


            preparedStatement.setInt(1, id);


            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int logId = resultSet.getInt("id");
                int inquirerId = resultSet.getInt("inquirer");
                Date callDate = resultSet.getDate("callDate");
                String details = resultSet.getString("details");

                System.out.println("ID: " + logId + ", Inquirer ID: " + inquirerId + ", Call Date: " + callDate + ", Details: " + details);
            }


            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Error retrieving inquiry logs for ID: " + id);
        }
    }
    public Inquirer getInquirerById(int id) {
        Inquirer inquirer = null;
        try {

            String sql = "SELECT * FROM INQUIRER WHERE id = ?";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {

                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phoneNumber = resultSet.getString("phoneNumber");


                inquirer = new Inquirer(firstName, lastName, phoneNumber);
            } else {

                return null;
            }


            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return inquirer;
    }

    public void getAllInquirers() {
        try {

            PreparedStatement preparedStatement = dbConnect.prepareStatement("SELECT * FROM inquirer");
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phoneNumber = resultSet.getString("phoneNumber");


                System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + (lastName != null ? lastName : "N/A") + ", Phone Number: " + phoneNumber);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Error retrieving inquirers.");
        }
    }





    public void close() {
        try {
            if (results != null) {
                results.close();
            }
            if (dbConnect != null) {
                dbConnect.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}