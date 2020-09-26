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
                privileges.add(new PrivilegeForTable(rs.getInt("id"),rs.getInt("arsa"),rs.getInt("arde"),rs.getInt("losa"),rs.getInt("lode"),rs.getInt("prsa"),rs.getInt("prde"),rs.getInt("prsa1"),rs.getInt("prde1"),rs.getInt("grsa"),rs.getInt("grde"),rs.getInt("ocsa"),rs.getInt("ocde"),rs.getInt("emsa"),rs.getInt("emde"),rs.getInt("absa"),rs.getInt("abde"),rs.getInt("desa"),rs.getInt("dede"),rs.getInt("pesa"),rs.getInt("pede"),rs.getInt("ussa"),rs.getInt("usde"),rs.getInt("res"),rs.getString("privilegeName")));

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

                }else{
                    penaltyMenuButton.setDisable(false);

                }

                if (rs.getInt("ussa")==0){
                    userMenuButton.setDisable(true);
                    userAddPrivilege.setDisable(true);
                    userAddPrivilege1.setDisable(true);

                }else{
                    userMenuButton.setDisable(false);
                    userAddPrivilege.setDisable(false);
                    userAddPrivilege1.setDisable(false);

                }
                if (rs.getInt("usde")==0){
                    userDeletePrivilege.setDisable(true);
                    userDeletePrivilege1.setDisable(true);
                    userEditPrivilege.setDisable(true);
                    userEditPrivilege1.setDisable(true);

                }else{
                    userDeletePrivilege.setDisable(false);
                    userDeletePrivilege1.setDisable(false);
                    userEditPrivilege.setDisable(false);
                    userEditPrivilege1.setDisable(false);

                }

                if (rs.getInt("res")==0){
                    repportMenuButton.setDisable(true);

                }else{
                    repportMenuButton.setDisable(false);

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
                privilegesTable.add(new PrivilegeForTable(rs.getInt("id"),rs.getInt("arsa"),rs.getInt("arde"),rs.getInt("losa"),rs.getInt("lode"),rs.getInt("prsa"),rs.getInt("prde"),rs.getInt("prsa1"),rs.getInt("prde1"),rs.getInt("grsa"),rs.getInt("grde"),rs.getInt("ocsa"),rs.getInt("ocde"),rs.getInt("emsa"),rs.getInt("emde"),rs.getInt("absa"),rs.getInt("abde"),rs.getInt("desa"),rs.getInt("dede"),rs.getInt("pesa"),rs.getInt("pede"),rs.getInt("ussa"),rs.getInt("usde"),rs.getInt("res"),rs.getString("privilegeName")));

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
    private CheckBox arde;

    @FXML
    private CheckBox res;

    @FXML
    private CheckBox losa;

    @FXML
    private CheckBox lode;

    @FXML
    private CheckBox prsa;

    @FXML
    private CheckBox prde;

    @FXML
    private CheckBox prsa1;

    @FXML
    private CheckBox prde1;

    @FXML
    private CheckBox grsa;

    @FXML
    private CheckBox grde;

    @FXML
    private CheckBox ocsa;

    @FXML
    private CheckBox ocde;

    @FXML
    private CheckBox emsa;

    @FXML
    private CheckBox emde;

    @FXML
    private CheckBox absa;

    @FXML
    private CheckBox abde;

    @FXML
    private CheckBox desa;

    @FXML
    private CheckBox dede;

    @FXML
    private CheckBox pesa;

    @FXML
    private CheckBox pede;

    @FXML
    private CheckBox ussa;

    @FXML
    private CheckBox usde;

    @FXML
    void addPriv(ActionEvent event) {

        int arsai=0,ardei=0,losai=0,lodei=0,prsai=0,prdei=0,prsai1=0,prdei1=0,grsai=0,grdei=0,ocsai=0,ocdei=0,emsai=0,emdei=0,absai=0,abdei=0,desai=0,dedei=0,pesai=0,pedei=0,ussai=0,usdei=0,resi=0;

        if (arsa.isSelected()){
            arsai=1;
        }
        if (arde.isSelected()){
            ardei=1;
        }
        if (losa.isSelected()){
            losai=1;
        }

        if (lode.isSelected()){
            lodei=1;
        }
        if (prsa.isSelected()){
            prsai=1;
        }
        if (prde.isSelected()){
            prdei=1;
        }
        if (prsa1.isSelected()){
            prsai1=1;
        }
        if (prde1.isSelected()){
            prdei1=1;
        }
        if (grsa.isSelected()){
            grsai=1;
        }
        if (grde.isSelected()){
            grdei=1;
        }
        if (ocsa.isSelected()){
            ocsai=1;
        }
        if (ocde.isSelected()){
            ocdei=1;
        }

        if (emsa.isSelected()){
            emsai=1;
        }
        if (emde.isSelected()){
            emdei=1;
        }
        if (absa.isSelected()){
            absai=1;
        }

        if (abde.isSelected()){
            abdei=1;
        }
        if (desa.isSelected()){
            desai=1;
        }
        if (dede.isSelected()){
            dedei=1;
        }

        if (pesa.isSelected()){
            pesai=1;
        }
        if (pede.isSelected()){
            pedei=1;
        }
        if (ussa.isSelected()){
            ussai=1;
        }
        if (usde.isSelected()){
            usdei=1;
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
                pst=con.prepareStatement("INSERT INTO `privileges`(`privilegeName`, `arsa`, `arde`, `losa`, `lode`, " +
                        "`prsa`, `prde`,`prsa1`, `prde1`, `grsa`, `grde`, `ocsa`, `ocde`, `emsa`, `emde`, `absa`, `abde`, `desa`," +
                        " `dede`, `pesa`, `pede`, `ussa`, `usde`, `res`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1,privilegeNamee.getText());
                pst.setInt(2,arsai);
                pst.setInt(3,ardei);
                pst.setInt(4,losai);
                pst.setInt(5,lodei);
                pst.setInt(6,prsai);
                pst.setInt(7,prdei);
                pst.setInt(8,prsai1);
                pst.setInt(9,prdei1);
                pst.setInt(10,grsai);
                pst.setInt(11,grdei);
                pst.setInt(12,ocsai);
                pst.setInt(13,ocdei);
                pst.setInt(14,emsai);
                pst.setInt(15,emdei);
                pst.setInt(16,absai);
                pst.setInt(17,abdei);
                pst.setInt(18,desai);
                pst.setInt(19,dedei);
                pst.setInt(20,pesai);
                pst.setInt(21,pedei);
                pst.setInt(22,ussai);
                pst.setInt(23,usdei);
                pst.setInt(24,resi);
                pst.execute();
                pst.close();

                warningMsg("إظافة","تمت الإظافة بنجاح");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable2();
            privilegeNamee.clear();
            arsa.setSelected(false);
            arde.setSelected(false);
            losa.setSelected(false);
            lode.setSelected(false);
            prsa.setSelected(false);
            prde.setSelected(false);
            prsa1.setSelected(false);
            prde1.setSelected(false);
            ocsa.setSelected(false);
            ocde.setSelected(false);
            emsa.setSelected(false);
            emde.setSelected(false);
            absa.setSelected(false);
            abde.setSelected(false);
            desa.setSelected(false);
            dede.setSelected(false);
            pesa.setSelected(false);
            pede.setSelected(false);
            ussa.setSelected(false);
            usde.setSelected(false);
            res.setSelected(false);
            grsa.setSelected(false);
            grde.setSelected(false);
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
            if (privilegesTableView.getItems().get(index).getArsa()==1){
                arsa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getArde()==1){
                arde.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getLosa()==1){
                losa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getLode()==1){
                lode.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrsa()==1){
                prsa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrde()==1){
                prde.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrsa1()==1){
                prsa1.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPrde1()==1){
                prde1.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getOcsa()==1){
                ocsa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getOcde()==1){
                ocde.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getEmsa()==1){
                emsa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getEmde()==1){
                emde.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getAbsa()==1){
                absa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getAbde()==1){
                abde.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getDesa()==1){
                desa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getDede()==1){
                dede.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPesa()==1){
                pesa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getPede()==1){
                pede.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getUssa()==1){
                ussa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getUsde()==1){
                usde.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getRes()==1){
                res.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getGrsa()==1){
                grsa.setSelected(true);
            }
            if (privilegesTableView.getItems().get(index).getGrde()==1){
                grde.setSelected(true);
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
                    int arsai=0,ardei=0,losai=0,lodei=0,prsai=0,prdei=0,prsai1=0,prdei1=0,grsai=0,grdei=0,ocsai=0,ocdei=0,emsai=0,emdei=0,absai=0,abdei=0,desai=0,dedei=0,pesai=0,pedei=0,ussai=0,usdei=0,resi=0;

                    if (arsa.isSelected()){
                        arsai=1;
                    }
                    if (arde.isSelected()){
                        ardei=1;
                    }
                    if (losa.isSelected()){
                        losai=1;
                    }

                    if (lode.isSelected()){
                        lodei=1;
                    }
                    if (prsa.isSelected()){
                        prsai=1;
                    }
                    if (prde.isSelected()){
                        prdei=1;
                    }
                    if (prsa1.isSelected()){
                        prsai1=1;
                    }
                    if (prde1.isSelected()){
                        prdei1=1;
                    }
                    if (grsa.isSelected()){
                        grsai=1;
                    }
                    if (grde.isSelected()){
                        grdei=1;
                    }
                    if (ocsa.isSelected()){
                        ocsai=1;
                    }
                    if (ocde.isSelected()){
                        ocdei=1;
                    }

                    if (emsa.isSelected()){
                        emsai=1;
                    }
                    if (emde.isSelected()){
                        emdei=1;
                    }
                    if (absa.isSelected()){
                        absai=1;
                    }

                    if (abde.isSelected()){
                        abdei=1;
                    }
                    if (desa.isSelected()){
                        desai=1;
                    }
                    if (dede.isSelected()){
                        dedei=1;
                    }

                    if (pesa.isSelected()){
                        pesai=1;
                    }
                    if (pede.isSelected()){
                        pedei=1;
                    }
                    if (ussa.isSelected()){
                        ussai=1;
                    }
                    if (usde.isSelected()){
                        usdei=1;
                    }
                    if (res.isSelected()){
                        resi=1;
                    }
                    con = new ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `privileges` SET`privilegeName`=?,`arsa`=?,`arde`=?,`losa`=?,`lode`=?,`prsa`=?,`prde`=?,`prsa1`=?,`prde1`=?,`grsa`=?,`grde`=?,`ocsa`=?,`ocde`=?,`emsa`=?,`emde`=?,`absa`=?,`abde`=?,`desa`=?,`dede`=?,`pesa`=?,`pede`=?,`ussa`=?,`usde`=?,`res`=? WHERE `id`=?");

                    pst.setString(1,privilegeNamee.getText());
                    pst.setInt(2,arsai);
                    pst.setInt(3,ardei);
                    pst.setInt(4,losai);
                    pst.setInt(5,lodei);
                    pst.setInt(6,prsai);
                    pst.setInt(7,prdei);
                    pst.setInt(8,prsai1);
                    pst.setInt(9,prdei1);
                    pst.setInt(10,grsai);
                    pst.setInt(11,grdei);
                    pst.setInt(12,ocsai);
                    pst.setInt(13,ocdei);
                    pst.setInt(14,emsai);
                    pst.setInt(15,emdei);
                    pst.setInt(16,absai);
                    pst.setInt(17,abdei);
                    pst.setInt(18,desai);
                    pst.setInt(19,dedei);
                    pst.setInt(20,pesai);
                    pst.setInt(21,pedei);
                    pst.setInt(22,ussai);
                    pst.setInt(23,usdei);
                    pst.setInt(24,resi);
                    pst.setInt(25,idEdit);

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
                arsa.setSelected(false);
                arde.setSelected(false);
                losa.setSelected(false);
                lode.setSelected(false);
                prsa.setSelected(false);
                prde.setSelected(false);
                prsa1.setSelected(false);
                prde1.setSelected(false);
                ocsa.setSelected(false);
                ocde.setSelected(false);
                emsa.setSelected(false);
                emde.setSelected(false);
                absa.setSelected(false);
                abde.setSelected(false);
                desa.setSelected(false);
                dede.setSelected(false);
                pesa.setSelected(false);
                pede.setSelected(false);
                ussa.setSelected(false);
                usde.setSelected(false);
                res.setSelected(false);
                grsa.setSelected(false);
                grde.setSelected(false);
            }

        }



    }@FXML
    void idReset2(MouseEvent event) {
        userEditPrivilege1.setText("تعديل الصلاحية");
        privilegeNamee.clear();
            arsa.setSelected(false);
            arde.setSelected(false);
            losa.setSelected(false);
            lode.setSelected(false);
            prsa.setSelected(false);
            prde.setSelected(false);
            prsa1.setSelected(false);
            prde1.setSelected(false);
            ocsa.setSelected(false);
            ocde.setSelected(false);
            emsa.setSelected(false);
            emde.setSelected(false);
            absa.setSelected(false);
            abde.setSelected(false);
            desa.setSelected(false);
            dede.setSelected(false);
            pesa.setSelected(false);
            pede.setSelected(false);
            ussa.setSelected(false);
            usde.setSelected(false);
            res.setSelected(false);
            grsa.setSelected(false);
            grde.setSelected(false);
    }

}



