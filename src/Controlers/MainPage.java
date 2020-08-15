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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;

import java.net.URL;
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

    public void login(ActionEvent actionEvent) {
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `users` WHERE `username`=? AND `password`=?");
            pst.setString(1,usernamee.getText());
            pst.setString(2,password.getText());
            rs=pst.executeQuery();
            int size=0;
            while(rs.next()){
                size++;
            }
            if (size>0){
                erreur.setVisible(false);

                try {

                    Parent root = FXMLLoader.load(getClass().getResource("/Views/areaPage.fxml"));
                    Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                    primaryStage.setTitle("المناطق");
                    primaryStage.setX(10);
                    primaryStage.setY(20);
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();

                }catch (Exception e){
                    System.out.println(e.getMessage());

                }
            }else{
                erreur.setVisible(true);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
