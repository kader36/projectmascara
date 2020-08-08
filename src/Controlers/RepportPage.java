package Controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RepportPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void areas(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/areaPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }

    }

    public void locations(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/locationPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void projects(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/projectPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void garantees(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/garanteePage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void occupations(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/occupationPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void employees(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/employeePage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void report(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/repportPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void users(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/userPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void penalties(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/penaltyPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void abstracts(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/abstractPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void deduction(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/deductionPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void login(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Views/projectPage.fxml"));
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المناطق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }

    }

    public void printOne(ActionEvent actionEvent)  {
        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report1.jrxml";
            String querry="SELECT * FROM `projects`,`areas` WHERE projects.areaId=areas.id AND projects.areaId =17";
            JasperDesign jd=  JRXmlLoader.load(path);
            JRDesignQuery query=new JRDesignQuery();
            query.setText(querry);
            jd.setQuery(query);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
            JasperViewer jv = new JasperViewer( jasperPrint, false );
            jv.viewReport( jasperPrint, false );

        } catch (JRException e) {
            e.printStackTrace();
        }

    }
    public void printTwo(ActionEvent actionEvent)  {
        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report2.jrxml";
            String querry="SELECT * FROM `projects`,`areas` WHERE areas.id=projects.areaId ORDER BY areas.id";
            JasperDesign jd=  JRXmlLoader.load(path);
            JRDesignQuery query=new JRDesignQuery();
            query.setText(querry);
            jd.setQuery(query);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
            JasperViewer jv = new JasperViewer( jasperPrint, false );
            jv.viewReport( jasperPrint, false );

        } catch (JRException e) {
            e.printStackTrace();
        }

    }
    public void printThree(ActionEvent actionEvent)  {
        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report3.jrxml";
            String querry="SELECT * FROM `projectoccupations`,`projects`, `occupations`  WHERE projectoccupations.idProject=projects.id AND projects.id=17 AND projectoccupations.idOccupation=occupations.id";
            JasperDesign jd=  JRXmlLoader.load(path);
            JRDesignQuery query=new JRDesignQuery();
            query.setText(querry);
            jd.setQuery(query);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
            JasperViewer jv = new JasperViewer( jasperPrint, false );
            jv.viewReport( jasperPrint, false );

        } catch (JRException e) {
            e.printStackTrace();
        }

    }
    public void printFour(ActionEvent actionEvent)  {
        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report4.jrxml";
            String querry="SELECT * FROM `deductions`,`projectsemployees`,`projects`, `occupations`,`employees`,`areas`  WHERE projects.id=17 AND deductions.idArea=areas.id AND deductions.idProject=projects.id AND deductions.idEmployeeDeduction=employees.id AND projectsemployees.idOccupation=occupations.id  AND projectsemployees.idEmployee=deductions.idEmployeeDeduction";
            JasperDesign jd=  JRXmlLoader.load(path);
            JRDesignQuery query=new JRDesignQuery();
            query.setText(querry);
            jd.setQuery(query);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
            JasperViewer jv = new JasperViewer( jasperPrint, false );
            jv.viewReport( jasperPrint, false );

        } catch (JRException e) {
            e.printStackTrace();
        }

    }
    public void printFive(ActionEvent actionEvent)  {
        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report4.jrxml";
            String querry="SELECT * FROM `deductions`,`projectsemployees`,`projects`, `occupations`,`employees`,`areas`  WHERE  deductions.idArea=areas.id AND deductions.idProject=projects.id AND deductions.idEmployeeDeduction=employees.id AND projectsemployees.idOccupation=occupations.id  AND projectsemployees.idEmployee=deductions.idEmployeeDeduction GROUP BY deductions.idProject";
            JasperDesign jd=  JRXmlLoader.load(path);
            JRDesignQuery query=new JRDesignQuery();
            query.setText(querry);
            jd.setQuery(query);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
            JasperViewer jv = new JasperViewer( jasperPrint, false );
            jv.viewReport( jasperPrint, false );

        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con=new Controlers.ConnectDB().getConnection();
    }
}
