package Controlers;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
     Connection con=null;
     public Connection getConnection(){
         try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection con= DriverManager.getConnection("jdbc:mysql://localhost/kader?useSSL=false","admin","admin");
             return con;
         } catch (Exception e) {
             warningMsg("قاعدة البيانات","خطأ في الإتصال بقاعدة البيانات");
             return null;
         }
     }
    public void warningMsg(String title,String message ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
