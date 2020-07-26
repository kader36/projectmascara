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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GaranteePage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<String> contracts= FXCollections.observableArrayList("طهي","توريد");
    ObservableList<String> garantees= FXCollections.observableArrayList("الافراج عن الضمان","تخفيض 5% من الضمان");
    int idArea=0,idLocation=0,idProject=0;

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> locationName;
    @FXML
    private ComboBox<String> garanteeType;
    @FXML
    private ComboBox<String> contractType;
    @FXML
    private TextField garanteeNumber;
    @FXML
    private ComboBox<String> projectName;


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
        fillComboProject();

    }
    @FXML
    void selectProject(ActionEvent event) {
        int index= projectName.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();
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
    public void fillComboProject(){
        projects.clear();
        projectName.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=?");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            rs=pst.executeQuery();
            while (rs.next()){
                projects.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));

            }

            for (int i=0;i<projects.size();i++){
                projectName.getItems().add(projects.get(i).getContractName());
            }

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

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


    public void addGarantee(ActionEvent actionEvent) {
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `garantees`(`areaId`, `locationId`,`idProject`, `garanteeNumber`, `garanteeType`, `contractType`) VALUES (?,?,?,?,?,?)");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setInt(3,idProject);
            pst.setString(4,garanteeNumber.getText());
            pst.setString(5,garanteeType.getValue());
            pst.setString(6,contractType.getValue());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable();
        garanteeNumber.clear();
    }
    @FXML
    private TableView<GaranteeForTable> garanteeTableView;

    @FXML
    private TableColumn<GaranteeForTable, String> areaNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> locationNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> projectNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> garanteeNumberTable;

    @FXML
    private TableColumn<GaranteeForTable, String> garanteeTypeTable;

    @FXML
    private TableColumn<GaranteeForTable, String> contractTypeTable;
    ObservableList garanteesTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        garanteeType.setItems(garantees);
        contractType.setItems(contracts);

        addToTable();
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        garanteeNumberTable.setCellValueFactory(new PropertyValueFactory<>("garanteeNumber"));
        garanteeTypeTable.setCellValueFactory(new PropertyValueFactory<>("garanteeType"));
        contractTypeTable.setCellValueFactory(new PropertyValueFactory<>("contractType"));
        garanteeTableView.setItems(garanteesTable);
    }
    public void addToTable(){
        garanteesTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `garantees`");
            rs=pst.executeQuery();
            while (rs.next()){
                garanteesTable.add(new GaranteeForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject"),getAreaName(rs.getInt("areaId")),getLocationName(rs.getInt("areaId"),rs.getInt("locationId")),getProjectName(rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject")),rs.getString("garanteeNumber"),rs.getString("garanteeType"),rs.getString("contractType")));

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
    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        int index= garanteeTableView.getSelectionModel().getSelectedIndex();
        int idDelete=garanteeTableView.getItems().get(index).getIdGarantee();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `garantees` WHERE `id`=?");
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
