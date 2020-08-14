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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
import java.util.ResourceBundle;

public class EmployeePage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    File file1,file2;
    ObservableList<Occupation> occupations= FXCollections.observableArrayList();
    ObservableList<String> identityTypeList= FXCollections.observableArrayList("بطاقة هوية","جواز السفر","رخصة السياقة");
    int idOccupation=0;
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
            }

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
    private ComboBox<String> reelOccupation;

    @FXML
    private DatePicker residenceEndDate;

    @FXML
    private DatePicker HealthCertificateStartDate;

    @FXML
    private DatePicker HealthCertificatEndDate;


    @FXML
    public void addEmployee(ActionEvent actionEvent) {
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `employees`(`employeeName`, `employeeNumber`, `employeeNationality`," +
                    " `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`," +
                    " `HealthCertificateStartDate`, `HealthCertificatEndDate`, `certificateImage`, `identityImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
            pst.execute();

        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
        addToTable();
        employeeName.clear();
        identityNumber.clear();
        employeeNumber.clear();
        religion.clear();
        employeeNationality.clear();
        residenceOccupation.clear();
    }
    @FXML
    void selectOccupation(ActionEvent event) {
        int index= reelOccupation.getSelectionModel().getSelectedIndex();
        idOccupation=occupations.get(index).getIdOcupation();
    }




    public void areas(javafx.event.ActionEvent actionEvent) {
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

    ObservableList employeesTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identityType.setItems(identityTypeList);
        fillCombo();

        addToTable();
        employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
        employeeNationalityTable.setCellValueFactory(new PropertyValueFactory<>("employeeNationality"));
        identityTypeTable.setCellValueFactory(new PropertyValueFactory<>("identityType"));
        identityNumberTable.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
        religionTable.setCellValueFactory(new PropertyValueFactory<>("religion"));
        residenceOccupationTable.setCellValueFactory(new PropertyValueFactory<>("residenceOccupation"));
        reelOccupationTable.setCellValueFactory(new PropertyValueFactory<>("reelOccupationName"));
        residenceEndDateTable.setCellValueFactory(new PropertyValueFactory<>("residenceEndDate"));
        HealthCertificateStartDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificateStartDate"));
        HealthCertificatEndDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificatEndDate"));
        employeeTableView.setItems(employeesTable);
    }
    public void addToTable(){
        employeesTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees`");
            rs=pst.executeQuery();
            while (rs.next()){
                employeesTable.add(new EmployeeForTable(rs.getInt("id"),rs.getInt("reelOccupation"),rs.getString("employeeName"),rs.getString("employeeNumber"),rs.getString("employeeNationality"),rs.getString("identityType"),rs.getString("identityNumber"),rs.getString("religion"),getOccupationName(rs.getInt("reelOccupation")),rs.getString("residenceOccupation"),rs.getString("residenceEndDate"),rs.getString("HealthCertificateStartDate"),rs.getString("HealthCertificatEndDate")));
            }


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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idDelete=0;
            addToTable();
        }
    }

    @FXML
    private TextField search;
    @FXML
    public void search(KeyEvent keyEvent) {
        String key=search.getText().trim();
        if (key.isEmpty()){
            addToTable();
            employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
            employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
            employeeNationalityTable.setCellValueFactory(new PropertyValueFactory<>("employeeNationality"));
            identityTypeTable.setCellValueFactory(new PropertyValueFactory<>("identityType"));
            identityNumberTable.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
            religionTable.setCellValueFactory(new PropertyValueFactory<>("religion"));
            residenceOccupationTable.setCellValueFactory(new PropertyValueFactory<>("residenceOccupation"));
            reelOccupationTable.setCellValueFactory(new PropertyValueFactory<>("reelOccupationName"));
            residenceEndDateTable.setCellValueFactory(new PropertyValueFactory<>("residenceEndDate"));
            HealthCertificateStartDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificateStartDate"));
            HealthCertificatEndDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificatEndDate"));
            employeeTableView.setItems(employeesTable);
        }else{
            employeesTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `employees` WHERE `employeeName` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    employeesTable.add(new EmployeeForTable(rs.getInt("id"),rs.getInt("reelOccupation"),rs.getString("employeeName"),rs.getString("employeeNumber"),rs.getString("employeeNationality"),rs.getString("identityType"),rs.getString("identityNumber"),rs.getString("religion"),getOccupationName(rs.getInt("reelOccupation")),rs.getString("residenceOccupation"),rs.getString("residenceEndDate"),rs.getString("HealthCertificateStartDate"),rs.getString("HealthCertificatEndDate")));
                }
                employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
                employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
                employeeNationalityTable.setCellValueFactory(new PropertyValueFactory<>("employeeNationality"));
                identityTypeTable.setCellValueFactory(new PropertyValueFactory<>("identityType"));
                identityNumberTable.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
                religionTable.setCellValueFactory(new PropertyValueFactory<>("religion"));
                residenceOccupationTable.setCellValueFactory(new PropertyValueFactory<>("residenceOccupation"));
                reelOccupationTable.setCellValueFactory(new PropertyValueFactory<>("reelOccupationName"));
                residenceEndDateTable.setCellValueFactory(new PropertyValueFactory<>("residenceEndDate"));
                HealthCertificateStartDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificateStartDate"));
                HealthCertificatEndDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificatEndDate"));
                employeeTableView.setItems(employeesTable);


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
        int idOccupation=0;


        if (edit.getText().contains("تعديل موظف")){
            edit.setText("حفظ");
            employeeName.setText(employeeTableView.getItems().get(index).getEmployeeName());
            identityNumber.setText(employeeTableView.getItems().get(index).getIdentityNumber());
            residenceEndDate.getEditor().setText(employeeTableView.getItems().get(index).getResidenceEndDate());
            HealthCertificateStartDate.getEditor().setText(employeeTableView.getItems().get(index).getHealthCertificateStartDate());
            HealthCertificatEndDate.getEditor().setText(employeeTableView.getItems().get(index).getResidenceEndDate());
            reelOccupation.setValue(employeeTableView.getItems().get(index).getReelOccupationName());
            identityType.setValue(employeeTableView.getItems().get(index).getIdentityType());
            employeeNumber.setText(employeeTableView.getItems().get(index).getEmployeeNumber());
            employeeNationality.setText(employeeTableView.getItems().get(index).getEmployeeNationality());
            residenceOccupation.setText(employeeTableView.getItems().get(index).getResidenceOccupation());
            religion.setText(employeeTableView.getItems().get(index).getReligion());
        }else if (edit.getText().contains("حفظ")){
            try {
                for (int i=0; i<occupations.size() ;i++){
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
                edit.setText("تعديل إستقطاع");
                employeeName.clear();
                identityNumber.clear();
                residenceEndDate.getEditor().clear();
                HealthCertificateStartDate.getEditor().clear();
                HealthCertificatEndDate.getEditor().clear();
                reelOccupation.getItems().clear();
                identityType.getItems().clear();
                employeeNumber.clear();
                employeeNationality.clear();
                residenceOccupation.clear();
                religion.clear();
                identityType.setItems(identityTypeList);
                fillCombo();



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            addToTable();
            idOccupation=0;
        }


    }
    @FXML
    void idReset(MouseEvent event) {
        edit.setText("تعديل موظف");
    }


    public void uploadHealth(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        file1=fileChooser.showOpenDialog(null);

    }
    public void uploadIdentity(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        file2=fileChooser.showOpenDialog(null);


    }
}



