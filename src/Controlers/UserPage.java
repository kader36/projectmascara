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
import sun.security.provider.MD5;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<PrivilegeForTable> privileges= FXCollections.observableArrayList();
    int idArea=0,idLocation=0,privilegesId=0;


    @FXML
    private TextField employeeName;

    @FXML
    private ComboBox<String> areaName;

    @FXML
    private ComboBox<String> locationName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField employeeNumber;
    @FXML
    private ComboBox<String> privilegeName;
    @FXML
    private TextField password;


    public void fillComboPrivilege(){
        privileges.clear();
        privilegeName.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `privileges`");
            rs=pst.executeQuery();
            while (rs.next()){
                privilegesTable.add(new PrivilegeForTable(rs.getInt("id"),rs.getInt("arss"),rs.getInt("arsa"),rs.getInt("arsd"),rs.getInt("arse"),rs.getInt("loss"),rs.getInt("losa"),rs.getInt("losd"),rs.getInt("lose"),rs.getInt("prss"),rs.getInt("prsa"),rs.getInt("prsd"),rs.getInt("prse"),rs.getInt("prst"),rs.getInt("prsi"),rs.getInt("prma"),rs.getInt("prmd"),rs.getInt("prme"),rs.getInt("prmt"),rs.getInt("prmi"),rs.getInt("gass"),rs.getInt("gasa"),rs.getInt("gasd"),rs.getInt("gase"),rs.getInt("ocss"),rs.getInt("ocsa"),rs.getInt("ocsd"),rs.getInt("ocse"),rs.getInt("emss"),rs.getInt("emsa"),rs.getInt("emsd"),rs.getInt("emse"),rs.getInt("abss"),rs.getInt("absa"),rs.getInt("absd"),rs.getInt("abse"),rs.getInt("abma"),rs.getInt("abmd"),rs.getInt("abme"),rs.getInt("dess"),rs.getInt("desa"),rs.getInt("desd"),rs.getInt("dese"),rs.getInt("dema"),rs.getInt("demd"),rs.getInt("deme"),rs.getInt("pess"),rs.getInt("pesa"),rs.getInt("pesd"),rs.getInt("pese"),rs.getInt("pema"),rs.getInt("pemd"),rs.getInt("peme"),rs.getInt("usss"),rs.getInt("ussa"),rs.getInt("ussd"),rs.getInt("usse"),rs.getInt("res"),rs.getString("privilegeName")));

            }
            pst.close();

            for (int i=0;i<privileges.size();i++){
                privilegeName.getItems().add(privileges.get(i).getPrivilegeNamee());
            }

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
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
            pst.close();

        } catch (SQLException throwables) {
            System.out.println("No Connection with DB");
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
                if (rs.getInt("ussa")==0){
                    userAddPrivilege.setDisable(true);
                    userAddPrivilege1.setDisable(true);

                }else{
                    userAddPrivilege.setDisable(false);
                    userAddPrivilege1.setDisable(false);

                }
                if (rs.getInt("ussd")==0){
                    userDeletePrivilege.setDisable(true);
                    userDeletePrivilege1.setDisable(true);

                }else{
                    userDeletePrivilege.setDisable(false);
                    userDeletePrivilege1.setDisable(false);

                }
                if (rs.getInt("usse")==0){
                    userEditPrivilege.setDisable(true);
                    userEditPrivilege1.setDisable(true);

                }else{
                    userEditPrivilege.setDisable(false);
                    userEditPrivilege1.setDisable(false);

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
    void selectArea(ActionEvent event) {
        int index= areaName.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        fillComboLocation();
    }
    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();

    }

    @FXML
    void selectPrivileges(ActionEvent event) {
        int index = privilegeName.getSelectionModel().getSelectedIndex();
        privilegesId = privileges.get(index).getIdPrivilege();
    }
    public String hashString(String s) throws NoSuchAlgorithmException {
        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(s.getBytes());

        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                sb.append(0);
                sb.append(hex.charAt(hex.length() - 1));
            } else {
                sb.append(hex.substring(hex.length() - 2));
            }
        }
        return sb.toString();
    }
    public void addUser(ActionEvent actionEvent) {
        int dejaExist=0;
        int size=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `users` WHERE `username`=?");
            pst.setString(1,username.getText());
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
        if (employeeName.getText().isEmpty() ||username.getText().isEmpty() ||password.getText().isEmpty()||employeeNumber.getText().isEmpty() ||email.getText().isEmpty() ||phoneNumber.getText().isEmpty() || privilegeName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `users`(`employeeName`, `username`, `password`, `email`," +
                        " `phoneNumber`, `employeeNumber`, `privilegesId`) VALUES (?,?,?,?,?,?,?)");
                pst.setString(1,employeeName.getText());
                pst.setString(2,username.getText());
                pst.setString(3, hashString(password.getText()));
                pst.setString(4,email.getText());
                pst.setString(5,phoneNumber.getText());
                pst.setString(6,employeeNumber.getText());
                pst.setInt(7,privilegesId);
                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");
            } catch (SQLException | NoSuchAlgorithmException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable();
        }

    }

    @FXML
    private TableView<UserForTable> userTableView;

    @FXML
    private TableColumn<UserForTable, String> employeeNameTable;

    @FXML
    private TableColumn<UserForTable, String> areaNameTable;

    @FXML
    private TableColumn<UserForTable, String> locationNameTable;

    @FXML
    private TableColumn<UserForTable, String> usernameTable;

    @FXML
    private TableColumn<UserForTable, String> emailTable;

    @FXML
    private TableColumn<UserForTable, String> phoneNumberTable;

    @FXML
    private TableColumn<UserForTable, String> employeeNumberTable;

    @FXML
    private TableColumn<UserForTable, String> privilegeNameTable;

    ObservableList usersTable= FXCollections.observableArrayList();


    @FXML
    private TableView<PrivilegeForTable> privilegesTableView;

    @FXML
    private TableColumn<PrivilegeForTable, String> privilegeNameeTable;
    ObservableList privilegesTable= FXCollections.observableArrayList();

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            fillComboPrivilege();
            addToTable();
            addToTable2();
            employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
            usernameTable.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailTable.setCellValueFactory(new PropertyValueFactory<>("email"));
            phoneNumberTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
            privilegeNameTable.setCellValueFactory(new PropertyValueFactory<>("privilegeName"));
            privilegeNameeTable.setCellValueFactory(new PropertyValueFactory<>("privilegeNamee"));
            userTableView.setItems(usersTable);
            privilegesTableView.setItems(privilegesTable);
        }
        public void addToTable(){
            usersTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `users`,`privileges` WHERE users.privilegesId=privileges.id ");
                rs=pst.executeQuery();
                while (rs.next()){
                    usersTable.add(new UserForTable(rs.getInt("id"),rs.getInt("privilegesId"),rs.getString("employeeName"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("employeeNumber"),rs.getString("privilegeName")));

                }
                pst.close();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    public void addToTable2(){
        privilegesTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `privileges`");
            rs=pst.executeQuery();
            while (rs.next()){
                privilegesTable.add(new PrivilegeForTable(rs.getInt("id"),rs.getInt("arss"),rs.getInt("arsa"),rs.getInt("arsd"),rs.getInt("arse"),rs.getInt("loss"),rs.getInt("losa"),rs.getInt("losd"),rs.getInt("lose"),rs.getInt("prss"),rs.getInt("prsa"),rs.getInt("prsd"),rs.getInt("prse"),rs.getInt("prst"),rs.getInt("prsi"),rs.getInt("prma"),rs.getInt("prmd"),rs.getInt("prme"),rs.getInt("prmt"),rs.getInt("prmi"),rs.getInt("gass"),rs.getInt("gasa"),rs.getInt("gasd"),rs.getInt("gase"),rs.getInt("ocss"),rs.getInt("ocsa"),rs.getInt("ocsd"),rs.getInt("ocse"),rs.getInt("emss"),rs.getInt("emsa"),rs.getInt("emsd"),rs.getInt("emse"),rs.getInt("abss"),rs.getInt("absa"),rs.getInt("absd"),rs.getInt("abse"),rs.getInt("abma"),rs.getInt("abmd"),rs.getInt("abme"),rs.getInt("dess"),rs.getInt("desa"),rs.getInt("desd"),rs.getInt("dese"),rs.getInt("dema"),rs.getInt("demd"),rs.getInt("deme"),rs.getInt("pess"),rs.getInt("pesa"),rs.getInt("pesd"),rs.getInt("pese"),rs.getInt("pema"),rs.getInt("pemd"),rs.getInt("peme"),rs.getInt("usss"),rs.getInt("ussa"),rs.getInt("ussd"),rs.getInt("usse"),rs.getInt("res"),rs.getString("privilegeName")));

            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public void deleteRow(ActionEvent actionEvent) {
        int index= userTableView.getSelectionModel().getSelectedIndex();
        int idDelete=userTableView.getItems().get(index).getIdUser();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `users` WHERE `id`=?");
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
    public void warningMsg(String title,String message ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    @FXML
    private TextField search;
    @FXML
    public void search(KeyEvent keyEvent) {
        String key=search.getText().trim();
        if (key.isEmpty()){
            addToTable();
            employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
            areaNameTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
            locationNameTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
            usernameTable.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailTable.setCellValueFactory(new PropertyValueFactory<>("email"));
            phoneNumberTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
            privilegeNameTable.setCellValueFactory(new PropertyValueFactory<>("privilegeName"));
            userTableView.setItems(usersTable);
        }else{
            usersTable.clear();
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `users`,`privileges` WHERE users.privilegesId=privileges.id AND `employeeName` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    usersTable.add(new UserForTable(rs.getInt("id"),rs.getInt("privilegesId"),rs.getString("employeeName"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("employeeNumber"),rs.getString("privilegeName")));
                }
                pst.close();

                employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
                usernameTable.setCellValueFactory(new PropertyValueFactory<>("username"));
                emailTable.setCellValueFactory(new PropertyValueFactory<>("email"));
                phoneNumberTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
                privilegeNameTable.setCellValueFactory(new PropertyValueFactory<>("privilegeName"));
                userTableView.setItems(usersTable);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

    @FXML
    private Button edit;
    public void edit(ActionEvent actionEvent) {
        int index= userTableView.getSelectionModel().getSelectedIndex();
        int idEdit=userTableView.getItems().get(index).getIdUser();
        int idArea=0,idLocation=0;


        if (userEditPrivilege.getText().contains("تعديل مستخدم")){
            userEditPrivilege.setText("حفظ");
            privilegeName.setValue(userTableView.getItems().get(index).getPrivilegeName());
            employeeName.setText(userTableView.getItems().get(index).getEmployeeName());
            username.setText(userTableView.getItems().get(index).getUsername());
            password.setText("");
            email.setText(userTableView.getItems().get(index).getEmail());
            phoneNumber.setText(userTableView.getItems().get(index).getPhoneNumber());
            employeeNumber.setText(userTableView.getItems().get(index).getEmployeeNumber());
        }else if (userEditPrivilege.getText().contains("حفظ")){
            int dejaExist=0;
            int size=0;
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `users` WHERE `username`=? AND id!=?");
                pst.setString(1,username.getText());
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
            if (employeeName.getText().isEmpty() ||username.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||email.getText().isEmpty() ||phoneNumber.getText().isEmpty() || privilegeName.getSelectionModel().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(dejaExist==1){
                warningMsg("تنبيه","المعلومات موجودة من قبل");
            }else{
                if (!password.getText().isEmpty()){
                    try {

                        for (int i=0; i<privileges.size() ;i++){
                            if (privileges.get(i).getPrivilegeNamee()==privilegeName.getValue()){
                                privilegesId=privileges.get(i).getIdPrivilege();
                            }
                        }

                        con = new ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `users` SET  `employeeName`=?,`username`=?,`password`=?,`email`=?,`phoneNumber`=?,`employeeNumber`=?,`privilegesId`=? WHERE `id`=?");
                        pst.setString(1, employeeName.getText());
                        pst.setString(2, username.getText());
                        pst.setString(3, hashString(password.getText()));
                        pst.setString(4, email.getText());
                        pst.setString(5, phoneNumber.getText());
                        pst.setString(6, employeeNumber.getText());
                        pst.setInt(7,privilegesId );
                        pst.setInt(8, idEdit);
                        pst.execute();
                        pst.close();

                        userEditPrivilege.setText("تعديل مستخدم");
                        employeeName.clear();
                        password.clear();
                        email.clear();
                        phoneNumber.clear();
                        employeeNumber.clear();
                        username.clear();
                        warningMsg("تعديل","تم التعديل بنجاح");


                    } catch (SQLException | NoSuchAlgorithmException throwables) {
                        throwables.printStackTrace();
                        warningMsg("تعديل","حدث خطأ أثناء التعديل");
                    }
                }else{
                    try {

                        for (int i=0; i<privileges.size() ;i++){
                            if (privileges.get(i).getPrivilegeNamee()==privilegeName.getValue()){
                                privilegesId=privileges.get(i).getIdPrivilege();
                            }
                        }

                        con = new ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `users` SET  `employeeName`=?,`username`=?,`email`=?,`phoneNumber`=?,`employeeNumber`=?,`privilegesId`=? WHERE `id`=?");
                        pst.setString(1, employeeName.getText());
                        pst.setString(2, username.getText());
                        pst.setString(3, email.getText());
                        pst.setString(4, phoneNumber.getText());
                        pst.setString(5, employeeNumber.getText());
                        pst.setInt(6,privilegesId );
                        pst.setInt(7, idEdit);
                        pst.execute();
                        pst.close();

                        userEditPrivilege.setText("تعديل مستخدم");
                        employeeName.clear();
                        password.clear();
                        email.clear();
                        phoneNumber.clear();
                        employeeNumber.clear();
                        username.clear();
                        warningMsg("تعديل","تم التعديل بنجاح");


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
    @FXML
    void idReset(MouseEvent event) {
        userEditPrivilege.setText("تعديل مستخدم");
    }
    @FXML
    private TextField privilegeNamee;

    @FXML
    private CheckBox arsa;

    @FXML
    private CheckBox arss;

    @FXML
    private CheckBox arse;

    @FXML
    private CheckBox arsd;

    @FXML
    private CheckBox losa;

    @FXML
    private CheckBox loss;

    @FXML
    private CheckBox lose;

    @FXML
    private CheckBox losd;

    @FXML
    private CheckBox prsa;

    @FXML
    private CheckBox prss;

    @FXML
    private CheckBox prse;

    @FXML
    private CheckBox prsd;

    @FXML
    private CheckBox prsi;

    @FXML
    private CheckBox prst;

    @FXML
    private CheckBox prma;

    @FXML
    private CheckBox prme;

    @FXML
    private CheckBox prmd;

    @FXML
    private CheckBox prmi;

    @FXML
    private CheckBox prmt;

    @FXML
    private CheckBox gasa;

    @FXML
    private CheckBox gass;

    @FXML
    private CheckBox gase;

    @FXML
    private CheckBox gasd;

    @FXML
    private CheckBox ocsa;

    @FXML
    private CheckBox ocss;

    @FXML
    private CheckBox ocse;

    @FXML
    private CheckBox ocsd;

    @FXML
    private CheckBox emsa;

    @FXML
    private CheckBox emss;

    @FXML
    private CheckBox emse;

    @FXML
    private CheckBox emsd;

    @FXML
    private CheckBox absa;

    @FXML
    private CheckBox abss;

    @FXML
    private CheckBox abse;

    @FXML
    private CheckBox absd;

    @FXML
    private CheckBox abma;

    @FXML
    private CheckBox abme;

    @FXML
    private CheckBox abmd;

    @FXML
    private CheckBox desa;

    @FXML
    private CheckBox dess;

    @FXML
    private CheckBox dese;

    @FXML
    private CheckBox desd;

    @FXML
    private CheckBox dema;

    @FXML
    private CheckBox deme;

    @FXML
    private CheckBox demd;

    @FXML
    private CheckBox pesa;

    @FXML
    private CheckBox pess;

    @FXML
    private CheckBox pese;

    @FXML
    private CheckBox pesd;

    @FXML
    private CheckBox pema;

    @FXML
    private CheckBox peme;

    @FXML
    private CheckBox pemd;

    @FXML
    private CheckBox ussa;

    @FXML
    private CheckBox usss;

    @FXML
    private CheckBox usse;

    @FXML
    private CheckBox ussd;

    @FXML
    private CheckBox res;

    @FXML
    void addPriv(ActionEvent event) {
        int arssi=0,arsai=0,arsdi=0,arsei=0,lossi=0,losai=0,losdi=0,losei=0,prssi=0,prsai=0,prsdi=0,prsei=0,prsti=0,prsii=0,prmai=0,prmdi=0,prmei=0,prmti=0,prmii=0,gassi=0 ,gasai=0,gasdi=0,gasei=0,ocssi=0,ocsai=0,ocsdi=0,ocsei=0,emssi=0,emsai=0,emsdi=0,emsei=0,abssi=0,absai=0,absdi=0,absei=0,abmai=0,abmdi=0,abmei=0,dessi=0,desai=0,desdi=0,desei=0,demai=0,demdi=0,demei=0,pessi=0,pesai=0,pesdi=0,pesei=0,pemai=0,pemdi=0,pemei=0,usssi=0,ussai=0,ussdi=0,ussei=0,resi=0;

        if (arss.isSelected()){
            arssi=1;
        }
        if (arsa.isSelected()){
            arsai=1;
        }
        if (arsd.isSelected()){
            arsdi=1;
        }
        if (arse.isSelected()){
            arsei=1;
        }
        if (loss.isSelected()){
            lossi=1;
        }
        if (losa.isSelected()){
            losai=1;
        }
        if (losd.isSelected()){
            losdi=1;
        }
        if (lose.isSelected()){
            losei=1;
        }
        if (prss.isSelected()){
            prssi=1;
        }
        if (prsa.isSelected()){
            prsai=1;
        }
        if (prsd.isSelected()){
            prsdi=1;
        }
        if (prse.isSelected()){
            prsei=1;
        }
        if (prst.isSelected()){
            prsti=1;
        }
        if (prsi.isSelected()){
            prsii=1;
        }
        if (prma.isSelected()){
            prmai=1;
        }
        if (prmd.isSelected()){
            prmdi=1;
        }
        if (prme.isSelected()){
            prmei=1;
        }
        if (prmt.isSelected()){
            prmti=1;
        }
        if (prmi.isSelected()){
            prmii=1;
        }
        if (gass.isSelected()){
            gassi=1;
        }
        if (gasa.isSelected()){
            gasai=1;
        }
        if (gasd.isSelected()){
            gasdi=1;
        }
        if (gase.isSelected()){
            gasei=1;
        }
        if (ocss.isSelected()){
            ocssi=1;
        }
        if (ocsa.isSelected()){
            ocsai=1;
        }
        if (ocsd.isSelected()){
            ocsdi=1;
        }
        if (ocse.isSelected()){
            ocsei=1;
        }
        if (emss.isSelected()){
            emssi=1;
        }
        if (emsa.isSelected()){
            emsai=1;
        }
        if (emsd.isSelected()){
            emsdi=1;
        }
        if (emse.isSelected()){
            emsei=1;
        }
        if (abss.isSelected()){
            abssi=1;
        }
        if (absa.isSelected()){
            absai=1;
        }
        if (absd.isSelected()){
            absdi=1;
        }
        if (abse.isSelected()){
            absei=1;
        }
        if (abma.isSelected()){
            abmai=1;
        }
        if (abmd.isSelected()){
            abmdi=1;
        }
        if (abme.isSelected()){
            abmei=1;
        }
        if (dess.isSelected()){
            dessi=1;
        }
        if (desa.isSelected()){
            desai=1;
        }
        if (desd.isSelected()){
            desdi=1;
        }
        if (dese.isSelected()){
            desei=1;
        }
        if (dema.isSelected()){
            demai=1;
        }
        if (demd.isSelected()){
            demdi=1;
        }
        if (deme.isSelected()){
            demei=1;
        }
        if (pess.isSelected()){
            pessi=1;
        }
        if (pesa.isSelected()){
            pesai=1;
        }
        if (pesd.isSelected()){
            pesdi=1;
        }
        if (pese.isSelected()){
            pesei=1;
        }
        if (pema.isSelected()){
            pemai=1;
        }
        if (pemd.isSelected()){
            pemdi=1;
        }
        if (peme.isSelected()){
            pemei=1;
        }
        if (usss.isSelected()){
            usssi=1;
        }
        if (ussa.isSelected()){
            ussai=1;
        }
        if (ussd.isSelected()){
            ussdi=1;
        }
        if (usse.isSelected()){
            ussei=1;
        }
        if (res.isSelected()){
            resi=1;
        }

        int dejaExist2=0;
        int size2=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `privileges` WHERE `privilegeName`=?");
            pst.setString(1,privilegeNamee.getText());
            rs=pst.executeQuery();
            while(rs.next()){
                size2++;
            }
            if (size2>0){
                dejaExist2=1;
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (privilegeNamee.getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist2==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `privileges`(`privilegeName`, `arss`, `arsa`, `arsd`, `arse`, `loss`, `losa`, `losd`, `lose`, `prss`, `prsa`, `prsd`, `prse`, `prst`, `prsi`, `prma`, `prmd`, `prme`, `prmt`, `prmi`, `gass`, `gasa`, `gasd`, `gase`, `ocss`, `ocsa`, `ocsd`, `ocse`, `emss`, `emsa`, `emsd`, `emse`, `abss`, `absa`, `absd`, `abse`, `abma`, `abmd`, `abme`, `dess`, `desa`, `desd`, `dese`, `dema`, `demd`, `deme`, `pess`, `pesa`, `pesd`, `pese`, `pema`, `pemd`, `peme`, `usss`, `ussa`, `ussd`, `usse`, `res`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1,privilegeNamee.getText());
                pst.setInt(2,arssi);
                pst.setInt(3,arsai);
                pst.setInt(4,arsdi);
                pst.setInt(5,arsei);
                pst.setInt(6,lossi);
                pst.setInt(7,losai);
                pst.setInt(8,losdi);
                pst.setInt(9,losei);
                pst.setInt(10,prssi);
                pst.setInt(11,prsai);
                pst.setInt(12,prsdi);
                pst.setInt(13,prsei);
                pst.setInt(14,prsti);
                pst.setInt(15,prsii);
                pst.setInt(16,prmai);
                pst.setInt(17,prmdi);
                pst.setInt(18,prmei);
                pst.setInt(19,prmti);
                pst.setInt(20,prmii);
                pst.setInt(21,gassi);
                pst.setInt(22,gasai);
                pst.setInt(23,gasdi);
                pst.setInt(24,gasei);
                pst.setInt(25,ocssi);
                pst.setInt(26,ocsai);
                pst.setInt(27,ocsdi);
                pst.setInt(28,ocsei);
                pst.setInt(29,emssi);
                pst.setInt(30,emsai);
                pst.setInt(31,emsdi);
                pst.setInt(32,emsei);
                pst.setInt(33,abssi);
                pst.setInt(34,absai);
                pst.setInt(35,absdi);
                pst.setInt(36,absei);
                pst.setInt(37,abmai);
                pst.setInt(38,abmdi);
                pst.setInt(39,abmei);
                pst.setInt(40,dessi);
                pst.setInt(41,desai);
                pst.setInt(42,desdi);
                pst.setInt(43,desei);
                pst.setInt(44,demai);
                pst.setInt(45,demdi);
                pst.setInt(46,demei);
                pst.setInt(47,pessi);
                pst.setInt(48,pesai);
                pst.setInt(49,pesdi);
                pst.setInt(50,pesei);
                pst.setInt(51,pemai);
                pst.setInt(52,pemdi);
                pst.setInt(53,pemei);
                pst.setInt(54,usssi);
                pst.setInt(55,ussai);
                pst.setInt(56,ussdi);
                pst.setInt(57,ussei);
                pst.setInt(58,resi);
                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable2();
            privilegeNamee.clear();
            arss.setSelected(false);
            arsa.setSelected(false);
            arsd.setSelected(false);
            arse.setSelected(false);
            loss.setSelected(false);
            losa.setSelected(false);
            losd.setSelected(false);
            lose.setSelected(false);
            prss.setSelected(false);
            prsa.setSelected(false);
            prsd.setSelected(false);
            prse.setSelected(false);
            prst.setSelected(false);
            prsi.setSelected(false);
            prma.setSelected(false);
            prmd.setSelected(false);
            prme.setSelected(false);
            prmt.setSelected(false);
            prmi.setSelected(false);
            gass.setSelected(false);
            gasa.setSelected(false);
            gasd.setSelected(false);
            gase.setSelected(false);
            ocss.setSelected(false);
            ocsa.setSelected(false);
            ocsd.setSelected(false);
            ocse.setSelected(false);
            emss.setSelected(false);
            emsa.setSelected(false);
            emsd.setSelected(false);
            emse.setSelected(false);
            abss.setSelected(false);
            absa.setSelected(false);
            absd.setSelected(false);
            abse.setSelected(false);
            abma.setSelected(false);
            abmd.setSelected(false);
            abme.setSelected(false);
            dess.setSelected(false);
            desa.setSelected(false);
            desd.setSelected(false);
            dese.setSelected(false);
            dema.setSelected(false);
            demd.setSelected(false);
            deme.setSelected(false);
            pess.setSelected(false);
            pesa.setSelected(false);
            pesd.setSelected(false);
            pese.setSelected(false);
            pema.setSelected(false);
            pemd.setSelected(false);
            peme.setSelected(false);
            usss.setSelected(false);
            ussa.setSelected(false);
            ussd.setSelected(false);
            usse.setSelected(false);
            res.setSelected(false);

            fillComboPrivilege();
        }



    }
    public void deleteRow2(ActionEvent actionEvent) {
        int index= privilegesTableView.getSelectionModel().getSelectedIndex();
        int idDelete=privilegesTableView.getItems().get(index).getIdPrivilege();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `privileges` WHERE `id`=?");
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

    @FXML
    private Button edit2;
    public void edit2(ActionEvent actionEvent) {
        int index= privilegesTableView.getSelectionModel().getSelectedIndex();
        int idEdit=privilegesTableView.getItems().get(index).getIdPrivilege();


        if (userEditPrivilege1.getText().contains("تعديل الصلاحية")){
            userEditPrivilege1.setText("حفظ");
            privilegeNamee.setText(privilegesTableView.getItems().get(index).getPrivilegeNamee());
            if (privilegesTableView.getItems().get(index).getArss()==1){
                arss.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getArsa()==1){
                arsa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getArsd()==1){
                arsd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getArse()==1){
                arse.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getLoss()==1){
                loss.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getLosa()==1){
                losa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getLosd()==1){
                losd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getLose()==1){
                lose.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrss()==1){
                prss.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrsa()==1){
                prsa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrsd()==1){
                prsd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrse()==1){
                prse.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrst()==1){
                prst.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrsi()==1){
                prsi.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrma()==1){
                prma.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrmd()==1){
                prmd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrme()==1){
                prme.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrmt()==1){
                prmt.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrmi()==1){
                prmi.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getGass()==1){
                gass.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getGasa()==1){
                gasa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getGasd()==1){
                gasd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getGase()==1){
                gase.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getOcss()==1){
                ocss.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getOcsa()==1){
                ocsa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getOcsd()==1){
                ocsd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getOcse()==1){
                ocse.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getEmss()==1){
                emss.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getEmsa()==1){
                emsa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getEmsd()==1){
                emsd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getEmse()==1){
                emse.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getAbss()==1){
                abss.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getAbsa()==1){
                absa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getAbsd()==1){
                absd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getAbse()==1){
                abse.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getAbma()==1){
                abma.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getAbmd()==1){
                abmd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getAbme()==1){
                abme.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getDess()==1){
                dess.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getDesa()==1){
                desa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getDesd()==1){
                desd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getDese()==1){
                dese.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getDema()==1){
                dema.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getDemd()==1){
                demd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getDeme()==1){
                deme.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPess()==1){
                pess.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPesa()==1){
                pesa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPesd()==1){
                pesd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPese()==1){
                pese.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPema()==1){
                pema.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPemd()==1){
                pemd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPeme()==1){
                peme.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getUsss()==1){
                usss.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getUssa()==1){
                ussa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getUssd()==1){
                ussd.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getUsse()==1){
                usse.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getRes()==1){
                res.setSelected(true);
            }
        }else if (userEditPrivilege1.getText().contains("حفظ")){
            int dejaExist2=0;
            int size2=0;
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `privileges` WHERE `privilegeName`=? AND id !=?");
                pst.setString(1,privilegeNamee.getText());
                pst.setInt(2,idEdit);

                rs=pst.executeQuery();
                while(rs.next()){
                    size2++;
                }
                if (size2>0){
                    dejaExist2=1;
                }
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (privilegeNamee.getText().isEmpty()){
                warningMsg("تنبيه","يرجى ملء الفراغات");
            }else if(dejaExist2==1){
                warningMsg("تنبيه","المعلومات موجودة من قبل");
            }else{
                try {
                    int arssi=0,arsai=0,arsdi=0,arsei=0,lossi=0,losai=0,losdi=0,losei=0,prssi=0,prsai=0,prsdi=0,prsei=0,prsti=0,prsii=0,prmai=0,prmdi=0,prmei=0,prmti=0,prmii=0,gassi=0 ,gasai=0,gasdi=0,gasei=0,ocssi=0,ocsai=0,ocsdi=0,ocsei=0,emssi=0,emsai=0,emsdi=0,emsei=0,abssi=0,absai=0,absdi=0,absei=0,abmai=0,abmdi=0,abmei=0,dessi=0,desai=0,desdi=0,desei=0,demai=0,demdi=0,demei=0,pessi=0,pesai=0,pesdi=0,pesei=0,pemai=0,pemdi=0,pemei=0,usssi=0,ussai=0,ussdi=0,ussei=0,resi=0;

                    if (arss.isSelected()){
                        arssi=1;
                    }
                    if (arsa.isSelected()){
                        arsai=1;
                    }
                    if (arsd.isSelected()){
                        arsdi=1;
                    }
                    if (arse.isSelected()){
                        arsei=1;
                    }
                    if (loss.isSelected()){
                        lossi=1;
                    }
                    if (losa.isSelected()){
                        losai=1;
                    }
                    if (losd.isSelected()){
                        losdi=1;
                    }
                    if (lose.isSelected()){
                        losei=1;
                    }
                    if (prss.isSelected()){
                        prssi=1;
                    }
                    if (prsa.isSelected()){
                        prsai=1;
                    }
                    if (prsd.isSelected()){
                        prsdi=1;
                    }
                    if (prse.isSelected()){
                        prsei=1;
                    }
                    if (prst.isSelected()){
                        prsti=1;
                    }
                    if (prsi.isSelected()){
                        prsii=1;
                    }
                    if (prma.isSelected()){
                        prmai=1;
                    }
                    if (prmd.isSelected()){
                        prmdi=1;
                    }
                    if (prme.isSelected()){
                        prmei=1;
                    }
                    if (prmt.isSelected()){
                        prmti=1;
                    }
                    if (prmi.isSelected()){
                        prmii=1;
                    }
                    if (gass.isSelected()){
                        gassi=1;
                    }
                    if (gasa.isSelected()){
                        gasai=1;
                    }
                    if (gasd.isSelected()){
                        gasdi=1;
                    }
                    if (gase.isSelected()){
                        gasei=1;
                    }
                    if (ocss.isSelected()){
                        ocssi=1;
                    }
                    if (ocsa.isSelected()){
                        ocsai=1;
                    }
                    if (ocsd.isSelected()){
                        ocsdi=1;
                    }
                    if (ocse.isSelected()){
                        ocsei=1;
                    }
                    if (emss.isSelected()){
                        emssi=1;
                    }
                    if (emsa.isSelected()){
                        emsai=1;
                    }
                    if (emsd.isSelected()){
                        emsdi=1;
                    }
                    if (emse.isSelected()){
                        emsei=1;
                    }
                    if (abss.isSelected()){
                        abssi=1;
                    }
                    if (absa.isSelected()){
                        absai=1;
                    }
                    if (absd.isSelected()){
                        absdi=1;
                    }
                    if (abse.isSelected()){
                        absei=1;
                    }
                    if (abma.isSelected()){
                        abmai=1;
                    }
                    if (abmd.isSelected()){
                        abmdi=1;
                    }
                    if (abme.isSelected()){
                        abmei=1;
                    }
                    if (dess.isSelected()){
                        dessi=1;
                    }
                    if (desa.isSelected()){
                        desai=1;
                    }
                    if (desd.isSelected()){
                        desdi=1;
                    }
                    if (dese.isSelected()){
                        desei=1;
                    }
                    if (dema.isSelected()){
                        demai=1;
                    }
                    if (demd.isSelected()){
                        demdi=1;
                    }
                    if (deme.isSelected()){
                        demei=1;
                    }
                    if (pess.isSelected()){
                        pessi=1;
                    }
                    if (pesa.isSelected()){
                        pesai=1;
                    }
                    if (pesd.isSelected()){
                        pesdi=1;
                    }
                    if (pese.isSelected()){
                        pesei=1;
                    }
                    if (pema.isSelected()){
                        pemai=1;
                    }
                    if (pemd.isSelected()){
                        pemdi=1;
                    }
                    if (peme.isSelected()){
                        pemei=1;
                    }
                    if (usss.isSelected()){
                        usssi=1;
                    }
                    if (ussa.isSelected()){
                        ussai=1;
                    }
                    if (ussd.isSelected()){
                        ussdi=1;
                    }
                    if (usse.isSelected()){
                        ussei=1;
                    }
                    if (res.isSelected()){
                        resi=1;
                    }
                    con = new ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `privileges` SET `privilegeName`=?,`arss`=?,`arsa`=?,`arsd`=?,`arse`=?,`loss`=?,`losa`=?,`losd`=?,`lose`=?,`prss`=?,`prsa`=?,`prsd`=?,`prse`=?,`prst`=?,`prsi`=?,`prma`=?,`prmd`=?,`prme`=?,`prmt`=?,`prmi`=?,`gass`=?,`gasa`=?,`gasd`=?,`gase`=?,`ocss`=?,`ocsa`=?,`ocsd`=?,`ocse`=?,`emss`=?,`emsa`=?,`emsd`=?,`emse`=?,`abss`=?,`absa`=?,`absd`=?,`abse`=?,`abma`=?,`abmd`=?,`abme`=?,`dess`=?,`desa`=?,`desd`=?,`dese`=?,`dema`=?,`demd`=?,`deme`=?,`pess`=?,`pesa`=?,`pesd`=?,`pese`=?,`pema`=?,`pemd`=?,`peme`=?,`usss`=?,`ussa`=?,`ussd`=?,`usse`=?,`res`=? WHERE `id`=?");

                    pst.setString(1,privilegeNamee.getText());
                    pst.setInt(2,arssi);
                    pst.setInt(3,arsai);
                    pst.setInt(4,arsdi);
                    pst.setInt(5,arsei);
                    pst.setInt(6,lossi);
                    pst.setInt(7,losai);
                    pst.setInt(8,losdi);
                    pst.setInt(9,losei);
                    pst.setInt(10,prssi);
                    pst.setInt(11,prsai);
                    pst.setInt(12,prsdi);
                    pst.setInt(13,prsei);
                    pst.setInt(14,prsti);
                    pst.setInt(15,prsii);
                    pst.setInt(16,prmai);
                    pst.setInt(17,prmdi);
                    pst.setInt(18,prmei);
                    pst.setInt(19,prmti);
                    pst.setInt(20,prmii);
                    pst.setInt(21,gassi);
                    pst.setInt(22,gasai);
                    pst.setInt(23,gasdi);
                    pst.setInt(24,gasei);
                    pst.setInt(25,ocssi);
                    pst.setInt(26,ocsai);
                    pst.setInt(27,ocsdi);
                    pst.setInt(28,ocsei);
                    pst.setInt(29,emssi);
                    pst.setInt(30,emsai);
                    pst.setInt(31,emsdi);
                    pst.setInt(32,emsei);
                    pst.setInt(33,abssi);
                    pst.setInt(34,absai);
                    pst.setInt(35,absdi);
                    pst.setInt(36,absei);
                    pst.setInt(37,abmai);
                    pst.setInt(38,abmdi);
                    pst.setInt(39,abmei);
                    pst.setInt(40,dessi);
                    pst.setInt(41,desai);
                    pst.setInt(42,desdi);
                    pst.setInt(43,desei);
                    pst.setInt(44,demai);
                    pst.setInt(45,demdi);
                    pst.setInt(46,demei);
                    pst.setInt(47,pessi);
                    pst.setInt(48,pesai);
                    pst.setInt(49,pesdi);
                    pst.setInt(50,pesei);
                    pst.setInt(51,pemai);
                    pst.setInt(52,pemdi);
                    pst.setInt(53,pemei);
                    pst.setInt(54,usssi);
                    pst.setInt(55,ussai);
                    pst.setInt(56,ussdi);
                    pst.setInt(57,ussei);
                    pst.setInt(58,resi);
                    pst.setInt(59,idEdit);

                    pst.execute();
                    pst.close();

                    warningMsg("تعديل","تم التعديل بنجاح");
                    userEditPrivilege1.setText("تعديل الصلاحية");


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
                addToTable2();
                idEdit=0;
                privilegeNamee.clear();
                arss.setSelected(false);
                arsa.setSelected(false);
                arsd.setSelected(false);
                arse.setSelected(false);
                loss.setSelected(false);
                losa.setSelected(false);
                losd.setSelected(false);
                lose.setSelected(false);
                prss.setSelected(false);
                prsa.setSelected(false);
                prsd.setSelected(false);
                prse.setSelected(false);
                prst.setSelected(false);
                prsi.setSelected(false);
                prma.setSelected(false);
                prmd.setSelected(false);
                prme.setSelected(false);
                prmt.setSelected(false);
                prmi.setSelected(false);
                gass.setSelected(false);
                gasa.setSelected(false);
                gasd.setSelected(false);
                gase.setSelected(false);
                ocss.setSelected(false);
                ocsa.setSelected(false);
                ocsd.setSelected(false);
                ocse.setSelected(false);
                emss.setSelected(false);
                emsa.setSelected(false);
                emsd.setSelected(false);
                emse.setSelected(false);
                abss.setSelected(false);
                absa.setSelected(false);
                absd.setSelected(false);
                abse.setSelected(false);
                abma.setSelected(false);
                abmd.setSelected(false);
                abme.setSelected(false);
                dess.setSelected(false);
                desa.setSelected(false);
                desd.setSelected(false);
                dese.setSelected(false);
                dema.setSelected(false);
                demd.setSelected(false);
                deme.setSelected(false);
                pess.setSelected(false);
                pesa.setSelected(false);
                pesd.setSelected(false);
                pese.setSelected(false);
                pema.setSelected(false);
                pemd.setSelected(false);
                peme.setSelected(false);
                usss.setSelected(false);
                ussa.setSelected(false);
                ussd.setSelected(false);
                usse.setSelected(false);
                res.setSelected(false);

            }

        }



    }@FXML
    void idReset2(MouseEvent event) {
        userEditPrivilege1.setText("تعديل الصلاحية");
        privilegeNamee.clear();
        privilegeNamee.clear();
        arss.setSelected(false);
        arsa.setSelected(false);
        arsd.setSelected(false);
        arse.setSelected(false);
        loss.setSelected(false);
        losa.setSelected(false);
        losd.setSelected(false);
        lose.setSelected(false);
        prss.setSelected(false);
        prsa.setSelected(false);
        prsd.setSelected(false);
        prse.setSelected(false);
        prst.setSelected(false);
        prsi.setSelected(false);
        prma.setSelected(false);
        prmd.setSelected(false);
        prme.setSelected(false);
        prmt.setSelected(false);
        prmi.setSelected(false);
        gass.setSelected(false);
        gasa.setSelected(false);
        gasd.setSelected(false);
        gase.setSelected(false);
        ocss.setSelected(false);
        ocsa.setSelected(false);
        ocsd.setSelected(false);
        ocse.setSelected(false);
        emss.setSelected(false);
        emsa.setSelected(false);
        emsd.setSelected(false);
        emse.setSelected(false);
        abss.setSelected(false);
        absa.setSelected(false);
        absd.setSelected(false);
        abse.setSelected(false);
        abma.setSelected(false);
        abmd.setSelected(false);
        abme.setSelected(false);
        dess.setSelected(false);
        desa.setSelected(false);
        desd.setSelected(false);
        dese.setSelected(false);
        dema.setSelected(false);
        demd.setSelected(false);
        deme.setSelected(false);
        pess.setSelected(false);
        pesa.setSelected(false);
        pesd.setSelected(false);
        pese.setSelected(false);
        pema.setSelected(false);
        pemd.setSelected(false);
        peme.setSelected(false);
        usss.setSelected(false);
        ussa.setSelected(false);
        ussd.setSelected(false);
        usse.setSelected(false);
        res.setSelected(false);

    }

}



