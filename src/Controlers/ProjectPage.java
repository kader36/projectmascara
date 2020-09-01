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

public class ProjectPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<EmployeeForList> employees= FXCollections.observableArrayList();
    ObservableList<Occupation> occupations= FXCollections.observableArrayList();
    ObservableList<ProjectOcupation> projectOccupation= FXCollections.observableArrayList();
    ObservableList<Masrouf> masroufats= FXCollections.observableArrayList();
    ObservableList<Masrouf2> masroufats2= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    int idMasroufat=0,idArea=0,idLocation=0,idArea2=0,idLocation2=0,idProject=0,idOccupation=0,idEmployee=0,idProjectOccupation=0;


    @FXML
    private TextField projectName;
    @FXML
    private TextField masroufPrice;

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> occupationName;

    @FXML
    private ComboBox<String> locationName;

    @FXML
    private ComboBox<String> masroufatNameCombo;


    @FXML
    private TextField contactDuration;
    @FXML
    private TextField contactNumber;

    @FXML
    private TextField contractPrice;

    @FXML
    private DatePicker contractStartDate;

    @FXML
    private DatePicker contractEndDate;

    @FXML
    private TextField maxNumber;

    public void fillComboArea(){
        areas.clear();
        areaName.getItems().clear();
        areaNameEmployee.getItems().clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `areas`");
            rs=pst.executeQuery();
            while (rs.next()){
                areas.add(new Area(rs.getInt("id"),rs.getString("areaName")));

            }
            con.close();

            for (int i=0;i<areas.size();i++){
                areaName.getItems().add(areas.get(i).getNameArea());
                areaNameEmployee.getItems().add(areas.get(i).getNameArea());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void fillComboArea2(){
        areas.clear();
        areaName1.getItems().clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `areas`");
            rs=pst.executeQuery();
            while (rs.next()){
                areas.add(new Area(rs.getInt("id"),rs.getString("areaName")));

            }
            con.close();

            for (int i=0;i<areas.size();i++){
                areaName1.getItems().add(areas.get(i).getNameArea());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void fillComboMasroufat(){
        masroufats.clear();
        masroufatNameCombo.getItems().clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `masroufat`");
            rs=pst.executeQuery();
            while (rs.next()){
                masroufats.add(new Masrouf(rs.getInt("id"),rs.getString("masroufName")));

            }
            con.close();

            for (int i=0;i<masroufats.size();i++){
                masroufatNameCombo.getItems().add(masroufats.get(i).getMasroufName());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void fillComboEmployee(){
        employeeNameEmployee.getItems().clear();
        employees.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees` WHERE `isBusy`='0' AND `reelOccupation`=?");
            pst.setInt(1,idOccupation);
            rs=pst.executeQuery();
            while (rs.next()){
                employees.add(new EmployeeForList(rs.getInt("id"),rs.getString("employeeName")));

            }
            con.close();

            for (int i=0;i<employees.size();i++){
                employeeNameEmployee.getItems().add(employees.get(i).getEmployeeName());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void fillComboOccupation(){
        occupations.clear();
        occupationName.getItems().clear();

        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `occupations`");
            rs=pst.executeQuery();
            while (rs.next()){
                occupations.add(new Occupation(rs.getInt("id"),rs.getString("occupationName")));

            }
            con.close();

            for (int i=0;i<occupations.size();i++){
                occupationName.getItems().add(occupations.get(i).getNameOcupation());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void fillComboOccupationEmployee(){
        projectOccupation.clear();
        occupationNameEmployee.getItems().clear();



        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations`,`occupations` WHERE projectoccupations.idOccupation=occupations.id AND `idProject`=?");
            pst.setInt(1,idProject);
            rs=pst.executeQuery();
            while (rs.next()){
                projectOccupation.add(new ProjectOcupation(rs.getInt("id"),rs.getInt("idProject"),rs.getInt("idOccupation"),rs.getInt("maxNumber"),rs.getInt("realNumber"),rs.getString("occupationName")));

            }
            con.close();

            for (int i=0;i<projectOccupation.size();i++){
                occupationNameEmployee.getItems().add(projectOccupation.get(i).getOccupationName());
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
            con.close();

            for (int i=0;i<locations.size();i++){
                locationName.getItems().add(locations.get(i).getLocationName());
            }

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void fillComboLocation2(){
        locations.clear();
        locationName1.getItems().clear();

        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `locations` WHERE `areaId`=?");
            pst.setInt(1,idArea2);
            rs=pst.executeQuery();
            while (rs.next()){
                locations.add(new Location(rs.getInt("areaId"),rs.getInt("id"),rs.getString("locationName")));

            }
            con.close();

            for (int i=0;i<locations.size();i++){
                locationName1.getItems().add(locations.get(i).getLocationName());
            }

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void fillComboLocationEmployee(){
        locations.clear();
        locationNameEmployee.getItems().clear();
        projects.clear();
        projectNameEmployee.getItems().clear();
        projectOccupation.clear();
        occupationNameEmployee.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `locations` WHERE `areaId`=?");
            pst.setInt(1,idArea);
            rs=pst.executeQuery();
            while (rs.next()){
                locations.add(new Location(rs.getInt("areaId"),rs.getInt("id"),rs.getString("locationName")));

            }
            con.close();

            for (int i=0;i<locations.size();i++){
                locationNameEmployee.getItems().add(locations.get(i).getLocationName());
            }

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void fillComboProjectEmployee(){
        projects.clear();
        projectNameEmployee.getItems().clear();
        projectOccupation.clear();
        occupationNameEmployee.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=? AND `projectType`='مشروع قطاع صحي'");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            rs=pst.executeQuery();
            while (rs.next()){
                projects.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));

            }
            con.close();

            for (int i=0;i<projects.size();i++){
                projectNameEmployee.getItems().add(projects.get(i).getContractName());
            }

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void fillProject(){
        projects.clear();
        projectNameEmployee.getItems().clear();
        projectOccupation.clear();
        occupationNameEmployee.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects`");
            rs=pst.executeQuery();
            while (rs.next()){
                projects.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));

            }
            con.close();


        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void fillTableProjectOccupation(){
        projectOccupation.clear();
        projectOccupationTableView.getItems().clear();
        int index= projectTableView.getSelectionModel().getSelectedIndex();
        idProject=projectTableView.getItems().get(index).getProjectId();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations`,`occupations` WHERE  projectoccupations.idOccupation=occupations.id AND projectoccupations.idProject=?");
            pst.setInt(1,idProject);
            rs=pst.executeQuery();
            while (rs.next()){
                projectOccupation.add(new ProjectOcupation(rs.getInt("id"),rs.getInt("idProject"),rs.getInt("idOccupation"),rs.getInt("maxNumber"),rs.getInt("realNumber"),rs.getString("occupationName")));

            }
            con.close();


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
    void selectMasroufat(ActionEvent event) {
        int index= masroufatNameCombo.getSelectionModel().getSelectedIndex();
        idMasroufat=masroufats.get(index).getIdMasrouf();
    }

    @FXML
    void selectArea2(ActionEvent event) {
        int index= areaName1.getSelectionModel().getSelectedIndex();
        idArea2=areas.get(index).getIdArea();
        fillComboLocation2();
    }

    @FXML
    void selectAreaEmployee(ActionEvent event) {
        int index= areaNameEmployee.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        fillComboLocationEmployee();
    }
    @FXML
    void selectEmployeeEmployee(ActionEvent event) {
        int index= employeeNameEmployee.getSelectionModel().getSelectedIndex();
        idEmployee=areas.get(index).getIdArea();
    }
    @FXML
    void selectOccupation(ActionEvent event) {
        int index= occupationName.getSelectionModel().getSelectedIndex();
        idOccupation=occupations.get(index).getIdOcupation();
    }
    @FXML
    void selectOccupationEmployee(ActionEvent event) {
        int index= occupationNameEmployee.getSelectionModel().getSelectedIndex();
        idOccupation=projectOccupation.get(index).getIdOccupation();
        fillComboEmployee();

    }

    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();
    }

    @FXML
    void selectLocation2(ActionEvent event) {
        int index= locationName1.getSelectionModel().getSelectedIndex();
        idLocation2=locations.get(index).getIdLocation();

    }
    @FXML
    void selectLocationEmployee(ActionEvent event) {
        int index= locationNameEmployee.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();
        fillComboProjectEmployee();

    }

    @FXML
    public void selectProjectEmployee(ActionEvent actionEvent) {
        int index= projectNameEmployee.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();
        fillComboOccupationEmployee();

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
    private Button projectDeletePrivilege;
    @FXML
    private Button projectDeletePrivilege1;
    @FXML
    private Button projectDeletePrivilege2;
    @FXML
    private Button projectDeletePrivilege3;
    @FXML
    private Button projectDeletePrivilege4;
    @FXML
    private Button projectDeletePrivilege5;
    @FXML
    private Button projectAddPrivilege;
    @FXML
    private Button projectAddPrivilege1;
    @FXML
    private Button projectAddPrivilege2;
    @FXML
    private Button projectAddPrivilege3;
    @FXML
    private Button projectAddPrivilege4;
    @FXML
    private Button projectAddPrivilege5;
    @FXML
    private Button projectEditPrivilege;
    @FXML
    private Button projectEditPrivilege1;

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
                    projectAddPrivilege.setDisable(true);
                    projectAddPrivilege1.setDisable(true);
                    projectAddPrivilege2.setDisable(true);
                    projectAddPrivilege3.setDisable(true);
                    projectAddPrivilege4.setDisable(true);
                    projectAddPrivilege5.setDisable(true);

                }else{
                    projectMenuButton.setDisable(false);
                    projectAddPrivilege.setDisable(false);
                    projectAddPrivilege1.setDisable(false);
                    projectAddPrivilege2.setDisable(false);
                    projectAddPrivilege3.setDisable(false);
                    projectAddPrivilege4.setDisable(false);
                    projectAddPrivilege5.setDisable(false);

                }
                if (rs.getInt("prde")==0){
                    projectEditPrivilege.setDisable(true);
                    projectEditPrivilege1.setDisable(true);
                    projectDeletePrivilege.setDisable(true);
                    projectDeletePrivilege1.setDisable(true);
                    projectDeletePrivilege3.setDisable(true);
                    projectDeletePrivilege4.setDisable(true);
                    projectDeletePrivilege5.setDisable(true);

                }else{
                    projectEditPrivilege.setDisable(false);
                    projectEditPrivilege1.setDisable(false);
                    projectDeletePrivilege.setDisable(false);
                    projectDeletePrivilege1.setDisable(false);
                    projectDeletePrivilege3.setDisable(false);
                    projectDeletePrivilege4.setDisable(false);
                    projectDeletePrivilege5.setDisable(false);

                }
                if (rs.getInt("prsa1")==0){
                    editPosition.setDisable(true);
                }else{
                    editPosition.setDisable(false);
                }

                if (rs.getInt("prde1")==0){
                    projectDeletePrivilege2.setDisable(true);
                }else{
                    projectDeletePrivilege2.setDisable(false);
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

                }else{
                    employeeMenuButton.setDisable(false);

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
    public void addProject(ActionEvent actionEvent) {
        int dejaExist1=0;
        int size1=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `locationId`=? AND `contractName`=?");
            pst.setInt(1,idLocation);
            pst.setString(2,projectName.getText());
            rs=pst.executeQuery();
            while(rs.next()){
                size1++;
            }
            if (size1>0){
                dejaExist1=1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (projectName.getText().isEmpty()||contactDuration.getText().isEmpty()||contactNumber.getText().isEmpty()||contractPrice.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||contractStartDate.getEditor().getText().isEmpty()||contractEndDate.getEditor().getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist1==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `projects`(`areaId`, `locationId`, `projectType`, `contractName`, `contractPrice`," +
                        " `contactDuration`, `contractStartDate`, `contractEndDate`, `contractNumber`) VALUES (?,?,?,?,?,?,?,?,?)");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setString(3,"مشروع قطاع صحي");
                pst.setString(4,projectName.getText());
                pst.setFloat(5, Float.parseFloat(contractPrice.getText()));
                pst.setInt(6, Integer.parseInt(contactDuration.getText()));
                pst.setString(7, String.valueOf(contractStartDate.getValue()));
                pst.setString(8, String.valueOf(contractEndDate.getValue()));
                pst.setString(9, contactNumber.getText());
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");
                projectName.clear();
                contactNumber.clear();
                contractPrice.clear();
                contactDuration.clear();
                contractStartDate.getEditor().clear();
                contractEndDate.getEditor().clear();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable();

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
    int dejaExist=0;
    int size=0;
    @FXML
    void addProjectOccupation(ActionEvent event) {
        int index= projectTableView.getSelectionModel().getSelectedIndex();
        idProject=projectTableView.getItems().get(index).getProjectId();

        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations` WHERE `idOccupation`=? AND `idProject`=?");
            pst.setInt(1,idOccupation);
            pst.setInt(2,idProject);
            rs=pst.executeQuery();
            while(rs.next()){
                size++;
            }

            if (size>0){
                dejaExist=1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (maxNumber.getText().isEmpty()||occupationName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();

                pst=con.prepareStatement("INSERT INTO `projectoccupations`(`idProject`, `idOccupation`, `maxNumber`, `realNumber`) VALUES (?,?,?,?)");
                pst.setInt(1,idProject);
                pst.setInt(2,idOccupation);
                pst.setInt(3,Integer.parseInt(maxNumber.getText()));
                pst.setInt(4,0);
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            fillTableProjectOccupation();
        }
        dejaExist=0;
        size=0;


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
    private TableColumn<ProjectForTable, String> contactDurationTable;

    @FXML
    private TableColumn<ProjectForTable, String> contractPriceTable;

    @FXML
    private TableColumn<ProjectForTable, String> contractStartDateTable;

    @FXML
    private TableColumn<ProjectForTable, String> contractEndDateTable;






    @FXML
    private TableView<ProjectForTable> projectTableView1;

    @FXML
    private TableColumn<ProjectForTable, String> projectNameTable1;

    @FXML
    private TableColumn<ProjectForTable, String> areaNameTable1;

    @FXML
    private TableColumn<ProjectForTable, String> locationNameTable1;

    @FXML
    private TableColumn<ProjectForTable, String> contactDurationTable1;

    @FXML
    private TableColumn<ProjectForTable, String> contractPriceTable1;
    @FXML
    private TableColumn<ProjectForTable, String> contractRestTable1;

    @FXML
    private TableColumn<ProjectForTable, String> contractStartDateTable1;

    @FXML
    private TableColumn<ProjectForTable, String> contractEndDateTable1;

    ObservableList projectsTable= FXCollections.observableArrayList();
    ObservableList projectsTable2= FXCollections.observableArrayList();
    public void addToTableMasrouf(){
        masroufats.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `masroufat`");
            rs=pst.executeQuery();
            while (rs.next()){
                masroufats.add(new Masrouf(rs.getInt("id"),rs.getString("masroufName")));
            }
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void addToTableMasrouf2(){
        masroufats2.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectmasroufate`");
            rs=pst.executeQuery();
            while (rs.next()){
                masroufats2.add(new Masrouf2(rs.getInt("id"),rs.getInt("projectId"),rs.getString("masroufName"),rs.getFloat("masroufPrice")));
            }
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void addToTable(){
        projectsTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects`,`areas`,`locations` WHERE projects.areaId=areas.id AND projects.locationId=locations.id AND projects.projectType='مشروع قطاع صحي'");
            rs=pst.executeQuery();
            while (rs.next()){
                projectsTable.add(new ProjectForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("contractName"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("contractPrice"),rs.getString("contractPrice"),rs.getString("contractNumber")));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }









    @FXML
    private ListView<String> employeeNameEmployee;
    @FXML
    private TableView<projectEmployeeForTable> projectEmployeeTableView;
    @FXML
    private TableView<ProjectOcupation> projectOccupationTableView;
    @FXML
    private TableColumn<projectEmployeeForTable, String> employeeNameEmployeeTable;

    @FXML
    private TableColumn<projectEmployeeForTable, String> occupationNameEmployeeTable;

    @FXML
    private TableColumn<projectEmployeeForTable, String> areaNameEmployeeTable;

    @FXML
    private TableColumn<projectEmployeeForTable, String> locationNameEmployeeTable;

    @FXML
    private TableColumn<projectEmployeeForTable, String> projectNameEmployeeTable;
    @FXML
    private TableColumn<ProjectForTable, String> contractNumberTable;
    @FXML
    private TableColumn<ProjectForTable, String> contractNumberTable1;


    @FXML
    private ComboBox<String> areaNameEmployee;

    @FXML
    private ComboBox<String> locationNameEmployee;

    @FXML
    private ComboBox<String> projectNameEmployee;

    @FXML
    private ComboBox<String> occupationNameEmployee;
    @FXML
    private TableView<Masrouf2> masroufat2TableView;

    @FXML
    private TableColumn<Masrouf2, String> masroufatNameComboTable;

    @FXML
    private TableColumn<Masrouf2, String> masroufPriceTable;

    ObservableList projectEmployeesTable= FXCollections.observableArrayList();
    ObservableList projectOccupationsTable= FXCollections.observableArrayList();
    @FXML
    private Button locatione;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        fillComboArea2();
        fillProject();
        fillComboOccupation();
        fillComboMasroufat();

        addToTable();
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        contactDurationTable.setCellValueFactory(new PropertyValueFactory<>("contactDuration"));
        contractPriceTable.setCellValueFactory(new PropertyValueFactory<>("contractPrice"));
        contractStartDateTable.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
        contractEndDateTable.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
        contractNumberTable.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        projectTableView.setItems(projectsTable);
        addToTableMilitaire();
        areaNameTable1.setCellValueFactory(new PropertyValueFactory<>("areaName"));
        locationNameTable1.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        projectNameTable1.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        contactDurationTable1.setCellValueFactory(new PropertyValueFactory<>("contactDuration"));
        contractPriceTable1.setCellValueFactory(new PropertyValueFactory<>("contractPrice"));
        contractRestTable1.setCellValueFactory(new PropertyValueFactory<>("contractPriceRest"));
        contractStartDateTable1.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
        contractEndDateTable1.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
        contractNumberTable1.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        projectTableView1.setItems(projectsTable2);

        addToTable2();
        employeeNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        occupationNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("occupationName"));
        areaNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
        locationNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        projectNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        projectEmployeeTableView.setItems(projectEmployeesTable);
        addToTableMasrouf();
        masroufNameTable.setCellValueFactory(new PropertyValueFactory<>("masroufName"));
        masroufNameTableView.setItems(masroufats);

        masroufatNameComboTable.setCellValueFactory(new PropertyValueFactory<>("masroufName"));
        masroufPriceTable.setCellValueFactory(new PropertyValueFactory<>("masroufPrice"));
        masroufat2TableView.setItems(masroufats2);

    }

    public void addToTable2(){
        projectEmployeesTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectsemployees`,`areas`,`locations`,`projects`,`occupations`,`employees` WHERE projectsemployees.idArea=areas.id AND projectsemployees.idLocation=locations.id AND projectsemployees.idProject=projects.id AND projectsemployees.idOccupation=occupations.id AND projectsemployees.idEmployee=employees.id");
            rs=pst.executeQuery();
            while (rs.next()){
                projectEmployeesTable.add(new projectEmployeeForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idOccupation"),rs.getInt("idEmployee"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("occupationName"),rs.getString("employeeName")));

            }
            con.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    public void warningMsg(String title,String message ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    int resultMax=-1;int resultMin=-1;

    @FXML
    public void addEmployeeProject(ActionEvent actionEvent) {
        int index= employeeNameEmployee.getSelectionModel().getSelectedIndex();
        idEmployee=employees.get(index).getId();


        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations` WHERE `idProject`=? AND `idOccupation`=?");
            pst.setInt(1,idProject);
            pst.setInt(2,idOccupation);
            rs=pst.executeQuery();
            while(rs.next()){
                resultMax=rs.getInt("maxNumber");
                resultMin=rs.getInt("realNumber");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (areaNameEmployee.getSelectionModel().isEmpty()||locationNameEmployee.getSelectionModel().isEmpty()||projectNameEmployee.getSelectionModel().isEmpty()||occupationNameEmployee.getSelectionModel().isEmpty()||employeeNameEmployee.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(resultMax<=resultMin){
            warningMsg("تنبيه","وصلت للحد الأقصى للعمالة في هذه الوظيفة للمشروع المحدد");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `projectsemployees`(`idArea`, `idLocation`, `idProject`, `idOccupation`, `idEmployee`) VALUES (?,?,?,?,?)");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setInt(3,idProject);
                pst.setInt(4,idOccupation);
                pst.setInt(5,idEmployee);
                pst.execute();

                try {
                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projectoccupations` SET `realNumber`=`realNumber`+1 WHERE idProject=? AND idOccupation=?");
                    pst.setInt(1,idProject);
                    pst.setInt(2, idOccupation);
                    pst.execute();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `employees` SET `isBusy`=? WHERE `id`=?");
                    pst.setInt(1,1);
                    pst.setInt(2, idEmployee);
                    pst.execute();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                warningMsg("إظافة","تمت الإظافة بنجاح");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }

            addToTable2();
            fillComboEmployee();
        }

        resultMax=-1;
        resultMin=-1;
        dejaExist=0;
        size=0;

    }


    @FXML
    private TableColumn<ProjectOcupation, String> occupationNameTable;

    @FXML
    private TableColumn<ProjectOcupation, Integer> maxNumberTable;

    @FXML
    private TableColumn<ProjectOcupation, Integer> realNumberTable;
    @FXML
    public void getSelectItemTable(MouseEvent mouseEvent) {
        projectEditPrivilege.setText("تعديل مشروع");
        int index= projectTableView.getSelectionModel().getSelectedIndex();
        idProject=projectTableView.getItems().get(index).getProjectId();
        System.out.println(idProject);
        fillTableProjectOccupation();

        occupationNameTable.setCellValueFactory(new PropertyValueFactory<>("occupationName"));
        maxNumberTable.setCellValueFactory(new PropertyValueFactory<>("maxNumber"));
        realNumberTable.setCellValueFactory(new PropertyValueFactory<>("realNumber"));
        projectOccupationTableView.setItems(projectOccupation);
    }


    public void deleteRow1(ActionEvent actionEvent) {
        int index= projectTableView.getSelectionModel().getSelectedIndex();
        int idDelete=projectTableView.getItems().get(index).getProjectId();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `projects` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTable();
            addToTable2();
            projectOccupationTableView.getItems().clear();
        }
    }
    public void deleteRow2(ActionEvent actionEvent) {
        int index= projectOccupationTableView.getSelectionModel().getSelectedIndex();
        int idDelete=projectOccupationTableView.getItems().get(index).getIdProjectOccupation();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `projectoccupations` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            fillTableProjectOccupation();
            addToTable2();
            addToTable();
        }
    }
    public void deleteRow3(ActionEvent actionEvent) {
        int index= projectEmployeeTableView.getSelectionModel().getSelectedIndex();
        int idDelete=projectEmployeeTableView.getItems().get(index).getId();
        int idUpdate=projectEmployeeTableView.getItems().get(index).getIdEmployee();
        try {
            con = new Controlers.ConnectDB().getConnection();
            pst = con.prepareStatement("UPDATE `employees` SET `isBusy`=? WHERE `id`=?");
            pst.setInt(1,0);
            pst.setInt(2, idUpdate);
            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `projectsemployees` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();


                try {
                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projectoccupations` SET `realNumber`=`realNumber`-1 WHERE idProject=? AND idOccupation=?");
                    pst.setInt(1,projectEmployeeTableView.getItems().get(index).getIdProject());
                    pst.setInt(2, projectEmployeeTableView.getItems().get(index).getIdOccupation());
                    pst.execute();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                warningMsg("إستبعاد","تم الإستبعاد بنجاح");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idDelete=0;

        }
        addToTable2();
        fillComboEmployee();

    }
    @FXML
    private TextField search;
    @FXML
    public void search(KeyEvent keyEvent) {

            String key=search.getText().trim();
            if (key.isEmpty()){
                addToTable();
                projectTableView.setItems(projectsTable);
            }else{
                projectsTable.clear();
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("SELECT * FROM `projects`,`areas`,`locations` WHERE projects.projectType='مشروع قطاع صحي' AND projects.areaId=areas.id AND projects.locationId=locations.id AND projects.contractName LIKE '%"+key+"%'");
                    rs=pst.executeQuery();
                    while (rs.next()){
                        projectsTable.add(new ProjectForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("contractName"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("contractPrice"),calculerRest(rs.getInt("id")),rs.getString("contractNumber")));
                    }
                    con.close();

                    projectTableView.setItems(projectsTable);

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
            addToTableMilitaire();
            projectTableView1.setItems(projectsTable2);
        }else{
            projectsTable2.clear();

            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `projects`,`areas`,`locations` WHERE projects.projectType='مشروع قطاع عسكري' AND projects.areaId=areas.id AND projects.locationId=locations.id AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    projectsTable2.add(new ProjectForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("contractName"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("contractPrice"),calculerRest(rs.getInt("id")),rs.getString("contractNumber")));
                }
                con.close();
                projectTableView1.setItems(projectsTable2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    @FXML
    private Button edit;
    public void edit(ActionEvent actionEvent) {
        int index= projectTableView.getSelectionModel().getSelectedIndex();
        int idEdit=projectTableView.getItems().get(index).getProjectId();


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


        if (projectEditPrivilege.getText().contains("تعديل مشروع")){
            projectEditPrivilege.setText("حفظ");
            contractStartDate.setValue(LocalDate.parse(projectTableView.getItems().get(index).getContractStartDate()));
            contractEndDate.setValue(LocalDate.parse(projectTableView.getItems().get(index).getContractEndDate()));
            contractStartDate.getEditor().setText(projectTableView.getItems().get(index).getContractStartDate());
            contractEndDate.getEditor().setText(projectTableView.getItems().get(index).getContractEndDate());

            areaName.setValue(projectTableView.getItems().get(index).getAreaName());
            locationName.setValue(projectTableView.getItems().get(index).getLocationName());
            projectName.setText(projectTableView.getItems().get(index).getContractName());
            contractPrice.setText(projectTableView.getItems().get(index).getContractPrice());
            contactDuration.setText(String.valueOf(projectTableView.getItems().get(index).getContactDuration()));
            contactNumber.setText(projectTableView.getItems().get(index).getContractNumber());


        }else if (projectEditPrivilege.getText().contains("حفظ")){
            int dejaExist1=0;
            int size1=0;
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `projects` WHERE `locationId`=? AND `contractName`=? AND id!=?");
                pst.setInt(1,idLocation);
                pst.setString(2,projectName.getText());
                pst.setInt(3,idEdit);

                rs=pst.executeQuery();
                while(rs.next()){
                    size1++;
                }
                if (size1>0){
                    dejaExist1=1;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (projectName.getText().isEmpty()||contactDuration.getText().isEmpty()||contactNumber.getText().isEmpty()||contractPrice.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||contractStartDate.getEditor().getText().isEmpty()||contractEndDate.getEditor().getText().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(dejaExist1==1){
                warningMsg("تنبيه","المعلومات موجودة من قبل");
            }else{
                try {


                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projects` SET `areaId`=?,`locationId`=?," +
                            "`projectType`=?,`contractName`=?,`contractPrice`=?,`contactDuration`=?" +
                            ",`contractStartDate`=?,`contractEndDate`=?,`contractNumber`=? WHERE `id`=?");
                    pst.setInt(1,idArea);
                    pst.setInt(2,idLocation);
                    pst.setString(3,"مشروع قطاع صحي");
                    pst.setString(4,projectName.getText());
                    pst.setFloat(5, Float.parseFloat(contractPrice.getText()));
                    pst.setInt(6, Integer.parseInt(contactDuration.getText()));
                    pst.setString(7, String.valueOf(contractStartDate.getValue()));
                    pst.setString(8, String.valueOf(contractEndDate.getValue()));
                    pst.setString(9, contactNumber.getText());
                    pst.setInt(10, idEdit);

                    pst.execute();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    projectEditPrivilege.setText("تعديل مشروع");
                    projectName.clear();
                    contractPrice.clear();
                    contactDuration.clear();
                    contractStartDate.getEditor().clear();
                    contractEndDate.getEditor().clear();
                    contactNumber.clear();
                    locationName.setPromptText(" الموقع");
                    areaName.setPromptText("المنطقة");
                    fillComboArea();
                    fillComboArea2();



                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
                addToTable();
                idEdit=0;
            }
        }
    }

    @FXML
    private TableView<Masrouf> masroufNameTableView;

    @FXML
    private TableColumn<Masrouf, String> masroufNameTable;

    @FXML
    private TextField searchMasrouf;

    @FXML
    private TextField masroufName;

    public void addMasrouf(ActionEvent actionEvent) {
        int dejaExist=0;
        int size=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `masroufat` WHERE `masroufName`=?");
            pst.setString(1,masroufName.getText());
            rs=pst.executeQuery();
            while(rs.next()){
                size++;
            }
            if (size>0){
                dejaExist=1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (masroufName.getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `masroufat`(`masroufName`) VALUES (?)");
                pst.setString(1,masroufName.getText());
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTableMasrouf();
            fillComboMasroufat();
            masroufName.clear();

        }

    }

    public void deleteRowMasrouf(ActionEvent actionEvent) {
        int index= masroufNameTableView.getSelectionModel().getSelectedIndex();
        int idDelete=masroufNameTableView.getItems().get(index).getIdMasrouf();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `masroufat` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTableMasrouf();
        }
    }

    @FXML
    public void searchMasrouf(KeyEvent keyEvent) {
        String key=searchMasrouf.getText().trim();
        if (key.isEmpty()){
            addToTableMasrouf();
            masroufNameTable.setCellValueFactory(new PropertyValueFactory<>("masroufName"));
            masroufNameTableView.setItems(masroufats);
        }else{
            masroufats.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `masroufat` WHERE `masroufName` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    masroufats.add(new Masrouf(rs.getInt("id"),rs.getString("masroufName")));
                }
                con.close();

                masroufNameTable.setCellValueFactory(new PropertyValueFactory<>("masroufName"));
                masroufNameTableView.setItems(masroufats);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }
    @FXML
    private TextField projectName1;

    @FXML
    private ComboBox<String> areaName1;

    @FXML
    private ComboBox<String> locationName1;

    @FXML
    private TextField contactDuration1;

    @FXML
    private TextField contractPrice1;

    @FXML
    private TextField contactNumber1;

    @FXML
    private DatePicker contractStartDate1;

    @FXML
    private DatePicker contractEndDate1;
    @FXML
    public void addProject2(ActionEvent actionEvent) {
        int dejaExist4=0;
        int size4=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `locationId`=? AND `contractName`=?");
            pst.setInt(1,idLocation2);
            pst.setString(2,projectName1.getText());
            rs=pst.executeQuery();
            while(rs.next()){
                size4++;
            }
            if (size4>0){
                dejaExist4=1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (projectName1.getText().isEmpty()||contactDuration1.getText().isEmpty()||contactNumber1.getText().isEmpty()||contractPrice1.getText().isEmpty()||areaName1.getSelectionModel().isEmpty()||locationName1.getSelectionModel().isEmpty()||contractStartDate1.getEditor().getText().isEmpty()||contractEndDate1.getEditor().getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist4==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `projects`(`areaId`, `locationId`, `projectType`, `contractName`, `contractPrice`," +
                        " `contactDuration`, `contractStartDate`, `contractEndDate`, `contractNumber`) VALUES (?,?,?,?,?,?,?,?,?)");
                pst.setInt(1,idArea2);
                pst.setInt(2,idLocation2);
                pst.setString(3,"مشروع قطاع عسكري");
                pst.setString(4,projectName1.getText());
                pst.setFloat(5, Float.parseFloat(contractPrice1.getText()));
                pst.setInt(6, Integer.parseInt(contactDuration1.getText()));
                pst.setString(7, String.valueOf(contractStartDate1.getValue()));
                pst.setString(8, String.valueOf(contractEndDate1.getValue()));
                pst.setString(9, contactNumber1.getText());
                pst.execute();

                warningMsg("إظافة","تمت الإظافة بنجاح");
                projectName1.clear();
                contactNumber1.clear();
                contractPrice1.clear();
                contactDuration1.clear();
                contractStartDate1.getEditor().clear();
                contractEndDate1.getEditor().clear();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }

            addToTableMilitaire();
        }

    }
    public void addToTableMilitaire(){
        projectsTable2.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects`,`areas`,`locations` WHERE projects.areaId=areas.id AND projects.locationId=locations.id AND projects.projectType='مشروع قطاع عسكري'");
            rs=pst.executeQuery();
            while (rs.next()){
                projectsTable2.add(new ProjectForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("contractName"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("contractPrice"),calculerRest(rs.getInt("id")),rs.getString("contractNumber")));
            }
            con.close();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    public String calculerRest(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null,somme=null,prixCont=null;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT SUM(`masroufPrice`) AS SOMME FROM `projectmasroufate` WHERE `projectId`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                somme= String.valueOf(rs.getFloat("SOMME"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT `contractPrice` FROM `projects` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                prixCont= rs.getString("contractPrice");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return String.valueOf(Float.parseFloat(prixCont)-Float.parseFloat(somme));

    }
    public void deleteRowMilitaire(ActionEvent actionEvent) {
        int index= projectTableView1.getSelectionModel().getSelectedIndex();
        int idDelete=projectTableView1.getItems().get(index).getProjectId();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `projects` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTableMilitaire();
        }
    }



    public void addMasroufe(ActionEvent actionEvent) {

        int index= projectTableView1.getSelectionModel().getSelectedIndex();
        if (Float.parseFloat(masroufPrice.getText())<=Float.parseFloat(projectTableView1.getItems().get(index).getContractPriceRest())){

            if (masroufPrice.getText().isEmpty()|| masroufatNameCombo.getSelectionModel().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else{
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("INSERT INTO `projectmasroufate`( `projectId`, `masroufName`, `masroufPrice`) VALUES (?,?,?)");
                    pst.setInt(1,idProject);
                    pst.setString(2,masroufatNameCombo.getValue());
                    pst.setString(3,masroufPrice.getText());
                    pst.execute();
                    warningMsg("إظافة","تمت الإظافة بنجاح");

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("إظافة","حدث خطأ أثناء الإظافة");
                }
            }

        }else{
            warningMsg("إظافة","التكلفة المشروع غير كافية");

        }

        fillTableMasroufate2();
        addToTableMilitaire();
    }
    @FXML
    private Button edit1;
    public void edit1(ActionEvent actionEvent) {
        int index= projectTableView1.getSelectionModel().getSelectedIndex();
        int idEdit=projectTableView1.getItems().get(index).getProjectId();


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


        if (projectEditPrivilege1.getText().contains("تعديل مشروع")){
            projectEditPrivilege1.setText("حفظ");
            areaName1.setValue(projectTableView1.getItems().get(index).getAreaName());
            locationName1.setValue(projectTableView1.getItems().get(index).getLocationName());
            projectName1.setText(projectTableView1.getItems().get(index).getContractName());
            contractPrice1.setText(projectTableView1.getItems().get(index).getContractPrice());
            contactDuration1.setText(String.valueOf(projectTableView1.getItems().get(index).getContactDuration()));
            contractStartDate1.setValue(LocalDate.parse(projectTableView1.getItems().get(index).getContractStartDate()));
            contractEndDate1.setValue(LocalDate.parse(projectTableView1.getItems().get(index).getContractEndDate()));
            contractStartDate1.getEditor().setText(projectTableView1.getItems().get(index).getContractStartDate());
            contractEndDate1.getEditor().setText(projectTableView1.getItems().get(index).getContractEndDate());
            contactNumber1.setText(projectTableView1.getItems().get(index).getContractNumber());


        }else if (projectEditPrivilege1.getText().contains("حفظ")){
            int dejaExist4=0;
            int size4=0;
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `projects` WHERE `locationId`=? AND `contractName`=? AND id!=?");
                pst.setInt(1,idLocation2);
                pst.setString(2,projectName1.getText());
                pst.setInt(3,idEdit);

                rs=pst.executeQuery();
                while(rs.next()){
                    size4++;
                }
                if (size4>0){
                    dejaExist4=1;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (projectName1.getText().isEmpty()||contactDuration1.getText().isEmpty()||contactNumber1.getText().isEmpty()||contractPrice1.getText().isEmpty()||areaName1.getSelectionModel().isEmpty()||locationName1.getSelectionModel().isEmpty()||contractStartDate1.getEditor().getText().isEmpty()||contractEndDate1.getEditor().getText().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(dejaExist4==1){
                warningMsg("تنبيه","المعلومات موجودة من قبل");
            }else{
                try {


                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projects` SET `areaId`=?,`locationId`=?," +
                            "`projectType`=?,`contractName`=?,`contractPrice`=?,`contactDuration`=?" +
                            ",`contractStartDate`=?,`contractEndDate`=?,`contractNumber`=? WHERE `id`=?");

                    pst.setInt(1,idArea2);
                    pst.setInt(2,idLocation2);
                    pst.setString(3,"مشروع قطاع عسكري");
                    pst.setString(4,projectName1.getText());
                    pst.setFloat(5, Float.parseFloat(contractPrice1.getText()));
                    pst.setInt(6, Integer.parseInt(contactDuration1.getText()));
                    pst.setString(7, String.valueOf(contractStartDate1.getValue()));
                    pst.setString(8, String.valueOf(contractEndDate1.getValue()));
                    pst.setString(9, contactNumber1.getText());
                    pst.setInt(10, idEdit);

                    pst.execute();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    projectEditPrivilege1.setText("تعديل مشروع");
                    projectName1.clear();
                    contractPrice1.clear();
                    contactDuration1.clear();
                    contractStartDate1.getEditor().clear();
                    contractEndDate1.getEditor().clear();
                    contactNumber1.clear();
                    locationName1.setPromptText(" الموقع");
                    areaName1.setPromptText("المنطقة");
                    fillComboArea2();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
                addToTableMilitaire();
                idEdit=0;
            }

        }


    }
    @FXML
    public void getSelectItemTable2(MouseEvent mouseEvent) {
        projectEditPrivilege1.setText("تعديل مشروع");
        int index= projectTableView1.getSelectionModel().getSelectedIndex();
        idProject=projectTableView1.getItems().get(index).getProjectId();
        System.out.println(idProject);
        fillTableMasroufate2();

        masroufatNameComboTable.setCellValueFactory(new PropertyValueFactory<>("masroufName"));
        masroufPriceTable.setCellValueFactory(new PropertyValueFactory<>("masroufPrice"));
        masroufat2TableView.setItems(masroufats2);
    }
    public void fillTableMasroufate2(){
        masroufats2.clear();
        masroufat2TableView.getItems().clear();
        int index= projectTableView1.getSelectionModel().getSelectedIndex();
        idProject=projectTableView1.getItems().get(index).getProjectId();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectmasroufate` WHERE `projectId`=?");
            pst.setInt(1,idProject);
            rs=pst.executeQuery();
            while (rs.next()){
                masroufats2.add(new Masrouf2(rs.getInt("id"),rs.getInt("projectId"),rs.getString("masroufName"),rs.getFloat("masroufPrice")));

            }
            con.close();



        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void deleteMasroufate2(ActionEvent actionEvent) {
        int index= masroufat2TableView.getSelectionModel().getSelectedIndex();
        int idDelete=masroufat2TableView.getItems().get(index).getIdMasrouf();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `projectmasroufate` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");

            }
            idDelete=0;
            addToTableMilitaire();

            fillTableMasroufate2();
        }
    }

    @FXML
    private Button editPosition;
    public void edit2(ActionEvent actionEvent) {
        int index= projectEmployeeTableView.getSelectionModel().getSelectedIndex();
        int idEdit=projectEmployeeTableView.getItems().get(index).getId();
        idEmployee=projectEmployeeTableView.getItems().get(index).getIdEmployee();


        for (int i=0; i<areas.size() ;i++){
            if (areas.get(i).getNameArea()==areaNameEmployee.getValue()){
                idArea=areas.get(i).getIdArea();
            }
        }
        for (int i=0; i<locations.size() ;i++){
            if (locations.get(i).getLocationName()==locationNameEmployee.getValue()){
                idLocation=locations.get(i).getIdLocation();
            }
        }
        for (int i=0; i<projects.size() ;i++){
            if (projects.get(i).getContractName()==projectNameEmployee.getValue()){
                idProject=projects.get(i).getIdProject();
            }
        }
        for (int i=0; i<occupations.size() ;i++){
            if (occupations.get(i).getNameOcupation()==occupationNameEmployee.getValue()){
                idOccupation=occupations.get(i).getIdOcupation();
            }
        }



        if (editPosition.getText().contains("نقل موظف")){
            editPosition.setText("حفظ");
            areaNameEmployee.setValue(projectEmployeeTableView.getItems().get(index).getAreaName());
            locationNameEmployee.setValue(projectEmployeeTableView.getItems().get(index).getLocationName());
            projectNameEmployee.setValue(projectEmployeeTableView.getItems().get(index).getProjectName());
            occupationNameEmployee.setValue(projectEmployeeTableView.getItems().get(index).getOccupationName());
            occupationNameEmployee.setDisable(true);


        }else if (editPosition.getText().contains("حفظ")){
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `projectoccupations` WHERE `idProject`=? AND `idOccupation`=? AND id!=?");
                pst.setInt(1,idProject);
                pst.setInt(2,idOccupation);
                pst.setInt(3,idEdit);

                rs=pst.executeQuery();
                while(rs.next()){
                    resultMax=rs.getInt("maxNumber");
                    resultMin=rs.getInt("realNumber");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (areaNameEmployee.getSelectionModel().isEmpty()||locationNameEmployee.getSelectionModel().isEmpty()||projectNameEmployee.getSelectionModel().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(resultMax<=resultMin){
                warningMsg("تنبيه","وصلت للحد الأقصى للعمالة في هذه الوظيفة للمشروع المحدد");
            }else{
                try {


                    con = new Controlers.ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projectsemployees` SET `idArea`=?,`idLocation`=?,`idProject`=?,`idEmployee`=? WHERE `id`=?");

                    pst.setInt(1,idArea);
                    pst.setInt(2,idLocation);
                    pst.setInt(3,idProject);
                    pst.setInt(4, idEmployee);
                    pst.setInt(5, idEdit);
                    pst.execute();
                    try {
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `projectoccupations` SET `realNumber`=`realNumber`-1 WHERE idProject=? AND idOccupation=?");
                        pst.setInt(1,projectEmployeeTableView.getItems().get(index).getIdProject());
                        pst.setInt(2, projectEmployeeTableView.getItems().get(index).getIdOccupation());
                        pst.execute();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    try {
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `projectoccupations` SET `realNumber`=`realNumber`+1 WHERE idProject=? AND idOccupation=?");
                        pst.setInt(1,idProject);
                        pst.setInt(2, idOccupation);
                        pst.execute();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                    warningMsg("نقل","تم النقل بنجاح");
                    editPosition.setText("نقل موظف");

                    locationNameEmployee.setPromptText("الموقع");
                    areaNameEmployee.setPromptText("المنطقة");
                    projectNameEmployee.setPromptText("المشروع");
                    occupationNameEmployee.setPromptText("الوظيفة");
                    fillComboEmployee();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("نقل","حدث خطأ أثناء النقل");

                }
                addToTable2();
                idEdit=0;
                occupationNameEmployee.setDisable(false);

            }

        }


    }

    @FXML
    void nakl(MouseEvent event) {
        occupationNameEmployee.setDisable(false);
        editPosition.setText("نقل موظف");
    }
}
