package com.divinehr.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {

	private static String URL;
    private static String DRIVER_NAME;
    private static String USERNAME;
    private static String PASSWORD;
    
    static {
        ResourceBundle rb= ResourceBundle.getBundle("dbdetails");
        
        URL= rb.getString("db.url");
        DRIVER_NAME= rb.getString("db.drivername");
        USERNAME= rb.getString("db.username");
        PASSWORD= rb.getString("db.password");
    }
     
    public static Connection provideConnection() {
        
        Connection conn = null;
        
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conn;
    }
	
}
