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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class DeductionPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Area> areas5= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Location> locations5= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<Project> projects5= FXCollections.observableArrayList();
    ObservableList<DeductionNames> deductionNames= FXCollections.observableArrayList();
    ObservableList<DeductionNames> deductionNames5= FXCollections.observableArrayList();
    int idArea=0,idLocation=0,idProject=0,idEmployee=0,idDeduction=0,idArea5=0,idLocation5=0,idProject5=0,idEmployee5=0,idDeduction5=0;

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> locationName;
    @FXML
    private ComboBox<String> typeDeduction;
    @FXML
    private TextField amountOfDeduction;
    @FXML
    private ComboBox<String> projectName;
    @FXML
    private ComboBox<String> areaName2;
    @FXML
    private ComboBox<String> locationName2;
    @FXML
    private ComboBox<String> typeDeduction2;
    @FXML
    private TextField amountOfDeduction2;
    @FXML
    private ComboBox<String> projectName2;

    @FXML
    private ComboBox<String> areaName1;
    @FXML
    private ComboBox<String> locationName1;
    @FXML
    private ComboBox<String> typeDeduction1;
    @FXML
    private TextField amountOfDeduction1;
    @FXML
    private ComboBox<String> projectName1;

    @FXML
    void selectArea(ActionEvent event) {
        int index= areaName.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        locationName.getItems().clear();
        projectName.getItems().clear();
        fillComboLocation();

    }
    @FXML
    void selectArea5(ActionEvent event) {
        int index= areaName2.getSelectionModel().getSelectedIndex();
        idArea5=areas5.get(index).getIdArea();
        locationName2.getItems().clear();
        projectName2.getItems().clear();
        fillComboLocation5();

    }
    @FXML
    void selectArea1(ActionEvent event) {
        int index= areaName1.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        locationName1.getItems().clear();
        projectName1.getItems().clear();
        fillComboLocation1();

    }
    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();
        fillComboProject();

    }
    @FXML
    void selectLocation5(ActionEvent event) {
        int index= locationName2.getSelectionModel().getSelectedIndex();
        idLocation5=locations5.get(index).getIdLocation();
        fillComboProject5();

    }

    @FXML
    void selectLocation1(ActionEvent event) {
        int index= locationName1.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();
        fillComboProject1();

    }
    @FXML
    void selectDeduction(ActionEvent event) {
        int index= typeDeduction.getSelectionModel().getSelectedIndex();
        idDeduction=deductionNames.get(index).getIdDeductionName();


    }
    @FXML
    void selectDeduction5(ActionEvent event) {
        int index= typeDeduction2.getSelectionModel().getSelectedIndex();
        idDeduction5=deductionNames5.get(index).getIdDeductionName();


    }
    @FXML
    void selectDeduction1(ActionEvent event) {
        int index= typeDeduction1.getSelectionModel().getSelectedIndex();
        idDeduction=deductionNames.get(index).getIdDeductionName();

    }
    @FXML
    void selectProject(ActionEvent event) {
        int index= projectName.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();


    }
    @FXML
    void selectProject5(ActionEvent event) {
        int index= projectName2.getSelectionModel().getSelectedIndex();
        idProject5=projects5.get(index).getIdProject();


    }
    @FXML
    void selectProject1(ActionEvent event) {
        int index= projectName1.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();


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
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

    public void fillComboLocation5(){
        locations5.clear();
        locationName2.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `locations` WHERE `areaId`=?");
            pst.setInt(1,idArea5);
            rs=pst.executeQuery();
            while (rs.next()){
                locations5.add(new Location(rs.getInt("areaId"),rs.getInt("id"),rs.getString("locationName")));

            }

            for (int i=0;i<locations5.size();i++){
                locationName2.getItems().add(locations5.get(i).getLocationName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

    public void fillComboLocation1(){
        locations.clear();
        locationName1.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `locations` WHERE `areaId`=?");
            pst.setInt(1,idArea);
            rs=pst.executeQuery();
            while (rs.next()){
                locations.add(new Location(rs.getInt("areaId"),rs.getInt("id"),rs.getString("locationName")));

            }

            for (int i=0;i<locations.size();i++){
                locationName1.getItems().add(locations.get(i).getLocationName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

    public void fillComboProject(){
        projects.clear();
        projectName.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=? AND (`projectType`='مشروع قطاع صحي' OR `projectType`='مشروع تعليمي')");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            rs=pst.executeQuery();
            while (rs.next()){
                projects.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));

            }

            for (int i=0;i<projects.size();i++){
                projectName.getItems().add(projects.get(i).getContractName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

    public void fillComboProject5(){
        projects5.clear();
        projectName2.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=? AND (`projectType`='مشروع النظافة' OR `projectType`='مشروع الصيانة')");
            pst.setInt(1,idArea5);
            pst.setInt(2,idLocation5);
            rs=pst.executeQuery();
            while (rs.next()){
                projects5.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));

            }

            for (int i=0;i<projects5.size();i++){
                projectName2.getItems().add(projects5.get(i).getContractName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }


    public void fillComboProject1(){
        projects.clear();
        projectName1.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=? AND `projectType`='مشروع قطاع عسكري'");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            rs=pst.executeQuery();
            while (rs.next()){
                projects.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));

            }

            for (int i=0;i<projects.size();i++){
                projectName1.getItems().add(projects.get(i).getContractName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

    public void fillComboDeductionType(){
        deductionNames.clear();
        typeDeduction.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductionnames`");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionNames.add(new DeductionNames(rs.getString("deductionName"), rs.getInt("id")));

            }

            for (int i=0;i<deductionNames.size();i++){
                typeDeduction.getItems().add(deductionNames.get(i).getDeductionName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }


    public void fillComboDeductionType1(){
        deductionNames.clear();
        typeDeduction1.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductionnames`");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionNames.add(new DeductionNames(rs.getString("deductionName"), rs.getInt("id")));

            }

            for (int i=0;i<deductionNames.size();i++){
                typeDeduction1.getItems().add(deductionNames.get(i).getDeductionName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }


    public void fillComboDeductionType5(){
        deductionNames5.clear();
        typeDeduction2.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductionnames`");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionNames5.add(new DeductionNames(rs.getString("deductionName"), rs.getInt("id")));

            }

            for (int i=0;i<deductionNames5.size();i++){
                typeDeduction2.getItems().add(deductionNames5.get(i).getDeductionName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

    public void fillComboArea(){
        areas.clear();
        areaName.getItems().clear();
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
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void fillComboArea5(){
        areas5.clear();
        areaName2.getItems().clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `areas`");
            rs=pst.executeQuery();
            while (rs.next()){
                areas5.add(new Area(rs.getInt("id"),rs.getString("areaName")));

            }
            for (int i=0;i<areas5.size();i++){
                areaName2.getItems().add(areas5.get(i).getNameArea());
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void fillComboArea1(){
        areas.clear();
        areaName1.getItems().clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `areas`");
            rs=pst.executeQuery();
            while (rs.next()){
                areas.add(new Area(rs.getInt("id"),rs.getString("areaName")));

            }
            for (int i=0;i<areas.size();i++){
                areaName1.getItems().add(areas.get(i).getNameArea());
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
    private Button areaDeletePrivilege;
    @FXML
    private Button areaAddPrivilege;
    @FXML
    private Button areaEditPrivilege;

    @FXML
    private Button locationDeletePrivilege;
    @FXML
    private Button locationAddPrivilege;
    @FXML
    private Button locationEditPrivilege;

    @FXML
    private Button garanteeEditPrivilege;
    @FXML
    private Button garanteeAddPrivilege;
    @FXML
    private Button garanteeDeletePrivilege;
    @FXML
    private Button garanteeAddPrivilege1;
    @FXML
    private Button garanteeDeletePrivilege1;

    @FXML
    private Button occupationDeletePrivilege;
    @FXML
    private Button occupationAddPrivilege;
    @FXML
    private Button occupationEditPrivilege;

    @FXML
    private Button employeeDeletePrivilege;
    @FXML
    private Button employeeAddPrivilege;
    @FXML
    private Button employeeEditPrivilege;

    @FXML
    private Button abstractDeletePrivilege;
    @FXML
    private Button abstractAddPrivilege;
    @FXML
    private Button abstractDeletePrivilege1;
    @FXML
    private Button abstractAddPrivilege1;
    @FXML
    private Button abstractEditPrivilege;

    @FXML
    private Button deductionDeletePrivilege;
    @FXML
    private Button deductionAddPrivilege;
    @FXML
    private Button deductionDeletePrivilege1;
    @FXML
    private Button deductionAddPrivilege1;
    @FXML
    private Button deductionEditPrivilege;
    @FXML
    private Button deductionAddPrivilege2;
    @FXML
    private Button deductionDeletePrivilege2;
    @FXML
    private Button deductionAddPrivilege3;
    @FXML
    private Button deductionDeletePrivilege3;

    @FXML
    private Button penaltyDeletePrivilege;
    @FXML
    private Button penaltyAddPrivilege;
    @FXML
    private Button penaltyEditPrivilege;

    @FXML
    private Button userDeletePrivilege;
    @FXML
    private Button userAddPrivilege;
    @FXML
    private Button userDeletePrivilege1;
    @FXML
    private Button userAddPrivilege1;
    @FXML
    private Button userEditPrivilege;
    @FXML
    private Button userEditPrivilege1;


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
                if (rs.getInt("arss")==0){
                    areaMenuButton.setDisable(true);
                }else{
                    areaMenuButton.setDisable(false);
                }

                if (rs.getInt("loss")==0){
                    locationMenuButton.setDisable(true);

                }else{
                    locationMenuButton.setDisable(false);

                }

                if ((rs.getInt("gass")==0)&&(rs.getInt("gass1")==0)){
                    garanteeMenuButton.setDisable(true);

                }else{
                    garanteeMenuButton.setDisable(false);

                }

                if (rs.getInt("ocss")==0){
                    occupationMenuButton.setDisable(true);

                }else{
                    occupationMenuButton.setDisable(false);

                }

                if (rs.getInt("emss")==0){
                    employeeMenuButton.setDisable(true);

                }else{
                    employeeMenuButton.setDisable(false);

                }

                if (rs.getInt("abss")==0){
                    abstractMenuButton.setDisable(true);

                }else{
                    abstractMenuButton.setDisable(false);

                }

                if (rs.getInt("dess")==0){
                    deductionMenuButton.setDisable(true);

                }else{
                    deductionMenuButton.setDisable(false);

                }

                if (rs.getInt("pess")==0){
                    penaltyMenuButton.setDisable(true);

                }else{
                    penaltyMenuButton.setDisable(false);

                }

                if (rs.getInt("usss")==0){
                    userMenuButton.setDisable(true);

                }else{
                    userMenuButton.setDisable(false);

                }

                if ((rs.getInt("res1")==1)||(rs.getInt("res2")==1)||(rs.getInt("res3")==1)||(rs.getInt("res4")==1)||(rs.getInt("res5")==1)||(rs.getInt("res6")==1)||(rs.getInt("res7")==1)||(rs.getInt("res8")==1)){
                    repportMenuButton.setDisable(false);

                }else{
                    repportMenuButton.setDisable(true);

                }
                if ((rs.getInt("prss")==0)&&(rs.getInt("prss1")==0)&&(rs.getInt("prms")==0)&&(rs.getInt("prrcs")==0)){
                    projectMenuButton.setDisable(true);

                }else{
                    projectMenuButton.setDisable(false);

                }
                if (rs.getInt("desa")==0){
                    deductionAddPrivilege.setDisable(true);

                }else{
                    deductionAddPrivilege.setDisable(false);

                }
                if (rs.getInt("desd")==0){
                    deductionDeletePrivilege.setDisable(true);

                }else{
                    deductionDeletePrivilege.setDisable(false);

                }
                if (rs.getInt("dese")==0){
                    deductionEditPrivilege.setDisable(true);

                }else{
                    deductionEditPrivilege.setDisable(false);

                }
                if (rs.getInt("dema")==0){
                    deductionAddPrivilege2.setDisable(true);

                }else{
                    deductionAddPrivilege2.setDisable(false);

                }
                if (rs.getInt("demd")==0){
                    deductionDeletePrivilege2.setDisable(true);

                }else{
                    deductionDeletePrivilege2.setDisable(false);

                }
                if (rs.getInt("deme")==0){
                    deductionEditPrivilege1.setDisable(true);

                }else{
                    deductionEditPrivilege1.setDisable(false);

                }
                if (rs.getInt("derca")==0){
                    deductionAddPrivilege3.setDisable(true);

                }else{
                    deductionAddPrivilege3.setDisable(false);

                }
                if (rs.getInt("dercd")==0){
                    deductionDeletePrivilege3.setDisable(true);

                }else{
                    deductionDeletePrivilege3.setDisable(false);

                }
                if (rs.getInt("derce")==0){
                    deductionEditPrivilege2.setDisable(true);

                }else{
                    deductionEditPrivilege2.setDisable(false);

                }
                if (rs.getInt("desa")==0 && rs.getInt("dema")==0){
                    deductionAddPrivilege1.setDisable(true);

                }else{
                    deductionAddPrivilege1.setDisable(false);

                }
                if (rs.getInt("desd")==0 && rs.getInt("demd")==0){
                    deductionDeletePrivilege1.setDisable(true);

                }else{
                    deductionDeletePrivilege1.setDisable(false);

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
    public void home(MouseEvent mouseEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/accueil.fxml"));
            AnchorPane root = loader.load();
            Accueil controller = loader.getController();
            controller.Init(idConnected,usernameConnected,employeeNameConnected);
            Stage primaryStage= (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("الصفحة الرئيسية");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void addDeduction(ActionEvent actionEvent) {

        if (amountOfDeduction.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()||typeDeduction.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else{
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,'تكلفة')");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setString(3,typeDeduction.getValue());
                pst.setFloat(4, Float.parseFloat(amountOfDeduction.getText()));
                pst.setInt(5,idProject);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );
                pst.setString(6, sdf.format(new Date()));
                pst.setString(7,"d");
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");
                pst.close();

                try {
                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projects` SET `penaltDaduct`=(SELECT SUM(`amountOfDeduction`) FROM `deductions` WHERE `idProject`=? AND `nort`='تكلفة' ) WHERE id=?");
                    pst.setInt(1,idProject);
                    pst.setInt(2,idProject);
                    pst.execute();
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                projectName.getItems().clear();
                locationName.getItems().clear();
                areaName.getItems().clear();
                amountOfDeduction.clear();
                fillComboArea();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable();
            amountOfDeduction.clear();
        }

    }
    public void addDeduction5(ActionEvent actionEvent) {

        if (amountOfDeduction2.getText().isEmpty()||areaName2.getSelectionModel().isEmpty()||locationName2.getSelectionModel().isEmpty()||projectName2.getSelectionModel().isEmpty()||typeDeduction2.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else{
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,'تكلفة')");
                pst.setInt(1,idArea5);
                pst.setInt(2,idLocation5);
                pst.setString(3,typeDeduction2.getValue());
                pst.setFloat(4, Float.parseFloat(amountOfDeduction2.getText()));
                pst.setInt(5,idProject5);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );
                pst.setString(6, sdf.format(new Date()));
                pst.setString(7,"d");
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");
                pst.close();

                try {
                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projects` SET `penaltDaduct`=(SELECT SUM(`amountOfDeduction`) FROM `deductions` WHERE `idProject`=? AND `nort`='تكلفة' ) WHERE id=?");
                    pst.setInt(1,idProject5);
                    pst.setInt(2,idProject5);
                    pst.execute();
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                projectName2.getItems().clear();
                locationName2.getItems().clear();
                areaName2.getItems().clear();
                amountOfDeduction2.clear();
                fillComboArea5();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable5();
            amountOfDeduction2.clear();
        }

    }
    public void addDeduction1(ActionEvent actionEvent) {

        if (amountOfDeduction1.getText().isEmpty()||areaName1.getSelectionModel().isEmpty()||locationName1.getSelectionModel().isEmpty()||projectName1.getSelectionModel().isEmpty()||typeDeduction1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else{
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,'تكلفة')");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setString(3,typeDeduction1.getValue());
                pst.setFloat(4, Float.parseFloat(amountOfDeduction1.getText()));
                pst.setInt(5,idProject);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );
                pst.setString(6, sdf.format(new Date()));
                pst.setString(7,"d");
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");
                pst.close();

                try {
                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projects` SET `penaltDaduct`=(SELECT SUM(`amountOfDeduction`) FROM `deductions` WHERE `idProject`=? AND `nort`='تكلفة' ) WHERE id=?");
                    pst.setInt(1,idProject);
                    pst.setInt(2,idProject);
                    pst.execute();
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                projectName1.getItems().clear();
                locationName1.getItems().clear();
                areaName1.getItems().clear();
                amountOfDeduction1.clear();
                fillComboArea1();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable1();
            amountOfDeduction1.clear();
        }

    }

    @FXML
    private TableView<DeductionForTable> deductionTableView;

    @FXML
    private TableColumn<GaranteeForTable, String> areaNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> locationNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> projectNameTable;

    @FXML
    private TableColumn<GaranteeForTable, Float> amountOfDeductionTable;

    @FXML
    private TableColumn<GaranteeForTable, String> typeDeductionTable;

    @FXML
    private TableView<DeductionForTable> deductionTableView2;

    @FXML
    private TableColumn<GaranteeForTable, String> areaNameTable2;

    @FXML
    private TableColumn<GaranteeForTable, String> locationNameTable2;

    @FXML
    private TableColumn<GaranteeForTable, String> projectNameTable2;

    @FXML
    private TableColumn<GaranteeForTable, Float> amountOfDeductionTable2;

    @FXML
    private TableColumn<GaranteeForTable, String> typeDeductionTable2;

    @FXML
    private TableView<DeductionForTable> deductionTableView1;

    @FXML
    private TableColumn<GaranteeForTable, String> areaNameTable1;

    @FXML
    private TableColumn<GaranteeForTable, String> locationNameTable1;

    @FXML
    private TableColumn<GaranteeForTable, String> projectNameTable1;

    @FXML
    private TableColumn<GaranteeForTable, Float> amountOfDeductionTable1;

    @FXML
    private TableColumn<GaranteeForTable, String> typeDeductionTable1;
    @FXML
    private TableView<DeductionNames> deductionNamesTableView;

    @FXML
    private TableColumn<DeductionNames, String> nameDeductionnTable;
    ObservableList deductionsTable= FXCollections.observableArrayList();
    ObservableList deductionsTable1= FXCollections.observableArrayList();
    ObservableList deductionsTable5= FXCollections.observableArrayList();
    ObservableList deductionNamesTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        fillComboArea1();
        fillComboArea5();
        fillComboDeductionType();
        fillComboDeductionType1();
        fillComboDeductionType5();
        addToTable();
        addToTable1();
        addToTable5();
        addToTable2();
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        amountOfDeductionTable.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
        typeDeductionTable.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
        deductionTableView.setItems(deductionsTable);


        areaNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        amountOfDeductionTable1.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
        typeDeductionTable1.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
        deductionTableView1.setItems(deductionsTable1);

        areaNameTable2.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable2.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable2.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        amountOfDeductionTable2.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
        typeDeductionTable2.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
        deductionTableView2.setItems(deductionsTable5);


        nameDeductionnTable.setCellValueFactory(new PropertyValueFactory<>("deductionName"));
        deductionNamesTableView.setItems(deductionNamesTable);

    }
    public void addToTable(){
        deductionsTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='d' AND (`projectType`='مشروع قطاع صحي' OR `projectType`='مشروع تعليمي')");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionsTable.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public void addToTable5(){
        deductionsTable5.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='d' AND (`projectType`='مشروع النظافة' OR `projectType`='مشروع الصيانة')");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionsTable5.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public void addToTable1(){
        deductionsTable1.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='d' AND `projects`.`projectType`='مشروع قطاع عسكري'");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionsTable1.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
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
    public void addToTable2(){
        deductionNamesTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductionnames`");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionNamesTable.add(new DeductionNames(rs.getString("deductionName"), rs.getInt("id")));

            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        int index= deductionTableView.getSelectionModel().getSelectedIndex();
        int idDelete=deductionTableView.getItems().get(index).getIdDeduction();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `deductions` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            int idProject=deductionTableView.getItems().get(index).getIdProject();

            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("UPDATE `projects` SET `penaltDaduct`=(SELECT SUM(`amountOfDeduction`) FROM `deductions` WHERE `idProject`=? AND `nort`='تكلفة' ) WHERE id=?");
                pst.setInt(1,idProject);
                pst.setInt(2,idProject);
                pst.execute();
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idDelete=0;
            addToTable();
        }
    }


    @FXML
    public void deleteRow5(ActionEvent actionEvent) {
        int index= deductionTableView2.getSelectionModel().getSelectedIndex();
        int idDelete=deductionTableView2.getItems().get(index).getIdDeduction();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `deductions` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            int idProject5=deductionTableView2.getItems().get(index).getIdProject();

            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("UPDATE `projects` SET `penaltDaduct`=(SELECT SUM(`amountOfDeduction`) FROM `deductions` WHERE `idProject`=? AND `nort`='تكلفة' ) WHERE id=?");
                pst.setInt(1,idProject5);
                pst.setInt(2,idProject5);
                pst.execute();
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idDelete=0;
            addToTable5();
        }
    }


    @FXML
    public void deleteRow1(ActionEvent actionEvent) {
        int index= deductionTableView1.getSelectionModel().getSelectedIndex();
        int idDelete=deductionTableView1.getItems().get(index).getIdDeduction();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `deductions` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            int idProject=deductionTableView1.getItems().get(index).getIdProject();

            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("UPDATE `projects` SET `penaltDaduct`=(SELECT SUM(`amountOfDeduction`) FROM `deductions` WHERE `idProject`=? AND `nort`='تكلفة' ) WHERE id=?");
                pst.setInt(1,idProject);
                pst.setInt(2,idProject);
                pst.execute();
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idDelete=0;
            addToTable1();
        }
    }

    @FXML
    private TextField search;
    @FXML
    public void search(KeyEvent keyEvent) {
        String key=search.getText().trim();
        if (key.isEmpty()){
            addToTable();

            deductionTableView.setItems(deductionsTable);
        }else{
            deductionsTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='d' AND (`projectType`='مشروع قطاع صحي' OR `projectType`='مشروع تعليمي') AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){

                    deductionsTable.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

                }
                pst.close();

                deductionTableView.setItems(deductionsTable);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }



    @FXML
    private TextField search1;
    @FXML
    public void search1(KeyEvent keyEvent) {
        String key=search1.getText().trim();
        if (key.isEmpty()){
            addToTable1();

            deductionTableView1.setItems(deductionsTable1);
        }else{
            deductionsTable1.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='d' AND projects.projectType='مشروع قطاع عسكري' AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){

                    deductionsTable1.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

                }
                pst.close();

                deductionTableView1.setItems(deductionsTable1);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }
    @FXML
    private TextField search3;
    @FXML
    public void search5(KeyEvent keyEvent) {
        String key=search3.getText().trim();
        if (key.isEmpty()){
            addToTable5();

            deductionTableView2.setItems(deductionsTable5);
        }else{
            deductionsTable5.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='d' AND (projects.projectType='مشروع النظافة' OR projects.projectType='مشروع الصيانة') AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){

                    deductionsTable5.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

                }
                pst.close();

                deductionTableView2.setItems(deductionsTable5);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

    @FXML
    private Button edit;
    public void edit(ActionEvent actionEvent) {
        int index= deductionTableView.getSelectionModel().getSelectedIndex();
        int idEdit=deductionTableView.getItems().get(index).getIdDeduction();
//        int idArea1=0,idLocation1=0,idProject1=0;


        if (deductionEditPrivilege.getText().contains("تعديل إستقطاع")){
            deductionEditPrivilege.setText("حفظ");
            areaName.setValue(deductionTableView.getItems().get(index).getNameArea());
            locationName.setValue(deductionTableView.getItems().get(index).getNameLocation());
            projectName.setValue(deductionTableView.getItems().get(index).getNameProject());
            typeDeduction.setValue(deductionTableView.getItems().get(index).getTypeDeduction());
            amountOfDeduction.setText(deductionTableView.getItems().get(index).getAmountOfDeduction());
        }else if (deductionEditPrivilege.getText().contains("حفظ")){
            if (amountOfDeduction.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()||typeDeduction.getSelectionModel().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else{
                try {
                    for (int i=0; i<areas.size() ;i++){
                        if (areas.get(i).getNameArea()==areaName.getValue()){
                            idArea=areas.get(i).getIdArea();
                        }
                    }
                    System.out.println(areaName.getValue()+" "+idArea);
                    for (int i=0; i<locations.size() ;i++){
                        if (locations.get(i).getLocationName()==locationName.getValue()){
                            idLocation=locations.get(i).getIdLocation();
                        }
                    }

                    for (int i=0; i<projects.size() ;i++){
                        if (projects.get(i).getContractName()==projectName.getValue()){
                            idProject=projects.get(i).getIdProject();
                        }
                    }
                    con = new Controlers.ConnectDB().getConnection();

                    pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idProject`=? WHERE `id`=?");

                    pst.setInt(1, idArea);
                    pst.setInt(2, idLocation);
                    pst.setString(3, typeDeduction.getValue());
                    pst.setString(4, amountOfDeduction.getText());
                    pst.setInt(5, idProject);
                    pst.setInt(6, idEdit);

                    pst.execute();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    deductionEditPrivilege.setText("تعديل إستقطاع");
                    projectName.getItems().clear();
                    locationName.getItems().clear();
                    areaName.getItems().clear();
                    amountOfDeduction.clear();

                    fillComboArea();

                    pst.close();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.out.println(throwables.getMessage());
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
                try {
                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projects` SET `penaltDaduct`=(SELECT SUM(`amountOfDeduction`) FROM `deductions` WHERE `idProject`=? AND `nort`='تكلفة' ) WHERE id=?");
                    pst.setInt(1,idProject);
                    pst.setInt(2,idProject);
                    pst.execute();
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                addToTable();
                idEdit=0;
            }

        }






    }


    @FXML
    private Button deductionEditPrivilege2;
    public void edit5(ActionEvent actionEvent) {
        int index= deductionTableView2.getSelectionModel().getSelectedIndex();
        int idEdit=deductionTableView2.getItems().get(index).getIdDeduction();
//        int idArea1=0,idLocation1=0,idProject1=0;


        if (deductionEditPrivilege2.getText().contains("تعديل إستقطاع")){
            deductionEditPrivilege2.setText("حفظ");
            areaName2.setValue(deductionTableView2.getItems().get(index).getNameArea());
            locationName2.setValue(deductionTableView2.getItems().get(index).getNameLocation());
            projectName2.setValue(deductionTableView2.getItems().get(index).getNameProject());
            typeDeduction2.setValue(deductionTableView2.getItems().get(index).getTypeDeduction());
            amountOfDeduction2.setText(deductionTableView2.getItems().get(index).getAmountOfDeduction());
        }else if (deductionEditPrivilege2.getText().contains("حفظ")){
            if (amountOfDeduction2.getText().isEmpty()||areaName2.getSelectionModel().isEmpty()||locationName2.getSelectionModel().isEmpty()||projectName2.getSelectionModel().isEmpty()||typeDeduction2.getSelectionModel().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else{
                try {
                    for (int i=0; i<areas5.size() ;i++){
                        if (areas5.get(i).getNameArea()==areaName2.getValue()){
                            idArea5=areas5.get(i).getIdArea();
                        }
                    }
                    for (int i=0; i<locations5.size() ;i++){
                        if (locations5.get(i).getLocationName()==locationName2.getValue()){
                            idLocation5=locations5.get(i).getIdLocation();
                        }
                    }

                    for (int i=0; i<projects5.size() ;i++){
                        if (projects5.get(i).getContractName()==projectName2.getValue()){
                            idProject5=projects5.get(i).getIdProject();
                        }
                    }
                    con = new Controlers.ConnectDB().getConnection();

                    pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idProject`=? WHERE `id`=?");

                    pst.setInt(1, idArea5);
                    pst.setInt(2, idLocation5);
                    pst.setString(3, typeDeduction2.getValue());
                    pst.setString(4, amountOfDeduction2.getText());
                    pst.setInt(5, idProject5);
                    pst.setInt(6, idEdit);

                    pst.execute();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    deductionEditPrivilege2.setText("تعديل إستقطاع");
                    projectName2.getItems().clear();
                    locationName2.getItems().clear();
                    areaName2.getItems().clear();
                    amountOfDeduction2.clear();

                    fillComboArea5();

                    pst.close();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.out.println(throwables.getMessage());
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
                try {
                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projects` SET `penaltDaduct`=(SELECT SUM(`amountOfDeduction`) FROM `deductions` WHERE `idProject`=? AND `nort`='تكلفة' ) WHERE id=?");
                    pst.setInt(1,idProject5);
                    pst.setInt(2,idProject5);
                    pst.execute();
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                addToTable5();
                idEdit=0;
            }

        }






    }



    @FXML
    private Button deductionEditPrivilege1;
    public void edit1(ActionEvent actionEvent) {
        int index= deductionTableView1.getSelectionModel().getSelectedIndex();
        int idEdit=deductionTableView1.getItems().get(index).getIdDeduction();
//        int idArea=0,idLocation=0,idEmployee=0,idProject=0;


        if (deductionEditPrivilege1.getText().contains("تعديل إستقطاع")){
            deductionEditPrivilege1.setText("حفظ");
            areaName1.setValue(deductionTableView1.getItems().get(index).getNameArea());
            locationName1.setValue(deductionTableView1.getItems().get(index).getNameLocation());
            projectName1.setValue(deductionTableView1.getItems().get(index).getNameProject());
            typeDeduction1.setValue(deductionTableView1.getItems().get(index).getTypeDeduction());
            amountOfDeduction1.setText(deductionTableView1.getItems().get(index).getAmountOfDeduction());
        }else if (deductionEditPrivilege1.getText().contains("حفظ")){
            if (amountOfDeduction1.getText().isEmpty()||areaName1.getSelectionModel().isEmpty()||locationName1.getSelectionModel().isEmpty()||projectName1.getSelectionModel().isEmpty()||typeDeduction1.getSelectionModel().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else{
                try {
                    for (int i=0; i<areas.size() ;i++){
                        if (areas.get(i).getNameArea()==areaName1.getValue()){
                            idArea=areas.get(i).getIdArea();
                        }
                    }
                    for (int i=0; i<locations.size() ;i++){
                        if (locations.get(i).getLocationName()==locationName1.getValue()){
                            idLocation=locations.get(i).getIdLocation();
                        }
                    }

                    for (int i=0; i<projects.size() ;i++){
                        if (projects.get(i).getContractName()==projectName1.getValue()){
                            idProject=projects.get(i).getIdProject();
                        }
                    }
                    con = new Controlers.ConnectDB().getConnection();

                    pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idProject`=? WHERE `id`=?");

                    pst.setInt(1, idArea);
                    pst.setInt(2, idLocation);
                    pst.setString(3, typeDeduction1.getValue());
                    pst.setString(4, amountOfDeduction1.getText());
                    pst.setInt(5, idProject);
                    pst.setInt(6, idEdit);

                    pst.execute();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    deductionEditPrivilege1.setText("تعديل إستقطاع");
                    projectName1.getItems().clear();
                    locationName1.getItems().clear();
                    areaName1.getItems().clear();
                    amountOfDeduction1.clear();

                    fillComboArea1();

                    pst.close();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
                try {
                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projects` SET `penaltDaduct`=(SELECT SUM(`amountOfDeduction`) FROM `deductions` WHERE `idProject`=? AND `nort`='تكلفة' ) WHERE id=?");
                    pst.setInt(1,idProject);
                    pst.setInt(2,idProject);
                    pst.execute();
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                addToTable1();
                idEdit=0;
            }

        }






    }

    @FXML
    void idReset(MouseEvent event) {
        deductionEditPrivilege.setText("تعديل إستقطاع");
    }

    @FXML
    void idReset5(MouseEvent event) {
        deductionEditPrivilege2.setText("تعديل إستقطاع");
    }


    public void warningMsg(String title,String message ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private TextField nameDeductionn;

    public void addDeductionn(ActionEvent actionEvent) {
        int dejaExist=0;
        int size=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductionnames` WHERE `deductionName`=?");
            pst.setString(1,nameDeductionn.getText());
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
        if (nameDeductionn.getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `deductionnames`(`deductionName`) VALUES (?)");
                pst.setString(1,nameDeductionn.getText());
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable2();
            fillComboDeductionType();
            nameDeductionn.clear();
        }

    }


    @FXML
    public void deleteRoww(ActionEvent actionEvent) {
        int index= deductionNamesTableView.getSelectionModel().getSelectedIndex();
        int idDelete=deductionNamesTableView.getItems().get(index).getIdDeductionName();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `deductionnames` WHERE `id`=?");
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
        }
        fillComboDeductionType();
    }
    @FXML
    private TextField search2;
    @FXML
    public void search2(KeyEvent keyEvent) {
        String key=search2.getText().trim();
        if (key.isEmpty()){
            addToTable2();
            nameDeductionnTable.setCellValueFactory(new PropertyValueFactory<>("deductionName"));
            deductionNamesTableView.setItems(deductionNamesTable);
        }else{
            deductionNamesTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM  `deductionnames` WHERE `deductionName` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    deductionNamesTable.add(new DeductionNames(rs.getString("deductionName"), rs.getInt("id")));
                }
                pst.close();

                nameDeductionnTable.setCellValueFactory(new PropertyValueFactory<>("deductionName"));
                deductionNamesTableView.setItems(deductionNamesTable);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }


    public void idReset1(MouseEvent mouseEvent) {
        deductionEditPrivilege1.setText("تعديل إستقطاع");

    }
}
