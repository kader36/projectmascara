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
                contractStartDate.getEditor().setText(rs.getString("contractStartDate"));
                contractEndDate.getEditor().setText(rs.getString("contractEndDate"));
            }


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
                    abstractAddPrivilege.setDisable(true);
                    abstractAddPrivilege1.setDisable(true);

                }else{
                    abstractMenuButton.setDisable(false);
                    abstractAddPrivilege.setDisable(false);
                    abstractAddPrivilege1.setDisable(false);

                }
                if (rs.getInt("abde")==0){
                    abstractDeletePrivilege.setDisable(true);
                    abstractDeletePrivilege1.setDisable(true);
                    abstractEditPrivilege.setDisable(true);

                }else{
                    abstractDeletePrivilege.setDisable(false);
                    abstractDeletePrivilege1.setDisable(false);
                    abstractEditPrivilege.setDisable(false);

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
        remarkTable.setCellValueFactory(new PropertyValueFactory<>("remark"));

        abstractTableView.setItems(abstractsTable);
        yearAbstractTableView.setItems(abstractYearsTable);
        contractStartDate.getEditor().setEditable(false);
        contractEndDate.getEditor().setEditable(false);


    }

    public void addToTable(){
        abstractsTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `abstract`,`areas`,`locations`,`projects` WHERE abstract.idArea=areas.id AND abstract.idLocation=locations.id AND abstract.idProject=projects.id");
            rs=pst.executeQuery();
            while (rs.next()){
                abstractsTable.add(new AbstractForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate")));

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
                warningMsg("حذف","تم الحذف بنجاح");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTable2();
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
                warningMsg("حذف","تم الحذف بنجاح");

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
            remarkTable.setCellValueFactory(new PropertyValueFactory<>("remark"));
            abstractTableView.setItems(abstractsTable);
        }else{
            abstractsTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("`abstract`,`areas`,`locations`,`projects` WHERE abstract.idArea=areas.id AND abstract.idLocation=locations.id AND abstract.idProject=projects.id AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    abstractsTable.add(new AbstractForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate")));

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
                remarkTable.setCellValueFactory(new PropertyValueFactory<>("remark"));
                abstractTableView.setItems(abstractsTable);


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
    void idReset2(MouseEvent event) {
//        abstractEditPrivilege.setText("تعديل الغرامة");

    }

    @FXML
    private ComboBox<String> yearAbstract;
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

        if ((!janvier.isSelected()&&!fevrier.isSelected()&&!mars.isSelected()&&!avril.isSelected()&&!may.isSelected()&&!juin.isSelected()&&!juilliet.isSelected()&&!aout.isSelected()&&!septembre.isSelected()&&!octobre.isSelected()&&!novembre.isSelected()&&!decembre.isSelected()) || yearAbstract.getSelectionModel().isEmpty() ){
            warningMsg("تنبيه","يرجى ملء الفراغات");
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

}
