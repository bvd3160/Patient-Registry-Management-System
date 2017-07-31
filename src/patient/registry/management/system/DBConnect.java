/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.registry.management.system;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jeany
 */
class DBConnect {
    private static String USERNAME = "root";
    private static String PASSWORD = "mypass";
    private static String CONN_STRING = "jdbc:mysql://localhost:3306/my_database";
    
    public DBConnect() {
            
    }
    
    /**
     * Establishing a connection to the database
     * @return whether or not a connection was established.
     */
    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Databse Connected!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Warning", JOptionPane.ERROR_MESSAGE); 
        }
        return conn;
    }
    
    /**
     * 
     * @param updateQuery
     * 
     * Inserting new data into a table.
     */
    public void updateTable(String updateQuery){
       //Connect to the database
       Connection conn = getConnection();
        try {
            Statement stmt = (Statement) conn.createStatement();
            stmt.executeUpdate(updateQuery);
            conn.close();
            JOptionPane.showMessageDialog(null, "Data Updated", "Message", JOptionPane.INFORMATION_MESSAGE); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Warning", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    /**
     * 
     * @param loginQuery
     * @return whether or not it's possible to log in based on 
     * the details the user has given.
     */
    public boolean loginPossible(String loginQuery){
       //Connect to the database
       Connection conn = getConnection();
       ResultSet resultSet = null;
       Boolean result = false;
       MainWindow mainWindow = new MainWindow();
        try {
            Statement stmt = (Statement) conn.createStatement();
            resultSet = stmt.executeQuery(loginQuery);
            while(resultSet.next()){
                if(resultSet.getString("userType").equalsIgnoreCase("Doctor")){
                    mainWindow.setGreetingName("Dr. "+resultSet.getString("lastName"));
                    mainWindow.setVisible(true);
                    result = true;
                }else{
                    mainWindow.setGreetingName(resultSet.getString("firstName"));
                    mainWindow.setVisible(true);
                    result = true;
                }             
            }
            conn.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Warning", JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        return result;
    }

    /**
     * 
     * @param searchQuery 
     * Search the patient table and if there are any results,
     * return an ArrayList full of the results to be displayed on
     * a table of your choosing.
     */
    public ArrayList<Patient> searchDatabase(String searchQuery) {
       Connection conn = getConnection();
       ArrayList<Patient> patientsList = new ArrayList<>();
       ResultSet rs = null;
       try {
            Statement stmt = (Statement) conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            ResultSetMetaData rsmd = rs.getMetaData();
            Patient patient;
            while(rs.next()){
                patient = new Patient(rs.getInt("patient_id"), rs.getString("fname"), rs.getString("lname")
                , rs.getString("gender"), rs.getString("address"), rs.getString("phonenum"), rs.getString("medcondition")
                , rs.getString("comments"), rs.getString("ward"), rs.getString("room"), rs.getDate("dob")
                , rs.getDate("date"), rs.getTime("time"));
                patientsList.add(patient);
                System.out.println("SEARCH SOMEWHAT WORKS!");
            }
            conn.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return patientsList;
    }    
}
