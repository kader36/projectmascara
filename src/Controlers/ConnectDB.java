package Controlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
     Connection con=null;
     public static Connection getConnection(){
         try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/kader","admin","admin");
             return con;
         } catch (Exception e) {
             return null;
         }
     }
}
