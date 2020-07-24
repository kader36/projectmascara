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

public class ProjectPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Occupation> occupations= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<String> projectTypeList= FXCollections.observableArrayList("مشروع قطاع عسكري","مشروع قطاع صحي");
    int idArea=0,idLocation=0;


    @FXML
    private TextField projectName;

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> occupationName;

    @FXML
    private ComboBox<String> locationName;

    @FXML
    private ComboBox<String> projectType;


    @FXML
    private TextField contactDuration;

    @FXML
    private TextField contractPrice;

    @FXML
    private DatePicker contractStartDate;

    @FXML
    private DatePicker contractEndDate;

    @FXML
    private TextField maxNumber;

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
    public void fillComboOccupation(){
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `occupations`");
            rs=pst.executeQuery();
            while (rs.next()){
                occupations.add(new Occupation(rs.getInt("id"),rs.getString("occupationName")));

            }
            for (int i=0;i<occupations.size();i++){
                occupationName.getItems().add(occupations.get(i).getNameOcupation());
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
    @FXML
    void selectArea(ActionEvent event) {
        int index= areaName.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        fillComboLocation();
    }
    @FXML
    void selectOccupation(ActionEvent event) {
        int index= occupationName.getSelectionModel().getSelectedIndex();
        idArea=occupations.get(index).getIdOcupation();
    }
    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();

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
    public void addProject(ActionEvent actionEvent) {
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `projects`(`areaId`, `locationId`, `projectType`, `contractName`, `contractPrice`," +
                    " `contactDuration`, `contractStartDate`, `contractEndDate`) VALUES (?,?,?,?,?,?,?,?)");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setString(3,projectType.getValue());
            pst.setString(4,projectName.getText());
            pst.setFloat(5, Float.parseFloat(contractPrice.getText()));
            pst.setInt(6, Integer.parseInt(contactDuration.getText()));
            pst.setString(7, String.valueOf(contractStartDate.getValue()));
            pst.setString(8, String.valueOf(contractEndDate.getValue()));
            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable();
    }

    @FXML
    void addProjectOccupation(ActionEvent event) {

    }




    @FXML
    private TableView<ProjectForTable> projectTableView;

    @FXML
    private TableColumn<ProjectForTable, String> projectNameTable;

    @FXML
    private TableColumn<ProjectForTable, String> areaNameTable;

    @FXML
    private TableColumn<ProjectForTable, String> locationNameTable;

    @FXML
    private TableColumn<ProjectForTable, String> projectTypeTable;

    @FXML
    private TableColumn<ProjectForTable, Integer> contactDurationTable;

    @FXML
    private TableColumn<ProjectForTable, Float> contractPriceTable;

    @FXML
    private TableColumn<ProjectForTable, String> contractStartDateTable;

    @FXML
    private TableColumn<ProjectForTable, String> contractEndDateTable;

    ObservableList projectsTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        fillComboOccupation();
        projectType.setItems(projectTypeList);

        addToTable();
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        projectTypeTable.setCellValueFactory(new PropertyValueFactory<>("projectType"));
        contactDurationTable.setCellValueFactory(new PropertyValueFactory<>("contactDuration"));
        contractPriceTable.setCellValueFactory(new PropertyValueFactory<>("contractPrice"));
        contractStartDateTable.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
        contractEndDateTable.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
        projectTableView.setItems(projectsTable);
    }
    public void addToTable(){
        projectsTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects`");
            rs=pst.executeQuery();
            while (rs.next()){
                projectsTable.add(new ProjectForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("contractName"),getAreaName(rs.getInt("areaId")),getLocationName(rs.getInt("areaId"),rs.getInt("locationId")),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));

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
    public String getProjectName(int idArea,int idLocation,int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `id`=? AND `areaId`=? AND `locationId`=?");
            pst.setInt(1,id);
            pst.setInt(2,idArea);
            pst.setInt(3,idLocation);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("contractName");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }
}
