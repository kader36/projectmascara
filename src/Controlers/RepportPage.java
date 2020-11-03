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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

public class RepportPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<Project> projects1= FXCollections.observableArrayList();
    ObservableList<Project> projects3= FXCollections.observableArrayList();
    ObservableList<String> contracts= FXCollections.observableArrayList("مشروع قطاع صحي","مشروع قطاع عسكري","مشروع النظافة","مشروع الصيانة","مشروع تعليمي");
    Date nowDate,nowDate1,nowDate2;
    int idArea=0,idLocation=0,idProject=0,idProject1=0,idProject3=0;

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
    private Button res1;//c bn

    @FXML
    private Button res2;//c bn

    @FXML
    private Button res3;//c bn

    @FXML
    private Button res4;//c bn

    @FXML
    private Button res5;//c bn

    @FXML
    private Button res6;//c bn

    @FXML
    private Button res7;//c bn

    @FXML
    private Button res8;//c bn



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
                if (rs.getInt("res1")==0){
                    res1.setDisable(true);

                }else{
                    res1.setDisable(false);

                }
                if (rs.getInt("res2")==0){
                    res2.setDisable(true);

                }else{
                    res2.setDisable(false);

                }
                if (rs.getInt("res3")==0){
                    res3.setDisable(true);

                }else{
                    res3.setDisable(false);

                }
                if (rs.getInt("res4")==0){
                    res4.setDisable(true);

                }else{
                    res4.setDisable(false);

                }
                if (rs.getInt("res5")==0){
                    res5.setDisable(true);

                }else{
                    res5.setDisable(false);

                }
                if (rs.getInt("res6")==0){
                    res6.setDisable(true);

                }else{
                    res6.setDisable(false);

                }
                if (rs.getInt("res7")==0){
                    res7.setDisable(true);

                }else{
                    res7.setDisable(false);

                }
                if (rs.getInt("res8")==0){
                    res8.setDisable(true);

                }else{
                    res8.setDisable(false);

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con=new Controlers.ConnectDB().getConnection();
        areaName1.setItems(contracts);

        Calendar now=Calendar.getInstance();
        if ((now.get(Calendar.MONTH)+2)<=12){
            nowDate1= Date.valueOf(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+2)+"-"+now.get(Calendar.DATE));

        }else{
            int a=now.get(Calendar.MONTH)+2-12;
            System.out.println(a);
            nowDate1= Date.valueOf((now.get(Calendar.YEAR)+1)+"-"+a+"-"+now.get(Calendar.DATE));
        }

    }

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> areaName1;
    @FXML
    private ComboBox<String> locationName;
    @FXML
    private ComboBox<String> projectName;
    @FXML
    private ComboBox<String> projectName1;
    @FXML
    private ComboBox<String> projectName2;
    @FXML
    private ComboBox<String> projectName3;
    public void warningMsg(String title,String message ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    @FXML
    void selectType(ActionEvent event) {
        areaName.getItems().clear();
        locationName.getItems().clear();
        projectName.getItems().clear();
        fillComboArea();
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
        fillComboProject();
        fillComboProject1();
        fillComboProject2();
        fillComboProject3();
    }

    @FXML
    void selectProject(ActionEvent event) {
        int index= projectName.getSelectionModel().getSelectedIndex();
        idProject=projects.get(index).getIdProject();
    }

    @FXML
    void selectProject1(ActionEvent event) {
        int index= projectName1.getSelectionModel().getSelectedIndex();
        idProject1=projects1.get(index).getIdProject();
    }

    @FXML
    void selectProject3(ActionEvent event) {
        int index= projectName3.getSelectionModel().getSelectedIndex();
        idProject3=projects3.get(index).getIdProject();
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
            System.out.println(throwables.getMessage());
        }
    }
    public void fillComboProject(){
        projects.clear();
        projectName.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=? AND `projectType`=?");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setString(3,areaName1.getValue());
            rs=pst.executeQuery();
            while (rs.next()){
                projects.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));
            }

            for (int i=0;i<projects.size();i++){
                projectName.getItems().add(projects.get(i).getContractName());
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.getStackTrace();
            System.out.println(throwables.getMessage());

        }
    }
    public void fillComboProject1(){
        projects1.clear();
        projectName1.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=? AND `projectType`=? AND `transfered`=?");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setString(3,areaName1.getValue());
            pst.setInt(4,0);
            rs=pst.executeQuery();
            while (rs.next()){
                projects1.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));
            }

            for (int i=0;i<projects1.size();i++){
                projectName1.getItems().add(projects1.get(i).getContractName());
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.getStackTrace();
            System.out.println(throwables.getMessage());

        }
    }
    public void fillComboProject2(){
        projectName2.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `garanteesur` WHERE `areaId`=? AND `locationId`=?");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            rs=pst.executeQuery();
            while (rs.next()){
                projectName2.getItems().add(rs.getString("projectName"));
            }

            pst.close();

        } catch (SQLException throwables) {
            throwables.getStackTrace();
            System.out.println(throwables.getMessage());

        }
    }
    public void fillComboProject3(){
        projects3.clear();
        projectName3.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `areaId`=? AND `locationId`=? AND `projectType`=? AND `transfered`=?");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setString(3,areaName1.getValue());
            pst.setInt(4,1);
            rs=pst.executeQuery();
            while (rs.next()){
                projects3.add(new Project(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("projectType"),rs.getString("contractName"),rs.getString("contractNumber"),rs.getString("contractDate"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getFloat("contractPrice")));
            }

            for (int i=0;i<projects3.size();i++){
                projectName3.getItems().add(projects3.get(i).getContractName());
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.getStackTrace();
            System.out.println(throwables.getMessage());

        }
    }
    public void fillComboArea(){
        if (areaName1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار نوع المشروع");
        }else{
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

    }


    @FXML
    private AnchorPane projectAnchor;

    @FXML
    private AnchorPane amalaAnchor;

    @FXML
    private AnchorPane penaltyAnchor;

    @FXML
    private AnchorPane abstractAnchor;

    @FXML
    private AnchorPane employeeAnchor;

    @FXML
    private AnchorPane notifAnchor;

    @FXML
    private AnchorPane garanteeAnchor;

    @FXML
    private AnchorPane garanteePolAnchor;

    @FXML
    void hideAbstract(ActionEvent event) {
        abstractAnchor.setVisible(false);
    }

    @FXML
    void hideAmala(ActionEvent event) {
        amalaAnchor.setVisible(false);
    }

    @FXML
    void hideEmployee(ActionEvent event) {
        employeeAnchor.setVisible(false);
    }

    @FXML
    void hideGarantee(ActionEvent event) {
        garanteeAnchor.setVisible(false);
        projectName.setVisible(true);

    }

    @FXML
    void hideGaranteePol(ActionEvent event) {
        garanteePolAnchor.setVisible(false);
    }

    @FXML
    void hideNotif(ActionEvent event) {
        notifAnchor.setVisible(false);
    }

    @FXML
    void hidePenalty(ActionEvent event) {
        penaltyAnchor.setVisible(false);
    }

    @FXML
    void hideProject(ActionEvent event) {
        projectAnchor.setVisible(false);
    }


    @FXML
    void showAbstract(ActionEvent event) {
        abstractAnchor.setVisible(true);
    }

    @FXML
    void showAmala(ActionEvent event) {
        amalaAnchor.setVisible(true);
    }

    @FXML
    void showEmployee(ActionEvent event) {
        employeeAnchor.setVisible(true);
    }

    @FXML
    void showGarantee(ActionEvent event) {
        garanteeAnchor.setVisible(true);
        projectName.setVisible(false);
    }

    @FXML
    void showGaranteePol(ActionEvent event) {
        garanteePolAnchor.setVisible(true);
    }

    @FXML
    void showNotif(ActionEvent event) {
        notifAnchor.setVisible(true);
    }

    @FXML
    void showPenalty(ActionEvent event) {
        penaltyAnchor.setVisible(true);
    }

    @FXML
    void showProject(ActionEvent event) {
        projectAnchor.setVisible(true);
    }

    public void printOne(ActionEvent actionEvent)  {
        if (areaName1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار نوع المشروع");
        }else if (areaName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المنطقة");
        }else {
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report1.jrxml";
                String querry="SELECT * FROM `projects`,`areas`,`users`,`privileges`,`locations` WHERE projects.locationId=locations.id AND projects.projectType='"+areaName1.getValue()+"' AND projects.areaId=areas.id AND projects.areaId ="+idArea+" AND users.id="+idConnected+" AND users.privilegesId=privileges.id ";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printTwo(ActionEvent actionEvent)  {
        if (areaName1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار نوع المشروع");
        }else {
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report2.jrxml";
                String querry="SELECT * FROM `projects`,`areas`, `users`,`privileges`,`locations`  WHERE areas.id=projects.areaId AND locations.id=projects.locationId AND projects.projectType='"+areaName1.getValue()+"' AND users.id="+idConnected+" AND users.privilegesId=privileges.id ORDER BY areas.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );
            } catch (JRException e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    public void printThree(ActionEvent actionEvent)  {
        if (projectName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المشروع");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report3.jrxml";
                String querry="SELECT * FROM `projectoccupations`,`projects`, `occupations`,`areas`,`users`,`privileges`,`locations`   WHERE projectoccupations.idProject=projects.id AND projects.id="+idProject+" AND projectoccupations.idOccupation=occupations.id AND projects.areaId=areas.id   AND  projects.locationId=locations.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printFour(ActionEvent actionEvent)  {
        if (projectName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المشروغ");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report4.jrxml";
                String querry="SELECT * FROM `deductions`,projects,areas,`users`,`privileges`,`locations`  WHERE deductions.idProject="+idProject+" AND projects.id=deductions.idProject AND  projects.locationId=locations.id AND deductions.idArea=areas.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printFive(ActionEvent actionEvent)  {
        if (areaName1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار نوع المشروع");
        }else {
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report5.jrxml";
                String querry="SELECT * FROM `deductions`,`projects`,`areas` ,`users`,`privileges`,`locations`  WHERE  deductions.idArea=areas.id AND  projects.locationId=locations.id AND projects.projectType='"+areaName1.getValue()+"' AND deductions.idProject=projects.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id GROUP BY deductions.idProject";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printSix(ActionEvent actionEvent)  {
        if (projectName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المشروع");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report6.jrxml";
                String querry="SELECT * FROM `abstract`,`areas`,`locations`,`projects`,`users`,`privileges`,`abstractyears` WHERE abstract.idProject=projects.id AND abstract.id=abstractyears.idAbstract AND projects.id="+idProject+" AND abstract.idArea = areas.id AND abstract.idLocation=locations.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printSeven(ActionEvent actionEvent)  {
        if (areaName1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار نوع المشروع");
        }else {
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report7.jrxml";
                String querry="SELECT * FROM `projectoccupations`,projects, areas, locations,occupations,`users`,`privileges` WHERE projectoccupations.idProject=projects.id AND projectoccupations.idOccupation=occupations.id AND  projects.projectType='"+areaName1.getValue()+"' AND projects.areaId = areas.id AND projects.locationId=locations.id AND (`maxNumber`-`realNumber`)>0  AND users.id="+idConnected+" AND users.privilegesId=privileges.id  ORDER BY projects.areaId,projects.locationId,projects.contractName ASC";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printEight(ActionEvent actionEvent)  {
        if (projectName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المشروع");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report8.jrxml";
                String querry="SELECT * FROM `projectmasroufate`,`projects`, `areas`,`users`,`privileges`,`locations`   WHERE  projects.id="+idProject+" AND projectmasroufate.projectId=projects.id AND  projects.locationId=locations.id AND projects.areaId=areas.id  AND users.id="+idConnected+" AND users.privilegesId=privileges.id GROUP BY projectmasroufate.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printNine(ActionEvent actionEvent)  {
        if (projectName1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار مشروع مسجل");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report9.jrxml";
                String querry="SELECT * FROM `garantees`,`historicalgarantees`,`projects`,`areas`,`locations` ,`users`,`privileges`,`banks` WHERE historicalgarantees.idGarantee=garantees.id AND projects.id=garantees.idProject AND projects.id="+idProject1+" AND garantees.areaId=areas.id AND garantees.locationId =locations.id AND garantees.bankId =banks.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printNine2(ActionEvent actionEvent)  {
        if (projectName3.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار مشروع مغلق");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report9.jrxml";
                String querry="SELECT * FROM `garantees`,`historicalgarantees`,`projects`,`areas`,`locations` ,`users`,`privileges`,`banks` WHERE historicalgarantees.idGarantee=garantees.id AND projects.id=garantees.idProject AND projects.id="+idProject3+" AND garantees.areaId=areas.id AND garantees.locationId =locations.id AND garantees.bankId =banks.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printTen(ActionEvent actionEvent)  {
        if (areaName1.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار نوع المشروع");
        }else {
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report10.jrxml";
                String querry="SELECT * FROM `abstract`,`areas`,`locations`,`projects`,`users`,`privileges`,`abstractyears` WHERE abstract.idProject=projects.id AND projects.projectType='"+areaName1.getValue()+"' AND abstract.id=abstractyears.idAbstract AND abstract.idArea = areas.id AND abstract.idLocation=locations.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id ORDER BY projects.areaId,projects.contractName ASC";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printEleven(ActionEvent actionEvent) {
        if (projectName2.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار مشروع غير مسجل");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report11.jrxml";
                String querry="SELECT * FROM `garanteesur`,`users`,`privileges`,`banks`,`areas`,`locations`,`historicalgaranteesur` WHERE `projectName`='"+projectName2.getValue()+"' AND garanteesur.bankId=banks.id AND garanteesur.areaId=areas.id AND garanteesur.locationId=locations.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id AND historicalgaranteesur.idGarantee=garanteesur.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );
            } catch (JRException e) {
                e.printStackTrace();
            }
        }

    }

    public void printTwelve(ActionEvent actionEvent) {
        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report12.jrxml";
            String querry="(SELECT employees.employeeName,employees.employeeNationality,'إنتهاء صلاحية بطاقة إقامة' AS notifType,'///' AS notifNumber,employees.residenceEndDate,employees.employeeSex,users.employeeName,privileges.privilegeName FROM `employees`,`users`,`privileges` WHERE `residenceEndDate` <='"+nowDate1.toString()+"' AND users.id="+idConnected+" AND users.privilegesId=privileges.id) UNION ALL (SELECT employees.employeeName,employees.employeeNationality,'إنتهاء صلاحية الشهادة الصحية',employees.HealthCertificateNumber,employees.HealthCertificatEndDate,employees.employeeSex,users.employeeName,privileges.privilegeName FROM `employees`,`users`,`privileges` WHERE `HealthCertificatEndDate` <= '"+nowDate1.toString()+"'  AND users.id="+idConnected+" AND users.privilegesId=privileges.id ) UNION ALL (SELECT employees.employeeName,employees.employeeNationality,'إنتهاء صلاحية شهادة تصنيف الهيئة',employees.ClassificationNumber,employees.ClassificationEndDate,employees.employeeSex,users.employeeName,privileges.privilegeName FROM `employees`,`users`,`privileges` WHERE `ClassificationEndDate` <= '"+nowDate1.toString()+"' AND users.id="+idConnected+" AND users.privilegesId=privileges.id )";
            JasperDesign jd=  JRXmlLoader.load(path);
            JRDesignQuery query=new JRDesignQuery();
            query.setText(querry);
            jd.setQuery(query);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
            JasperViewer jv = new JasperViewer( jasperPrint, false );
            jv.viewReport( jasperPrint, false );

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void printThirteen(ActionEvent actionEvent) {
        if (areaName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المنطقة");
        }else {
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report13.jrxml";
                String querry="(SELECT employees.employeeName,employees.employeeNationality,'إنتهاء صلاحية بطاقة إقامة' AS notifType,'///' AS notifNumber,employees.residenceEndDate,areas.areaName,locations.locationName,users.employeeName,privileges.privilegeName FROM `employees`,`users`,`privileges`,`projectsemployees`,`areas`,`locations` WHERE projectsemployees.idArea="+idArea+" AND projectsemployees.idArea=areas.id AND projectsemployees.idLocation=locations.id AND projectsemployees.idEmployee=employees.id AND `residenceEndDate` <='"+nowDate1.toString()+"' AND users.id="+idConnected+" AND users.privilegesId=privileges.id) UNION ALL (SELECT employees.employeeName,employees.employeeNationality,'إنتهاء صلاحية الشهادة الصحية',employees.HealthCertificateNumber,employees.HealthCertificatEndDate,areas.areaName,locations.locationName,users.employeeName,privileges.privilegeName FROM `employees`,`users`,`privileges`,`projectsemployees`,`areas`,`locations` WHERE projectsemployees.idArea="+idArea+" AND projectsemployees.idArea=areas.id AND projectsemployees.idLocation=locations.id AND projectsemployees.idEmployee=employees.id AND `HealthCertificatEndDate` <= '"+nowDate1.toString()+"'  AND users.id="+idConnected+" AND users.privilegesId=privileges.id ) UNION ALL (SELECT employees.employeeName,employees.employeeNationality,'إنتهاء صلاحية شهادة تصنيف الهيئة',employees.ClassificationNumber,employees.ClassificationEndDate,areas.areaName,locations.locationName,users.employeeName,privileges.privilegeName FROM `employees`,`users`,`privileges`,`projectsemployees`,`areas`,`locations` WHERE projectsemployees.idArea="+idArea+" AND projectsemployees.idArea=areas.id AND projectsemployees.idLocation=locations.id AND projectsemployees.idEmployee=employees.id AND `ClassificationEndDate` <= '"+nowDate1.toString()+"' AND users.id="+idConnected+" AND users.privilegesId=privileges.id )";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printFourteen(ActionEvent actionEvent) {
        if (locationName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار الموقع");
        }else {
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report14.jrxml";
                String querry="(SELECT employees.employeeName,employees.employeeNationality,'إنتهاء صلاحية بطاقة إقامة' AS notifType,'///' AS notifNumber,employees.residenceEndDate,locations.locationName,users.employeeName,privileges.privilegeName FROM `employees`,`users`,`privileges`,`projectsemployees`,`locations` WHERE projectsemployees.idLocation="+idLocation+" AND projectsemployees.idLocation=locations.id AND projectsemployees.idEmployee=employees.id AND `residenceEndDate` <='"+nowDate1.toString()+"' AND users.id="+idConnected+" AND users.privilegesId=privileges.id) UNION ALL (SELECT employees.employeeName,employees.employeeNationality,'إنتهاء صلاحية الشهادة الصحية',employees.HealthCertificateNumber,employees.HealthCertificatEndDate,locations.locationName,users.employeeName,privileges.privilegeName FROM `employees`,`users`,`privileges`,`projectsemployees`,`locations` WHERE projectsemployees.idLocation="+idLocation+" AND projectsemployees.idLocation=locations.id AND projectsemployees.idEmployee=employees.id AND `HealthCertificatEndDate` <= '"+nowDate1.toString()+"'  AND users.id="+idConnected+" AND users.privilegesId=privileges.id ) UNION ALL (SELECT employees.employeeName,employees.employeeNationality,'إنتهاء صلاحية شهادة تصنيف الهيئة',employees.ClassificationNumber,employees.ClassificationEndDate,locations.locationName,users.employeeName,privileges.privilegeName FROM `employees`,`users`,`privileges`,`projectsemployees`,`locations` WHERE projectsemployees.idLocation="+idLocation+" AND projectsemployees.idLocation=locations.id AND projectsemployees.idEmployee=employees.id AND `ClassificationEndDate` <= '"+nowDate1.toString()+"' AND users.id="+idConnected+" AND users.privilegesId=privileges.id )";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printFifteen(ActionEvent actionEvent) {
        if (locationName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار الموقع");
        }else {
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report15.jrxml";
                String querry="SELECT * FROM `projectsemployees`,`locations`,`employees`,`users`,`privileges` WHERE projectsemployees.idLocation=locations.id AND locations.id="+idLocation+" AND projectsemployees.idEmployee=employees.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printSixteen(ActionEvent actionEvent) {
        if (areaName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المنطقة");
        }else {
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report16.jrxml";
                String querry="SELECT DISTINCT employeeNationality,areas.areaName,users.employeeName,privileges.privilegeName,locations.locationName ,(SELECT COUNT(*) FROM `employees` AS empl1,`projectsemployees` WHERE empl2.employeeNationality=empl1.employeeNationality AND projectsemployees.idArea="+idArea+" AND projectsemployees.idEmployee=empl1.id ) AS total,(SELECT COUNT(*) FROM `employees` AS empl1,`projectsemployees` WHERE empl2.employeeNationality=empl1.employeeNationality AND projectsemployees.idArea="+idArea+" AND projectsemployees.idEmployee=empl1.id AND empl1.employeeSex='أنثى') AS female,(SELECT COUNT(*) FROM `employees` AS empl1,`projectsemployees` WHERE empl2.employeeNationality=empl1.employeeNationality AND projectsemployees.idArea="+idArea+" AND projectsemployees.idEmployee=empl1.id AND empl1.employeeSex='ذكر' ) AS male FROM `employees` AS empl2,`projectsemployees`,`areas`,`locations`,`users`,`privileges` WHERE projectsemployees.idArea="+idArea+" AND projectsemployees.idArea=areas.id AND projectsemployees.idLocation=locations.id AND projectsemployees.idEmployee=empl2.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
                JasperDesign jd=  JRXmlLoader.load(path);
                JRDesignQuery query=new JRDesignQuery();
                query.setText(querry);
                jd.setQuery(query);
                JasperReport jr= JasperCompileManager.compileReport(jd);
                JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
                JasperViewer jv = new JasperViewer( jasperPrint, false );
                jv.viewReport( jasperPrint, false );

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }

    public void printSeventeen(ActionEvent actionEvent) {
        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report17.jrxml";
            String querry="SELECT DISTINCT pempl2.idArea,areas.areaName,users.employeeName,privileges.privilegeName,employeeNationality,(SELECT COUNT(*) FROM `employees` AS empl1,`projectsemployees` AS pempl1 WHERE empl2.employeeNationality=empl1.employeeNationality AND pempl2.idArea=pempl1.idArea AND empl1.id=pempl1.idEmployee ) AS total,(SELECT COUNT(*) FROM `employees` AS empl1,`projectsemployees` AS pempl1 WHERE empl2.employeeNationality=empl1.employeeNationality AND pempl2.idArea=pempl1.idArea AND empl1.id=pempl1.idEmployee AND empl1.employeeSex='أنثى' ) AS female,(SELECT COUNT(*) FROM `employees` AS empl1,`projectsemployees` AS pempl1 WHERE empl2.employeeNationality=empl1.employeeNationality AND pempl2.idArea=pempl1.idArea AND empl1.id=pempl1.idEmployee AND empl1.employeeSex='ذكر' ) AS male FROM `employees` AS empl2,`projectsemployees` AS pempl2,`areas`,`users`,`privileges` WHERE pempl2.idArea=areas.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id ORDER BY pempl2.idArea";
            JasperDesign jd=  JRXmlLoader.load(path);
            JRDesignQuery query=new JRDesignQuery();
            query.setText(querry);
            jd.setQuery(query);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jr, null, con);
            JasperViewer jv = new JasperViewer( jasperPrint, false );
            jv.viewReport( jasperPrint, false );

        } catch (JRException e) {
            e.printStackTrace();
        }
    }


}
