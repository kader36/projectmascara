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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RepportPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    int idArea=0,idLocation=0,idProject=0;

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

    public void printOne(ActionEvent actionEvent)  {
        if (areaName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المنطقة");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report1.jrxml";
                String querry="SELECT * FROM `projects`,`areas`,`users`,`privileges` WHERE projects.areaId=areas.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id AND projects.areaId ="+idArea;
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

        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report2.jrxml";
            String querry="SELECT * FROM `projects`,`areas`, `users`,`privileges`,`locations`  WHERE areas.id=projects.areaId AND locations.id=projects.locationId AND users.id="+idConnected+" AND users.privilegesId=privileges.id ORDER BY areas.id";
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
    public void printThree(ActionEvent actionEvent)  {
        if (projectName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المشروع");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report3.jrxml";
                String querry="SELECT * FROM `projectoccupations`,`projects`, `occupations`,`areas`,`users`,`privileges`   WHERE projectoccupations.idProject=projects.id AND projects.id="+idProject+" AND projectoccupations.idOccupation=occupations.id AND projects.areaId=areas.id  AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
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
                String querry="SELECT * FROM `deductions`,projects,areas,`users`,`privileges`  WHERE deductions.idProject="+idProject+" AND projects.id=deductions.idProject AND deductions.idArea=areas.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
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
        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report5.jrxml";
            String querry="SELECT * FROM `deductions`,`projects`,`areas` ,`users`,`privileges` WHERE  deductions.idArea=areas.id AND deductions.idProject=projects.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id GROUP BY deductions.idProject";
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

        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report7.jrxml";
            String querry="SELECT * FROM `projectoccupations`,projects, areas, locations,occupations,`users`,`privileges` WHERE projectoccupations.idProject=projects.id AND projectoccupations.idOccupation=occupations.id AND projects.areaId = areas.id AND projects.locationId=locations.id AND (`maxNumber`-`realNumber`)>0  AND users.id="+idConnected+" AND users.privilegesId=privileges.id  ORDER BY projects.areaId,projects.locationId,projects.contractName ASC";
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
    public void printEight(ActionEvent actionEvent)  {
        if (projectName.getSelectionModel().isEmpty()){
            warningMsg("تنبيه","يرجى إختيار المشروع");
        }else{
            try {
                String path=System.getProperty("user.dir")+"\\src\\report\\Report8.jrxml";
                String querry="SELECT * FROM `projectmasroufate`,`projects`, `occupations`,`areas`,`users`,`privileges` WHERE  projects.id="+idProject+" AND projectmasroufate.projectId=projects.id AND projects.areaId=areas.id  AND users.id="+idConnected+" AND users.privilegesId=privileges.id GROUP BY projectmasroufate.id";
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
        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report9.jrxml";
            String querry="SELECT * FROM `garantees`,`historicalgarantees`,`projects`,`areas`,`locations` ,`users`,`privileges` WHERE historicalgarantees.idGarantee=garantees.id AND projects.id=garantees.idProject AND garantees.areaId=areas.id AND garantees.locationId =locations.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id";
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
    public void printTen(ActionEvent actionEvent)  {

        try {
            String path=System.getProperty("user.dir")+"\\src\\report\\Report10.jrxml";
            String querry="SELECT * FROM `abstract`,`areas`,`locations`,`projects`,`users`,`privileges`,`abstractyears` WHERE abstract.idProject=projects.id AND abstract.id=abstractyears.idAbstract AND abstract.idArea = areas.id AND abstract.idLocation=locations.id AND users.id="+idConnected+" AND users.privilegesId=privileges.id ORDER BY projects.areaId,projects.contractName ASC";
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con=new Controlers.ConnectDB().getConnection();
        fillComboArea();
    }

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> locationName;
    @FXML
    private ComboBox<String> projectName;
    public void warningMsg(String title,String message ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
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
}
