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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AbstractPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<String> contracts= FXCollections.observableArrayList("طهي","توريد");
    int idArea=0,idLocation=0,idProject=0,idAbstract=0;

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> locationName;
    @FXML
    private TextField contractType;

    @FXML
    private ComboBox<String> projectName;



    @FXML
    private TableView<AbstractForTable> abstractTableView;
    @FXML
    private TableView<AbstractForTable2> yearAbstractTableView;

    @FXML
    private TableColumn<AbstractForTable, String> projectNameTable;

    @FXML
    private TableColumn<AbstractForTable, String> contractNumberTable;

    @FXML
    private TableColumn<AbstractForTable, String> contractTypeTable;

    @FXML
    private TableColumn<AbstractForTable, String> areaNameTable;

    @FXML
    private TableColumn<AbstractForTable, String> locationNameTable;

    @FXML
    private TableColumn<AbstractForTable, String> contractStartDateTable;

    @FXML
    private TableColumn<AbstractForTable, String> contractEndDateTable;

    @FXML
    private TableColumn<AbstractForTable2, String> yearTable;
    @FXML
    private TableColumn<AbstractForTable2, String> janvierTable;

    @FXML
    private TableColumn<AbstractForTable2, String> fevrierTable;

    @FXML
    private TableColumn<AbstractForTable2, String> marsTable;

    @FXML
    private TableColumn<AbstractForTable2, String> avrilTable;

    @FXML
    private TableColumn<AbstractForTable2, String> mayTable;

    @FXML
    private TableColumn<AbstractForTable2, String> juinTable;

    @FXML
    private TableColumn<AbstractForTable2, String> juillietTable;

    @FXML
    private TableColumn<AbstractForTable2, String> aoutTable;

    @FXML
    private TableColumn<AbstractForTable2, String> septembreTable;

    @FXML
    private TableColumn<AbstractForTable2, String> octobreTable;

    @FXML
    private TableColumn<AbstractForTable2, String> novembreTable;

    @FXML
    private TableColumn<AbstractForTable2, String> decembreTable;

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
    private TextField contractNumber;

    @FXML
    private DatePicker contractStartDate;

    @FXML
    private DatePicker contractEndDate;
    @FXML
    void selectProject(ActionEvent event) {
        int index= projectName.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `id`=?");
            pst.setInt(1,idProject);
            rs=pst.executeQuery();
            while (rs.next()){
                contractNumber.setText(rs.getString("contractNumber"));
                contractType.setText(rs.getString("projectType"));
                contractStartDate.setValue(LocalDate.parse(rs.getString("contractStartDate")));
                contractEndDate.setValue(LocalDate.parse(rs.getString("contractEndDate")));
            }


        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void fillComboLocation(){
        locations.clear();
        locationName.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
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

            con=new ConnectDB().getConnection();
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
            con=new ConnectDB().getConnection();
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


    ObservableList abstractsTable= FXCollections.observableArrayList();
    ObservableList abstractYearsTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        addToTable();
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        contractNumberTable.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        contractTypeTable.setCellValueFactory(new PropertyValueFactory<>("contractType"));
        contractStartDateTable.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
        contractEndDateTable.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
        yearTable.setCellValueFactory(new PropertyValueFactory<>("year"));
        janvierTable.setCellValueFactory(new PropertyValueFactory<>("janvier"));
        fevrierTable.setCellValueFactory(new PropertyValueFactory<>("fevrier"));
        marsTable.setCellValueFactory(new PropertyValueFactory<>("mars"));
        avrilTable.setCellValueFactory(new PropertyValueFactory<>("avril"));
        mayTable.setCellValueFactory(new PropertyValueFactory<>("may"));
        juinTable.setCellValueFactory(new PropertyValueFactory<>("juin"));
        juillietTable.setCellValueFactory(new PropertyValueFactory<>("juilliet"));
        aoutTable.setCellValueFactory(new PropertyValueFactory<>("aout"));
        septembreTable.setCellValueFactory(new PropertyValueFactory<>("septembre"));
        octobreTable.setCellValueFactory(new PropertyValueFactory<>("octobre"));
        novembreTable.setCellValueFactory(new PropertyValueFactory<>("novembre"));
        decembreTable.setCellValueFactory(new PropertyValueFactory<>("decembre"));

        abstractTableView.setItems(abstractsTable);
        yearAbstractTableView.setItems(abstractYearsTable);

    }
    public void addToTable(){
        abstractsTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstract`");
            rs=pst.executeQuery();
            while (rs.next()){
                abstractsTable.add(new AbstractForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getProjectName(rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject")),getContractNumber(rs.getInt("idProject")),getContractType(rs.getInt("idProject")),getContractStartDate(rs.getInt("idProject")),getContractEndDate(rs.getInt("idProject"))));

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    public void fillTableAbstractYears(){
        abstractYearsTable.clear();
        yearAbstractTableView.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations` WHERE `idProject`=?");
            pst.setInt(1,idAbstract);
            rs=pst.executeQuery();
            while (rs.next()){
                abstractYearsTable.add(new ProjectOcupation(rs.getInt("id"),rs.getInt("idProject"),rs.getInt("idOccupation"),rs.getInt("maxNumber"),rs.getInt("realNumber"),rs.getString("idOccupation")));

            }


        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

    public String getEmployeeName(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("employeeName");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }
    public String getAreaName(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new ConnectDB().getConnection();
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
    public String getContractNumber(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("contractNumber");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }
    public String getContractStartDate(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("contractStartDate");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }
    public String getContractEndDate(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("contractStartDate");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }
    public String getContractType(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("projectType");
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
            con=new ConnectDB().getConnection();
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
            con=new ConnectDB().getConnection();
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
    private CheckBox janvier;

    @FXML
    private CheckBox fevrier;

    @FXML
    private CheckBox mars;

    @FXML
    private CheckBox avril;

    @FXML
    private CheckBox may;

    @FXML
    private CheckBox juin;

    @FXML
    private CheckBox juilliet;

    @FXML
    private CheckBox aout;

    @FXML
    private CheckBox septembre;

    @FXML
    private CheckBox octobre;

    @FXML
    private CheckBox novembre;

    @FXML
    private CheckBox decembre;
    @FXML
    public void addAbstract(ActionEvent actionEvent) {
        String month1="",month2="",month3="",month4="",month5="",month6="",month7="",month8="",month9="",month10="",month11="",month12="";
        if (janvier.isSelected()){
            month1="X";
        }if (fevrier.isSelected()){
            month2="X";
        }if (mars.isSelected()){
            month3="X";
        }if (avril.isSelected()){
            month4="X";
        }if (may.isSelected()){
            month5="X";
        }if (juin.isSelected()){
            month6="X";
        }if (juilliet.isSelected()){
            month7="X";
        }if (aout.isSelected()){
            month8="X";
        }if (septembre.isSelected()){
            month9="X";
        }if (octobre.isSelected()){
            month10="X";
        }if (novembre.isSelected()){
            month11="X";
        }if (decembre.isSelected()){
            month12="X";
        }
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `abstract`(`idArea`, `idLocation`, `idProject`) VALUES (?,?,?)");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setInt(3,idProject);
//            pst.setString(5,month1);
//            pst.setString(6,month2);
//            pst.setString(7,month3);
//            pst.setString(8,month4);
//            pst.setString(9,month5);
//            pst.setString(10,month6);
//            pst.setString(11,month7);
//            pst.setString(12,month8);
//            pst.setString(13,month9);
//            pst.setString(14,month10);
//            pst.setString(15,month11);
//            pst.setString(16,month12);
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable();
        areaName.getItems().clear();
        areas.clear();
        locationName.getItems().clear();
        projectName.getItems().clear();
        fillComboArea();
        areaName.setValue("");

    }
    public void deleteRow(ActionEvent actionEvent) {
        int index= abstractTableView.getSelectionModel().getSelectedIndex();
        int idDelete=abstractTableView.getItems().get(index).getIdAbstract();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `abstract` WHERE `id`=?");
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
            areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
            locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
            projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
            contractNumberTable.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
            contractTypeTable.setCellValueFactory(new PropertyValueFactory<>("contractType"));
            contractStartDateTable.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
            contractEndDateTable.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
            janvierTable.setCellValueFactory(new PropertyValueFactory<>("janvier"));
            fevrierTable.setCellValueFactory(new PropertyValueFactory<>("fevrier"));
            marsTable.setCellValueFactory(new PropertyValueFactory<>("mars"));
            avrilTable.setCellValueFactory(new PropertyValueFactory<>("avril"));
            mayTable.setCellValueFactory(new PropertyValueFactory<>("may"));
            juinTable.setCellValueFactory(new PropertyValueFactory<>("juin"));
            juillietTable.setCellValueFactory(new PropertyValueFactory<>("juilliet"));
            aoutTable.setCellValueFactory(new PropertyValueFactory<>("aout"));
            septembreTable.setCellValueFactory(new PropertyValueFactory<>("septembre"));
            octobreTable.setCellValueFactory(new PropertyValueFactory<>("octobre"));
            novembreTable.setCellValueFactory(new PropertyValueFactory<>("novembre"));
            decembreTable.setCellValueFactory(new PropertyValueFactory<>("decembre"));
            abstractTableView.setItems(abstractsTable);
        }else{
            abstractsTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `abstract`,`projects` WHERE abstract.idProject =projects.id AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    abstractsTable.add(new AbstractForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getProjectName(rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject")),getContractNumber(rs.getInt("idProject")),getContractType(rs.getInt("idProject")),getContractStartDate(rs.getInt("idProject")),getContractEndDate(rs.getInt("idProject"))));
//                    ,rs.getString("jan"),rs.getString("feb"),rs.getString("mar"),rs.getString("apr"),rs.getString("may"),rs.getString("jun"),rs.getString("jul"),rs.getString("aug"),rs.getString("sep"),rs.getString("oct"),rs.getString("nov"),rs.getString("dcm")

                }
                areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
                locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
                projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
                contractNumberTable.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
                contractTypeTable.setCellValueFactory(new PropertyValueFactory<>("contractType"));
                contractStartDateTable.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
                contractEndDateTable.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
                janvierTable.setCellValueFactory(new PropertyValueFactory<>("janvier"));
                fevrierTable.setCellValueFactory(new PropertyValueFactory<>("fevrier"));
                marsTable.setCellValueFactory(new PropertyValueFactory<>("mars"));
                avrilTable.setCellValueFactory(new PropertyValueFactory<>("avril"));
                mayTable.setCellValueFactory(new PropertyValueFactory<>("may"));
                juinTable.setCellValueFactory(new PropertyValueFactory<>("juin"));
                juillietTable.setCellValueFactory(new PropertyValueFactory<>("juilliet"));
                aoutTable.setCellValueFactory(new PropertyValueFactory<>("aout"));
                septembreTable.setCellValueFactory(new PropertyValueFactory<>("septembre"));
                octobreTable.setCellValueFactory(new PropertyValueFactory<>("octobre"));
                novembreTable.setCellValueFactory(new PropertyValueFactory<>("novembre"));
                decembreTable.setCellValueFactory(new PropertyValueFactory<>("decembre"));
                abstractTableView.setItems(abstractsTable);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }
    @FXML
    void idReset(MouseEvent event) {
//        edit.setText("تعديل الغرامة");
        fillTableAbstractYears();
    }

}
