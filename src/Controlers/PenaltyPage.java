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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class PenaltyPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Area> areas5= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Location> locations5= FXCollections.observableArrayList();
    ObservableList<String> locoremps= FXCollections.observableArrayList("موقع","موظف");
    ObservableList<String> perOrCoss= FXCollections.observableArrayList("تكلفة");
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<Project> projects5= FXCollections.observableArrayList();
    ObservableList<EmployeeForList> employees= FXCollections.observableArrayList();
    ObservableList<EmployeeForList> employees5= FXCollections.observableArrayList();
    ObservableList<Occupation> occupations= FXCollections.observableArrayList();
    ObservableList<Occupation> occupations5= FXCollections.observableArrayList();
    int idArea=0,idLocation=0,idProject=0,idEmployee=0,idOccupation=0,idArea5=0,idLocation5=0,idProject5=0,idEmployee5=0,idOccupation5=0;
    String nortValue=null,nortValue1=null,nortValue5=null;
    int isChanged=0,isChanged1=0,isChanged5=0;

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> locationName;
    @FXML
    private ComboBox<String> occupationName;
    @FXML
    private ComboBox<String> locoremp;
    @FXML
    private ComboBox<String> perOrCos;

    @FXML
    private ComboBox<String> employeeName;
    @FXML
    private TextField amountOfDeduction;
    @FXML
    private TextField typeDeduction;
    @FXML
    private ComboBox<String> projectName;



    @FXML
    private ComboBox<String> areaName2;
    @FXML
    private ComboBox<String> locationName2;
    @FXML
    private ComboBox<String> occupationName2;
    @FXML
    private ComboBox<String> locoremp2;
    @FXML
    private ComboBox<String> perOrCos2;

    @FXML
    private ComboBox<String> employeeName2;
    @FXML
    private TextField amountOfDeduction2;
    @FXML
    private TextField typeDeduction2;
    @FXML
    private ComboBox<String> projectName2;


    @FXML
    private ComboBox<String> areaName1;
    @FXML
    private ComboBox<String> locationName1;
    @FXML
    private ComboBox<String> occupationName1;
    @FXML
    private ComboBox<String> locoremp1;
    @FXML
    private ComboBox<String> perOrCos1;

    @FXML
    private ComboBox<String> employeeName1;
    @FXML
    private TextField amountOfDeduction1;
    @FXML
    private TextField typeDeduction1;
    @FXML
    private ComboBox<String> projectName1;

    @FXML
    void selectArea(ActionEvent event) {
        int index= areaName.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        fillComboLocation();
        employeeName.getItems().clear();
        projectName.getItems().clear();
        employeeName.setValue("");

    }
    @FXML
    void selectArea5(ActionEvent event) {
        int index= areaName2.getSelectionModel().getSelectedIndex();
        idArea5=areas5.get(index).getIdArea();
        fillComboLocation5();
        employeeName2.getItems().clear();
        projectName2.getItems().clear();
        employeeName2.setValue("");

    }


    @FXML
    void selectArea1(ActionEvent event) {
        int index= areaName1.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        fillComboLocation1();
        employeeName1.getItems().clear();
        projectName1.getItems().clear();
        employeeName1.setValue("");

    }

    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();
        fillComboProject();
        employeeName.getItems().clear();
        employeeName.setValue("");

    }

    @FXML
    void selectLocation5(ActionEvent event) {
        int index= locationName2.getSelectionModel().getSelectedIndex();
        idLocation5=locations5.get(index).getIdLocation();
        fillComboProject5();
        employeeName2.getItems().clear();
        employeeName2.setValue("");

    }


    @FXML
    void selectLocation1(ActionEvent event) {
        int index= locationName1.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();
        fillComboProject1();
        employeeName1.getItems().clear();
        employeeName1.setValue("");

    }

    @FXML
    void selectEmployee(ActionEvent event) {
        int index= employeeName.getSelectionModel().getSelectedIndex();
        idEmployee=employees.get(index).getId();
        isChanged=1;


    }

    @FXML
    void selectEmployee5(ActionEvent event) {
        int index= employeeName2.getSelectionModel().getSelectedIndex();
        idEmployee5=employees5.get(index).getId();
        isChanged5=1;


    }


    @FXML
    void selectEmployee1(ActionEvent event) {
        int index= employeeName1.getSelectionModel().getSelectedIndex();
        idEmployee=employees.get(index).getId();
        isChanged1=1;


    }
    @FXML
    void selectProject(ActionEvent event) {
        int index= projectName.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();
        fillComboOccupation();

    }
    @FXML
    void selectProject5(ActionEvent event) {
        int index= projectName2.getSelectionModel().getSelectedIndex();
        idProject5=projects5.get(index).getIdProject();
        fillComboOccupation5();

    }

    @FXML
    void selectProject1(ActionEvent event) {
        int index= projectName1.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();
        fillComboOccupation1();

    }
    @FXML
    void selectOccupation(ActionEvent event) {
        int index= occupationName.getSelectionModel().getSelectedIndex();
        idOccupation=occupations.get(index).getIdOcupation();
        fillComboEmployee();
        employeeName.setValue("");

    }
    @FXML
    void selectOccupation5(ActionEvent event) {
        int index= occupationName2.getSelectionModel().getSelectedIndex();
        idOccupation5=occupations5.get(index).getIdOcupation();
        fillComboEmployee5();
        employeeName2.setValue("");

    }

    @FXML
    void selectOccupation1(ActionEvent event) {
        int index= occupationName1.getSelectionModel().getSelectedIndex();
        idOccupation=occupations.get(index).getIdOcupation();
        fillComboEmployee1();
        employeeName1.setValue("");

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

    public void fillComboOccupation(){
        occupations.clear();
        occupationName.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations`, `occupations` WHERE projectoccupations.idProject=? AND projectoccupations.idOccupation=occupations.id");
            pst.setInt(1,idProject);
            rs=pst.executeQuery();
            while (rs.next()){
                occupations.add(new Occupation(rs.getInt(6),rs.getString("occupationName")));

            }
            for (int i=0;i<occupations.size();i++){
                occupationName.getItems().add(occupations.get(i).getNameOcupation());
                System.out.println(occupations.get(i).getIdOcupation());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

    public void fillComboOccupation5(){
        occupations5.clear();
        occupationName2.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations`, `occupations` WHERE projectoccupations.idProject=? AND projectoccupations.idOccupation=occupations.id");
            pst.setInt(1,idProject5);
            rs=pst.executeQuery();
            while (rs.next()){
                occupations5.add(new Occupation(rs.getInt(6),rs.getString("occupationName")));

            }
            for (int i=0;i<occupations5.size();i++){
                occupationName2.getItems().add(occupations5.get(i).getNameOcupation());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }


    public void fillComboOccupation1(){
        occupations.clear();
        occupationName1.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations`, `occupations` WHERE projectoccupations.idProject=? AND projectoccupations.idOccupation=occupations.id");
            pst.setInt(1,idProject);
            rs=pst.executeQuery();
            while (rs.next()){
                occupations.add(new Occupation(rs.getInt(6),rs.getString("occupationName")));

            }
            for (int i=0;i<occupations.size();i++){
                occupationName1.getItems().add(occupations.get(i).getNameOcupation());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void fillComboEmployee(){
        employees.clear();
        employeeName.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectsemployees`,`employees` WHERE projectsemployees.idEmployee=employees.id AND projectsemployees.idProject=? AND `idOccupation`=?");
            pst.setInt(1,idProject);
            pst.setInt(2,idOccupation);
            rs=pst.executeQuery();
            while (rs.next()){
                employees.add(new EmployeeForList(rs.getInt("idEmployee"),rs.getString("employeeName")));

            }
            for (int i=0;i<employees.size();i++){
                employeeName.getItems().add(employees.get(i).getEmployeeName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }


    public void fillComboEmployee5(){
        employees5.clear();
        employeeName2.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectsemployees`,`employees` WHERE projectsemployees.idEmployee=employees.id AND projectsemployees.idProject=? AND `idOccupation`=?");
            pst.setInt(1,idProject5);
            pst.setInt(2,idOccupation5);
            rs=pst.executeQuery();
            while (rs.next()){
                employees5.add(new EmployeeForList(rs.getInt("idEmployee"),rs.getString("employeeName")));

            }
            for (int i=0;i<employees5.size();i++){
                employeeName2.getItems().add(employees5.get(i).getEmployeeName());
            }
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }


    public void fillComboEmployee1(){
        employees.clear();
        employeeName1.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectsemployees`,`employees` WHERE projectsemployees.idEmployee=employees.id AND projectsemployees.idProject=? AND `idOccupation`=?");
            pst.setInt(1,idProject);
            pst.setInt(2,idOccupation);
            rs=pst.executeQuery();
            while (rs.next()){
                employees.add(new EmployeeForList(rs.getInt("idEmployee"),rs.getString("employeeName")));

            }
            for (int i=0;i<employees.size();i++){
                employeeName1.getItems().add(employees.get(i).getEmployeeName());
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
    private Button penaltyDeletePrivilege;
    @FXML
    private Button penaltyDeletePrivilege2;
    @FXML
    private Button penaltyAddPrivilege;
    @FXML
    private Button penaltyEditPrivilege;
    @FXML
    private Button penaltyAddPrivilege1;
    @FXML
    private Button penaltyAddPrivilege2;
    @FXML
    private Button penaltyDeletePrivilege1;


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
                if (rs.getInt("pesa")==0){
                    penaltyAddPrivilege.setDisable(true);

                }else{
                    penaltyAddPrivilege.setDisable(false);

                }
                if (rs.getInt("pesd")==0){
                    penaltyDeletePrivilege.setDisable(true);

                }else{
                    penaltyDeletePrivilege.setDisable(false);

                }
                if (rs.getInt("pese")==0){
                    penaltyEditPrivilege.setDisable(true);

                }else{
                    penaltyEditPrivilege.setDisable(false);

                }
                if (rs.getInt("pema")==0){
                    penaltyAddPrivilege1.setDisable(true);

                }else{
                    penaltyAddPrivilege1.setDisable(false);

                }
                if (rs.getInt("pemd")==0){
                    penaltyDeletePrivilege1.setDisable(true);

                }else{
                    penaltyDeletePrivilege1.setDisable(false);

                }
                if (rs.getInt("peme")==0){
                    penaltyEditPrivilege1.setDisable(true);

                }else{
                    penaltyEditPrivilege1.setDisable(false);

                }
                if (rs.getInt("perca")==0){
                    penaltyAddPrivilege2.setDisable(true);

                }else{
                    penaltyAddPrivilege2.setDisable(false);

                }
                if (rs.getInt("percd")==0){
                    penaltyDeletePrivilege2.setDisable(true);

                }else{
                    penaltyDeletePrivilege2.setDisable(false);

                }
                if (rs.getInt("perce")==0){
                    penaltyEditPrivilege2.setDisable(true);

                }else{
                    penaltyEditPrivilege2.setDisable(false);

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
        if (amountOfDeduction.getText().isEmpty()||typeDeduction.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()||locoremp.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else{
            try {
                con=new ConnectDB().getConnection();
                if (idEmployee>0){
                    pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `idEmployeeDeduction`,`empoyeeNameDed`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    pst.setInt(1,idArea);
                    pst.setInt(2,idLocation);
                    pst.setString(3,typeDeduction.getText());
                    pst.setFloat(4, Float.parseFloat(amountOfDeduction.getText()));
                    pst.setInt(5,idProject);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );

                    pst.setString(6, sdf.format(new Date()));
                    pst.setInt(7,idEmployee);
                    pst.setString(8,employeeName.getValue());
                    pst.setString(9,"p");
                    pst.setString(10,"تكلفة");

                }else{
                    pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,?)");
                    pst.setInt(1,idArea);
                    pst.setInt(2,idLocation);
                    pst.setString(3,typeDeduction.getText());
                    pst.setFloat(4, Float.parseFloat(amountOfDeduction.getText()));
                    pst.setInt(5,idProject);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd" );
                    pst.setString(6, sdf.format(new Date()));
                    pst.setString(7,"p");
                    pst.setString(8,"تكلفة");

                }



                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");
                employeeName.getItems().clear();
                projectName.getItems().clear();
                locationName.getItems().clear();
                areaName.getItems().clear();
                amountOfDeduction.clear();
                typeDeduction.clear();
                fillComboArea();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
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
            amountOfDeduction.clear();
        }

    }
    public void addDeduction5(ActionEvent actionEvent) {
        if (amountOfDeduction2.getText().isEmpty()||typeDeduction2.getText().isEmpty()||areaName2.getSelectionModel().isEmpty()||locationName2.getSelectionModel().isEmpty()||projectName2.getSelectionModel().isEmpty()||locoremp2.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else{
            try {
                con=new ConnectDB().getConnection();
                if (idEmployee5>0){
                    pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `idEmployeeDeduction`,`empoyeeNameDed`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    pst.setInt(1,idArea5);
                    pst.setInt(2,idLocation5);
                    pst.setString(3,typeDeduction2.getText());
                    pst.setFloat(4, Float.parseFloat(amountOfDeduction2.getText()));
                    pst.setInt(5,idProject5);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );

                    pst.setString(6, sdf.format(new Date()));
                    pst.setInt(7,idEmployee5);
                    pst.setString(8,employeeName2.getValue());
                    pst.setString(9,"p");
                    pst.setString(10,"تكلفة");

                }else{
                    pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,?)");
                    pst.setInt(1,idArea5);
                    pst.setInt(2,idLocation5);
                    pst.setString(3,typeDeduction2.getText());
                    pst.setFloat(4, Float.parseFloat(amountOfDeduction2.getText()));
                    pst.setInt(5,idProject5);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd" );
                    pst.setString(6, sdf.format(new Date()));
                    pst.setString(7,"p");
                    pst.setString(8,"تكلفة");

                }



                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");
                employeeName2.getItems().clear();
                projectName2.getItems().clear();
                locationName2.getItems().clear();
                areaName2.getItems().clear();
                amountOfDeduction2.clear();
                typeDeduction2.clear();
                fillComboArea5();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
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
            amountOfDeduction2.clear();
        }

    }

    public void addDeduction1(ActionEvent actionEvent) {
        if (amountOfDeduction1.getText().isEmpty()||typeDeduction1.getText().isEmpty()||areaName1.getSelectionModel().isEmpty()||locationName1.getSelectionModel().isEmpty()||projectName1.getSelectionModel().isEmpty()||locoremp1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else{
            try {
                con=new ConnectDB().getConnection();
                if (idEmployee>0){
                    pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `idEmployeeDeduction`,`empoyeeNameDed`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    pst.setInt(1,idArea);
                    pst.setInt(2,idLocation);
                    pst.setString(3,typeDeduction1.getText());
                    pst.setFloat(4, Float.parseFloat(amountOfDeduction1.getText()));
                    pst.setInt(5,idProject);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );

                    pst.setString(6, sdf.format(new Date()));
                    pst.setInt(7,idEmployee);
                    pst.setString(8,employeeName1.getValue());
                    pst.setString(9,"p");
                    pst.setString(10,"تكلفة");

                }else{
                    pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,?)");
                    pst.setInt(1,idArea);
                    pst.setInt(2,idLocation);
                    pst.setString(3,typeDeduction1.getText());
                    pst.setFloat(4, Float.parseFloat(amountOfDeduction1.getText()));
                    pst.setInt(5,idProject);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd" );
                    pst.setString(6, sdf.format(new Date()));
                    pst.setString(7,"p");
                    pst.setString(8,"تكلفة");

                }



                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");
                employeeName1.getItems().clear();
                projectName1.getItems().clear();
                locationName1.getItems().clear();
                areaName1.getItems().clear();
                amountOfDeduction1.clear();
                typeDeduction1.clear();
                fillComboArea1();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
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
            amountOfDeduction1.clear();
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
    private TableView<DeductionForTable> deductionTableView;

    @FXML
    private TableColumn<DeductionForTable, String> areaNameTable;

    @FXML
    private TableColumn<DeductionForTable, String> locationNameTable;

    @FXML
    private TableColumn<DeductionForTable, String> projectNameTable;

    @FXML
    private TableColumn<DeductionForTable, Float> amountOfDeductionTable;

    @FXML
    private TableColumn<DeductionForTable, String> nortTable;

    @FXML
    private TableColumn<DeductionForTable, String> typeDeductionTable;

    @FXML
    private TableColumn<DeductionForTable, String> employeeNameTable;

    @FXML
    private TableView<DeductionForTable> deductionTableView2;

    @FXML
    private TableColumn<DeductionForTable, String> areaNameTable2;

    @FXML
    private TableColumn<DeductionForTable, String> locationNameTable2;

    @FXML
    private TableColumn<DeductionForTable, String> projectNameTable2;

    @FXML
    private TableColumn<DeductionForTable, Float> amountOfDeductionTable2;

    @FXML
    private TableColumn<DeductionForTable, String> nortTable2;

    @FXML
    private TableColumn<DeductionForTable, String> typeDeductionTable2;

    @FXML
    private TableColumn<DeductionForTable, String> employeeNameTable2;


    @FXML
    private TableView<DeductionForTable> deductionTableView1;

    @FXML
    private TableColumn<DeductionForTable, String> areaNameTable1;

    @FXML
    private TableColumn<DeductionForTable, String> locationNameTable1;

    @FXML
    private TableColumn<DeductionForTable, String> projectNameTable1;

    @FXML
    private TableColumn<DeductionForTable, Float> amountOfDeductionTable1;

    @FXML
    private TableColumn<DeductionForTable, String> nortTable1;

    @FXML
    private TableColumn<DeductionForTable, String> typeDeductionTable1;

    @FXML
    private TableColumn<DeductionForTable, String> employeeNameTable1;

    ObservableList deductionsTable= FXCollections.observableArrayList();
    ObservableList deductionsTable1= FXCollections.observableArrayList();
    ObservableList deductionsTable5= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        fillComboArea1();
        fillComboArea5();
        occupationName.setVisible(false);
        occupationName1.setVisible(false);
        occupationName2.setVisible(false);
        per.setVisible(false);
        per1.setVisible(false);
        per2.setVisible(false);
        employeeName.setVisible(false);
        employeeName1.setVisible(false);
        employeeName2.setVisible(false);
        perOrCos.setItems(perOrCoss);
        perOrCos1.setItems(perOrCoss);
        perOrCos2.setItems(perOrCoss);
        locoremp.setItems(locoremps);
        locoremp1.setItems(locoremps);
        locoremp2.setItems(locoremps);
        addToTable();
        addToTable1();
        addToTable5();
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        amountOfDeductionTable.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
        nortTable.setCellValueFactory(new PropertyValueFactory<>("nort"));
        typeDeductionTable.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
        employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("nameEmployee"));
        deductionTableView.setItems(deductionsTable);


        areaNameTable2.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable2.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable2.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        amountOfDeductionTable2.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
        nortTable2.setCellValueFactory(new PropertyValueFactory<>("nort"));
        typeDeductionTable2.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
        employeeNameTable2.setCellValueFactory(new PropertyValueFactory<>("nameEmployee"));
        deductionTableView2.setItems(deductionsTable5);

        areaNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        amountOfDeductionTable1.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
        nortTable1.setCellValueFactory(new PropertyValueFactory<>("nort"));
        typeDeductionTable1.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
        employeeNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameEmployee"));
        deductionTableView1.setItems(deductionsTable1);

    }
    public void addToTable(){
        deductionsTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='p' AND projects.projectType='مشروع قطاع صحي'");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionsTable.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

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
            pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='p' AND projects.projectType='مشروع قطاع عسكري'");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionsTable1.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

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
            pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='p' AND (projects.projectType='مشروع النظافة' OR projects.projectType='مشروع الصيانة')");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionsTable5.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

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
            idDelete=0;
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
            idDelete=0;
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
            idDelete=0;
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
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='p' AND projects.contractName LIKE '%"+key+"%'");
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
    private TextField search2;
    @FXML
    public void search5(KeyEvent keyEvent) {
        String key=search2.getText().trim();
        if (key.isEmpty()){
            addToTable5();

            deductionTableView2.setItems(deductionsTable5);
        }else{
            deductionsTable5.clear();
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='p' AND projects.contractName LIKE '%"+key+"%'");
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
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `deductions`,`areas`,`locations`,`projects` WHERE deductions.idArea=areas.id AND deductions.idLocation=locations.id AND deductions.idProject=projects.id AND deductions.dorp='p' AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    deductionsTable1.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("empoyeeNameDed"),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));


                }
                pst.close();

                deductionTableView.setItems(deductionsTable);


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

        String EmployeeN="موقع";

        if (penaltyEditPrivilege.getText().contains("تعديل الغرامة")){
            penaltyEditPrivilege.setText("حفظ");
            areaName.setValue(deductionTableView.getItems().get(index).getNameArea());
            locationName.setValue(deductionTableView.getItems().get(index).getNameLocation());
            projectName.setValue(deductionTableView.getItems().get(index).getNameProject());
            employeeName.setValue(deductionTableView.getItems().get(index).getNameEmployee());
            perOrCos.setValue(deductionTableView.getItems().get(index).getNort());
            typeDeduction.setText(deductionTableView.getItems().get(index).getTypeDeduction());
            amountOfDeduction.setText(deductionTableView.getItems().get(index).getAmountOfDeduction());

            if (deductionTableView.getSelectionModel().getSelectedItem().getNameEmployee()!=null){
                locoremp.setValue("موظف");
            }else if (deductionTableView.getSelectionModel().getSelectedItem().getNameEmployee()==null){
                locoremp.setValue("موقع");
            }
            if (locoremp.getSelectionModel().getSelectedItem()=="موظف"){
                occupationName.setVisible(true);
                employeeName.setVisible(true);
            }else{
                occupationName.setVisible(false);
                employeeName.setVisible(false);
            }
        }else if (penaltyEditPrivilege.getText().contains("حفظ")){
            if (amountOfDeduction.getText().isEmpty()||typeDeduction.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()||locoremp.getSelectionModel().isEmpty()||perOrCos.getSelectionModel().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else{
                try {
                    for (int i=0; i<areas.size() ;i++){
                        if (areas.get(i).getNameArea()==areaName.getValue()){
                            idArea=areas.get(i).getIdArea();
                        }
                    }
                    for (int i=0; i<locations.size() ;i++){
                        if (locations.get(i).getLocationName()==locationName.getValue()){
                            idLocation=locations.get(i).getIdLocation();
                        }
                    }

                    for (int i=0; i<employees.size() ;i++){
                        if (employees.get(i).getEmployeeName()==employeeName.getValue()){
                            idEmployee=employees.get(i).getId();

                        }
                    }
                    for (int i=0; i<projects.size() ;i++){
                        if (projects.get(i).getContractName()==projectName.getValue()){
                            idProject=projects.get(i).getIdProject();
                        }
                    }
                    con = new ConnectDB().getConnection();

                    if (isChanged==1){
                        pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idEmployeeDeduction`=?,`idProject`=?,`nort`=?,`empoyeeNameDed`=? WHERE `id`=?");
                        pst.setInt(1, idArea);
                        pst.setInt(2, idLocation);
                        pst.setString(3, typeDeduction.getText());
                        pst.setString(4, amountOfDeduction.getText());
                        pst.setInt(5, idEmployee);
                        pst.setInt(6, idProject);
                        pst.setString(7, nortValue);
                        pst.setString(8, employeeName.getValue());
                        pst.setInt(9, idEdit);
                        pst.execute();
                        pst.close();

                        penaltyEditPrivilege.setText("تعديل الغرامة");
                        employeeName.getItems().clear();
                        projectName.getItems().clear();
                        locationName.getItems().clear();
                        areaName.getItems().clear();
                        amountOfDeduction.clear();
                        typeDeduction.clear();
                        fillComboArea();
                    }else if (idEmployee<1){
                        pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idProject`=?,`nort`=?,`empoyeeNameDed`=? WHERE `id`=?");
                        pst.setInt(1, idArea);
                        pst.setInt(2, idLocation);
                        pst.setString(3, typeDeduction.getText());
                        pst.setString(4, amountOfDeduction.getText());
                        pst.setInt(5, idProject);
                        pst.setString(6, nortValue);
                        pst.setString(7, employeeName.getValue());
                        pst.setInt(8, idEdit);
                        pst.execute();
                        pst.close();

                        penaltyEditPrivilege.setText("تعديل الغرامة");
                        employeeName.getItems().clear();
                        projectName.getItems().clear();
                        locationName.getItems().clear();
                        areaName.getItems().clear();
                        amountOfDeduction.clear();
                        typeDeduction.clear();
                        fillComboArea();
                    }

                    warningMsg("تعديل","تم التعديل بنجاح");
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
                isChanged=0;
                addToTable();
                idEdit=0;
            }

        }


    }

    public void edit5(ActionEvent actionEvent) {
        int index= deductionTableView2.getSelectionModel().getSelectedIndex();
        int idEdit=deductionTableView2.getItems().get(index).getIdDeduction();

        String EmployeeN5="موقع";

        if (penaltyEditPrivilege2.getText().contains("تعديل الغرامة")){
            penaltyEditPrivilege2.setText("حفظ");
            areaName2.setValue(deductionTableView2.getItems().get(index).getNameArea());
            locationName2.setValue(deductionTableView2.getItems().get(index).getNameLocation());
            projectName2.setValue(deductionTableView2.getItems().get(index).getNameProject());
            employeeName2.setValue(deductionTableView2.getItems().get(index).getNameEmployee());
            perOrCos2.setValue(deductionTableView2.getItems().get(index).getNort());
            typeDeduction2.setText(deductionTableView2.getItems().get(index).getTypeDeduction());
            amountOfDeduction2.setText(deductionTableView2.getItems().get(index).getAmountOfDeduction());

            if (deductionTableView2.getSelectionModel().getSelectedItem().getNameEmployee()!=null){
                locoremp2.setValue("موظف");
            }else if (deductionTableView2.getSelectionModel().getSelectedItem().getNameEmployee()==null){
                locoremp2.setValue("موقع");
            }
            if (locoremp2.getSelectionModel().getSelectedItem()=="موظف"){
                occupationName2.setVisible(true);
                employeeName2.setVisible(true);
            }else{
                occupationName2.setVisible(false);
                employeeName2.setVisible(false);
            }
        }else if (penaltyEditPrivilege2.getText().contains("حفظ")){
            if (amountOfDeduction2.getText().isEmpty()||typeDeduction2.getText().isEmpty()||areaName2.getSelectionModel().isEmpty()||locationName2.getSelectionModel().isEmpty()||projectName2.getSelectionModel().isEmpty()||locoremp2.getSelectionModel().isEmpty()||perOrCos2.getSelectionModel().isEmpty()){
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

                    for (int i=0; i<employees5.size() ;i++){
                        if (employees5.get(i).getEmployeeName()==employeeName2.getValue()){
                            idEmployee5=employees5.get(i).getId();

                        }
                    }
                    for (int i=0; i<projects5.size() ;i++){
                        if (projects5.get(i).getContractName()==projectName2.getValue()){
                            idProject5=projects5.get(i).getIdProject();
                        }
                    }
                    con = new ConnectDB().getConnection();

                    if (isChanged5==1){
                        pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idEmployeeDeduction`=?,`idProject`=?,`nort`=?,`empoyeeNameDed`=? WHERE `id`=?");
                        pst.setInt(1, idArea5);
                        pst.setInt(2, idLocation5);
                        pst.setString(3, typeDeduction2.getText());
                        pst.setString(4, amountOfDeduction2.getText());
                        pst.setInt(5, idEmployee5);
                        pst.setInt(6, idProject5);
                        pst.setString(7, nortValue5);
                        pst.setString(8, employeeName2.getValue());
                        pst.setInt(9, idEdit);
                        pst.execute();
                        pst.close();

                        penaltyEditPrivilege2.setText("تعديل الغرامة");
                        employeeName2.getItems().clear();
                        projectName2.getItems().clear();
                        locationName2.getItems().clear();
                        areaName2.getItems().clear();
                        amountOfDeduction2.clear();
                        typeDeduction2.clear();
                        fillComboArea5();
                    }else if (idEmployee5<1){
                        pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idProject`=?,`nort`=?,`empoyeeNameDed`=? WHERE `id`=?");
                        pst.setInt(1, idArea5);
                        pst.setInt(2, idLocation5);
                        pst.setString(3, typeDeduction2.getText());
                        pst.setString(4, amountOfDeduction2.getText());
                        pst.setInt(5, idProject5);
                        pst.setString(6, nortValue5);
                        pst.setString(7, employeeName2.getValue());
                        pst.setInt(8, idEdit);
                        pst.execute();
                        pst.close();

                        penaltyEditPrivilege2.setText("تعديل الغرامة");
                        employeeName2.getItems().clear();
                        projectName2.getItems().clear();
                        locationName2.getItems().clear();
                        areaName2.getItems().clear();
                        amountOfDeduction2.clear();
                        typeDeduction2.clear();
                        fillComboArea5();
                    }

                    warningMsg("تعديل","تم التعديل بنجاح");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
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
                isChanged5=0;
                addToTable5();
                idEdit=0;
            }

        }


    }


    @FXML
    private Button penaltyEditPrivilege1;
    @FXML
    private Button penaltyEditPrivilege2;
    public void edit1(ActionEvent actionEvent) {
        int index= deductionTableView1.getSelectionModel().getSelectedIndex();
        int idEdit=deductionTableView1.getItems().get(index).getIdDeduction();


        if (penaltyEditPrivilege1.getText().contains("تعديل الغرامة")){
            penaltyEditPrivilege1.setText("حفظ");
            areaName1.setValue(deductionTableView1.getItems().get(index).getNameArea());
            locationName1.setValue(deductionTableView1.getItems().get(index).getNameLocation());
            projectName1.setValue(deductionTableView1.getItems().get(index).getNameProject());
            employeeName1.setValue(deductionTableView1.getItems().get(index).getNameEmployee());
            perOrCos1.setValue(deductionTableView1.getItems().get(index).getNort());
            typeDeduction1.setText(deductionTableView1.getItems().get(index).getTypeDeduction());
            amountOfDeduction1.setText(deductionTableView1.getItems().get(index).getAmountOfDeduction());
            if (locoremp1.getSelectionModel().getSelectedItem()=="موظف"){
                occupationName1.setVisible(true);
                employeeName1.setVisible(true);
            }else{
                occupationName1.setVisible(false);
                employeeName1.setVisible(false);
            }
            if (deductionTableView1.getSelectionModel().getSelectedItem().getNameEmployee()!=null){
                locoremp1.setValue("موظف");
            }else if (deductionTableView1.getSelectionModel().getSelectedItem().getNameEmployee()==null){
                locoremp1.setValue("موقع");
            }
        }else if (penaltyEditPrivilege1.getText().contains("حفظ")){
            if (amountOfDeduction1.getText().isEmpty()||typeDeduction1.getText().isEmpty()||areaName1.getSelectionModel().isEmpty()||locationName1.getSelectionModel().isEmpty()||projectName1.getSelectionModel().isEmpty()||locoremp1.getSelectionModel().isEmpty()||perOrCos1.getSelectionModel().isEmpty()){
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

                    for (int i=0; i<employees.size() ;i++){
                        if (employees.get(i).getEmployeeName()==employeeName1.getValue()){
                            idEmployee=employees.get(i).getId();

                        }
                    }
                    for (int i=0; i<projects.size() ;i++){
                        if (projects.get(i).getContractName()==projectName1.getValue()){
                            idProject=projects.get(i).getIdProject();
                        }
                    }
                    con = new ConnectDB().getConnection();

                    if (isChanged1==1){
                        pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idEmployeeDeduction`=?,`idProject`=?,`nort`=?,`empoyeeNameDed`=? WHERE `id`=?");
                        pst.setInt(1, idArea);
                        pst.setInt(2, idLocation);
                        pst.setString(3, typeDeduction1.getText());
                        pst.setString(4, amountOfDeduction1.getText());
                        pst.setInt(5, idEmployee);
                        pst.setInt(6, idProject);
                        pst.setString(7, nortValue1);
                        pst.setString(8, employeeName1.getValue());
                        pst.setInt(9, idEdit);
                        pst.execute();
                        pst.close();

                        penaltyEditPrivilege1.setText("تعديل الغرامة");
                        employeeName1.getItems().clear();
                        projectName1.getItems().clear();
                        locationName1.getItems().clear();
                        areaName1.getItems().clear();
                        amountOfDeduction1.clear();
                        typeDeduction1.clear();
                        fillComboArea1();
                    }else if (idEmployee<1){
                        pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idProject`=?,`nort`=?,`empoyeeNameDed`=? WHERE `id`=?");
                        pst.setInt(1, idArea);
                        pst.setInt(2, idLocation);
                        pst.setString(3, typeDeduction1.getText());
                        pst.setString(4, amountOfDeduction1.getText());
                        pst.setInt(5, idProject);
                        pst.setString(6, nortValue1);
                        pst.setString(7, employeeName1.getValue());
                        pst.setInt(8, idEdit);
                        pst.execute();
                        pst.close();

                        penaltyEditPrivilege1.setText("تعديل الغرامة");
                        employeeName1.getItems().clear();
                        projectName1.getItems().clear();
                        locationName1.getItems().clear();
                        areaName1.getItems().clear();
                        amountOfDeduction1.clear();
                        typeDeduction1.clear();
                        fillComboArea1();
                    }

                    warningMsg("تعديل","تم التعديل بنجاح");
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
                isChanged1=0;
                addToTable1();
                idEdit=0;
            }

        }


    }
    @FXML
    void idReset(MouseEvent event) {
        penaltyEditPrivilege.setText("تعديل الغرامة");
    }
    @FXML
    void idReset5(MouseEvent event) {
        penaltyEditPrivilege2.setText("تعديل الغرامة");
    }

    @FXML
    void idReset1(MouseEvent event) {
        penaltyEditPrivilege1.setText("تعديل الغرامة");
    }


    public void selectLocOrEmp(ActionEvent actionEvent) {
        if (locoremp.getValue()=="موظف"){
//            System.out.println("thats work");
            occupationName.setVisible(true);
            employeeName.setVisible(true);
        }else {
//            System.out.println("thats not work");
            occupationName.setVisible(false);
            employeeName.setVisible(false);

        }
    }
    public void selectLocOrEmp5(ActionEvent actionEvent) {
        if (locoremp2.getValue()=="موظف"){
//            System.out.println("thats work");
            occupationName2.setVisible(true);
            employeeName2.setVisible(true);
        }else {
//            System.out.println("thats not work");
            occupationName2.setVisible(false);
            employeeName2.setVisible(false);

        }
    }


    public void selectLocOrEmp1(ActionEvent actionEvent) {
        if (locoremp1.getValue()=="موظف"){
//            System.out.println("thats work");
            occupationName1.setVisible(true);
            employeeName1.setVisible(true);
        }else {
//            System.out.println("thats not work");
            occupationName1.setVisible(false);
            employeeName1.setVisible(false);

        }
    }

    @FXML
    private Label per;

    @FXML
    private Label per1;
    @FXML
    private Label per2;

    public void selectPerOrCos(ActionEvent actionEvent) {
        if (perOrCos.getValue()=="نسبة"){
//            System.out.println("thats work");
            per.setVisible(true);
            nortValue="نسبة";

        }else {
//            System.out.println("thats not work");
            per.setVisible(false);
            nortValue="تكلفة";

        }
    }
    public void selectPerOrCos5(ActionEvent actionEvent) {
        if (perOrCos2.getValue()=="نسبة"){
//            System.out.println("thats work");
            per2.setVisible(true);
            nortValue5="نسبة";

        }else {
//            System.out.println("thats not work");
            per2.setVisible(false);
            nortValue5="تكلفة";

        }
    }


    public void selectPerOrCos1(ActionEvent actionEvent) {
        if (perOrCos1.getValue()=="نسبة"){
//            System.out.println("thats work");
            per1.setVisible(true);
            nortValue1="نسبة";

        }else {
//            System.out.println("thats not work");
            per1.setVisible(false);
            nortValue1="تكلفة";

        }
    }


}
