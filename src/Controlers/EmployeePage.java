package Controlers;

import com.mysql.jdbc.Blob;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeePage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    File file1=null,file2=null,file3=null;
    ObservableList<Occupation> occupations= FXCollections.observableArrayList();
    ObservableList<Nationality> nationalities= FXCollections.observableArrayList();
    ObservableList<String> identityTypeList= FXCollections.observableArrayList("بطاقة هوية","جواز السفر","رخصة الإقامة");
    ObservableList<String> employeeTypeList= FXCollections.observableArrayList("موظف سعودي","موظف أجنبي");
    ObservableList<String> employeeSexList= FXCollections.observableArrayList("ذكر","أنثى");
    int idOccupation=0,idOccupation2=0;
    public void fillCombo(){
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `occupations`");
            rs=pst.executeQuery();
            while (rs.next()){
                occupations.add(new Occupation(rs.getInt("id"),rs.getString("occupationName")));

            }
            for (int i=0;i<occupations.size();i++){
                reelOccupation.getItems().add(occupations.get(i).getNameOcupation());
                reelOccupation2.getItems().add(occupations.get(i).getNameOcupation());
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void fillCombo2(){
        nationalities.clear();
        employeeNationality2.getItems().clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `nationalities`");
            rs=pst.executeQuery();
            while (rs.next()){
                nationalities.add(new Nationality(rs.getInt("id"),rs.getString("nationalityName")));

            }
            for (int i=0;i<nationalities.size();i++){
                employeeNationality2.getItems().add(nationalities.get(i).getNameNationality());
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    private TextField employeeName;



    @FXML
    private TextField identityNumber;

    @FXML
    private TextField employeeNumber;

    @FXML
    private TextField employeeNationality;

    @FXML
    private TextField religion;

    @FXML
    private TextField residenceOccupation;

    @FXML
    private ComboBox<String> identityType;

    @FXML
    private ComboBox<String> employeeType;

    @FXML
    private ComboBox<String> reelOccupation;

    @FXML
    private DatePicker residenceEndDate;

    @FXML
    private DatePicker HealthCertificateStartDate;

    @FXML
    private DatePicker HealthCertificatEndDate;

    @FXML
    private TextField HealthCertificateNumber;

    @FXML
    private TextField employeeName2;

    @FXML
    private TextField employeeNumber2;

    @FXML
    private ComboBox<String> identityType2;

    @FXML
    private TextField identityNumber2;

    @FXML
    private TextField religion2;

    @FXML
    private TextField residenceOccupation2;

    @FXML
    private ComboBox<String> reelOccupation2;

    @FXML
    private DatePicker residenceEndDate2;

    @FXML
    private DatePicker HealthCertificateStartDate2;

    @FXML
    private DatePicker HealthCertificatEndDate2;

    @FXML
    private TextField HealthCertificateNumber2;

    @FXML
    private TextField ClassificationNumber2;

    @FXML
    private DatePicker ClassificationStartDate2;

    @FXML
    private DatePicker ClassificationEndDate2;

    @FXML
    private ComboBox<String> employeeNationality2;

    @FXML
    private TextField ClassificationNumber;

    @FXML
    private DatePicker ClassificationStartDate;

    @FXML
    private DatePicker ClassificationEndDate;

    @FXML
    private ComboBox<String> employeeSex;

    @FXML
    public void addEmployee(ActionEvent actionEvent) {
        int dejaExist=0;
        int size=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            if (employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي"){
                pst=con.prepareStatement("SELECT * FROM `employees` WHERE `identityNumber`=? OR `employeeNumber`=?");
                pst.setString(1,identityNumber.getText());
                pst.setString(2,employeeNumber.getText());

            }else{
                pst=con.prepareStatement("SELECT * FROM `employees` WHERE `identityNumber`=? OR `employeeNumber`=?");
                pst.setString(1,identityNumber2.getText());
                pst.setString(2,employeeNumber2.getText());
            }

            rs=pst.executeQuery();
            while(rs.next()){
                size++;
            }
            if (size>0){
                dejaExist=1;
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if ((employeeType.getSelectionModel().getSelectedItem()=="موظف أجنبي")&&(employeeName2.getText().isEmpty() ||employeeNumber2.getText().isEmpty() ||employeeNationality2.getSelectionModel().isEmpty() ||identityType2.getSelectionModel().isEmpty() ||identityNumber2.getText().isEmpty() ||HealthCertificateNumber2.getText().isEmpty() ||ClassificationNumber2.getText().isEmpty() ||religion2.getText().isEmpty() ||residenceOccupation2.getText().isEmpty()  ||reelOccupation2.getSelectionModel().isEmpty()  ||employeeSex.getSelectionModel().isEmpty() ||residenceEndDate2.getEditor().getText().isEmpty()||HealthCertificateStartDate2.getEditor().getText().isEmpty()||HealthCertificatEndDate2.getEditor().getText().isEmpty()||ClassificationStartDate2.getEditor().getText().isEmpty()||ClassificationEndDate2.getEditor().getText().isEmpty())){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if ((employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي")&&(employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||identityNumber.getText().isEmpty() ||HealthCertificateNumber.getText().isEmpty() ||ClassificationNumber.getText().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||employeeSex.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()||HealthCertificateStartDate.getEditor().getText().isEmpty()||HealthCertificatEndDate.getEditor().getText().isEmpty()||ClassificationStartDate.getEditor().getText().isEmpty()||ClassificationEndDate.getEditor().getText().isEmpty())){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                if (file1!=null && file2!=null && file3!=null){

                    if (employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي"){
                        con=new Controlers.ConnectDB().getConnection();
                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`, `certificateImage`, `identityImage`, `classificationImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف سعودي");
                        pst.setString(2,employeeName.getText());
                        pst.setString(3,employeeNumber.getText());
                        pst.setString(4,"سعودي");
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,"بطاقة هوية");
                        pst.setString(7,identityNumber.getText());
                        pst.setString(8,"مسلم");
                        pst.setString(9," ");
                        pst.setInt(10,idOccupation);
                        pst.setString(11, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(12, HealthCertificateNumber.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setString(15, ClassificationNumber.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(18, fis1,(int) file1.length());
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(19, fis2,(int) file2.length());
                        FileInputStream fis3=new FileInputStream(file3);
                        pst.setBinaryStream(20, fis3,(int) file3.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }else if (employeeType.getSelectionModel().getSelectedItem()=="موظف أجنبي"){
                        con=new Controlers.ConnectDB().getConnection();

                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`, `certificateImage`, `identityImage`, `classificationImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف أجنبي");
                        pst.setString(2,employeeName2.getText());
                        pst.setString(3,employeeNumber2.getText());
                        pst.setString(4,employeeNationality2.getValue());
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,identityType2.getValue());
                        pst.setString(7,identityNumber2.getText());
                        pst.setString(8,religion2.getText());
                        pst.setString(9,residenceOccupation2.getText());
                        pst.setInt(10,idOccupation2);
                        pst.setString(11, String.valueOf(residenceEndDate2.getValue()));
                        pst.setString(12, HealthCertificateNumber2.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate2.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate2.getValue()));
                        pst.setString(15, ClassificationNumber2.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate2.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate2.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(18, fis1,(int) file1.length());
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(19, fis2,(int) file2.length());
                        FileInputStream fis3=new FileInputStream(file3);
                        pst.setBinaryStream(20, fis3,(int) file3.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }

                }else if (file1!=null && file2!=null){
                    if (employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي"){
                        con=new Controlers.ConnectDB().getConnection();
                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`, `certificateImage`, `identityImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف سعودي");
                        pst.setString(2,employeeName.getText());
                        pst.setString(3,employeeNumber.getText());
                        pst.setString(4,"سعودي");
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,"بطاقة هوية");
                        pst.setString(7,identityNumber.getText());
                        pst.setString(8,"مسلم");
                        pst.setString(9," ");
                        pst.setInt(10,idOccupation);
                        pst.setString(11, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(12, HealthCertificateNumber.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setString(15, ClassificationNumber.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(18, fis1,(int) file1.length());
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(19, fis2,(int) file2.length());

                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }else if (employeeType.getSelectionModel().getSelectedItem()=="موظف أجنبي"){
                        con=new Controlers.ConnectDB().getConnection();

                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`, `certificateImage`, `identityImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف أجنبي");
                        pst.setString(2,employeeName2.getText());
                        pst.setString(3,employeeNumber2.getText());
                        pst.setString(4,employeeNationality2.getValue());
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,identityType2.getValue());
                        pst.setString(7,identityNumber2.getText());
                        pst.setString(8,religion2.getText());
                        pst.setString(9,residenceOccupation2.getText());
                        pst.setInt(10,idOccupation2);
                        pst.setString(11, String.valueOf(residenceEndDate2.getValue()));
                        pst.setString(12, HealthCertificateNumber2.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate2.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate2.getValue()));
                        pst.setString(15, ClassificationNumber2.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate2.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate2.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(18, fis1,(int) file1.length());
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(19, fis2,(int) file2.length());

                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }
                }else if (file1!=null && file3!=null){
                    if (employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي"){
                        con=new Controlers.ConnectDB().getConnection();
                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`, `certificateImage`, `classificationImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف سعودي");
                        pst.setString(2,employeeName.getText());
                        pst.setString(3,employeeNumber.getText());
                        pst.setString(4,"سعودي");
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,"بطاقة هوية");
                        pst.setString(7,identityNumber.getText());
                        pst.setString(8,"مسلم");
                        pst.setString(9," ");
                        pst.setInt(10,idOccupation);
                        pst.setString(11, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(12, HealthCertificateNumber.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setString(15, ClassificationNumber.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(18, fis1,(int) file1.length());
                        FileInputStream fis3=new FileInputStream(file3);
                        pst.setBinaryStream(19, fis3,(int) file3.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }else if (employeeType.getSelectionModel().getSelectedItem()=="موظف أجنبي"){
                        con=new Controlers.ConnectDB().getConnection();

                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`, `certificateImage`, `classificationImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف أجنبي");
                        pst.setString(2,employeeName2.getText());
                        pst.setString(3,employeeNumber2.getText());
                        pst.setString(4,employeeNationality2.getValue());
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,identityType2.getValue());
                        pst.setString(7,identityNumber2.getText());
                        pst.setString(8,religion2.getText());
                        pst.setString(9,residenceOccupation2.getText());
                        pst.setInt(10,idOccupation2);
                        pst.setString(11, String.valueOf(residenceEndDate2.getValue()));
                        pst.setString(12, HealthCertificateNumber2.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate2.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate2.getValue()));
                        pst.setString(15, ClassificationNumber2.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate2.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate2.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(18, fis1,(int) file1.length());
                        FileInputStream fis3=new FileInputStream(file3);
                        pst.setBinaryStream(19, fis3,(int) file3.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }
                }else if (file2!=null && file3!=null){
                    if (employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي"){
                        con=new Controlers.ConnectDB().getConnection();
                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`,`identityImage`, `classificationImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف سعودي");
                        pst.setString(2,employeeName.getText());
                        pst.setString(3,employeeNumber.getText());
                        pst.setString(4,"سعودي");
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,"بطاقة هوية");
                        pst.setString(7,identityNumber.getText());
                        pst.setString(8,"مسلم");
                        pst.setString(9," ");
                        pst.setInt(10,idOccupation);
                        pst.setString(11, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(12, HealthCertificateNumber.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setString(15, ClassificationNumber.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate.getValue()));
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(18, fis2,(int) file2.length());
                        FileInputStream fis3=new FileInputStream(file3);
                        pst.setBinaryStream(19, fis3,(int) file3.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }else if (employeeType.getSelectionModel().getSelectedItem()=="موظف أجنبي"){
                        con=new Controlers.ConnectDB().getConnection();

                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`, `identityImage`, `classificationImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف أجنبي");
                        pst.setString(2,employeeName2.getText());
                        pst.setString(3,employeeNumber2.getText());
                        pst.setString(4,employeeNationality2.getValue());
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,identityType2.getValue());
                        pst.setString(7,identityNumber2.getText());
                        pst.setString(8,religion2.getText());
                        pst.setString(9,residenceOccupation2.getText());
                        pst.setInt(10,idOccupation2);
                        pst.setString(11, String.valueOf(residenceEndDate2.getValue()));
                        pst.setString(12, HealthCertificateNumber2.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate2.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate2.getValue()));
                        pst.setString(15, ClassificationNumber2.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate2.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate2.getValue()));
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(18, fis2,(int) file2.length());
                        FileInputStream fis3=new FileInputStream(file3);
                        pst.setBinaryStream(19, fis3,(int) file3.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }
                }else if (file1!=null){
                    if (employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي"){
                        con=new Controlers.ConnectDB().getConnection();
                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`, `certificateImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف سعودي");
                        pst.setString(2,employeeName.getText());
                        pst.setString(3,employeeNumber.getText());
                        pst.setString(4,"سعودي");
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,"بطاقة هوية");
                        pst.setString(7,identityNumber.getText());
                        pst.setString(8,"مسلم");
                        pst.setString(9," ");
                        pst.setInt(10,idOccupation);
                        pst.setString(11, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(12, HealthCertificateNumber.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setString(15, ClassificationNumber.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(18, fis1,(int) file1.length());
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }else if (employeeType.getSelectionModel().getSelectedItem()=="موظف أجنبي"){
                        con=new Controlers.ConnectDB().getConnection();

                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`, `certificateImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف أجنبي");
                        pst.setString(2,employeeName2.getText());
                        pst.setString(3,employeeNumber2.getText());
                        pst.setString(4,employeeNationality2.getValue());
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,identityType2.getValue());
                        pst.setString(7,identityNumber2.getText());
                        pst.setString(8,religion2.getText());
                        pst.setString(9,residenceOccupation2.getText());
                        pst.setInt(10,idOccupation2);
                        pst.setString(11, String.valueOf(residenceEndDate2.getValue()));
                        pst.setString(12, HealthCertificateNumber2.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate2.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate2.getValue()));
                        pst.setString(15, ClassificationNumber2.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate2.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate2.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(18, fis1,(int) file1.length());
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }
                }else if (file2!=null){
                    if (employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي"){
                        con=new Controlers.ConnectDB().getConnection();
                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`,`identityImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف سعودي");
                        pst.setString(2,employeeName.getText());
                        pst.setString(3,employeeNumber.getText());
                        pst.setString(4,"سعودي");
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,"بطاقة هوية");
                        pst.setString(7,identityNumber.getText());
                        pst.setString(8,"مسلم");
                        pst.setString(9," ");
                        pst.setInt(10,idOccupation);
                        pst.setString(11, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(12, HealthCertificateNumber.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setString(15, ClassificationNumber.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate.getValue()));
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(18, fis2,(int) file2.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }else if (employeeType.getSelectionModel().getSelectedItem()=="موظف أجنبي"){
                        con=new Controlers.ConnectDB().getConnection();

                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`,`identityImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف أجنبي");
                        pst.setString(2,employeeName2.getText());
                        pst.setString(3,employeeNumber2.getText());
                        pst.setString(4,employeeNationality2.getValue());
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,identityType2.getValue());
                        pst.setString(7,identityNumber2.getText());
                        pst.setString(8,religion2.getText());
                        pst.setString(9,residenceOccupation2.getText());
                        pst.setInt(10,idOccupation2);
                        pst.setString(11, String.valueOf(residenceEndDate2.getValue()));
                        pst.setString(12, HealthCertificateNumber2.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate2.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate2.getValue()));
                        pst.setString(15, ClassificationNumber2.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate2.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate2.getValue()));
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(18, fis2,(int) file2.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }
                }else if (file3!=null){
                    if (employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي"){
                        con=new Controlers.ConnectDB().getConnection();
                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`,`classificationImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف سعودي");
                        pst.setString(2,employeeName.getText());
                        pst.setString(3,employeeNumber.getText());
                        pst.setString(4,"سعودي");
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,"بطاقة هوية");
                        pst.setString(7,identityNumber.getText());
                        pst.setString(8,"مسلم");
                        pst.setString(9," ");
                        pst.setInt(10,idOccupation);
                        pst.setString(11, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(12, HealthCertificateNumber.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setString(15, ClassificationNumber.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate.getValue()));
                        FileInputStream fis3=new FileInputStream(file3);
                        pst.setBinaryStream(18, fis3,(int) file3.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }else if (employeeType.getSelectionModel().getSelectedItem()=="موظف أجنبي"){
                        con=new Controlers.ConnectDB().getConnection();

                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`,`classificationImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف أجنبي");
                        pst.setString(2,employeeName2.getText());
                        pst.setString(3,employeeNumber2.getText());
                        pst.setString(4,employeeNationality2.getValue());
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,identityType2.getValue());
                        pst.setString(7,identityNumber2.getText());
                        pst.setString(8,religion2.getText());
                        pst.setString(9,residenceOccupation2.getText());
                        pst.setInt(10,idOccupation2);
                        pst.setString(11, String.valueOf(residenceEndDate2.getValue()));
                        pst.setString(12, HealthCertificateNumber2.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate2.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate2.getValue()));
                        pst.setString(15, ClassificationNumber2.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate2.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate2.getValue()));
                        FileInputStream fis3=new FileInputStream(file3);
                        pst.setBinaryStream(18, fis3,(int) file3.length());
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }
                }else{
                    if (employeeType.getSelectionModel().getSelectedItem()=="موظف سعودي"){
                        con=new Controlers.ConnectDB().getConnection();
                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف سعودي");
                        pst.setString(2,employeeName.getText());
                        pst.setString(3,employeeNumber.getText());
                        pst.setString(4,"سعودي");
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,"بطاقة هوية");
                        pst.setString(7,identityNumber.getText());
                        pst.setString(8,"مسلم");
                        pst.setString(9," ");
                        pst.setInt(10,idOccupation);
                        pst.setString(11, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(12, HealthCertificateNumber.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setString(15, ClassificationNumber.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate.getValue()));

                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }else if (employeeType.getSelectionModel().getSelectedItem()=="موظف أجنبي"){
                        con=new Controlers.ConnectDB().getConnection();

                        pst=con.prepareStatement("INSERT INTO `employees`(`employeeType`, `employeeName`, `employeeNumber`, `employeeNationality`," +
                                " `employeeSex`, `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`, `HealthCertificateNumber`, `HealthCertificateStartDate`, `HealthCertificatEndDate`, `ClassificationNumber`, `ClassificationStartDate`, `ClassificationEndDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        pst.setString(1,"موظف أجنبي");
                        pst.setString(2,employeeName2.getText());
                        pst.setString(3,employeeNumber2.getText());
                        pst.setString(4,employeeNationality2.getValue());
                        pst.setString(5,employeeSex.getValue());
                        pst.setString(6,identityType2.getValue());
                        pst.setString(7,identityNumber2.getText());
                        pst.setString(8,religion2.getText());
                        pst.setString(9,residenceOccupation2.getText());
                        pst.setInt(10,idOccupation2);
                        pst.setString(11, String.valueOf(residenceEndDate2.getValue()));
                        pst.setString(12, HealthCertificateNumber2.getText());
                        pst.setString(13, String.valueOf(HealthCertificateStartDate2.getValue()));
                        pst.setString(14, String.valueOf(HealthCertificatEndDate2.getValue()));
                        pst.setString(15, ClassificationNumber2.getText());
                        pst.setString(16, String.valueOf(ClassificationStartDate2.getValue()));
                        pst.setString(17, String.valueOf(ClassificationEndDate2.getValue()));
                        pst.execute();
                        warningMsg("إظافة","تمت الإظافة بنجاح");
                        pst.close();
                    }
                }


            } catch (SQLException | FileNotFoundException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables.getMessage());
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable();
            employeeName.clear();
            identityNumber.clear();
            employeeNumber.clear();
            religion.clear();
            employeeNationality.clear();
            residenceOccupation.clear();
        }

    }
    @FXML
    void selectOccupation(ActionEvent event) {
        int index= reelOccupation.getSelectionModel().getSelectedIndex();
        idOccupation=occupations.get(index).getIdOcupation();
    }
    @FXML
    void selectOccupation2(ActionEvent event) {
        int index= reelOccupation2.getSelectionModel().getSelectedIndex();
        idOccupation2=occupations.get(index).getIdOcupation();
    }



    @FXML
    private Label usernameMenu;
    @FXML
    private Button areaMenuButton;//c bn

    @FXML
    private Button locationMenuButton; //c bn

    @FXML
    private Button projectMenuButton;//mazel

    @FXML
    private Button garanteeMenuButton;// cbn

    @FXML
    private Button occupationMenuButton;// c bn

    @FXML
    private Button employeeMenuButton;// cbn

    @FXML
    private Button abstractMenuButton;// c bn

    @FXML
    private Button deductionMenuButton;// c bn

    @FXML
    private Button penaltyMenuButton;// c bn

    @FXML
    private Button userMenuButton;// c bn

    @FXML
    private Button repportMenuButton;//c bn


    @FXML
    private Button employeeDeletePrivilege;
    @FXML
    private Button employeeAddPrivilege;
    @FXML
    private Button employeeEditPrivilege;




    int idConnected=0;
    String usernameConnected="";
    String employeeNameConnected="";
    public void Init(int idConnected,String usernameConnected,String employeeNameConnected){
        this.idConnected = idConnected;
        this.usernameConnected = usernameConnected;
        this.employeeNameConnected = employeeNameConnected;
        usernameMenu.setText(employeeNameConnected);
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `users`,`privileges` WHERE users.id=? AND users.privilegesId=privileges.id");
            pst.setInt(1,idConnected);
            rs=pst.executeQuery();
            while (rs.next()){
                if (rs.getInt("arsa")==0){
                    areaMenuButton.setDisable(true);
                }else{
                    areaMenuButton.setDisable(false);
                }
                if (rs.getInt("losa")==0){
                    locationMenuButton.setDisable(true);

                }else{
                    locationMenuButton.setDisable(false);

                }

                if (rs.getInt("prsa")==0){
                    projectMenuButton.setDisable(true);

                }else{
                    projectMenuButton.setDisable(false);

                }

                if (rs.getInt("grsa")==0){
                    garanteeMenuButton.setDisable(true);

                }else{
                    garanteeMenuButton.setDisable(false);

                }

                if (rs.getInt("ocsa")==0){
                    occupationMenuButton.setDisable(true);

                }else{
                    occupationMenuButton.setDisable(false);

                }

                if (rs.getInt("emsa")==0){
                    employeeMenuButton.setDisable(true);
                    employeeAddPrivilege.setDisable(true);

                }else{
                    employeeMenuButton.setDisable(false);
                    employeeAddPrivilege.setDisable(false);

                }
                if (rs.getInt("emde")==0){
                    employeeDeletePrivilege.setDisable(true);
                    employeeEditPrivilege.setDisable(true);

                }else{
                    employeeDeletePrivilege.setDisable(false);
                    employeeEditPrivilege.setDisable(false);

                }

                if (rs.getInt("absa")==0){
                    abstractMenuButton.setDisable(true);

                }else{
                    abstractMenuButton.setDisable(false);

                }

                if (rs.getInt("desa")==0){
                    deductionMenuButton.setDisable(true);

                }else{
                    deductionMenuButton.setDisable(false);

                }

                if (rs.getInt("pesa")==0){
                    penaltyMenuButton.setDisable(true);

                }else{
                    penaltyMenuButton.setDisable(false);

                }

                if (rs.getInt("ussa")==0){
                    userMenuButton.setDisable(true);

                }else{
                    userMenuButton.setDisable(false);

                }

                if (rs.getInt("res")==0){
                    repportMenuButton.setDisable(true);

                }else{
                    repportMenuButton.setDisable(false);

                }
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void areas(javafx.event.ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/areaPage.fxml"));
            AnchorPane root = loader.load();
            AreaPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/locationPage.fxml"));
            AnchorPane root = loader.load();
            LocationPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المواقع");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void projects(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/projectPage.fxml"));
            AnchorPane root = loader.load();
            ProjectPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المشاريع");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void garantees(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/garanteePage.fxml"));
            AnchorPane root = loader.load();
            GaranteePage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("الضمانات");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void occupations(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/occupationPage.fxml"));
            AnchorPane root = loader.load();
            OccupationPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("الوظائق");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void employees(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/employeePage.fxml"));
            AnchorPane root = loader.load();
            EmployeePage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("الموظفين");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void users(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/userPage.fxml"));
            AnchorPane root = loader.load();
            UserPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("إدارة المستخدمين");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void penalties(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/penaltyPage.fxml"));
            AnchorPane root = loader.load();
            PenaltyPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("الغرامات");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void report(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/repportPage.fxml"));
            AnchorPane root = loader.load();
            RepportPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("التقارير");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void abstracts(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/abstractPage.fxml"));
            AnchorPane root = loader.load();
            AbstractPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المستخلصات");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void deduction(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/deductionPage.fxml"));
            AnchorPane root = loader.load();
            DeductionPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("الإستقطاعات");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public void login(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/projectPage.fxml"));
            AnchorPane root = loader.load();
            ProjectPage controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("المشاريع");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }

    }

    @FXML
    private TableView<EmployeeForTable> employeeTableView;

    @FXML
    private TableColumn<EmployeeForTable, String> employeeNameTable;

    @FXML
    private TableColumn<EmployeeForTable, String> employeeNumberTable;

    @FXML
    private TableColumn<EmployeeForTable, String> employeeNationalityTable;

    @FXML
    private TableColumn<EmployeeForTable, String> identityTypeTable;

    @FXML
    private TableColumn<EmployeeForTable, String> identityNumberTable;

    @FXML
    private TableColumn<EmployeeForTable, String> religionTable;

    @FXML
    private TableColumn<EmployeeForTable, String> residenceOccupationTable;

    @FXML
    private TableColumn<EmployeeForTable, String> reelOccupationTable;

    @FXML
    private TableColumn<EmployeeForTable, String> residenceEndDateTable;

    @FXML
    private TableColumn<EmployeeForTable, String> HealthCertificateStartDateTable;

    @FXML
    private TableColumn<EmployeeForTable, String> HealthCertificatEndDateTable;

    @FXML
    private TableColumn<EmployeeForTable, String> ClassificationNumberTable;

    @FXML
    private TableColumn<EmployeeForTable, String> ClassificationStartDateTable;

    @FXML
    private TableColumn<EmployeeForTable, String> ClassificationEndDateTable;

    @FXML
    private TableColumn<EmployeeForTable, String> HealthCertificateNumberTable;

    ObservableList employeesTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identityType2.setItems(identityTypeList);
        employeeType.setItems(employeeTypeList);
        employeeSex.setItems(employeeSexList);
        employeeType.getSelectionModel().select(0);
        employeeSaoudi.setVisible(true);
        employeeAjnabi.setVisible(false);

        fillCombo();

        addToTable();
        employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
        employeeNationalityTable.setCellValueFactory(new PropertyValueFactory<>("employeeNationality"));
        identityTypeTable.setCellValueFactory(new PropertyValueFactory<>("identityType"));
        identityNumberTable.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
        religionTable.setCellValueFactory(new PropertyValueFactory<>("religion"));
//        residenceOccupationTable.setCellValueFactory(new PropertyValueFactory<>("residenceOccupation"));
        HealthCertificateNumberTable.setCellValueFactory(new PropertyValueFactory<>("HealthCertificateNumber"));
        ClassificationNumberTable.setCellValueFactory(new PropertyValueFactory<>("ClassificationNumber"));
        ClassificationStartDateTable.setCellValueFactory(new PropertyValueFactory<>("ClassificationStartDate"));
        ClassificationEndDateTable.setCellValueFactory(new PropertyValueFactory<>("ClassificationEndDate"));
        reelOccupationTable.setCellValueFactory(new PropertyValueFactory<>("reelOccupationName"));
        residenceEndDateTable.setCellValueFactory(new PropertyValueFactory<>("residenceEndDate"));
        HealthCertificateStartDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificateStartDate"));
        HealthCertificatEndDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificatEndDate"));
        employeeTableView.setItems(employeesTable);
        fillCombo2();
        addToTable2();
        newNationalityTable.setCellValueFactory(new PropertyValueFactory<>("nameNationality"));
        nationalitiesTableView.setItems(nationalities);
    }
    public void addToTable(){
        employeesTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees`");
            rs=pst.executeQuery();
            while (rs.next()){
                employeesTable.add(new EmployeeForTable(rs.getInt("id"),rs.getInt("reelOccupation"),rs.getString("employeeName"),rs.getString("employeeNumber"),rs.getString("employeeNationality"),rs.getString("identityType"),rs.getString("identityNumber"),rs.getString("religion"),getOccupationName(rs.getInt("reelOccupation")),rs.getString("residenceOccupation"),rs.getString("residenceEndDate"),rs.getString("HealthCertificateStartDate"),rs.getString("HealthCertificatEndDate"),rs.getString("employeeSex"),rs.getString("HealthCertificateNumber"),rs.getString("ClassificationNumber"),rs.getString("ClassificationStartDate"),rs.getString("ClassificationEndDate"),rs.getString("employeeType")));
            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    public void addToTable2(){
        nationalities.clear();
        nationalitiesTableView.getItems().clear();

        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `nationalities`");
            rs=pst.executeQuery();
            while (rs.next()){
                nationalities.add(new Nationality(rs.getInt("id"),rs.getString("nationalityName")));
            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    public String getOccupationName(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `occupations` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("occupationName");
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }

    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        int index= employeeTableView.getSelectionModel().getSelectedIndex();
        int idDelete=employeeTableView.getItems().get(index).getIdEmployee();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `employees` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTable();
        }
    }
    public void warningMsg(String title,String message ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private TextField search;
    @FXML
    public void search(KeyEvent keyEvent) {
        String key=search.getText().trim();
        if (key.isEmpty()){
            addToTable();
            employeeTableView.setItems(employeesTable);
        }else{
            employeesTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `employees` WHERE `employeeName` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    employeesTable.add(new EmployeeForTable(rs.getInt("id"),rs.getInt("reelOccupation"),rs.getString("employeeName"),rs.getString("employeeNumber"),rs.getString("employeeNationality"),rs.getString("identityType"),rs.getString("identityNumber"),rs.getString("religion"),getOccupationName(rs.getInt("reelOccupation")),rs.getString("residenceOccupation"),rs.getString("residenceEndDate"),rs.getString("HealthCertificateStartDate"),rs.getString("HealthCertificatEndDate"),rs.getString("employeeSex"),rs.getString("HealthCertificateNumber"),rs.getString("ClassificationNumber"),rs.getString("ClassificationStartDate"),rs.getString("ClassificationEndDate"),rs.getString("employeeType")));
                }

                employeeTableView.setItems(employeesTable);
                pst.close();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

    @FXML
    private Button edit;
    public void edit(ActionEvent actionEvent) {
        int index= employeeTableView.getSelectionModel().getSelectedIndex();
        int idEdit=employeeTableView.getItems().get(index).getIdEmployee();


        if (employeeEditPrivilege.getText().contains("تعديل موظف")){
            employeeEditPrivilege.setText("حفظ");
            employeeName.setText(employeeTableView.getItems().get(index).getEmployeeName());
            identityNumber.setText(employeeTableView.getItems().get(index).getIdentityNumber());
            residenceEndDate.setValue(LocalDate.parse(employeeTableView.getItems().get(index).getResidenceEndDate()));
            HealthCertificateStartDate.setValue(LocalDate.parse(employeeTableView.getItems().get(index).getHealthCertificateStartDate()));
            HealthCertificatEndDate.setValue(LocalDate.parse(employeeTableView.getItems().get(index).getResidenceEndDate()));
            residenceEndDate.getEditor().setText(employeeTableView.getItems().get(index).getResidenceEndDate());
            HealthCertificateStartDate.getEditor().setText(employeeTableView.getItems().get(index).getHealthCertificateStartDate());
            HealthCertificatEndDate.getEditor().setText(employeeTableView.getItems().get(index).getResidenceEndDate());
            reelOccupation.setValue(employeeTableView.getItems().get(index).getReelOccupationName());
            identityType.setValue(employeeTableView.getItems().get(index).getIdentityType());
            employeeNumber.setText(employeeTableView.getItems().get(index).getEmployeeNumber());
            employeeNationality.setText(employeeTableView.getItems().get(index).getEmployeeNationality());
            residenceOccupation.setText(employeeTableView.getItems().get(index).getResidenceOccupation());
            religion.setText(employeeTableView.getItems().get(index).getReligion());
            file1=null;file2=null;
        }else if (employeeEditPrivilege.getText().contains("حفظ")){

            if (file1!=null && file2!=null){
                int dejaExist=0;
                int size=0;
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("SELECT * FROM `employees` WHERE (`identityNumber`=? OR `employeeNumber`=?) AND id!=?");
                    pst.setString(1,identityNumber.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setInt(3,idEdit);

                    rs=pst.executeQuery();
                    while(rs.next()){
                        size++;
                    }
                    if (size>0){
                        dejaExist=1;
                    }
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||employeeNationality.getText().isEmpty() ||identityNumber.getText().isEmpty() ||religion.getText().isEmpty() ||residenceOccupation.getText().isEmpty() ||identityType.getSelectionModel().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()){
                    warningMsg("تنبيه","يرجى ملء الفراغات");
                }else if(dejaExist==1){
                    warningMsg("تنبيه","المعلومات موجودة من قبل");
                }else{
                    try {
                        System.out.println(reelOccupation.getValue());
                        for (int i=0; i<occupations.size() ;i++){
                            System.out.println(occupations.get(i).getNameOcupation());
                            if (occupations.get(i).getNameOcupation()==reelOccupation.getValue()){
                                idOccupation=occupations.get(i).getIdOcupation();
                            }
                        }
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `employees` SET `employeeName`=?,`employeeNumber`=?,`employeeNationality`=?,`identityType`=?,`identityNumber`=?,`religion`=?,`residenceOccupation`=?,`reelOccupation`=?,`residenceEndDate`=?,`HealthCertificateStartDate`=?,`HealthCertificatEndDate`=?,`certificateImage`=?,`identityImage`=?  WHERE `id`=?");


                        pst.setString(1,employeeName.getText());
                        pst.setString(2,employeeNumber.getText());
                        pst.setString(3,employeeNationality.getText());
                        pst.setString(4,identityType.getValue());
                        pst.setString(5,identityNumber.getText());
                        pst.setString(6,religion.getText());
                        pst.setString(7,residenceOccupation.getText());
                        pst.setInt(8,idOccupation);
                        pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(12, fis1,(int) file1.length());
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(13, fis2,(int) file2.length());
                        pst.setInt(14,idEdit);
                        pst.execute();
                        warningMsg("تعديل","تم التعديل بنجاح");
                        pst.close();

                    } catch (SQLException | FileNotFoundException throwables) {
                        throwables.printStackTrace();
                        warningMsg("تعديل","حدث خطأ أثناء التعديل");
                    }
                }

            }else if (file1== null && file2==null){
                int dejaExist=0;
                int size=0;
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("SELECT * FROM `employees` WHERE (`identityNumber`=? OR `employeeNumber`=?) AND id!=?");
                    pst.setString(1,identityNumber.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setInt(3,idEdit);

                    rs=pst.executeQuery();
                    while(rs.next()){
                        size++;
                    }
                    if (size>0){
                        dejaExist=1;
                    }
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||employeeNationality.getText().isEmpty() ||identityNumber.getText().isEmpty() ||religion.getText().isEmpty() ||residenceOccupation.getText().isEmpty() ||identityType.getSelectionModel().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()||HealthCertificateStartDate.getEditor().getText().isEmpty()||HealthCertificatEndDate.getEditor().getText().isEmpty()){
                    warningMsg("تنبيه","يرجى ملء الفراغات");
                }else if(dejaExist==1){
                    warningMsg("تنبيه","المعلومات موجودة من قبل");
                }else{
                    try {
                        System.out.println(reelOccupation.getValue());
                        for (int i=0; i<occupations.size() ;i++){
                            System.out.println(occupations.get(i).getNameOcupation());
                            if (occupations.get(i).getNameOcupation()==reelOccupation.getValue()){
                                idOccupation=occupations.get(i).getIdOcupation();
                            }
                        }
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `employees` SET `employeeName`=?,`employeeNumber`=?,`employeeNationality`=?,`identityType`=?,`identityNumber`=?,`religion`=?,`residenceOccupation`=?,`reelOccupation`=?,`residenceEndDate`=?,`HealthCertificateStartDate`=?,`HealthCertificatEndDate`=?  WHERE `id`=?");


                        pst.setString(1,employeeName.getText());
                        pst.setString(2,employeeNumber.getText());
                        pst.setString(3,employeeNationality.getText());
                        pst.setString(4,identityType.getValue());
                        pst.setString(5,identityNumber.getText());
                        pst.setString(6,religion.getText());
                        pst.setString(7,residenceOccupation.getText());
                        pst.setInt(8,idOccupation);
                        pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setInt(12,idEdit);
                        pst.execute();
                        warningMsg("تعديل","تم التعديل بنجاح");
                        pst.close();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        warningMsg("تعديل","حدث خطأ أثناء التعديل");
                    }
                }



            }else if (file1== null && file2!=null){
                int dejaExist=0;
                int size=0;
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("SELECT * FROM `employees` WHERE (`identityNumber`=? OR `employeeNumber`=?) AND id!=?");
                    pst.setString(1,identityNumber.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setInt(3,idEdit);

                    rs=pst.executeQuery();
                    while(rs.next()){
                        size++;
                    }
                    if (size>0){
                        dejaExist=1;
                    }
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||employeeNationality.getText().isEmpty() ||identityNumber.getText().isEmpty() ||religion.getText().isEmpty() ||residenceOccupation.getText().isEmpty() ||identityType.getSelectionModel().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()||HealthCertificateStartDate.getEditor().getText().isEmpty()||HealthCertificatEndDate.getEditor().getText().isEmpty()){
                    warningMsg("تنبيه","يرجى ملء الفراغات");
                }else if(dejaExist==1){
                    warningMsg("تنبيه","المعلومات موجودة من قبل");
                }else{
                    try {
                        System.out.println(reelOccupation.getValue());
                        for (int i=0; i<occupations.size() ;i++){
                            System.out.println(occupations.get(i).getNameOcupation());
                            if (occupations.get(i).getNameOcupation()==reelOccupation.getValue()){
                                idOccupation=occupations.get(i).getIdOcupation();
                            }
                        }
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `employees` SET `employeeName`=?,`employeeNumber`=?,`employeeNationality`=?,`identityType`=?,`identityNumber`=?,`religion`=?,`residenceOccupation`=?,`reelOccupation`=?,`residenceEndDate`=?,`HealthCertificateStartDate`=?,`HealthCertificatEndDate`=?,`identityImage`=?  WHERE `id`=?");


                        pst.setString(1,employeeName.getText());
                        pst.setString(2,employeeNumber.getText());
                        pst.setString(3,employeeNationality.getText());
                        pst.setString(4,identityType.getValue());
                        pst.setString(5,identityNumber.getText());
                        pst.setString(6,religion.getText());
                        pst.setString(7,residenceOccupation.getText());
                        pst.setInt(8,idOccupation);
                        pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));

                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(12, fis2,(int) file2.length());
                        pst.setInt(13,idEdit);
                        pst.execute();
                        warningMsg("تعديل","تم التعديل بنجاح");
                        pst.close();

                    } catch (SQLException | FileNotFoundException throwables) {
                        throwables.printStackTrace();
                        warningMsg("تعديل","حدث خطأ أثناء التعديل");
                    }
                }


            }else if (file2==null && file1!=null){
                int dejaExist=0;
                int size=0;
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("SELECT * FROM `employees` WHERE (`identityNumber`=? OR `employeeNumber`=?) AND id!=?");
                    pst.setString(1,identityNumber.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setInt(3,idEdit);

                    rs=pst.executeQuery();
                    while(rs.next()){
                        size++;
                    }
                    if (size>0){
                        dejaExist=1;
                    }
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||employeeNationality.getText().isEmpty() ||identityNumber.getText().isEmpty() ||religion.getText().isEmpty() ||residenceOccupation.getText().isEmpty() ||identityType.getSelectionModel().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()||HealthCertificateStartDate.getEditor().getText().isEmpty()||HealthCertificatEndDate.getEditor().getText().isEmpty()){
                    warningMsg("تنبيه","يرجى ملء الفراغات");
                }else if(dejaExist==1){
                    warningMsg("تنبيه","المعلومات موجودة من قبل");
                }else{
                    try {
                        System.out.println(reelOccupation.getValue());
                        for (int i=0; i<occupations.size() ;i++){
                            System.out.println(occupations.get(i).getNameOcupation());
                            if (occupations.get(i).getNameOcupation()==reelOccupation.getValue()){
                                idOccupation=occupations.get(i).getIdOcupation();
                            }
                        }
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `employees` SET `employeeName`=?,`employeeNumber`=?,`employeeNationality`=?,`identityType`=?,`identityNumber`=?,`religion`=?,`residenceOccupation`=?,`reelOccupation`=?,`residenceEndDate`=?,`HealthCertificateStartDate`=?,`HealthCertificatEndDate`=?,`certificateImage`=? WHERE `id`=?");


                        pst.setString(1,employeeName.getText());
                        pst.setString(2,employeeNumber.getText());
                        pst.setString(3,employeeNationality.getText());
                        pst.setString(4,identityType.getValue());
                        pst.setString(5,identityNumber.getText());
                        pst.setString(6,religion.getText());
                        pst.setString(7,residenceOccupation.getText());
                        pst.setInt(8,idOccupation);
                        pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(12, fis1,(int) file1.length());

                        pst.setInt(13,idEdit);
                        pst.execute();
                        warningMsg("تعديل","تم التعديل بنجاح");
                        pst.close();

                    } catch (SQLException | FileNotFoundException throwables) {
                        throwables.printStackTrace();
                        warningMsg("تعديل","حدث خطأ أثناء التعديل");
                    }
                }

            }
            employeeEditPrivilege.setText("تعديل موظف");
            employeeName.clear();
            identityNumber.clear();
            residenceEndDate.getEditor().clear();
            HealthCertificateStartDate.getEditor().clear();
            HealthCertificatEndDate.getEditor().clear();
            reelOccupation.getItems().clear();
            employeeNumber.clear();
            employeeNationality.clear();
            residenceOccupation.clear();
            religion.clear();
            fillCombo();
            addToTable();
        }


    }

    @FXML
    private ImageView imageHealth1;
    @FXML
    private ImageView imageClassification1;

    @FXML
    private ImageView imageIdentity1;

    @FXML
    private TextField employeeName1;

    @FXML
    private TextField employeeNumber1;

    @FXML
    private TextField employeeNationality1;
    @FXML
    public void LogoutButton(MouseEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("/Views/loginPage.fxml"));
            Stage primaryStage= (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("تسجيل الدخول");
            primaryStage.setScene(new Scene(loader));
            primaryStage.setResizable(true);
            primaryStage.setX(400);
            primaryStage.setY(100);
            primaryStage.setMaxHeight(469);
            primaryStage.setMaxWidth(460);
            primaryStage.show();
        }catch (Exception e){

            System.out.println(e.getMessage());

        }
    }
    @FXML
    private TextField religion1;

    @FXML
    private TextField identityType1;

    @FXML
    private TextField employeeSex1;

    @FXML
    private TextField identityNumber1;

    @FXML
    private TextField residenceOccupation1;

    @FXML
    private TextField reelOccupation1;

    @FXML
    private TextField residenceEndDate1;

    @FXML
    private TextField HealthCertificateStartDate1;
    @FXML
    private TextField HealthCertificateNumber1;
    @FXML
    private TextField ClassificationNumber1;
    @FXML
    private TextField ClassificationStartDate1;
    @FXML
    private TextField ClassificationEndDate1;

    @FXML
    private TextField HealthCertificatEndDate1;
    @FXML
    void idReset(MouseEvent event) {
        employeeEditPrivilege.setText("تعديل موظف");
        int index= employeeTableView.getSelectionModel().getSelectedIndex();
        int idEmpl=employeeTableView.getItems().get(index).getIdEmployee();

        employeeName1.setText(employeeTableView.getItems().get(index).getEmployeeName());
        identityNumber1.setText(employeeTableView.getItems().get(index).getIdentityNumber());
        residenceEndDate1.setText(employeeTableView.getItems().get(index).getResidenceEndDate());
        HealthCertificateStartDate1.setText(employeeTableView.getItems().get(index).getHealthCertificateStartDate());
        HealthCertificateNumber1.setText(employeeTableView.getItems().get(index).getHealthCertificateNumber());
        ClassificationNumber1.setText(employeeTableView.getItems().get(index).getClassificationNumber());
        ClassificationStartDate1.setText(employeeTableView.getItems().get(index).getClassificationStartDate());
        ClassificationEndDate1.setText(employeeTableView.getItems().get(index).getClassificationEndDate());
        HealthCertificatEndDate1.setText(employeeTableView.getItems().get(index).getResidenceEndDate());
        reelOccupation1.setText(employeeTableView.getItems().get(index).getReelOccupationName());
        employeeSex1.setText(employeeTableView.getItems().get(index).getEmployeeSex());
        identityType1.setText(employeeTableView.getItems().get(index).getIdentityType());
        employeeNumber1.setText(employeeTableView.getItems().get(index).getEmployeeNumber());
        employeeNationality1.setText(employeeTableView.getItems().get(index).getEmployeeNationality());
        residenceOccupation1.setText(employeeTableView.getItems().get(index).getResidenceOccupation());
        religion1.setText(employeeTableView.getItems().get(index).getReligion());
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees` WHERE `id`=?");
            pst.setInt(1,idEmpl);
            rs=pst.executeQuery();

            while (rs.next()){
                Blob certificateImageBlob= (Blob) rs.getBlob("certificateImage");
                Blob identityImageBlob= (Blob) rs.getBlob("identityImage");
                Blob classificationImageBlob= (Blob) rs.getBlob("classificationImage");

                if (certificateImageBlob!=null){
                    InputStream inputStream1=certificateImageBlob.getBinaryStream();
                    Image image1=new Image(inputStream1);
                    imageHealth1.setImage(image1);

                }else{
                    Image imageLogo=new Image("imgs/CompLogo.png");
                    imageHealth1.setImage(imageLogo);

                }

                if (identityImageBlob!=null){
                    InputStream inputStream1=identityImageBlob.getBinaryStream();
                    Image image1=new Image(inputStream1);
                    imageIdentity1.setImage(image1);
                }else{
                    Image imageLogo=new Image("imgs/CompLogo.png");
                    imageIdentity1.setImage(imageLogo);

                }

                if (classificationImageBlob!=null){
                    InputStream inputStream1=classificationImageBlob.getBinaryStream();
                    Image image1=new Image(inputStream1);
                    imageClassification1.setImage(image1);
                }else{
                    Image imageLogo=new Image("imgs/CompLogo.png");
                    imageClassification1.setImage(imageLogo);

                }

            }

            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    public void uploadHealth(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        file1=fileChooser.showOpenDialog(null);

    }

    public void uploadIdentity(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        file2=fileChooser.showOpenDialog(null);


    }
    public void uploadClassification(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        file3=fileChooser.showOpenDialog(null);

    }
    @FXML
    private AnchorPane employeeSaoudi;

    @FXML
    private AnchorPane employeeAjnabi;

    public void selectEmployeeType(ActionEvent actionEvent) {
        String selected=employeeType.getSelectionModel().getSelectedItem();
        if (selected=="موظف سعودي"){
            employeeSaoudi.setVisible(true);
            employeeAjnabi.setVisible(false);
        }else {
            employeeSaoudi.setVisible(false);
            employeeAjnabi.setVisible(true);
        }
        file1=null;
        file2=null;
        file3=null;
    }

    @FXML
    private TableView<Nationality> nationalitiesTableView;

    @FXML
    private TableColumn<Nationality, String> newNationalityTable;

    @FXML
    private TextField searchNationality;

    @FXML
    private TextField newNationality;
    int dejaExist=0;
    int size=0;
    public void addNationality(ActionEvent actionEvent) {

            dejaExist=0;
            size=0;
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `nationalities` WHERE `nationalityName`=?");
                pst.setString(1,newNationality.getText());
                rs=pst.executeQuery();
                while(rs.next()){
                    size++;
                }
                if (size>0){
                    dejaExist=1;
                }
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (newNationality.getText().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(dejaExist==1){
                warningMsg("تنبيه","المعلومات موجودة من قبل");
            }else{
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("INSERT INTO `nationalities`(`nationalityName`) VALUES (?)");
                    pst.setString(1,newNationality.getText());
                    pst.execute();
                    warningMsg("إظافة","تمت الإظافة بنجاح");
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("إظافة","حدث خطأ أثناء الإظافة");
                }
                addToTable2();
                fillCombo2();
                newNationality.clear();
            }


    }

    @FXML
    public void searchNationality(KeyEvent keyEvent) {
        String key=searchNationality.getText().trim();
        if (key.isEmpty()){
            addToTable2();
            nationalitiesTableView.setItems(nationalities);
        }else{
            nationalities.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `nationalities` WHERE `nationalityName` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    nationalities.add(new Nationality(rs.getInt("id"),rs.getString("nationalityName")));
                }
                nationalitiesTableView.setItems(nationalities);
                pst.close();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

    @FXML
    public void deleteNationality(ActionEvent actionEvent) {
        int index= nationalitiesTableView.getSelectionModel().getSelectedIndex();
        int idDelete=nationalitiesTableView.getItems().get(index).getIdNationality();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `nationalities` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTable2();
            fillCombo2();
        }
    }

    public void afficherHealth(ActionEvent actionEvent) {
        imageClassification1.setVisible(false);
        imageHealth1.setVisible(true);
    }

    public void afficherClassification(ActionEvent actionEvent) {
        imageClassification1.setVisible(true);
        imageHealth1.setVisible(false);
    }
}



