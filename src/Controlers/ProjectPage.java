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

public class ProjectPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<EmployeeForList> employees= FXCollections.observableArrayList();
    ObservableList<Occupation> occupations= FXCollections.observableArrayList();
    ObservableList<ProjectOcupation> projectOccupation= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<String> projectTypeList= FXCollections.observableArrayList("مشروع قطاع عسكري","مشروع قطاع صحي");
    int idArea=0,idLocation=0,idProject=0,idOccupation=0,idEmployee=0,idProjectOccupation=0;


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
            for (int i=0;i<areas.size();i++){
                areaName.getItems().add(areas.get(i).getNameArea());
                areaNameEmployee.getItems().add(areas.get(i).getNameArea());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void fillComboEmployee(){
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees`");
            rs=pst.executeQuery();
            while (rs.next()){
                employees.add(new EmployeeForList(rs.getInt("id"),rs.getString("employeeName")));

            }
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
            pst=con.prepareStatement("SELECT * FROM `projectoccupations` WHERE `idProject`=?");
            pst.setInt(1,idProject);
            rs=pst.executeQuery();
            while (rs.next()){
                projectOccupation.add(new ProjectOcupation(rs.getInt("id"),rs.getInt("idProject"),rs.getInt("idOccupation"),rs.getInt("maxNumber"),rs.getInt("realNumber"),getOccupationName(rs.getInt("idOccupation"))));

            }
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

            for (int i=0;i<locations.size();i++){
                locationName.getItems().add(locations.get(i).getLocationName());
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
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=?");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            rs=pst.executeQuery();
            while (rs.next()){
                projects.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));

            }

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


        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void fillTableProjectOccupation(){
        projectOccupation.clear();
        projectOccupationTableView.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectoccupations` WHERE `idProject`=?");
            pst.setInt(1,idProjectOccupation);
            rs=pst.executeQuery();
            while (rs.next()){
                projectOccupation.add(new ProjectOcupation(rs.getInt("id"),rs.getInt("idProject"),rs.getInt("idOccupation"),rs.getInt("maxNumber"),rs.getInt("realNumber"),getOccupationName(rs.getInt("idOccupation"))));

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
    void selectProjectOccupation(ActionEvent event) {

    }
    @FXML
    void selectAreaEmployee(ActionEvent event) {
        int index= areaNameEmployee.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        fillComboLocationEmployee();
    }
    @FXML
    void selectEmployeeEmployee(ActionEvent event) {

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
    }

    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();

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
    public void addProject(ActionEvent actionEvent) {
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `projects`(`areaId`, `locationId`, `projectType`, `contractName`, `contractPrice`," +
                    " `contactDuration`, `contractStartDate`, `contractEndDate`, `contractNumber`) VALUES (?,?,?,?,?,?,?,?,?)");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setString(3,projectType.getValue());
            pst.setString(4,projectName.getText());
            pst.setFloat(5, Float.parseFloat(contractPrice.getText()));
            pst.setInt(6, Integer.parseInt(contactDuration.getText()));
            pst.setString(7, String.valueOf(contractStartDate.getValue()));
            pst.setString(8, String.valueOf(contractEndDate.getValue()));
            pst.setString(9, contactNumber.getText());
            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable();
    }

    @FXML
    void addProjectOccupation(ActionEvent event) {

        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `projectoccupations`(`idProject`, `idOccupation`, `maxNumber`, `realNumber`) VALUES (?,?,?,?)");
            pst.setInt(1,idProjectOccupation);
            pst.setInt(2,idOccupation);
            pst.setInt(3,Integer.parseInt(maxNumber.getText()));
            pst.setInt(4,0);

            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        fillTableProjectOccupation();

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

    ObservableList projectsTable= FXCollections.observableArrayList();

    public void addToTable(){
        projectsTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects`");
            rs=pst.executeQuery();
            while (rs.next()){
                projectsTable.add(new ProjectForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("contractName"),getAreaName(rs.getInt("areaId")),getLocationName(rs.getInt("areaId"),rs.getInt("locationId")),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("contractPrice"),rs.getString("contractNumber")));
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
    public String getEmployeeName(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
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
    private ComboBox<String> areaNameEmployee;

    @FXML
    private ComboBox<String> locationNameEmployee;

    @FXML
    private ComboBox<String> projectNameEmployee;

    @FXML
    private ComboBox<String> occupationNameEmployee;

    ObservableList projectEmployeesTable= FXCollections.observableArrayList();
    ObservableList projectOccupationsTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        fillComboEmployee();
        fillProject();
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
        contractNumberTable.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        projectTableView.setItems(projectsTable);

        addToTable2();
        employeeNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        occupationNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("occupationName"));
        areaNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
        locationNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        projectNameEmployeeTable.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        projectEmployeeTableView.setItems(projectEmployeesTable);

    }

    public void addToTable2(){
        projectEmployeesTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectsemployees`");
            rs=pst.executeQuery();
            while (rs.next()){
                projectEmployeesTable.add(new projectEmployeeForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idOccupation"),rs.getInt("idEmployee"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getProjectName(rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject")),getOccupationName(rs.getInt("idOccupation")),getEmployeeName(rs.getInt("idEmployee"))));

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    @FXML
    public void addEmployeeProject(ActionEvent actionEvent) {
        int index= employeeNameEmployee.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        idEmployee=employees.get(index).getId();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `projectsemployees`(`idArea`, `idLocation`, `idProject`, `idOccupation`, `idEmployee`) VALUES (?,?,?,?,?)");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setInt(3,idProject);
            pst.setInt(4,idOccupation);
            pst.setInt(5,idEmployee);
            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable2();

    }


    @FXML
    private TableColumn<ProjectOcupation, String> occupationNameTable;

    @FXML
    private TableColumn<ProjectOcupation, Integer> maxNumberTable;

    @FXML
    private TableColumn<ProjectOcupation, Integer> realNumberTable;
    @FXML
    public void getSelectItemTable(MouseEvent mouseEvent) {
        edit.setText("تعديل مشروع");
        int index= projectTableView.getSelectionModel().getSelectedIndex();
        idProjectOccupation=projects.get(index).getIdProject();
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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
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
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `projectsemployees` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idDelete=0;
            addToTable2();
        }
    }
    @FXML
    private TextField search;
    @FXML
    public void search(KeyEvent keyEvent) {
        String key=search.getText().trim();
        if (key.isEmpty()){
            addToTable();
            areaNameTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
            locationNameTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
            projectNameTable.setCellValueFactory(new PropertyValueFactory<>("contractName"));
            projectTypeTable.setCellValueFactory(new PropertyValueFactory<>("projectType"));
            contactDurationTable.setCellValueFactory(new PropertyValueFactory<>("contactDuration"));
            contractPriceTable.setCellValueFactory(new PropertyValueFactory<>("contractPrice"));
            contractStartDateTable.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
            contractEndDateTable.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
            contractNumberTable.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
            projectTableView.setItems(projectsTable);
        }else{
            projectsTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `projects` WHERE projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    projectsTable.add(new ProjectForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("contractName"),getAreaName(rs.getInt("areaId")),getLocationName(rs.getInt("areaId"),rs.getInt("locationId")),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("contractPrice"),rs.getString("contractNumber")));


                }
                areaNameTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
                locationNameTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
                projectNameTable.setCellValueFactory(new PropertyValueFactory<>("contractName"));
                projectTypeTable.setCellValueFactory(new PropertyValueFactory<>("projectType"));
                contactDurationTable.setCellValueFactory(new PropertyValueFactory<>("contactDuration"));
                contractPriceTable.setCellValueFactory(new PropertyValueFactory<>("contractPrice"));
                contractStartDateTable.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
                contractEndDateTable.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
                contractNumberTable.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
                projectTableView.setItems(projectsTable);


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
        int idArea=0,idLocation=0,idEmployee=0,idProject=0;


        if (edit.getText().contains("تعديل مشروع")){
            edit.setText("حفظ");
            areaName.setValue(projectTableView.getItems().get(index).getAreaName());
            locationName.setValue(projectTableView.getItems().get(index).getLocationName());
            projectType.setValue(projectTableView.getItems().get(index).getProjectType());
            projectName.setText(projectTableView.getItems().get(index).getContractName());
            contractPrice.setText(projectTableView.getItems().get(index).getContractPrice());
            contactDuration.setText(String.valueOf(projectTableView.getItems().get(index).getContactDuration()));
            contractStartDate.setValue(LocalDate.parse(projectTableView.getItems().get(index).getContractStartDate()));
            contractEndDate.setValue(LocalDate.parse(projectTableView.getItems().get(index).getContractEndDate()));
            contactNumber.setText(projectTableView.getItems().get(index).getContractNumber());

        }else if (edit.getText().contains("حفظ")){
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

                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("UPDATE `projects` SET `areaId`=?,`locationId`=?," +
                        "`projectType`=?,`contractName`=?,`contractPrice`=?,`contactDuration`=?" +
                        ",`contractStartDate`=?,`contractEndDate`=?,`contractNumber`=? WHERE `id`=?");

                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setString(3,projectType.getValue());
                pst.setString(4,projectName.getText());
                pst.setFloat(5, Float.parseFloat(contractPrice.getText()));
                pst.setInt(6, Integer.parseInt(contactDuration.getText()));
                pst.setString(7, String.valueOf(contractStartDate.getValue()));
                pst.setString(8, String.valueOf(contractEndDate.getValue()));
                pst.setString(9, contactNumber.getText());
                pst.setInt(10, idEdit);

                pst.execute();
                edit.setText("تعديل مشروع");
                projectType.setPromptText("نوع المشروع");
                projectName.clear();
                contractPrice.clear();
                contactDuration.clear();
                contractStartDate.getEditor().clear();
                contractEndDate.getEditor().clear();
                contactNumber.clear();
                locationName.setPromptText(" الموقع");
                areaName.setPromptText("المنطقة");
                fillComboArea();



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            addToTable();
            idEdit=0;
        }


    }

}
