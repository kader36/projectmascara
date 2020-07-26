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
import javafx.stage.Stage;

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
                    " `HealthCertificateStartDate`, `HealthCertificatEndDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
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
            pst.execute();

        } catch (SQLException throwables) {
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

}
