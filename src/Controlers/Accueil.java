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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Accueil implements Initializable {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
    ObservableList<Notification> notifications= FXCollections.observableArrayList();
    ObservableList<Notification> notifications1= FXCollections.observableArrayList();
    ObservableList<Notification> notifications2= FXCollections.observableArrayList();
    Date nowDate,nowDate1,nowDate2;
    public void addToTable(){
        notifications.clear();
        Calendar now=Calendar.getInstance();
        if ((now.get(Calendar.MONTH)+2)<=12){
            nowDate= Date.valueOf(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+2)+"-"+now.get(Calendar.DATE));

        }else{
            int a=now.get(Calendar.MONTH)+2-12;
            System.out.println(a);
            nowDate= Date.valueOf((now.get(Calendar.YEAR)+1)+"-"+a+"-"+now.get(Calendar.DATE));
        }
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees` WHERE `residenceEndDate` <= ?");
            pst.setString(1,nowDate.toString());
            rs=pst.executeQuery();
            while (rs.next()){
                notifications.add(new Notification(rs.getInt("id"),rs.getString("employeeName"),"إنتهاء صلاحية بطاقة إقامة",rs.getString("residenceEndDate")));
            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void addToTable1(){
        notifications1.clear();
        Calendar now=Calendar.getInstance();
        if ((now.get(Calendar.MONTH)+2)<=12){
            nowDate1= Date.valueOf(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+2)+"-"+now.get(Calendar.DATE));

        }else{
            int a=now.get(Calendar.MONTH)+2-12;
            System.out.println(a);
            nowDate1= Date.valueOf((now.get(Calendar.YEAR)+1)+"-"+a+"-"+now.get(Calendar.DATE));
        }

        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees` WHERE `HealthCertificatEndDate` <= ?");
            pst.setString(1,nowDate1.toString());
            rs=pst.executeQuery();
            while (rs.next()){
                notifications1.add(new Notification(rs.getInt("id"),rs.getString("employeeName"),"إنتهاء صلاحية الشهادة الصحية",rs.getString("HealthCertificatEndDate")));
            }
            pst.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void addToTable2(){
        notifications2.clear();
        Calendar now=Calendar.getInstance();
        if ((now.get(Calendar.MONTH)+2)<=12){
            nowDate2= Date.valueOf(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+2)+"-"+now.get(Calendar.DATE));
        }else{
            int a=now.get(Calendar.MONTH)+2-12;
            System.out.println(a);
            nowDate2= Date.valueOf((now.get(Calendar.YEAR)+1)+"-"+a+"-"+now.get(Calendar.DATE));
        }

        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees` WHERE `ClassificationEndDate` <= ?");
            pst.setString(1,nowDate2.toString());
            rs=pst.executeQuery();
            while (rs.next()){
                notifications2.add(new Notification(rs.getInt("id"),rs.getString("employeeName"),"إنتهاء صلاحية شهادة تصنيف الهيئة",rs.getString("ClassificationEndDate")));
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



                if ((rs.getInt("res1")==1)||(rs.getInt("res2")==1)||(rs.getInt("res3")==1)||(rs.getInt("res4")==1)||(rs.getInt("res5")==1)||(rs.getInt("res6")==1)||(rs.getInt("res7")==1)||(rs.getInt("res8")==1)){
                    repportMenuButton.setDisable(false);

                }else{
                    repportMenuButton.setDisable(true);

                }
                if ((rs.getInt("prss")==0)&&(rs.getInt("prss1")==0)&&(rs.getInt("prms")==0)&&(rs.getInt("prrcs")==0)){
                    projectMenuButton.setDisable(true);

                }else{
                    projectMenuButton.setDisable(false);

                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
    private AnchorPane info;

    @FXML
    void showInfo(MouseEvent event) {
        if (info.isVisible()){
            info.setVisible(false);
        }else{
            info.setVisible(true);

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
    @FXML
    private TableView<Notification> notificationTableView;

    @FXML
    private TableColumn<Notification, String> endDate;

    @FXML
    private TableColumn<Notification, String> employeeName;

    @FXML
    private TableView<Notification> notificationTableView1;

    @FXML
    private TableColumn<Notification, String> endDate1;

    @FXML
    private TableColumn<Notification, String> employeeName1;

    @FXML
    private TableView<Notification> notificationTableView2;

    @FXML
    private TableColumn<Notification, String> endDate2;

    @FXML
    private TableColumn<Notification, String> employeeName2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addToTable();
        employeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("dateOfEnd"));
        notificationTableView.setItems(notifications);

        addToTable1();
        employeeName1.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        endDate1.setCellValueFactory(new PropertyValueFactory<>("dateOfEnd"));
        notificationTableView1.setItems(notifications1);

        addToTable2();
        employeeName2.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        endDate2.setCellValueFactory(new PropertyValueFactory<>("dateOfEnd"));
        notificationTableView2.setItems(notifications2);
    }
}
