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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AbstractPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
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
    private TableColumn<AbstractForTable2, String> remarkTable;


    @FXML
    private ComboBox<String> areaName1;
    @FXML
    private ComboBox<String> locationName1;
    @FXML
    private TextField contractType1;

    @FXML
    private ComboBox<String> projectName1;



    @FXML
    private TableView<AbstractForTable> abstractTableView1;
    @FXML
    private TableView<AbstractForTable2> yearAbstractTableView1;

    @FXML
    private TableColumn<AbstractForTable, String> projectNameTable1;

    @FXML
    private TableColumn<AbstractForTable, String> contractNumberTable1;

    @FXML
    private TableColumn<AbstractForTable, String> contractTypeTable1;

    @FXML
    private TableColumn<AbstractForTable, String> areaNameTable1;

    @FXML
    private TableColumn<AbstractForTable, String> locationNameTable1;

    @FXML
    private TableColumn<AbstractForTable, String> contractStartDateTable1;

    @FXML
    private TableColumn<AbstractForTable, String> contractEndDateTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> yearTable1;
    @FXML
    private TableColumn<AbstractForTable2, String> janvierTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> fevrierTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> marsTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> avrilTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> mayTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> juinTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> juillietTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> aoutTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> septembreTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> octobreTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> novembreTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> decembreTable1;

    @FXML
    private TableColumn<AbstractForTable2, String> remarkTable1;

    @FXML
    void selectArea(ActionEvent event) {
        int index= areaName.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();

        fillComboLocation();


    }
    @FXML
    void selectArea1(ActionEvent event) {
        int index= areaName1.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();

        fillComboLocation1();


    }
    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();

        fillComboProject();


    }
    @FXML
    void selectLocation1(ActionEvent event) {
        int index= locationName1.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();

        fillComboProject1();


    }

    @FXML
    private TextField contractNumber;

    @FXML
    private DatePicker contractStartDate;

    @FXML
    private DatePicker contractEndDate;

    @FXML
    private TextField contractNumber1;

    @FXML
    private DatePicker contractStartDate1;

    @FXML
    private DatePicker contractEndDate1;
    @FXML
    void selectProject(ActionEvent event) {
        int index= projectName.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `id`=? AND `projectType`='مشروع قطاع صحي'");
            pst.setInt(1,idProject);
            rs=pst.executeQuery();
            while (rs.next()){
                contractNumber.setText(rs.getString("contractNumber"));
                contractType.setText(rs.getString("projectType"));
                contractStartDate.setValue(LocalDate.parse(rs.getString("contractStartDate")));
                contractEndDate.setValue(LocalDate.parse(rs.getString("contractEndDate")));
                contractStartDate.getEditor().setText(rs.getString("contractStartDate"));
                contractEndDate.getEditor().setText(rs.getString("contractEndDate"));
            }
            pst.close();


        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @FXML
    void selectProject1(ActionEvent event) {
        int index= projectName1.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `id`=? AND `projectType`='مشروع قطاع عسكري'");
            pst.setInt(1,idProject);
            rs=pst.executeQuery();
            while (rs.next()){
                contractNumber1.setText(rs.getString("contractNumber"));
                contractType1.setText(rs.getString("projectType"));
                contractStartDate1.setValue(LocalDate.parse(rs.getString("contractStartDate")));
                contractEndDate1.setValue(LocalDate.parse(rs.getString("contractEndDate")));
                contractStartDate1.getEditor().setText(rs.getString("contractStartDate"));
                contractEndDate1.getEditor().setText(rs.getString("contractEndDate"));
            }
            pst.close();


        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void fillComboLocation(){
        locations.clear();
        locationName.getItems().clear();
        contractStartDate.getEditor().clear();
        contractEndDate.getEditor().clear();
        contractNumber.clear();
        contractType.clear();
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
    public void fillComboLocation1(){
        locations.clear();
        locationName1.getItems().clear();
        contractStartDate1.getEditor().clear();
        contractEndDate1.getEditor().clear();
        contractNumber1.clear();
        contractType1.clear();
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
        for (int i =0;i<projects.size();i++){
            projectName.getItems().add(projects.get(i).getContractName());
        }
        contractStartDate.getEditor().clear();
        contractEndDate.getEditor().clear();
        contractNumber.clear();
        contractType.clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=? AND `projectType`='مشروع قطاع صحي'");
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

    public void fillComboProject1(){
        projects.clear();
        projectName1.getItems().clear();
        for (int i =0;i<projects.size();i++){
            projectName1.getItems().add(projects.get(i).getContractName());
        }
        contractStartDate1.getEditor().clear();
        contractEndDate1.getEditor().clear();
        contractNumber1.clear();
        contractType1.clear();
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
    private Button abstractAddPrivilege2;
    @FXML
    private Button abstractDeletePrivilege2;
    @FXML
    private Button abstractAddPrivilege11;
    @FXML
    private Button abstractDeletePrivilege11;


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

                if (rs.getInt("gass")==0){
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

                if (rs.getInt("res")==0){
                    repportMenuButton.setDisable(true);

                }else{
                    repportMenuButton.setDisable(false);

                }
                if (rs.getInt("prss")==0){
                    projectMenuButton.setDisable(true);

                }else{
                    projectMenuButton.setDisable(false);

                }
                if (rs.getInt("absa")==0){
                    abstractAddPrivilege.setDisable(true);
                    abstractAddPrivilege2.setDisable(true);

                }else{
                    abstractAddPrivilege.setDisable(false);
                    abstractAddPrivilege2.setDisable(false);

                }
                if (rs.getInt("absd")==0){
                    abstractDeletePrivilege.setDisable(true);
                    abstractDeletePrivilege2.setDisable(true);

                }else{
                    abstractDeletePrivilege.setDisable(false);
                    abstractDeletePrivilege2.setDisable(false);

                }
                if (rs.getInt("abse")==0){
                    abstractEditPrivilege.setDisable(true);
                    abstractEditPrivilege2.setDisable(true);

                }else{
                    abstractEditPrivilege.setDisable(false);
                    abstractEditPrivilege2.setDisable(false);

                }
                if (rs.getInt("abma")==0){
                    abstractAddPrivilege1.setDisable(true);
                    abstractAddPrivilege11.setDisable(true);

                }else{
                    abstractAddPrivilege1.setDisable(false);
                    abstractAddPrivilege11.setDisable(false);

                }
                if (rs.getInt("abmd")==0){
                    abstractDeletePrivilege1.setDisable(true);
                    abstractDeletePrivilege11.setDisable(true);

                }else{
                    abstractDeletePrivilege1.setDisable(false);
                    abstractDeletePrivilege11.setDisable(false);

                }
                if (rs.getInt("abme")==0){
                    abstractEditPrivilege1.setDisable(true);
                    abstractEditPrivilege21.setDisable(true);
                }else{
                    abstractEditPrivilege1.setDisable(false);
                    abstractEditPrivilege21.setDisable(false);
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


    ObservableList abstractsTable= FXCollections.observableArrayList();
    ObservableList abstractYearsTable= FXCollections.observableArrayList();
    ObservableList abstractsTable1= FXCollections.observableArrayList();
    ObservableList abstractYearsTable1= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        fillComboArea1();
        addToTable();
        addToTable1();
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
        remarkTable.setCellValueFactory(new PropertyValueFactory<>("remark"));

        abstractTableView.setItems(abstractsTable);
        yearAbstractTableView.setItems(abstractYearsTable);
        contractStartDate.getEditor().setEditable(false);
        contractEndDate.getEditor().setEditable(false);

        areaNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable1.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        contractNumberTable1.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        contractTypeTable1.setCellValueFactory(new PropertyValueFactory<>("contractType"));
        contractStartDateTable1.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
        contractEndDateTable1.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
        yearTable1.setCellValueFactory(new PropertyValueFactory<>("year"));
        janvierTable1.setCellValueFactory(new PropertyValueFactory<>("janvier"));
        fevrierTable1.setCellValueFactory(new PropertyValueFactory<>("fevrier"));
        marsTable1.setCellValueFactory(new PropertyValueFactory<>("mars"));
        avrilTable1.setCellValueFactory(new PropertyValueFactory<>("avril"));
        mayTable1.setCellValueFactory(new PropertyValueFactory<>("may"));
        juinTable1.setCellValueFactory(new PropertyValueFactory<>("juin"));
        juillietTable1.setCellValueFactory(new PropertyValueFactory<>("juilliet"));
        aoutTable1.setCellValueFactory(new PropertyValueFactory<>("aout"));
        septembreTable1.setCellValueFactory(new PropertyValueFactory<>("septembre"));
        octobreTable1.setCellValueFactory(new PropertyValueFactory<>("octobre"));
        novembreTable1.setCellValueFactory(new PropertyValueFactory<>("novembre"));
        decembreTable1.setCellValueFactory(new PropertyValueFactory<>("decembre"));
        remarkTable1.setCellValueFactory(new PropertyValueFactory<>("remark"));

        abstractTableView1.setItems(abstractsTable1);
        yearAbstractTableView1.setItems(abstractYearsTable1);
        contractStartDate1.getEditor().setEditable(false);
        contractEndDate1.getEditor().setEditable(false);

    }

    public void addToTable(){
        abstractsTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstract`,`areas`,`locations`,`projects` WHERE abstract.idArea=areas.id AND abstract.idLocation=locations.id AND abstract.idProject=projects.id AND `projects`.`projectType`='مشروع قطاع صحي'");
            rs=pst.executeQuery();
            while (rs.next()){
                abstractsTable.add(new AbstractForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate")));

            }
            con.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void addToTable1(){
        abstractsTable1.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstract`,`areas`,`locations`,`projects` WHERE abstract.idArea=areas.id AND abstract.idLocation=locations.id AND abstract.idProject=projects.id AND `projects`.`projectType`='مشروع قطاع عسكري'");
            rs=pst.executeQuery();
            while (rs.next()){
                abstractsTable1.add(new AbstractForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate")));

            }
            con.close();


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
            pst.close();


        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void fillTableAbstractYears1(){
        abstractYearsTable1.clear();
        yearAbstractTableView1.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations` WHERE `idProject`=?");
            pst.setInt(1,idAbstract);
            rs=pst.executeQuery();
            while (rs.next()){
                abstractYearsTable1.add(new ProjectOcupation(rs.getInt("id"),rs.getInt("idProject"),rs.getInt("idOccupation"),rs.getInt("maxNumber"),rs.getInt("realNumber"),rs.getString("idOccupation")));
            }
            pst.close();


        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
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
    private TextArea remark;
    @FXML
    int dejaExist=0;
    int size=0;
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
        dejaExist=0;
        size=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstract` WHERE `idArea`=? AND `idLocation`=? AND `idProject`=?");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setInt(3,idProject);
            rs=pst.executeQuery();
            while(rs.next()){
                size++;
            }
            pst.close();

            if (size>0){
                dejaExist=1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `abstract`(`idArea`, `idLocation`, `idProject`) VALUES (?,?,?)");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setInt(3,idProject);
                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }

            addToTable();
            areaName.getItems().clear();
            areas.clear();
            locationName.getItems().clear();
            projectName.getItems().clear();
            fillComboArea();
        }



    }
    public void deleteRow2(ActionEvent actionEvent) {
        int index= yearAbstractTableView.getSelectionModel().getSelectedIndex();
        int idDelete=yearAbstractTableView.getItems().get(index).getIdAbstractYears();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `abstractyears` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                pst.close();

                warningMsg("حذف","تم الحذف بنجاح");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTable2();
        }
    }
    public void deleteRow21(ActionEvent actionEvent) {
        int index= yearAbstractTableView1.getSelectionModel().getSelectedIndex();
        int idDelete=yearAbstractTableView1.getItems().get(index).getIdAbstractYears();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `abstractyears` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                pst.close();

                warningMsg("حذف","تم الحذف بنجاح");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTable21();
        }
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
                pst.close();

                warningMsg("حذف","تم الحذف بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");

            }
            idDelete=0;
            addToTable();
        }
    }
    public void deleteRow1(ActionEvent actionEvent) {
        int index= abstractTableView1.getSelectionModel().getSelectedIndex();
        int idDelete=abstractTableView1.getItems().get(index).getIdAbstract();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `abstract` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                pst.close();

                warningMsg("حذف","تم الحذف بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");

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

            abstractTableView.setItems(abstractsTable);
        }else{
            abstractsTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `abstract`,`areas`,`locations`,`projects` WHERE abstract.idArea=areas.id AND abstract.idLocation=locations.id AND abstract.idProject=projects.id AND projects.projectType='مشروع قطاع صحي' AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    abstractsTable.add(new AbstractForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate")));

                }
                pst.close();


                abstractTableView.setItems(abstractsTable);


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

            abstractTableView1.setItems(abstractsTable1);
        }else{
            abstractsTable1.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `abstract`,`areas`,`locations`,`projects` WHERE abstract.idArea=areas.id AND abstract.idLocation=locations.id AND abstract.idProject=projects.id AND projects.projectType='مشروع قطاع عسكري' AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    abstractsTable1.add(new AbstractForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate")));

                }
                pst.close();


                abstractTableView1.setItems(abstractsTable1);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

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
    void idReset(MouseEvent event) {
        abstractEditPrivilege.setText("تعديل مستخلص");
        ObservableList<String> years= FXCollections.observableArrayList();

        fillTableAbstractYears();
        addToTable2();
        yearAbstract.setItems(null);
        years.clear();
        int index= abstractTableView.getSelectionModel().getSelectedIndex();
        String beginDate=abstractTableView.getItems().get(index).getContractStartDate();
        beginDate=beginDate.substring(0,4);
        String endDate=abstractTableView.getItems().get(index).getContractEndDate();
        endDate=endDate.substring(0,4);
        int begin=Integer.parseInt(beginDate);
        int end=Integer.parseInt(endDate);

        for (int i =begin;i<=end;i++){
            years.add(String.valueOf(i));
        }
        yearAbstract.setItems(years);
    }
    @FXML
    void idReset1(MouseEvent event) {
        abstractEditPrivilege1.setText("تعديل مستخلص");
        ObservableList<String> years1= FXCollections.observableArrayList();

        fillTableAbstractYears1();
        addToTable21();
        yearAbstract1.setItems(null);
        years1.clear();
        int index= abstractTableView1.getSelectionModel().getSelectedIndex();
        String beginDate=abstractTableView1.getItems().get(index).getContractStartDate();
        beginDate=beginDate.substring(0,4);
        String endDate=abstractTableView1.getItems().get(index).getContractEndDate();
        endDate=endDate.substring(0,4);
        int begin=Integer.parseInt(beginDate);
        int end=Integer.parseInt(endDate);

        for (int i =begin;i<=end;i++){
            years1.add(String.valueOf(i));
        }
        yearAbstract1.setItems(years1);
    }



    @FXML
    private ComboBox<String> yearAbstract;
    @FXML
    private ComboBox<String> yearAbstract1;
    public void addAbstractYears(ActionEvent actionEvent) {
        int index= abstractTableView.getSelectionModel().getSelectedIndex();
        int idAbstract=abstractTableView.getItems().get(index).getIdAbstract();

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
        dejaExist=0;

        size=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstractyears` WHERE `year`=? AND `idAbstract`=?");
            pst.setString(1,yearAbstract.getValue());
            pst.setInt(2,abstractTableView.getItems().get(index).getIdAbstract());
            rs=pst.executeQuery();
            while(rs.next()){
                size++;
            }
            pst.close();

            if (size>0){
                dejaExist=1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if ((!janvier.isSelected()&&!fevrier.isSelected()&&!mars.isSelected()&&!avril.isSelected()&&!may.isSelected()&&!juin.isSelected()&&!juilliet.isSelected()&&!aout.isSelected()&&!septembre.isSelected()&&!octobre.isSelected()&&!novembre.isSelected()&&!decembre.isSelected()) || yearAbstract.getSelectionModel().isEmpty() ){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","السنة موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `abstractyears`(`idAbstract`, `year`, `jan`, `feb`, `mar`, `apr`, `may`, `jun`, `jul`, `aug`, `sep`, `oct`, `nov`, `dcm`,`remark`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setInt(1,idAbstract);
                pst.setString(2,yearAbstract.getValue());
                pst.setString(3,month1);
                pst.setString(4,month2);
                pst.setString(5,month3);
                pst.setString(6,month4);
                pst.setString(7,month5);
                pst.setString(8,month6);
                pst.setString(9,month7);
                pst.setString(10,month8);
                pst.setString(11,month9);
                pst.setString(12,month10);
                pst.setString(13,month11);
                pst.setString(14,month12);
                pst.setString(15,remark.getText());
                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }

            addToTable2();
            janvier.setSelected(false);
            fevrier.setSelected(false);
            mars.setSelected(false);
            avril.setSelected(false);
            may.setSelected(false);
            juin.setSelected(false);
            juilliet.setSelected(false);
            aout.setSelected(false);
            septembre.setSelected(false);
            octobre.setSelected(false);
            novembre.setSelected(false);
            decembre.setSelected(false);
            janvier.setDisable(false);
            fevrier.setDisable(false);
            mars.setDisable(false);
            avril.setDisable(false);
            may.setDisable(false);
            juin.setDisable(false);
            juilliet.setDisable(false);
            aout.setDisable(false);
            septembre.setDisable(false);
            octobre.setDisable(false);
            novembre.setDisable(false);
            decembre.setDisable(false);
            remark.clear();

        }


    }
    public void addAbstractYears1(ActionEvent actionEvent) {
        int index= abstractTableView1.getSelectionModel().getSelectedIndex();
        int idAbstract=abstractTableView1.getItems().get(index).getIdAbstract();

        String month1="",month2="",month3="",month4="",month5="",month6="",month7="",month8="",month9="",month10="",month11="",month12="";
        if (janvier1.isSelected()){
            month1="X";
        }if (fevrier1.isSelected()){
            month2="X";
        }if (mars1.isSelected()){
            month3="X";
        }if (avril1.isSelected()){
            month4="X";
        }if (may1.isSelected()){
            month5="X";
        }if (juin1.isSelected()){
            month6="X";
        }if (juilliet1.isSelected()){
            month7="X";
        }if (aout1.isSelected()){
            month8="X";
        }if (septembre1.isSelected()){
            month9="X";
        }if (octobre1.isSelected()){
            month10="X";
        }if (novembre1.isSelected()){
            month11="X";
        }if (decembre1.isSelected()){
            month12="X";
        }
        dejaExist=0;

        size=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstractyears` WHERE `year`=? AND `idAbstract`=?");
            pst.setString(1,yearAbstract1.getValue());
            pst.setInt(2,abstractTableView1.getItems().get(index).getIdAbstract());
            rs=pst.executeQuery();
            while(rs.next()){
                size++;
            }
            pst.close();

            if (size>0){
                dejaExist=1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if ((!janvier1.isSelected()&&!fevrier1.isSelected()&&!mars1.isSelected()&&!avril1.isSelected()&&!may1.isSelected()&&!juin1.isSelected()&&!juilliet1.isSelected()&&!aout1.isSelected()&&!septembre1.isSelected()&&!octobre1.isSelected()&&!novembre1.isSelected()&&!decembre1.isSelected()) || yearAbstract1.getSelectionModel().isEmpty() ){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","السنة موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `abstractyears`(`idAbstract`, `year`, `jan`, `feb`, `mar`, `apr`, `may`, `jun`, `jul`, `aug`, `sep`, `oct`, `nov`, `dcm`,`remark`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setInt(1,idAbstract);
                pst.setString(2,yearAbstract1.getValue());
                pst.setString(3,month1);
                pst.setString(4,month2);
                pst.setString(5,month3);
                pst.setString(6,month4);
                pst.setString(7,month5);
                pst.setString(8,month6);
                pst.setString(9,month7);
                pst.setString(10,month8);
                pst.setString(11,month9);
                pst.setString(12,month10);
                pst.setString(13,month11);
                pst.setString(14,month12);
                pst.setString(15,remark1.getText());
                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }

            addToTable21();
            janvier1.setSelected(false);
            fevrier1.setSelected(false);
            mars1.setSelected(false);
            avril1.setSelected(false);
            may1.setSelected(false);
            juin1.setSelected(false);
            juilliet1.setSelected(false);
            aout1.setSelected(false);
            septembre1.setSelected(false);
            octobre1.setSelected(false);
            novembre1.setSelected(false);
            decembre1.setSelected(false);
            janvier1.setDisable(false);
            fevrier1.setDisable(false);
            mars1.setDisable(false);
            avril1.setDisable(false);
            may1.setDisable(false);
            juin1.setDisable(false);
            juilliet1.setDisable(false);
            aout1.setDisable(false);
            septembre1.setDisable(false);
            octobre1.setDisable(false);
            novembre1.setDisable(false);
            decembre1.setDisable(false);
            remark1.clear();

        }


    }
    public void warningMsg(String title,String message ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }


    public void addToTable2(){
        abstractYearsTable.clear();
        int index= abstractTableView.getSelectionModel().getSelectedIndex();
        int idAbstract=abstractTableView.getItems().get(index).getIdAbstract();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstractyears` WHERE idAbstract=?");
            pst.setInt(1,idAbstract);
            rs=pst.executeQuery();
            while (rs.next()){
                abstractYearsTable.add(new AbstractForTable2(rs.getInt("id"),rs.getInt("idAbstract"),rs.getString("year"),rs.getString("jan"),rs.getString("feb"),rs.getString("mar"),rs.getString("apr"),rs.getString("may"),rs.getString("jun"),rs.getString("jul"),rs.getString("aug"),rs.getString("sep"),rs.getString("nov"),rs.getString("oct"),rs.getString("dcm"),rs.getString("remark")));

            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void addToTable21(){
        abstractYearsTable1.clear();
        int index= abstractTableView1.getSelectionModel().getSelectedIndex();
        int idAbstract=abstractTableView1.getItems().get(index).getIdAbstract();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstractyears` WHERE idAbstract=?");
            pst.setInt(1,idAbstract);
            rs=pst.executeQuery();
            while (rs.next()){
                abstractYearsTable1.add(new AbstractForTable2(rs.getInt("id"),rs.getInt("idAbstract"),rs.getString("year"),rs.getString("jan"),rs.getString("feb"),rs.getString("mar"),rs.getString("apr"),rs.getString("may"),rs.getString("jun"),rs.getString("jul"),rs.getString("aug"),rs.getString("sep"),rs.getString("nov"),rs.getString("oct"),rs.getString("dcm"),rs.getString("remark")));

            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    private Button edit;
    public void edit(ActionEvent actionEvent) {
        int index= abstractTableView.getSelectionModel().getSelectedIndex();
        int idEdit=abstractTableView.getItems().get(index).getIdAbstract();


        if (abstractEditPrivilege.getText().contains("تعديل مستخلص")){
            abstractEditPrivilege.setText("حفظ");
            areaName.setValue(abstractTableView.getItems().get(index).getNameArea());
            locationName.setValue(abstractTableView.getItems().get(index).getNameLocation());
            projectName.setValue(abstractTableView.getItems().get(index).getNameProject());
            contractType.setText(abstractTableView.getItems().get(index).getContractType());
            contractNumber.setText(abstractTableView.getItems().get(index).getContractNumber());
            contractStartDate.setValue(LocalDate.parse(abstractTableView.getItems().get(index).getContractStartDate()));
            contractEndDate.setValue(LocalDate.parse(abstractTableView.getItems().get(index).getContractEndDate()));
            contractStartDate.getEditor().setText(abstractTableView.getItems().get(index).getContractStartDate());
            contractEndDate.getEditor().setText(abstractTableView.getItems().get(index).getContractEndDate());
        }else if (abstractEditPrivilege.getText().contains("حفظ")){
            dejaExist=0;
            size=0;
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `abstract` WHERE `idArea`=? AND `idLocation`=? AND `idProject`=? AND id!=?");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setInt(3,idProject);
                pst.setInt(4,idEdit);
                rs=pst.executeQuery();
                while(rs.next()){
                    size++;
                }
                pst.close();

                if (size>0){
                    dejaExist=1;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(dejaExist==1){
                warningMsg("تنبيه","المعلومات موجودة من قبل");
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

                    for (int i=0; i<projects.size() ;i++){
                        if (projects.get(i).getContractName()==projectName.getValue()){
                            idProject=projects.get(i).getIdProject();
                        }
                    }

                    con = new ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `abstract` SET `idArea`=?,`idLocation`=?,`idProject`=? WHERE `id`=?");

                    pst.setInt(1, idArea);
                    pst.setInt(2, idLocation);
                    pst.setInt(3, idProject);
                    pst.setInt(4, idEdit);
                    pst.execute();
                    pst.close();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    abstractEditPrivilege.setText("تعديل مستخلص");
                    projectName.getItems().clear();
                    locationName.getItems().clear();
                    areaName.getItems().clear();
                    areaName.setValue("");
                    fillComboArea();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
            }

            addToTable();
            idEdit=0;
        }


    }
    @FXML
    private Button abstractEditPrivilege1;
    public void edit1(ActionEvent actionEvent) {
        int index= abstractTableView1.getSelectionModel().getSelectedIndex();
        int idEdit=abstractTableView1.getItems().get(index).getIdAbstract();


        if (abstractEditPrivilege1.getText().contains("تعديل مستخلص")){
            abstractEditPrivilege1.setText("حفظ");
            areaName1.setValue(abstractTableView1.getItems().get(index).getNameArea());
            locationName1.setValue(abstractTableView1.getItems().get(index).getNameLocation());
            projectName1.setValue(abstractTableView1.getItems().get(index).getNameProject());
            contractType1.setText(abstractTableView1.getItems().get(index).getContractType());
            contractNumber1.setText(abstractTableView1.getItems().get(index).getContractNumber());
            contractStartDate1.setValue(LocalDate.parse(abstractTableView1.getItems().get(index).getContractStartDate()));
            contractEndDate1.setValue(LocalDate.parse(abstractTableView1.getItems().get(index).getContractEndDate()));
            contractStartDate1.getEditor().setText(abstractTableView1.getItems().get(index).getContractStartDate());
            contractEndDate1.getEditor().setText(abstractTableView1.getItems().get(index).getContractEndDate());
        }else if (abstractEditPrivilege1.getText().contains("حفظ")){
            dejaExist=0;
            size=0;
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `abstract` WHERE `idArea`=? AND `idLocation`=? AND `idProject`=? AND id!=?");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setInt(3,idProject);
                pst.setInt(4,idEdit);
                rs=pst.executeQuery();
                while(rs.next()){
                    size++;
                }
                pst.close();

                if (size>0){
                    dejaExist=1;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (areaName1.getSelectionModel().isEmpty()||locationName1.getSelectionModel().isEmpty()||projectName1.getSelectionModel().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(dejaExist==1){
                warningMsg("تنبيه","المعلومات موجودة من قبل");
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

                    for (int i=0; i<projects.size() ;i++){
                        if (projects.get(i).getContractName()==projectName.getValue()){
                            idProject=projects.get(i).getIdProject();
                        }
                    }

                    con = new ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `abstract` SET `idArea`=?,`idLocation`=?,`idProject`=? WHERE `id`=?");

                    pst.setInt(1, idArea);
                    pst.setInt(2, idLocation);
                    pst.setInt(3, idProject);
                    pst.setInt(4, idEdit);
                    pst.execute();
                    pst.close();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    abstractEditPrivilege1.setText("تعديل مستخلص");
                    projectName1.getItems().clear();
                    locationName1.getItems().clear();
                    areaName1.getItems().clear();
                    fillComboArea1();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
            }

            addToTable1();
            idEdit=0;
        }


    }
    @FXML
    private Button abstractEditPrivilege2;
    public void edit2(ActionEvent actionEvent) {
        int index= yearAbstractTableView.getSelectionModel().getSelectedIndex();
        int idEdit=yearAbstractTableView.getItems().get(index).getIdAbstractYears();


        if (abstractEditPrivilege2.getText().contains("تعديل")){
            abstractEditPrivilege2.setText("حفظ");
            remark.setText(yearAbstractTableView.getItems().get(index).getRemark());

            if (!yearAbstractTableView.getItems().get(index).getJanvier().isEmpty()){
                janvier.setSelected(true);
            }else{
                janvier.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getFevrier().isEmpty()){
                fevrier.setSelected(true);
            }else{
                fevrier.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getMars().isEmpty()){
                mars.setSelected(true);
            }else{
                mars.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getAvril().isEmpty()){
                avril.setSelected(true);
            }else{
                avril.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getMay().isEmpty()){
                may.setSelected(true);
            }else{
                may.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getJuin().isEmpty()){
                juin.setSelected(true);
            }else{
                juin.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getJuilliet().isEmpty()){
                juilliet.setSelected(true);
            }else{
                juilliet.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getAout().isEmpty()){
                aout.setSelected(true);
            }else{
                aout.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getSeptembre().isEmpty()){
                septembre.setSelected(true);
            }else{
                septembre.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getOctobre().isEmpty()){
                octobre.setSelected(true);
            }else{
                octobre.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getNovembre().isEmpty()){
                novembre.setSelected(true);
            }else{
                novembre.setSelected(false);

            }
            if (!yearAbstractTableView.getItems().get(index).getDecembre().isEmpty()){
                decembre.setSelected(true);
            }else{
                decembre.setSelected(false);

            }
            yearAbstract.setValue(yearAbstractTableView.getItems().get(index).getYear());

        }else if (abstractEditPrivilege2.getText().contains("حفظ")){
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
            dejaExist=0;
            size=0;
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `abstractyears` WHERE `year`=? AND `idAbstract`=? AND `id`!=?");
                pst.setString(1,yearAbstract.getValue());
                pst.setInt(2,yearAbstractTableView.getItems().get(index).getIdAbstract());
                pst.setInt(3,idEdit);
                rs=pst.executeQuery();
                while(rs.next()){
                    size++;
                }
                pst.close();

                if (size>0){
                    dejaExist=1;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if ((!janvier.isSelected()&&!fevrier.isSelected()&&!mars.isSelected()&&!avril.isSelected()&&!may.isSelected()&&!juin.isSelected()&&!juilliet.isSelected()&&!aout.isSelected()&&!septembre.isSelected()&&!octobre.isSelected()&&!novembre.isSelected()&&!decembre.isSelected()) || yearAbstract.getSelectionModel().isEmpty() ){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(dejaExist==1){
                warningMsg("تنبيه","السنة موجودة من قبل");
            }else{
                try {
                    con = new ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `abstractyears` SET `year`=? ,`jan`=?,`feb`=?,`mar`=?,`apr`=?,`may`=?,`jun`=?,`jul`=?,`aug`=?,`sep`=?,`oct`=?,`nov`=?,`dcm`=?,`remark`=? WHERE `id`=?");
                    pst.setString(1,yearAbstract.getValue());
                    pst.setString(2,month1);
                    pst.setString(3,month2);
                    pst.setString(4,month3);
                    pst.setString(5,month4);
                    pst.setString(6,month5);
                    pst.setString(7,month6);
                    pst.setString(8,month7);
                    pst.setString(9,month8);
                    pst.setString(10,month9);
                    pst.setString(11,month10);
                    pst.setString(12,month11);
                    pst.setString(13,month12);
                    pst.setString(14,remark.getText());
                    pst.setInt(15, idEdit);
                    pst.execute();
                    pst.close();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    abstractEditPrivilege2.setText("تعديل");

                    addToTable2();
                    janvier.setSelected(false);
                    fevrier.setSelected(false);
                    mars.setSelected(false);
                    avril.setSelected(false);
                    may.setSelected(false);
                    juin.setSelected(false);
                    juilliet.setSelected(false);
                    aout.setSelected(false);
                    septembre.setSelected(false);
                    octobre.setSelected(false);
                    novembre.setSelected(false);
                    decembre.setSelected(false);
                    janvier.setDisable(false);
                    fevrier.setDisable(false);
                    mars.setDisable(false);
                    avril.setDisable(false);
                    may.setDisable(false);
                    juin.setDisable(false);
                    juilliet.setDisable(false);
                    aout.setDisable(false);
                    septembre.setDisable(false);
                    octobre.setDisable(false);
                    novembre.setDisable(false);
                    decembre.setDisable(false);
                    remark.clear();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
            }

            idEdit=0;
        }


    }

    @FXML
    private Button abstractEditPrivilege21;
    public void edit21(ActionEvent actionEvent) {
        int index= yearAbstractTableView1.getSelectionModel().getSelectedIndex();
        int idEdit=yearAbstractTableView1.getItems().get(index).getIdAbstractYears();


        if (abstractEditPrivilege21.getText().contains("تعديل")){
            abstractEditPrivilege21.setText("حفظ");
            remark1.setText(yearAbstractTableView1.getItems().get(index).getRemark());

            if (!yearAbstractTableView1.getItems().get(index).getJanvier().isEmpty()){
                janvier1.setSelected(true);
            }else{
                janvier1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getFevrier().isEmpty()){
                fevrier1.setSelected(true);
            }else{
                fevrier1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getMars().isEmpty()){
                mars1.setSelected(true);
            }else{
                mars1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getAvril().isEmpty()){
                avril1.setSelected(true);
            }else{
                avril1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getMay().isEmpty()){
                may1.setSelected(true);
            }else{
                may1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getJuin().isEmpty()){
                juin1.setSelected(true);
            }else{
                juin1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getJuilliet().isEmpty()){
                juilliet1.setSelected(true);
            }else{
                juilliet1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getAout().isEmpty()){
                aout1.setSelected(true);
            }else{
                aout1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getSeptembre().isEmpty()){
                septembre1.setSelected(true);
            }else{
                septembre1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getOctobre().isEmpty()){
                octobre1.setSelected(true);
            }else{
                octobre1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getNovembre().isEmpty()){
                novembre1.setSelected(true);
            }else{
                novembre1.setSelected(false);

            }
            if (!yearAbstractTableView1.getItems().get(index).getDecembre().isEmpty()){
                decembre1.setSelected(true);
            }else{
                decembre1.setSelected(false);

            }
            yearAbstract1.setValue(yearAbstractTableView1.getItems().get(index).getYear());

        }else if (abstractEditPrivilege21.getText().contains("حفظ")){
            String month1="",month2="",month3="",month4="",month5="",month6="",month7="",month8="",month9="",month10="",month11="",month12="";
            if (janvier1.isSelected()){
                month1="X";
            }if (fevrier1.isSelected()){
                month2="X";
            }if (mars1.isSelected()){
                month3="X";
            }if (avril1.isSelected()){
                month4="X";
            }if (may1.isSelected()){
                month5="X";
            }if (juin1.isSelected()){
                month6="X";
            }if (juilliet1.isSelected()){
                month7="X";
            }if (aout1.isSelected()){
                month8="X";
            }if (septembre1.isSelected()){
                month9="X";
            }if (octobre1.isSelected()){
                month10="X";
            }if (novembre1.isSelected()){
                month11="X";
            }if (decembre1.isSelected()){
                month12="X";
            }
            dejaExist=0;
            size=0;
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `abstractyears` WHERE `year`=? AND `idAbstract`=? AND `id`!=?");
                pst.setString(1,yearAbstract1.getValue());
                pst.setInt(2,yearAbstractTableView1.getItems().get(index).getIdAbstract());
                pst.setInt(3,idEdit);
                rs=pst.executeQuery();
                while(rs.next()){
                    size++;
                }
                pst.close();

                if (size>0){
                    dejaExist=1;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if ((!janvier1.isSelected()&&!fevrier1.isSelected()&&!mars1.isSelected()&&!avril1.isSelected()&&!may1.isSelected()&&!juin1.isSelected()&&!juilliet1.isSelected()&&!aout1.isSelected()&&!septembre1.isSelected()&&!octobre1.isSelected()&&!novembre1.isSelected()&&!decembre1.isSelected()) || yearAbstract1.getSelectionModel().isEmpty() ){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(dejaExist==1){
                warningMsg("تنبيه","السنة موجودة من قبل");
            }else{
                try {
                    con = new ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `abstractyears` SET `year`=? ,`jan`=?,`feb`=?,`mar`=?,`apr`=?,`may`=?,`jun`=?,`jul`=?,`aug`=?,`sep`=?,`oct`=?,`nov`=?,`dcm`=?,`remark`=? WHERE `id`=?");
                    pst.setString(1,yearAbstract1.getValue());
                    pst.setString(2,month1);
                    pst.setString(3,month2);
                    pst.setString(4,month3);
                    pst.setString(5,month4);
                    pst.setString(6,month5);
                    pst.setString(7,month6);
                    pst.setString(8,month7);
                    pst.setString(9,month8);
                    pst.setString(10,month9);
                    pst.setString(11,month10);
                    pst.setString(12,month11);
                    pst.setString(13,month12);
                    pst.setString(14,remark1.getText());
                    pst.setInt(15, idEdit);
                    pst.execute();
                    pst.close();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    abstractEditPrivilege21.setText("تعديل");

                    addToTable21();
                    janvier1.setSelected(false);
                    fevrier1.setSelected(false);
                    mars1.setSelected(false);
                    avril1.setSelected(false);
                    may1.setSelected(false);
                    juin1.setSelected(false);
                    juilliet1.setSelected(false);
                    aout1.setSelected(false);
                    septembre1.setSelected(false);
                    octobre1.setSelected(false);
                    novembre1.setSelected(false);
                    decembre1.setSelected(false);
                    janvier1.setDisable(false);
                    fevrier1.setDisable(false);
                    mars1.setDisable(false);
                    avril1.setDisable(false);
                    may1.setDisable(false);
                    juin1.setDisable(false);
                    juilliet1.setDisable(false);
                    aout1.setDisable(false);
                    septembre1.setDisable(false);
                    octobre1.setDisable(false);
                    novembre1.setDisable(false);
                    decembre1.setDisable(false);
                    remark1.clear();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
            }

            idEdit=0;
        }


    }


    @FXML
    private CheckBox janvier1;

    @FXML
    private CheckBox fevrier1;

    @FXML
    private CheckBox mars1;

    @FXML
    private CheckBox avril1;

    @FXML
    private CheckBox may1;

    @FXML
    private CheckBox juin1;

    @FXML
    private CheckBox juilliet1;

    @FXML
    private CheckBox aout1;

    @FXML
    private CheckBox septembre1;

    @FXML
    private CheckBox octobre1;

    @FXML
    private CheckBox novembre1;

    @FXML
    private CheckBox decembre1;

    @FXML
    private TextArea remark1;
    @FXML
    public void addAbstract1(ActionEvent actionEvent) {
        String month1="",month2="",month3="",month4="",month5="",month6="",month7="",month8="",month9="",month10="",month11="",month12="";
        if (janvier1.isSelected()){
            month1="X";
        }if (fevrier1.isSelected()){
            month2="X";
        }if (mars1.isSelected()){
            month3="X";
        }if (avril1.isSelected()){
            month4="X";
        }if (may1.isSelected()){
            month5="X";
        }if (juin1.isSelected()){
            month6="X";
        }if (juilliet1.isSelected()){
            month7="X";
        }if (aout1.isSelected()){
            month8="X";
        }if (septembre1.isSelected()){
            month9="X";
        }if (octobre1.isSelected()){
            month10="X";
        }if (novembre1.isSelected()){
            month11="X";
        }if (decembre1.isSelected()){
            month12="X";
        }
        dejaExist=0;
        size=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstract` WHERE `idArea`=? AND `idLocation`=? AND `idProject`=?");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setInt(3,idProject);
            rs=pst.executeQuery();
            while(rs.next()){
                size++;
            }
            pst.close();

            if (size>0){
                dejaExist=1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (areaName1.getSelectionModel().isEmpty()||locationName1.getSelectionModel().isEmpty()||projectName1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `abstract`(`idArea`, `idLocation`, `idProject`) VALUES (?,?,?)");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setInt(3,idProject);
                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }

            addToTable1();
            areaName1.getItems().clear();
            areas.clear();
            locationName1.getItems().clear();
            projectName1.getItems().clear();
            fillComboArea1();
        }



    }

    public void idReset21(MouseEvent mouseEvent) {
        abstractEditPrivilege21.setText("تعديل");
        janvier1.setSelected(false);
        fevrier1.setSelected(false);
        mars1.setSelected(false);
        avril1.setSelected(false);
        may1.setSelected(false);
        juin1.setSelected(false);
        juilliet1.setSelected(false);
        aout1.setSelected(false);
        septembre1.setSelected(false);
        octobre1.setSelected(false);
        novembre1.setSelected(false);
        decembre1.setSelected(false);
        janvier1.setDisable(false);
        fevrier1.setDisable(false);
        mars1.setDisable(false);
        avril1.setDisable(false);
        may1.setDisable(false);
        juin1.setDisable(false);
        juilliet1.setDisable(false);
        aout1.setDisable(false);
        septembre1.setDisable(false);
        octobre1.setDisable(false);
        novembre1.setDisable(false);
        decembre1.setDisable(false);
        remark1.clear();
    }

    public void idReset2(MouseEvent mouseEvent) {
        abstractEditPrivilege2.setText("تعديل");
        janvier.setSelected(false);
        fevrier.setSelected(false);
        mars.setSelected(false);
        avril.setSelected(false);
        may.setSelected(false);
        juin.setSelected(false);
        juilliet.setSelected(false);
        aout.setSelected(false);
        septembre.setSelected(false);
        octobre.setSelected(false);
        novembre.setSelected(false);
        decembre.setSelected(false);
        janvier.setDisable(false);
        fevrier.setDisable(false);
        mars.setDisable(false);
        avril.setDisable(false);
        may.setDisable(false);
        juin.setDisable(false);
        juilliet.setDisable(false);
        aout.setDisable(false);
        septembre.setDisable(false);
        octobre.setDisable(false);
        novembre.setDisable(false);
        decembre.setDisable(false);
        remark.clear();
    }
}
