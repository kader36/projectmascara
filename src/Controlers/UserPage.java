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

public class UserPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    //ObservableList<String> privileges= FXCollections.observableArrayList("الافراج عن الضمان","تخفيض 5% من الضمان");
    int idArea=0,idLocation=0,privilegesId=0;


    @FXML
    private TextField employeeName;

    @FXML
    private ComboBox<String> areaName;

    @FXML
    private ComboBox<String> locationName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField employeeNumber;
    @FXML
    private ComboBox<String> privilegeName;
    @FXML
    private PasswordField password;


    public void fillComboArea(){
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `areas`");
            rs=pst.executeQuery();
            while (rs.next()){
                areas.add(new Area(rs.getInt("id"),rs.getString("areaName")));

            }
            for (int i=0;i<areas.size();i++){
                areaName.getItems().add(areas.get(i).getNameArea());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void fillComboLocation(){
        locations.clear();
        locationName.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `locations` WHERE `areaId`=?");
            pst.setInt(1,idArea);
            rs=pst.executeQuery();
            while (rs.next()){
                locations.add(new Location(rs.getInt("areaId"),rs.getInt("id"),rs.getString("locationName")));

            }

            for (int i=0;i<locations.size();i++){
                locationName.getItems().add(locations.get(i).getLocationName());
            }

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
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
    void selectArea(ActionEvent event) {
        int index= areaName.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        fillComboLocation();
    }
    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();

    }
    @FXML
    void selectPrivileges(ActionEvent event) {
        int index= privilegeName.getSelectionModel().getSelectedIndex();
        //privilegesId=privileges.get(index).
        //privilegesId=privileges.get(index).

    }
    public void addUser(ActionEvent actionEvent) {
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `users`(`employeeName`, `idArea`, `idLocation`, `username`, `password`, `email`," +
                    " `phoneNumber`, `employeeNumber`, `privilegesId`) VALUES (?,?,?,?,?,?,?,?,?)");
            pst.setString(1,employeeName.getText());
            pst.setInt(2,idArea);
            pst.setInt(3,idLocation);
            pst.setString(4,username.getText());
            pst.setString(5,password.getText());
            pst.setString(6,email.getText());
            pst.setString(7,phoneNumber.getText());
            pst.setString(8,employeeNumber.getText());
            pst.setInt(9,privilegesId);
            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable();
    }

    @FXML
    private TableView<UserForTable> userTableView;

    @FXML
    private TableColumn<UserForTable, String> employeeNameTable;

    @FXML
    private TableColumn<UserForTable, String> areaNameTable;

    @FXML
    private TableColumn<UserForTable, String> locationNameTable;

    @FXML
    private TableColumn<UserForTable, String> usernameTable;

    @FXML
    private TableColumn<UserForTable, String> emailTable;

    @FXML
    private TableColumn<UserForTable, String> phoneNumberTable;

    @FXML
    private TableColumn<UserForTable, String> employeeNumberTable;

    @FXML
    private TableColumn<UserForTable, String> privilegeNameTable;

    ObservableList usersTable= FXCollections.observableArrayList();

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            fillComboArea();
            addToTable();
            employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
            areaNameTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
            locationNameTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
            usernameTable.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailTable.setCellValueFactory(new PropertyValueFactory<>("email"));
            phoneNumberTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
            privilegeNameTable.setCellValueFactory(new PropertyValueFactory<>("privilegeName"));
            userTableView.setItems(usersTable);
        }
        public void addToTable(){
            usersTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `users`");
                rs=pst.executeQuery();
                while (rs.next()){
                    usersTable.add(new UserForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("privilegesId"),rs.getString("employeeName"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("employeeNumber"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getPrivilegeName(rs.getInt("privilegesId"))));

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    public String getAreaName(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `areas` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("areaName");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }
    public String getLocationName(int idArea,int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `locations` WHERE `id`=? AND `areaId`=?");
            pst.setInt(1,id);
            pst.setInt(2,idArea);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("locationName");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }
    public String getPrivilegeName(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `users` WHERE `privilegesId`=?");
            pst.setInt(1,id);

            rs=pst.executeQuery();
            while (rs.next()){
                return result= String.valueOf(rs.getInt("privilegesId"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }

    public void deleteRow(ActionEvent actionEvent) {
        int index= userTableView.getSelectionModel().getSelectedIndex();
        int idDelete=userTableView.getItems().get(index).getIdUser();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `users` WHERE `id`=?");
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



