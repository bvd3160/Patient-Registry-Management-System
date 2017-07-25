/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.registry.management.system;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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

    public void searchDatabase(String searchQuery) {
       Connection conn = getConnection();
       ArrayList arr = null;
       ResultSet resultSet = null;
       try {
            Statement stmt = (Statement) conn.createStatement();
            resultSet = stmt.executeQuery(searchQuery);
            
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int colCount = rsmd.getColumnCount();
            Vector column = new Vector(colCount);
            for (int i = 0; i < colCount; i++) {
               column.add(rsmd.getColumnName(i));
            }
            Vector data = new Vector();
            Vector row = new Vector();
            while(resultSet.next()){
                System.out.println("SEARCH SOMEWHAT WORKS!");
                row = new Vector(colCount);
                for (int i = 0; i < colCount; i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
            //Testing out that the search feature works!
            JFrame frame = new JFrame();
            frame.setSize(600, 720);
            frame.setLocationRelativeTo(null);
            JPanel panel = new JPanel();
            JTable table = new JTable(data, column);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.setLayout(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            frame.setContentPane(scrollPane);
            frame.setVisible(true);
            
            conn.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
       exportSearchResult(arr);
    }
    
    private void exportSearchResult(ArrayList data){
        
    }
        
    public void importSearchResult(){
        
    }
    
}
