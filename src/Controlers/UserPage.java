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
import java.util.ResourceBundle;

public class UserPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    //ObservableList<String> privileges= FXCollections.observableArrayList("الافراج عن الضمان","تخفيض 5% من الضمان");
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
    private PasswordField password;


    public void fillComboArea(){
        try {
            con=new Controlers.ConnectDB().getConnection();
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
        int index= privilegeName.getSelectionModel().getSelectedIndex();
        //privilegesId=privileges.get(index).

    }
    public void addUser(ActionEvent actionEvent) {
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `users`(`employeeName`, `idArea`, `idLocation`, `username`, `password`, `email`," +
                    " `phoneNumber`, `employeeNumber`, `privilegesId`) VALUES (?,?,?,?,?,?,?,?,?)");
            pst.setString(1,employeeName.getText());
            pst.setInt(2,idArea);
            pst.setInt(3,idLocation);
            pst.setString(4,username.getText());
            pst.setString(5,password.getText());
            pst.setString(6,email.getText());
            pst.setString(7,phoneNumber.getText());
            pst.setString(8,employeeNumber.getText());
            pst.setInt(9,privilegesId);
            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable();
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
            fillComboArea();
            addToTable();
            addToTable2();
            employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
            areaNameTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
            locationNameTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
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
                pst=con.prepareStatement("SELECT * FROM `users`");
                rs=pst.executeQuery();
                while (rs.next()){
                    usersTable.add(new UserForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("privilegesId"),rs.getString("employeeName"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("employeeNumber"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getPrivilegeName(rs.getInt("privilegesId"))));

                }


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
                privilegesTable.add(new PrivilegeForTable(rs.getInt("id"),rs.getInt("arsa"),rs.getInt("arde"),rs.getInt("losa"),rs.getInt("lode"),rs.getInt("prsa"),rs.getInt("prde"),rs.getInt("grsa"),rs.getInt("grde"),rs.getInt("ocsa"),rs.getInt("ocde"),rs.getInt("emsa"),rs.getInt("emde"),rs.getInt("absa"),rs.getInt("abde"),rs.getInt("desa"),rs.getInt("dede"),rs.getInt("pesa"),rs.getInt("pede"),rs.getInt("ussa"),rs.getInt("usde"),rs.getInt("res"),rs.getString("privilegeName")));

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
    public String getPrivilegeName(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `users` WHERE `privilegesId`=?");
            pst.setInt(1,id);

            rs=pst.executeQuery();
            while (rs.next()){
                return result= String.valueOf(rs.getInt("privilegesId"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
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
                pst=con.prepareStatement("SELECT * FROM `users` WHERE `employeeName` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    usersTable.add(new UserForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("privilegesId"),rs.getString("employeeName"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("phoneNumber"),rs.getString("employeeNumber"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getPrivilegeName(rs.getInt("privilegesId"))));
                }
                employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
                areaNameTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
                locationNameTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
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


        if (edit.getText().contains("تعديل مستخدم")){
            edit.setText("حفظ");
            areaName.setValue(userTableView.getItems().get(index).getAreaName());
            locationName.setValue(userTableView.getItems().get(index).getLocationName());
            employeeName.setText(userTableView.getItems().get(index).getEmployeeName());
            username.setText(userTableView.getItems().get(index).getUsername());
            password.setText(userTableView.getItems().get(index).getPassword());
            email.setText(userTableView.getItems().get(index).getEmail());
            phoneNumber.setText(userTableView.getItems().get(index).getPhoneNumber());
            employeeNumber.setText(userTableView.getItems().get(index).getEmployeeNumber());
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
//                for (int i=0; i<employees.size() ;i++){
//                    if (employees.get(i).getEmployeeName()==employeeName.getValue()){
//                        idEmployee=employees.get(i).getId();
//                    }
//                }

                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("UPDATE `users` SET `idArea`=?,`idLocation`=?, `employeeName`=?,`username`=?,`password`=?,`email`=?,`phoneNumber`=?,`employeeNumber`=?,`privilegesId`=? WHERE `id`=?");
                pst.setInt(1, idArea);
                pst.setInt(2, idLocation);
                pst.setString(3, employeeName.getText());
                pst.setString(4, username.getText());
                pst.setString(5, password.getText());
                pst.setString(6, email.getText());
                pst.setString(7, phoneNumber.getText());
                pst.setString(8, employeeNumber.getText());
                pst.setString(9, "0");
                pst.setInt(10, idEdit);
                pst.execute();
                edit.setText("تعديل مستخدم");
                locationName.getItems().clear();
                areaName.getItems().clear();
                employeeName.clear();
                password.clear();
                email.clear();
                phoneNumber.clear();
                employeeNumber.clear();
                username.clear();
                fillComboArea();



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            addToTable();
            idEdit=0;
        }


    }
    @FXML
    void idReset(MouseEvent event) {
        edit.setText("تعديل مستخدم");
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

        int arsai=0,ardei=0,losai=0,lodei=0,prsai=0,prdei=0,grsai=0,grdei=0,ocsai=0,ocdei=0,emsai=0,emdei=0,absai=0,abdei=0,desai=0,dedei=0,pesai=0,pedei=0,ussai=0,usdei=0,resi=0;

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
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `privileges`(`privilegeName`, `arsa`, `arde`, `losa`, `lode`, " +
                    "`prsa`, `prde`, `grsa`, `grde`, `ocsa`, `ocde`, `emsa`, `emde`, `absa`, `abde`, `desa`," +
                    " `dede`, `pesa`, `pede`, `ussa`, `usde`, `res`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,privilegeNamee.getText());
            pst.setInt(2,arsai);
            pst.setInt(3,ardei);
            pst.setInt(4,losai);
            pst.setInt(5,lodei);
            pst.setInt(6,prsai);
            pst.setInt(7,prdei);
            pst.setInt(8,grsai);
            pst.setInt(9,grdei);
            pst.setInt(10,ocsai);
            pst.setInt(11,ocdei);
            pst.setInt(12,emsai);
            pst.setInt(13,emdei);
            pst.setInt(14,absai);
            pst.setInt(15,abdei);
            pst.setInt(16,desai);
            pst.setInt(17,dedei);
            pst.setInt(18,pesai);
            pst.setInt(19,pedei);
            pst.setInt(20,ussai);
            pst.setInt(21,usdei);
            pst.setInt(22,resi);
            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable2();

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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idDelete=0;
            addToTable2();
        }
    }

}



