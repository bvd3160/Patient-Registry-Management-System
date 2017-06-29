/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.registry.management.system;

import java.sql.*;
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
    
    public void updateTable(String updateQuery){
       //Connect to the database
       Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Databse Connected!");
            Statement stmt = (Statement) conn.createStatement();
            stmt.executeUpdate(updateQuery);
            conn.close();
            JOptionPane.showMessageDialog(null, "Data Updated", "Message", JOptionPane.INFORMATION_MESSAGE); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Warning", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    public boolean loginPossible(String loginQuery){
       //Connect to the database
       Connection conn = null;
       ResultSet resultSet = null;
       Boolean result = false;
       MainWindow mainWindow = new MainWindow();
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Databse Connected!");
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
    
}
