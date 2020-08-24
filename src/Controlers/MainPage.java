package Controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainPage implements Initializable {
    public javafx.scene.control.TextField usernamee;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private PasswordField password;
    @FXML
    private Label erreur;
    public String hashString(String s) throws NoSuchAlgorithmException {
        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(s.getBytes());

        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                sb.append(0);
                sb.append(hex.charAt(hex.length() - 1));
            } else {
                sb.append(hex.substring(hex.length() - 2));
            }
        }
        return sb.toString();
    }
    public void login(ActionEvent actionEvent) {
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `users` WHERE `username`=? AND `password`=?");
            pst.setString(1,usernamee.getText());
            pst.setString(2,hashString(password.getText()));
            rs=pst.executeQuery();
            String usernameConnected = "";
            String employeeNameConnected = "";
            int idConnected=0;
            int size=0;
            while(rs.next()){
                usernameConnected = rs.getString("username");
                employeeNameConnected = rs.getString("employeeName");
                idConnected = rs.getInt("id");

                size++;
            }
            if (size>0){
                erreur.setVisible(false);

                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/accueil.fxml"));
                    AnchorPane root = loader.load();
                    Accueil controller = loader.getController();
                    controller.Init(idConnected,usernameConnected,employeeNameConnected);
                    Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

                    primaryStage.setTitle("المناطق");
                    primaryStage.setX(10);
                    primaryStage.setY(20);
                    primaryStage.setMaxHeight(636);
                    primaryStage.setMaxWidth(1325);
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();

                }catch (Exception e){
                    System.out.println(e.getMessage());

                }
            }else{
                erreur.setVisible(true);
            }



        } catch (SQLException | NoSuchAlgorithmException throwables) {
            throwables.printStackTrace();
        }


    }


}
