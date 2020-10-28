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
    private ComboBox<String> garanteeType;

    @FXML
    private TextField garanteeNumber;
    @FXML
    private TextField garanteePrice;
    @FXML
    private ComboBox<String> projectName;


    @FXML
    private TableView<Banks> bankTableView;
    @FXML
    private TableColumn<Banks, String> bankNameTable;
    @FXML
    private TextField bankName1;

    @FXML
    public void deleteBank(ActionEvent actionEvent) {
        int index= bankTableView.getSelectionModel().getSelectedIndex();
        int idDelete=bankTableView.getItems().get(index).getIdBank();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `banks` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTableBank();
            fillComboBanks();

        }
    }
    @FXML
    public void addBank(ActionEvent actionEvent) {
        int dejaExist=0;
        int size=0;
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `banks` WHERE `bankName`=?");
            pst.setString(1,bankName1.getText());
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
        if (bankName1.getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `banks`(`bankName`) VALUES (?)");
                pst.setString(1,bankName1.getText());
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTableBank();
            fillComboBanks();
            bankName1.clear();

        }

    }
    ObservableList<Banks> banks= FXCollections.observableArrayList();

    public void addToTableBank(){
        banks.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `banks`");
            rs=pst.executeQuery();
            while (rs.next()){
                banks.add(new Banks(rs.getInt("id"),rs.getString("bankName")));
            }
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @FXML
    private ComboBox<String> bankType;
    public void fillComboBanks(){
        banks.clear();
        bankType.getItems().clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `banks`");
            rs=pst.executeQuery();
            while (rs.next()){
                banks.add(new Banks(rs.getInt("id"),rs.getString("bankName")));

            }
            con.close();

            for (int i=0;i<banks.size();i++){
                bankType.getItems().add(banks.get(i).getBankName());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





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
    @FXML
    void selectBank(ActionEvent event) {
        int index= bankType.getSelectionModel().getSelectedIndex();
        idBank=banks.get(index).getIdBank();
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
                if (rs.getInt("gasa")==0){
                    garanteeAddPrivilege.setDisable(true);
                    garanteeAddPrivilege1.setDisable(true);

                }else{
                    garanteeAddPrivilege.setDisable(false);
                    garanteeAddPrivilege1.setDisable(false);

                }
                if (rs.getInt("gasd")==0){
                    garanteeDeletePrivilege.setDisable(true);
                    garanteeDeletePrivilege1.setDisable(true);

                }else{
                    garanteeDeletePrivilege.setDisable(false);
                    garanteeDeletePrivilege1.setDisable(false);

                }
                if (rs.getInt("gase")==0){
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
            pst=con.prepareStatement("SELECT * FROM `garantees` WHERE `garanteeNumber`=? OR `idProject`=?");
            pst.setString(1,garanteeNumber.getText());
            pst.setInt(2,idProject);
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
        if (garanteeNumber.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()||garanteeType.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `garantees`(`areaId`, `locationId`,`idProject`, `garanteeNumber`, `garanteeType`, `bankId`, `garanteePrice`) VALUES (?,?,?,?,?,?,?)");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setInt(3,idProject);
                pst.setString(4,garanteeNumber.getText());
                pst.setString(5,garanteeType.getValue());
                pst.setInt(6,idBank);
                pst.setDouble(7,Double.valueOf(garanteePrice.getText()));
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable();
            areaName.getItems().clear();
            locationName.getItems().clear();
            projectName.getItems().clear();
            garanteeNumber.clear();
            garanteePrice.clear();
            garanteeType.setItems(garantees);
            fillComboArea();
        }

    }
    @FXML
    private TableView<GaranteeForTable> garanteeTableView;

    @FXML
    private TableColumn<GaranteeForTable, String> areaNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> locationNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> bankTypeTable;

    @FXML
    private TableColumn<GaranteeForTable, String> garanteePriceTable;

    @FXML
    private TableColumn<GaranteeForTable, String> projectNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> garanteeNumberTable;

    @FXML
    private TableColumn<GaranteeForTable, String> garanteeTypeTable;

    @FXML
    private TableView<HistoricalGaranteeForTable> historicalGaranteeTableView;

    @FXML
    private TableColumn<HistoricalGaranteeForTable, String> dateHistoricalTable;

    @FXML
    private TableColumn<HistoricalGaranteeForTable, String> descriptionTable;

    @FXML
    private TableColumn<HistoricalGaranteeForTable, String> nnameUserTable;
    ObservableList garanteesTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        fillComboBanks();
        garanteeType.setItems(garantees);


        addToTable();
        garanteePriceTable.setCellValueFactory(new PropertyValueFactory<>("garanteePrice"));
        bankTypeTable.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        garanteeNumberTable.setCellValueFactory(new PropertyValueFactory<>("garanteeNumber"));
        garanteeTypeTable.setCellValueFactory(new PropertyValueFactory<>("garanteeType"));
        garanteeTableView.setItems(garanteesTable);
        addToTableBank();
        bankNameTable.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        bankTableView.setItems(banks);
    }
    public void addToTable(){
        garanteesTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `garantees`,`areas`,`locations`,`projects`,`banks` WHERE garantees.areaId=areas.id AND garantees.locationId=locations.id AND garantees.idProject=projects.id AND garantees.bankId=banks.id AND garantees.historiser=0");
            rs=pst.executeQuery();
            while (rs.next()){
                garanteesTable.add(new GaranteeForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject"),rs.getInt("bankId"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("garanteeNumber"),rs.getString("garanteeType"),rs.getString("bankName"),rs.getDouble("garanteePrice")));
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
        int idDelete=garanteeTableView.getItems().get(index).getIdGarantee();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `garantees` WHERE `id`=?");
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
                pst=con.prepareStatement("SELECT * FROM  `garantees`,`areas`,`locations`,`projects`,`banks` WHERE garantees.areaId=areas.id AND garantees.locationId=locations.id AND garantees.idProject=projects.id AND garantees.bankId=banks.id AND garantees.historiser=0 AND garantees.garanteeNumber LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    garanteesTable.add(new GaranteeForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject"),rs.getInt("bankId"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("contractName"),rs.getString("garanteeNumber"),rs.getString("garanteeType"),rs.getString("bankName"),rs.getDouble("garanteePrice")));
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
        int idEdit=garanteeTableView.getItems().get(index).getIdGarantee();


        if (garanteeEditPrivilege.getText().contains("تعديل ضمان")){
            garanteeEditPrivilege.setText("حفظ");
            areaName.setValue(garanteeTableView.getItems().get(index).getNameArea());
            locationName.setValue(garanteeTableView.getItems().get(index).getNameLocation());
            projectName.setValue(garanteeTableView.getItems().get(index).getNameProject());
            garanteeType.setValue(garanteeTableView.getItems().get(index).getGaranteeType());
            garanteeNumber.setText(garanteeTableView.getItems().get(index).getGaranteeNumber());
            garanteePrice.setText(String.valueOf(garanteeTableView.getItems().get(index).getGaranteePrice()));
            bankType.setValue(garanteeTableView.getItems().get(index).getBankName());

        }else if (garanteeEditPrivilege.getText().contains("حفظ")){
            int dejaExist=0;
            int size=0;
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `garantees` WHERE (`garanteeNumber`=? OR `idProject`=?) AND id!=?");
                pst.setString(1,garanteeNumber.getText());
                pst.setInt(2,idProject);
                pst.setInt(3,idEdit);

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
            if (garanteeNumber.getText().isEmpty()||garanteePrice.getText().isEmpty()||areaName.getSelectionModel().isEmpty()||locationName.getSelectionModel().isEmpty()||projectName.getSelectionModel().isEmpty()||garanteeType.getSelectionModel().isEmpty()||bankType.getSelectionModel().isEmpty()){
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

                    for (int i=0; i<banks.size() ;i++){
                        if (banks.get(i).getBankName()==bankType.getValue()){
                            idBank=banks.get(i).getIdBank();
                        }
                    }
                    con = new ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `garantees` SET `areaId`=?,`locationId`=?,`idProject`=?,`garanteeNumber`=?,`garanteeType`=?,`bankId`=?,`garanteePrice`=? WHERE `id`=?");


                    pst.setInt(1,idArea);
                    pst.setInt(2,idLocation);
                    pst.setInt(3,idProject);
                    pst.setString(4,garanteeNumber.getText());
                    pst.setString(5,garanteeType.getValue());
                    pst.setInt(6,idBank);
                    pst.setDouble(7,Double.valueOf(garanteePrice.getText()));
                    pst.setInt(8,idEdit);
                    pst.execute();
                    garanteeEditPrivilege.setText("تعديل ضمان");
                    locationName.getItems().clear();
                    projectName.getItems().clear();
                    garanteeNumber.clear();
                    garanteePrice.clear();
                    garanteeType.setItems(garantees);
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


    ObservableList<HistoricalGaranteeForTable> historicalGarantee= FXCollections.observableArrayList();

    @FXML
    private TextArea description;

    @FXML
    void idReset(MouseEvent event) {
        garanteeEditPrivilege.setText("تعديل ضمان");
        areaName.setValue("");
        locationName.setValue("");
        projectName.setValue("");
        garanteeNumber.clear();
        garanteeType.setValue("");
        garanteeType.setItems(garantees);

        int index= garanteeTableView.getSelectionModel().getSelectedIndex();
        idGarantee=garanteeTableView.getItems().get(index).getIdGarantee();
        fillTableHistoricalGarantee();
        dateHistoricalTable.setCellValueFactory(new PropertyValueFactory<>("dateHistorical"));
        descriptionTable.setCellValueFactory(new PropertyValueFactory<>("description"));
        nnameUserTable.setCellValueFactory(new PropertyValueFactory<>("nameUser"));
        historicalGaranteeTableView.setItems(historicalGarantee);
    }
    public void fillTableHistoricalGarantee(){
        historicalGarantee.clear();
        historicalGaranteeTableView.getItems().clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `historicalgarantees` WHERE `idGarantee`=?");
            pst.setInt(1,idGarantee);
            rs=pst.executeQuery();
            while (rs.next()){
                historicalGarantee.add(new HistoricalGaranteeForTable(rs.getInt("id"),idConnected,rs.getInt("idGarantee"),rs.getString("dateHistorical"),rs.getString("description"),rs.getString("idUser")));

            }
            pst.close();


        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void selectIdHistorical(MouseEvent mouseEvent) {
        int index=historicalGaranteeTableView.getSelectionModel().getSelectedIndex();
        idHistorical=historicalGaranteeTableView.getItems().get(index).getIdHistorical();
        garanteeEditPrivilege1.setText("تعديل تحديث");
        description.clear();
    }

    public void addHistorical(ActionEvent actionEvent) {
        if (description.getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else{
            if (idGarantee>0) {
                try {
                    con=new ConnectDB().getConnection();
                    pst=con.prepareStatement("INSERT INTO `historicalgarantees`(`dateHistorical`, `description`, `idUser`, `idGarantee`) VALUES (?,?,?,?)");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );

                    pst.setString(1, sdf.format(new Date()));
                    pst.setString(2,description.getText());
                    pst.setString(3,employeeNameConnected);
                    pst.setInt(4,idGarantee);
                    pst.execute();
                    warningMsg("إظافة","تمت الإظافة بنجاح");
                    pst.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("إظافة","حدث خطأ أثناء الإظافة");
                }
                fillTableHistoricalGarantee();
            }
        }


    }
    @FXML
    public void deleteRow2(ActionEvent actionEvent) {
        int index= historicalGaranteeTableView.getSelectionModel().getSelectedIndex();
        int idDelete=historicalGaranteeTableView.getItems().get(index).getIdHistorical();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `historicalgarantees` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");

            }
            idDelete=0;
            fillTableHistoricalGarantee();
        }
    }

    @FXML
    private Button garanteeEditPrivilege1;
    public void edit2(ActionEvent actionEvent) {

        int index= historicalGaranteeTableView.getSelectionModel().getSelectedIndex();
        int idEdit= historicalGaranteeTableView.getItems().get(index).getIdHistorical();

        if (garanteeEditPrivilege1.getText().contains("تعديل تحديث")){
            garanteeEditPrivilege1.setText("حفظ");
            description.setText(historicalGaranteeTableView.getItems().get(index).getDescription());
        }else if (garanteeEditPrivilege1.getText().contains("حفظ")){
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("UPDATE `historicalgarantees` SET `description`=? WHERE `id`=?");
                pst.setString(1, description.getText());
                pst.setInt(2, idEdit);
                pst.execute();
                warningMsg("تعديل","تم التعديل بنجاح");
                garanteeEditPrivilege1.setText("تعديل تحديث");
                pst.close();
                description.clear();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("تعديل","حدث خطأ أثناء التعديل");
            }
            fillTableHistoricalGarantee();
            idEdit=0;
        }


    }



    @FXML
    private AnchorPane anchorHistoriser;

    @FXML
    private TextField historiserNumber;

    @FXML
    private DatePicker historiserDate;

    @FXML
    void hideHistoriser(ActionEvent event) {
        anchorHistoriser.setVisible(false);
    }
    int idhistoriser=0;
    @FXML
    void showHistoriser(ActionEvent event) {
        int index= garanteeTableView.getSelectionModel().getSelectedIndex();
        if (index>=0){
            anchorHistoriser.setVisible(true);
        }else{
            warningMsg("تحذير","يرجى إختيار الضمان");

        }
    }

    @FXML
    void historiser(ActionEvent event) {
        int index= garanteeTableView.getSelectionModel().getSelectedIndex();
        int idEdit= garanteeTableView.getItems().get(index).getIdGarantee();
        if (historiserNumber.getText().isEmpty()||historiserDate.getEditor().getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else{
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("UPDATE `garantees` SET `historiser`=?,`historiserNumber`=?,`historiserDate`=? WHERE `id`=?");
                pst.setInt(1, 1);
                pst.setString(2, historiserNumber.getText());
                pst.setString(3, historiserDate.getEditor().getText());
                pst.setInt(4, idEdit);
                pst.execute();
                anchorHistoriser.setVisible(false);
                warningMsg("تعديل","تم إنهاء الضمان بنجاح");
                pst.close();
                addToTable();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("تعديل","حدث خطأ أثناء إنهاء الضمان");
            }
        }
    }

}
