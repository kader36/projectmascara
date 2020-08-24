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
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<String> locoremps= FXCollections.observableArrayList("موقع","موظف");
    ObservableList<String> perOrCoss= FXCollections.observableArrayList("نسبة","تكلفة");
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<EmployeeForList> employees= FXCollections.observableArrayList();
    ObservableList<Occupation> occupations= FXCollections.observableArrayList();
    int idArea=0,idLocation=0,idProject=0,idEmployee=0,idOccupation=0;
    String nortValue=null;
    int isChanged=0;

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
    void selectArea(ActionEvent event) {
        int index= areaName.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        fillComboLocation();
        employeeName.getItems().clear();
        projectName.getItems().clear();
        employeeName.setValue("");

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
    void selectEmployee(ActionEvent event) {
        int index= employeeName.getSelectionModel().getSelectedIndex();
        idEmployee=employees.get(index).getId();
        isChanged=1;


    }
    @FXML
    void selectProject(ActionEvent event) {
        int index= projectName.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();
        fillComboOccupation();

    }
    @FXML
    void selectOccupation(ActionEvent event) {
        int index= occupationName.getSelectionModel().getSelectedIndex();
        idOccupation=occupations.get(index).getIdOcupation();
        fillComboEmployee();
        employeeName.setValue("");

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

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }
    public void fillComboEmployee(){
        employees.clear();
        employeeName.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectsemployees` WHERE `idProject`=? AND `idOccupation`=?");
            pst.setInt(1,idProject);
            pst.setInt(2,idOccupation);
            rs=pst.executeQuery();
            while (rs.next()){
                employees.add(new EmployeeForList(rs.getInt("idEmployee"),getEmployeeName(rs.getInt("idEmployee"))));

            }
            for (int i=0;i<employees.size();i++){
                employeeName.getItems().add(employees.get(i).getEmployeeName());
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
        usernameMenu.setText("#"+employeeNameConnected);
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

                }else{
                    projectMenuButton.setDisable(false);

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
                    penaltyAddPrivilege.setDisable(true);

                }else{
                    penaltyMenuButton.setDisable(false);
                    penaltyAddPrivilege.setDisable(false);

                }
                if (rs.getInt("pede")==0){
                    penaltyDeletePrivilege.setDisable(true);
                    penaltyEditPrivilege.setDisable(true);

                }else{
                    penaltyDeletePrivilege.setDisable(false);
                    penaltyEditPrivilege.setDisable(false);

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


    public void addDeduction(ActionEvent actionEvent) {
        if (amountOfDeduction.getText().isEmpty()||typeDeduction.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()||locoremp.getSelectionModel().isEmpty()||perOrCos.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else{
            try {
                con=new ConnectDB().getConnection();
                if (idEmployee>0){
                    pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `idEmployeeDeduction`, `dorp`, `nort`) VALUES (?,?,?,?,?,?,?,?,?)");
                    pst.setInt(1,idArea);
                    pst.setInt(2,idLocation);
                    pst.setString(3,typeDeduction.getText());
                    pst.setFloat(4, Float.parseFloat(amountOfDeduction.getText()));
                    pst.setInt(5,idProject);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );

                    pst.setString(6, sdf.format(new Date()));
                    pst.setInt(7,idEmployee);
                    pst.setString(8,"p");
                    pst.setString(9,perOrCos.getValue());

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
                    pst.setString(8,perOrCos.getValue());

                }



                pst.execute();
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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            addToTable();
            amountOfDeduction.clear();
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

    ObservableList deductionsTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        occupationName.setVisible(false);
        per.setVisible(false);
        employeeName.setVisible(false);
        perOrCos.setItems(perOrCoss);
        locoremp.setItems(locoremps);
        addToTable();
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        amountOfDeductionTable.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
        nortTable.setCellValueFactory(new PropertyValueFactory<>("nort"));
        typeDeductionTable.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
        employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("nameEmployee"));
        deductionTableView.setItems(deductionsTable);

    }
    public void addToTable(){
        deductionsTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductions` WHERE `dorp`='p'");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionsTable.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getProjectName(rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject")),getEmployeeName(rs.getInt("idEmployeeDeduction")),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));

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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
            amountOfDeductionTable.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
            nortTable.setCellValueFactory(new PropertyValueFactory<>("nort"));
            typeDeductionTable.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
            deductionTableView.setItems(deductionsTable);
        }else{
            deductionsTable.clear();
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `deductions`,`projects` WHERE deductions.idProject =projects.id AND deductions.dorp='p' AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    deductionsTable.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getProjectName(rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject")),getEmployeeName(rs.getInt("idEmployeeDeduction")),rs.getString("typeDeduction"),rs.getString("amountOfDeduction"),rs.getString("nort")));


                }
                areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
                locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
                projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
                amountOfDeductionTable.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
                nortTable.setCellValueFactory(new PropertyValueFactory<>("nort"));
                typeDeductionTable.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
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
        int idArea=0,idLocation=0,idEmployee=0,idProject=0;
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
            if (locoremp.getSelectionModel().getSelectedItem()=="موظف"){
                occupationName.setVisible(true);
                employeeName.setVisible(true);
            }else{
                occupationName.setVisible(false);
                employeeName.setVisible(false);
            }
            System.out.println(deductionTableView.getSelectionModel().getSelectedItem().getNameEmployee());
            if (deductionTableView.getSelectionModel().getSelectedItem().getNameEmployee()!=null){
                locoremp.setValue("موظف");
            }else if (deductionTableView.getSelectionModel().getSelectedItem().getNameEmployee()==null){
                locoremp.setValue("موقع");
            }
        }else if (penaltyEditPrivilege.getText().contains("حفظ")){
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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            isChanged=0;
            addToTable();
            idEdit=0;
        }


    }
    @FXML
    void idReset(MouseEvent event) {
        penaltyEditPrivilege.setText("تعديل الغرامة");
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

    @FXML
    private Label per;

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
}
