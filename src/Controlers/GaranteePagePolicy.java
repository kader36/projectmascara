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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class GaranteePagePolicy implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<String> garantees= FXCollections.observableArrayList("الافراج عن الضمان","تخفيض 5% من الضمان");
    int idArea=0,idLocation=0,idProject=0,idGarantee=0,idHistorical=0,idBank=0;

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> locationName;
    @FXML
    private ComboBox<String> projectName;
    @FXML
    private TextField policyNumber;
    @FXML
    private DatePicker policyBeginDate;

    @FXML
    private DatePicker policyEndDate;

    @FXML
    private DatePicker policyRenewDate;




    @FXML
    void selectArea(ActionEvent event) {
        int index= areaName.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        locationName.getItems().clear();
        projectName.getItems().clear();
        fillComboLocation();

    }
    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();

        projectName.getItems().clear();
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
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
        }
    }

    public void fillComboArea(){
        areaName.getItems().clear();
        areas.clear();
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
    private Button garanteeEditPrivilege;
    @FXML
    private Button garanteeAddPrivilege;
    @FXML
    private Button garanteeDeletePrivilege;



    int idConnected=0;
    String usernameConnected="";
    String employeeNameConnected="";
    public void Init(int idConnected,String usernameConnected,String employeeNameConnected){
        this.idConnected = idConnected;
        this.usernameConnected = usernameConnected;
        this.employeeNameConnected = employeeNameConnected;
        usernameMenu.setText(employeeNameConnected);
        try {
            con=new ConnectDB().getConnection();
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

                if (rs.getInt("res")==0){
                    repportMenuButton.setDisable(true);

                }else{
                    repportMenuButton.setDisable(false);

                }
                if ((rs.getInt("prss")==0)&&(rs.getInt("prss1")==0)&&(rs.getInt("prms")==0)&&(rs.getInt("prrcs")==0)){
                    projectMenuButton.setDisable(true);

                }else{
                    projectMenuButton.setDisable(false);

                }
                if (rs.getInt("gasa1")==0){
                    garanteeAddPrivilege.setDisable(true);

                }else{
                    garanteeAddPrivilege.setDisable(false);

                }
                if (rs.getInt("gasd1")==0){
                    garanteeDeletePrivilege.setDisable(true);

                }else{
                    garanteeDeletePrivilege.setDisable(false);

                }
                if (rs.getInt("gase1")==0){
                    garanteeEditPrivilege.setDisable(true);

                }else{
                    garanteeEditPrivilege.setDisable(false);

                }
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
    public void areas(ActionEvent actionEvent) {
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


    public void addGarantee(ActionEvent actionEvent) {
        int dejaExist=0;
        int size=0;
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `garanteespolicy` WHERE `policyNumber`=?");
            pst.setString(1,policyNumber.getText());
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
        if (policyNumber.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()||policyBeginDate.getEditor().getText().isEmpty()||policyEndDate.getEditor().getText().isEmpty()||policyRenewDate.getEditor().getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `garanteespolicy`(`idArea`, `idLocation`, `idProject`, `policyNumber`, `policyBeginDate`, `policyEndDate`, `policyRenewDate`) VALUES (?,?,?,?,?,?,?)");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setInt(3,idProject);
                pst.setString(4,policyNumber.getText());
                pst.setString(5,policyBeginDate.getEditor().getText());
                pst.setString(6,policyEndDate.getEditor().getText());
                pst.setString(7,policyRenewDate.getEditor().getText());
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            areaName.getItems().clear();
            locationName.getItems().clear();
            projectName.getItems().clear();
            policyNumber.clear();
            policyBeginDate.getEditor().clear();
            policyEndDate.getEditor().clear();
            policyRenewDate.getEditor().clear();
            garanteeEditPrivilege.setText("تعديل بوليصة التأمين");

            addToTable();

            fillComboArea();

        }

    }
    @FXML
    private TableView<GaranteePolicyForTable> garanteeTableView;


    @FXML
    private TableColumn<GaranteePolicyForTable, String> contractNameTable;

    @FXML
    private TableColumn<GaranteePolicyForTable, String> contractNumberTable;

    @FXML
    private TableColumn<GaranteePolicyForTable, Double> contractPriceTable;

    @FXML
    private TableColumn<GaranteePolicyForTable, String> contractStartDateTable;

    @FXML
    private TableColumn<GaranteePolicyForTable, String> contractEndDateTable;

    @FXML
    private TableColumn<GaranteePolicyForTable, String> policyNumberTable;

    @FXML
    private TableColumn<GaranteePolicyForTable, String> policyBeginDateTable;

    @FXML
    private TableColumn<GaranteePolicyForTable, String> policyEndDateTable;

    @FXML
    private TableColumn<GaranteePolicyForTable, String> policyRenewDateTable;

    ObservableList garanteesTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        addToTable();
        contractNameTable.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        contractNumberTable.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        contractPriceTable.setCellValueFactory(new PropertyValueFactory<>("contractPrice"));
        contractStartDateTable.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
        contractEndDateTable.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
        policyNumberTable.setCellValueFactory(new PropertyValueFactory<>("policyNumber"));
        policyBeginDateTable.setCellValueFactory(new PropertyValueFactory<>("policyBeginDate"));
        policyEndDateTable.setCellValueFactory(new PropertyValueFactory<>("policyEndDate"));
        policyRenewDateTable.setCellValueFactory(new PropertyValueFactory<>("policyRenewDate"));
        garanteeTableView.setItems(garanteesTable);
    }
    public void addToTable(){
        garanteesTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `garanteespolicy`,`areas`,`locations`,`projects` WHERE garanteespolicy.idArea=areas.id AND garanteespolicy.idLocation=locations.id AND garanteespolicy.idProject=projects.id");
            rs=pst.executeQuery();
            while (rs.next()){
                garanteesTable.add(new GaranteePolicyForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("policyNumber"),rs.getString("policyBeginDate"),rs.getString("policyEndDate"),rs.getString("policyRenewDate"),rs.getString("areaName"),rs.getString("locationName"),rs.getDouble("contractPrice")));
            }
            pst.close();


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
    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        int index= garanteeTableView.getSelectionModel().getSelectedIndex();
        int idDelete=garanteeTableView.getItems().get(index).getIdPolicy();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `garanteespolicy` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
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

            garanteeTableView.setItems(garanteesTable);
        }else{
            garanteesTable.clear();
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM  `garanteespolicy`,`areas`,`locations`,`projects` WHERE garanteespolicy.idArea=areas.id AND garanteespolicy.idLocation=locations.id AND garanteespolicy.idProject=projects.id AND garanteespolicy.policyNumber LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    garanteesTable.add(new GaranteePolicyForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("policyNumber"),rs.getString("policyBeginDate"),rs.getString("policyEndDate"),rs.getString("policyRenewDate"),rs.getString("areaName"),rs.getString("locationName"),rs.getDouble("contractPrice")));

                }
                pst.close();

                garanteeTableView.setItems(garanteesTable);


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
    private Button edit;
    public void edit(ActionEvent actionEvent) {
        int index= garanteeTableView.getSelectionModel().getSelectedIndex();
        int idEdit=garanteeTableView.getItems().get(index).getIdPolicy();


        if (garanteeEditPrivilege.getText().contains("تعديل بوليصة التأمين")){
            garanteeEditPrivilege.setText("حفظ");
            areaName.setValue(garanteeTableView.getItems().get(index).getAreaName());
            locationName.setValue(garanteeTableView.getItems().get(index).getLocationName());
            projectName.setValue(garanteeTableView.getItems().get(index).getContractName());
            policyNumber.setText(garanteeTableView.getItems().get(index).getPolicyNumber());
            policyBeginDate.getEditor().setText(String.valueOf(garanteeTableView.getItems().get(index).getPolicyBeginDate()));
            policyEndDate.getEditor().setText(String.valueOf(garanteeTableView.getItems().get(index).getPolicyEndDate()));
            policyRenewDate.getEditor().setText(String.valueOf(garanteeTableView.getItems().get(index).getPolicyRenewDate()));

        }else if (garanteeEditPrivilege.getText().contains("حفظ")){
            int dejaExist=0;
            int size=0;
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `garanteespolicy` WHERE (`policyNumber`=?) AND id!=?");
                pst.setString(1,policyNumber.getText());
                pst.setInt(2,idEdit);

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
            if (policyNumber.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()||policyBeginDate.getEditor().getText().isEmpty()||policyEndDate.getEditor().getText().isEmpty()||policyRenewDate.getEditor().getText().isEmpty()){
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
                    pst = con.prepareStatement("UPDATE `garanteespolicy` SET `idArea`=?,`idLocation`=?,`idProject`=?,`policyNumber`=?,`policyBeginDate`=?,`policyEndDate`=?,`policyRenewDate`=? WHERE `id`=?");


                    pst.setInt(1,idArea);
                    pst.setInt(2,idLocation);
                    pst.setInt(3,idProject);
                    pst.setString(4,policyNumber.getText());
                    pst.setString(5,policyBeginDate.getEditor().getText());
                    pst.setString(6,policyEndDate.getEditor().getText());
                    pst.setString(7,policyRenewDate.getEditor().getText());
                    pst.setInt(8,idEdit);
                    pst.execute();
                    garanteeEditPrivilege.setText("تعديل بوليصة التأمين");
                    policyNumber.clear();
                    policyBeginDate.getEditor().clear();
                    policyEndDate.getEditor().clear();
                    policyRenewDate.getEditor().clear();
                    warningMsg("تعديل","تم التعديل بنجاح");
                    pst.close();



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
    void idReset(MouseEvent event) {
        garanteeEditPrivilege.setText("تعديل بوليصة التأمين");
    }



}
