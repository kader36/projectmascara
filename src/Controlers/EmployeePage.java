package Controlers;

import com.mysql.jdbc.Blob;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeePage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    File file1=null,file2=null;
    ObservableList<Occupation> occupations= FXCollections.observableArrayList();
    ObservableList<String> identityTypeList= FXCollections.observableArrayList("بطاقة هوية","جواز السفر","رخصة الإقامة");
    int idOccupation=0;
    public void fillCombo(){
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `occupations`");
            rs=pst.executeQuery();
            while (rs.next()){
                occupations.add(new Occupation(rs.getInt("id"),rs.getString("occupationName")));

            }
            for (int i=0;i<occupations.size();i++){
                reelOccupation.getItems().add(occupations.get(i).getNameOcupation());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    private TextField employeeName;



    @FXML
    private TextField identityNumber;

    @FXML
    private TextField employeeNumber;

    @FXML
    private TextField employeeNationality;

    @FXML
    private TextField religion;

    @FXML
    private TextField residenceOccupation;

    @FXML
    private ComboBox<String> identityType;

    @FXML
    private ComboBox<String> reelOccupation;

    @FXML
    private DatePicker residenceEndDate;

    @FXML
    private DatePicker HealthCertificateStartDate;

    @FXML
    private DatePicker HealthCertificatEndDate;


    @FXML
    public void addEmployee(ActionEvent actionEvent) {
        int dejaExist=0;
        int size=0;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees` WHERE `identityNumber`=? OR `employeeNumber`=?");
            pst.setString(1,identityNumber.getText());
            pst.setString(2,employeeNumber.getText());
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
        if (employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||employeeNationality.getText().isEmpty() ||identityNumber.getText().isEmpty() ||religion.getText().isEmpty() ||residenceOccupation.getText().isEmpty() ||identityType.getSelectionModel().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()||HealthCertificateStartDate.getEditor().getText().isEmpty()||HealthCertificatEndDate.getEditor().getText().isEmpty()){
            warningMsg("تنبيه","يرجى ملء الفراغات");
        }else if(dejaExist==1){
            warningMsg("تنبيه","المعلومات موجودة من قبل");
        }else{
            try {
                if (file1!=null && file2!=null){
                    con=new Controlers.ConnectDB().getConnection();

                    pst=con.prepareStatement("INSERT INTO `employees`(`employeeName`, `employeeNumber`, `employeeNationality`," +
                            " `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`," +
                            " `HealthCertificateStartDate`, `HealthCertificatEndDate`, `certificateImage`, `identityImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1,employeeName.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setString(3,employeeNationality.getText());
                    pst.setString(4,identityType.getValue());
                    pst.setString(5,identityNumber.getText());
                    pst.setString(6,religion.getText());
                    pst.setString(7,residenceOccupation.getText());
                    pst.setInt(8,idOccupation);
                    pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                    pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                    pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));
                    FileInputStream fis1=new FileInputStream(file1);
                    pst.setBinaryStream(12, fis1,(int) file1.length());
                    FileInputStream fis2=new FileInputStream(file2);
                    pst.setBinaryStream(13, fis2,(int) file2.length());
                    pst.execute();
                    warningMsg("إظافة","تمت الإظافة بنجاح");
                }else if (file1== null && file2!=null){
                    con=new Controlers.ConnectDB().getConnection();

                    pst=con.prepareStatement("INSERT INTO `employees`(`employeeName`, `employeeNumber`, `employeeNationality`," +
                            " `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`," +
                            " `HealthCertificateStartDate`, `HealthCertificatEndDate`, `identityImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1,employeeName.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setString(3,employeeNationality.getText());
                    pst.setString(4,identityType.getValue());
                    pst.setString(5,identityNumber.getText());
                    pst.setString(6,religion.getText());
                    pst.setString(7,residenceOccupation.getText());
                    pst.setInt(8,idOccupation);
                    pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                    pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                    pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));

                    FileInputStream fis2=new FileInputStream(file2);
                    pst.setBinaryStream(12, fis2,(int) file2.length());
                    pst.execute();
                    warningMsg("إظافة","تمت الإظافة بنجاح");
                }else if (file2==null && file1!=null){
                    con=new Controlers.ConnectDB().getConnection();

                    pst=con.prepareStatement("INSERT INTO `employees`(`employeeName`, `employeeNumber`, `employeeNationality`," +
                            " `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`," +
                            " `HealthCertificateStartDate`, `HealthCertificatEndDate`, `certificateImage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1,employeeName.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setString(3,employeeNationality.getText());
                    pst.setString(4,identityType.getValue());
                    pst.setString(5,identityNumber.getText());
                    pst.setString(6,religion.getText());
                    pst.setString(7,residenceOccupation.getText());
                    pst.setInt(8,idOccupation);
                    pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                    pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                    pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));
                    FileInputStream fis1=new FileInputStream(file1);
                    pst.setBinaryStream(12, fis1,(int) file1.length());

                    pst.execute();
                    warningMsg("إظافة","تمت الإظافة بنجاح");
                }else if (file1==null && file2==null){
                    con=new Controlers.ConnectDB().getConnection();

                    pst=con.prepareStatement("INSERT INTO `employees`(`employeeName`, `employeeNumber`, `employeeNationality`," +
                            " `identityType`, `identityNumber`, `religion`, `residenceOccupation`, `reelOccupation`, `residenceEndDate`," +
                            " `HealthCertificateStartDate`, `HealthCertificatEndDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1,employeeName.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setString(3,employeeNationality.getText());
                    pst.setString(4,identityType.getValue());
                    pst.setString(5,identityNumber.getText());
                    pst.setString(6,religion.getText());
                    pst.setString(7,residenceOccupation.getText());
                    pst.setInt(8,idOccupation);
                    pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                    pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                    pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));

                    pst.execute();
                    warningMsg("إظافة","تمت الإظافة بنجاح");
                }


            } catch (SQLException | FileNotFoundException throwables) {
                throwables.printStackTrace();
                System.out.println(throwables.getMessage());
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTable();
            employeeName.clear();
            identityNumber.clear();
            employeeNumber.clear();
            religion.clear();
            employeeNationality.clear();
            residenceOccupation.clear();
        }

    }
    @FXML
    void selectOccupation(ActionEvent event) {
        int index= reelOccupation.getSelectionModel().getSelectedIndex();
        idOccupation=occupations.get(index).getIdOcupation();
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
                    employeeAddPrivilege.setDisable(true);

                }else{
                    employeeMenuButton.setDisable(false);
                    employeeAddPrivilege.setDisable(false);

                }
                if (rs.getInt("emde")==0){
                    employeeDeletePrivilege.setDisable(true);
                    employeeEditPrivilege.setDisable(true);

                }else{
                    employeeDeletePrivilege.setDisable(false);
                    employeeEditPrivilege.setDisable(false);

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

    @FXML
    private TableView<EmployeeForTable> employeeTableView;

    @FXML
    private TableColumn<EmployeeForTable, String> employeeNameTable;

    @FXML
    private TableColumn<EmployeeForTable, String> employeeNumberTable;

    @FXML
    private TableColumn<EmployeeForTable, String> employeeNationalityTable;

    @FXML
    private TableColumn<EmployeeForTable, String> identityTypeTable;

    @FXML
    private TableColumn<EmployeeForTable, String> identityNumberTable;

    @FXML
    private TableColumn<EmployeeForTable, String> religionTable;

    @FXML
    private TableColumn<EmployeeForTable, String> residenceOccupationTable;

    @FXML
    private TableColumn<EmployeeForTable, String> reelOccupationTable;

    @FXML
    private TableColumn<EmployeeForTable, String> residenceEndDateTable;

    @FXML
    private TableColumn<EmployeeForTable, String> HealthCertificateStartDateTable;

    @FXML
    private TableColumn<EmployeeForTable, String> HealthCertificatEndDateTable;

    ObservableList employeesTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identityType.setItems(identityTypeList);
        fillCombo();

        addToTable();
        employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
        employeeNationalityTable.setCellValueFactory(new PropertyValueFactory<>("employeeNationality"));
        identityTypeTable.setCellValueFactory(new PropertyValueFactory<>("identityType"));
        identityNumberTable.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
        religionTable.setCellValueFactory(new PropertyValueFactory<>("religion"));
        residenceOccupationTable.setCellValueFactory(new PropertyValueFactory<>("residenceOccupation"));
        reelOccupationTable.setCellValueFactory(new PropertyValueFactory<>("reelOccupationName"));
        residenceEndDateTable.setCellValueFactory(new PropertyValueFactory<>("residenceEndDate"));
        HealthCertificateStartDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificateStartDate"));
        HealthCertificatEndDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificatEndDate"));
        employeeTableView.setItems(employeesTable);
    }
    public void addToTable(){
        employeesTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees`");
            rs=pst.executeQuery();
            while (rs.next()){
                employeesTable.add(new EmployeeForTable(rs.getInt("id"),rs.getInt("reelOccupation"),rs.getString("employeeName"),rs.getString("employeeNumber"),rs.getString("employeeNationality"),rs.getString("identityType"),rs.getString("identityNumber"),rs.getString("religion"),getOccupationName(rs.getInt("reelOccupation")),rs.getString("residenceOccupation"),rs.getString("residenceEndDate"),rs.getString("HealthCertificateStartDate"),rs.getString("HealthCertificatEndDate")));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    public String getOccupationName(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `occupations` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("occupationName");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }

    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        int index= employeeTableView.getSelectionModel().getSelectedIndex();
        int idDelete=employeeTableView.getItems().get(index).getIdEmployee();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `employees` WHERE `id`=?");
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
            employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
            employeeNationalityTable.setCellValueFactory(new PropertyValueFactory<>("employeeNationality"));
            identityTypeTable.setCellValueFactory(new PropertyValueFactory<>("identityType"));
            identityNumberTable.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
            religionTable.setCellValueFactory(new PropertyValueFactory<>("religion"));
            residenceOccupationTable.setCellValueFactory(new PropertyValueFactory<>("residenceOccupation"));
            reelOccupationTable.setCellValueFactory(new PropertyValueFactory<>("reelOccupationName"));
            residenceEndDateTable.setCellValueFactory(new PropertyValueFactory<>("residenceEndDate"));
            HealthCertificateStartDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificateStartDate"));
            HealthCertificatEndDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificatEndDate"));
            employeeTableView.setItems(employeesTable);
        }else{
            employeesTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `employees` WHERE `employeeName` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    employeesTable.add(new EmployeeForTable(rs.getInt("id"),rs.getInt("reelOccupation"),rs.getString("employeeName"),rs.getString("employeeNumber"),rs.getString("employeeNationality"),rs.getString("identityType"),rs.getString("identityNumber"),rs.getString("religion"),getOccupationName(rs.getInt("reelOccupation")),rs.getString("residenceOccupation"),rs.getString("residenceEndDate"),rs.getString("HealthCertificateStartDate"),rs.getString("HealthCertificatEndDate")));
                }
                employeeNameTable.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
                employeeNumberTable.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
                employeeNationalityTable.setCellValueFactory(new PropertyValueFactory<>("employeeNationality"));
                identityTypeTable.setCellValueFactory(new PropertyValueFactory<>("identityType"));
                identityNumberTable.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
                religionTable.setCellValueFactory(new PropertyValueFactory<>("religion"));
                residenceOccupationTable.setCellValueFactory(new PropertyValueFactory<>("residenceOccupation"));
                reelOccupationTable.setCellValueFactory(new PropertyValueFactory<>("reelOccupationName"));
                residenceEndDateTable.setCellValueFactory(new PropertyValueFactory<>("residenceEndDate"));
                HealthCertificateStartDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificateStartDate"));
                HealthCertificatEndDateTable.setCellValueFactory(new PropertyValueFactory<>("healthCertificatEndDate"));
                employeeTableView.setItems(employeesTable);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

    @FXML
    private Button edit;
    public void edit(ActionEvent actionEvent) {
        int index= employeeTableView.getSelectionModel().getSelectedIndex();
        int idEdit=employeeTableView.getItems().get(index).getIdEmployee();


        if (employeeEditPrivilege.getText().contains("تعديل موظف")){
            employeeEditPrivilege.setText("حفظ");
            employeeName.setText(employeeTableView.getItems().get(index).getEmployeeName());
            identityNumber.setText(employeeTableView.getItems().get(index).getIdentityNumber());
            residenceEndDate.setValue(LocalDate.parse(employeeTableView.getItems().get(index).getResidenceEndDate()));
            HealthCertificateStartDate.setValue(LocalDate.parse(employeeTableView.getItems().get(index).getHealthCertificateStartDate()));
            HealthCertificatEndDate.setValue(LocalDate.parse(employeeTableView.getItems().get(index).getResidenceEndDate()));
            reelOccupation.setValue(employeeTableView.getItems().get(index).getReelOccupationName());
            identityType.setValue(employeeTableView.getItems().get(index).getIdentityType());
            employeeNumber.setText(employeeTableView.getItems().get(index).getEmployeeNumber());
            employeeNationality.setText(employeeTableView.getItems().get(index).getEmployeeNationality());
            residenceOccupation.setText(employeeTableView.getItems().get(index).getResidenceOccupation());
            religion.setText(employeeTableView.getItems().get(index).getReligion());
            file1=null;file2=null;
        }else if (employeeEditPrivilege.getText().contains("حفظ")){

            if (file1!=null && file2!=null){
                int dejaExist=0;
                int size=0;
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("SELECT * FROM `employees` WHERE (`identityNumber`=? OR `employeeNumber`=?) AND id!=?");
                    pst.setString(1,identityNumber.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setInt(3,idEdit);

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
                if (employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||employeeNationality.getText().isEmpty() ||identityNumber.getText().isEmpty() ||religion.getText().isEmpty() ||residenceOccupation.getText().isEmpty() ||identityType.getSelectionModel().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()||HealthCertificateStartDate.getEditor().getText().isEmpty()||HealthCertificatEndDate.getEditor().getText().isEmpty()){
                    warningMsg("تنبيه","يرجى ملء الفراغات");
                }else if(dejaExist==1){
                    warningMsg("تنبيه","المعلومات موجودة من قبل");
                }else{
                    try {
                        System.out.println(reelOccupation.getValue());
                        for (int i=0; i<occupations.size() ;i++){
                            System.out.println(occupations.get(i).getNameOcupation());
                            if (occupations.get(i).getNameOcupation()==reelOccupation.getValue()){
                                idOccupation=occupations.get(i).getIdOcupation();
                            }
                        }
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `employees` SET `employeeName`=?,`employeeNumber`=?,`employeeNationality`=?,`identityType`=?,`identityNumber`=?,`religion`=?,`residenceOccupation`=?,`reelOccupation`=?,`residenceEndDate`=?,`HealthCertificateStartDate`=?,`HealthCertificatEndDate`=?,`certificateImage`=?,`identityImage`=?  WHERE `id`=?");


                        pst.setString(1,employeeName.getText());
                        pst.setString(2,employeeNumber.getText());
                        pst.setString(3,employeeNationality.getText());
                        pst.setString(4,identityType.getValue());
                        pst.setString(5,identityNumber.getText());
                        pst.setString(6,religion.getText());
                        pst.setString(7,residenceOccupation.getText());
                        pst.setInt(8,idOccupation);
                        pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(12, fis1,(int) file1.length());
                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(13, fis2,(int) file2.length());
                        pst.setInt(14,idEdit);
                        pst.execute();
                        warningMsg("تعديل","تم التعديل بنجاح");
                    } catch (SQLException | FileNotFoundException throwables) {
                        throwables.printStackTrace();
                        warningMsg("تعديل","حدث خطأ أثناء التعديل");
                    }
                }

            }else if (file1== null && file2==null){
                int dejaExist=0;
                int size=0;
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("SELECT * FROM `employees` WHERE (`identityNumber`=? OR `employeeNumber`=?) AND id!=?");
                    pst.setString(1,identityNumber.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setInt(3,idEdit);

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
                if (employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||employeeNationality.getText().isEmpty() ||identityNumber.getText().isEmpty() ||religion.getText().isEmpty() ||residenceOccupation.getText().isEmpty() ||identityType.getSelectionModel().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()||HealthCertificateStartDate.getEditor().getText().isEmpty()||HealthCertificatEndDate.getEditor().getText().isEmpty()){
                    warningMsg("تنبيه","يرجى ملء الفراغات");
                }else if(dejaExist==1){
                    warningMsg("تنبيه","المعلومات موجودة من قبل");
                }else{
                    try {
                        System.out.println(reelOccupation.getValue());
                        for (int i=0; i<occupations.size() ;i++){
                            System.out.println(occupations.get(i).getNameOcupation());
                            if (occupations.get(i).getNameOcupation()==reelOccupation.getValue()){
                                idOccupation=occupations.get(i).getIdOcupation();
                            }
                        }
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `employees` SET `employeeName`=?,`employeeNumber`=?,`employeeNationality`=?,`identityType`=?,`identityNumber`=?,`religion`=?,`residenceOccupation`=?,`reelOccupation`=?,`residenceEndDate`=?,`HealthCertificateStartDate`=?,`HealthCertificatEndDate`=?  WHERE `id`=?");


                        pst.setString(1,employeeName.getText());
                        pst.setString(2,employeeNumber.getText());
                        pst.setString(3,employeeNationality.getText());
                        pst.setString(4,identityType.getValue());
                        pst.setString(5,identityNumber.getText());
                        pst.setString(6,religion.getText());
                        pst.setString(7,residenceOccupation.getText());
                        pst.setInt(8,idOccupation);
                        pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));
                        pst.setInt(12,idEdit);
                        pst.execute();
                        warningMsg("تعديل","تم التعديل بنجاح");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        warningMsg("تعديل","حدث خطأ أثناء التعديل");
                    }
                }



            }else if (file1== null && file2!=null){
                int dejaExist=0;
                int size=0;
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("SELECT * FROM `employees` WHERE (`identityNumber`=? OR `employeeNumber`=?) AND id!=?");
                    pst.setString(1,identityNumber.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setInt(3,idEdit);

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
                if (employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||employeeNationality.getText().isEmpty() ||identityNumber.getText().isEmpty() ||religion.getText().isEmpty() ||residenceOccupation.getText().isEmpty() ||identityType.getSelectionModel().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()||HealthCertificateStartDate.getEditor().getText().isEmpty()||HealthCertificatEndDate.getEditor().getText().isEmpty()){
                    warningMsg("تنبيه","يرجى ملء الفراغات");
                }else if(dejaExist==1){
                    warningMsg("تنبيه","المعلومات موجودة من قبل");
                }else{
                    try {
                        System.out.println(reelOccupation.getValue());
                        for (int i=0; i<occupations.size() ;i++){
                            System.out.println(occupations.get(i).getNameOcupation());
                            if (occupations.get(i).getNameOcupation()==reelOccupation.getValue()){
                                idOccupation=occupations.get(i).getIdOcupation();
                            }
                        }
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `employees` SET `employeeName`=?,`employeeNumber`=?,`employeeNationality`=?,`identityType`=?,`identityNumber`=?,`religion`=?,`residenceOccupation`=?,`reelOccupation`=?,`residenceEndDate`=?,`HealthCertificateStartDate`=?,`HealthCertificatEndDate`=?,`identityImage`=?  WHERE `id`=?");


                        pst.setString(1,employeeName.getText());
                        pst.setString(2,employeeNumber.getText());
                        pst.setString(3,employeeNationality.getText());
                        pst.setString(4,identityType.getValue());
                        pst.setString(5,identityNumber.getText());
                        pst.setString(6,religion.getText());
                        pst.setString(7,residenceOccupation.getText());
                        pst.setInt(8,idOccupation);
                        pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));

                        FileInputStream fis2=new FileInputStream(file2);
                        pst.setBinaryStream(12, fis2,(int) file2.length());
                        pst.setInt(13,idEdit);
                        pst.execute();
                        warningMsg("تعديل","تم التعديل بنجاح");

                    } catch (SQLException | FileNotFoundException throwables) {
                        throwables.printStackTrace();
                        warningMsg("تعديل","حدث خطأ أثناء التعديل");
                    }
                }


            }else if (file2==null && file1!=null){
                int dejaExist=0;
                int size=0;
                try {
                    con=new Controlers.ConnectDB().getConnection();
                    pst=con.prepareStatement("SELECT * FROM `employees` WHERE (`identityNumber`=? OR `employeeNumber`=?) AND id!=?");
                    pst.setString(1,identityNumber.getText());
                    pst.setString(2,employeeNumber.getText());
                    pst.setInt(3,idEdit);

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
                if (employeeName.getText().isEmpty() ||employeeNumber.getText().isEmpty() ||employeeNationality.getText().isEmpty() ||identityNumber.getText().isEmpty() ||religion.getText().isEmpty() ||residenceOccupation.getText().isEmpty() ||identityType.getSelectionModel().isEmpty() ||reelOccupation.getSelectionModel().isEmpty() ||residenceEndDate.getEditor().getText().isEmpty()||HealthCertificateStartDate.getEditor().getText().isEmpty()||HealthCertificatEndDate.getEditor().getText().isEmpty()){
                    warningMsg("تنبيه","يرجى ملء الفراغات");
                }else if(dejaExist==1){
                    warningMsg("تنبيه","المعلومات موجودة من قبل");
                }else{
                    try {
                        System.out.println(reelOccupation.getValue());
                        for (int i=0; i<occupations.size() ;i++){
                            System.out.println(occupations.get(i).getNameOcupation());
                            if (occupations.get(i).getNameOcupation()==reelOccupation.getValue()){
                                idOccupation=occupations.get(i).getIdOcupation();
                            }
                        }
                        con = new Controlers.ConnectDB().getConnection();
                        pst = con.prepareStatement("UPDATE `employees` SET `employeeName`=?,`employeeNumber`=?,`employeeNationality`=?,`identityType`=?,`identityNumber`=?,`religion`=?,`residenceOccupation`=?,`reelOccupation`=?,`residenceEndDate`=?,`HealthCertificateStartDate`=?,`HealthCertificatEndDate`=?,`certificateImage`=? WHERE `id`=?");


                        pst.setString(1,employeeName.getText());
                        pst.setString(2,employeeNumber.getText());
                        pst.setString(3,employeeNationality.getText());
                        pst.setString(4,identityType.getValue());
                        pst.setString(5,identityNumber.getText());
                        pst.setString(6,religion.getText());
                        pst.setString(7,residenceOccupation.getText());
                        pst.setInt(8,idOccupation);
                        pst.setString(9, String.valueOf(residenceEndDate.getValue()));
                        pst.setString(10, String.valueOf(HealthCertificateStartDate.getValue()));
                        pst.setString(11, String.valueOf(HealthCertificatEndDate.getValue()));
                        FileInputStream fis1=new FileInputStream(file1);
                        pst.setBinaryStream(12, fis1,(int) file1.length());

                        pst.setInt(13,idEdit);
                        pst.execute();
                        warningMsg("تعديل","تم التعديل بنجاح");
                    } catch (SQLException | FileNotFoundException throwables) {
                        throwables.printStackTrace();
                        warningMsg("تعديل","حدث خطأ أثناء التعديل");
                    }
                }

            }
            employeeEditPrivilege.setText("تعديل موظف");
            employeeName.clear();
            identityNumber.clear();
            residenceEndDate.getEditor().clear();
            HealthCertificateStartDate.getEditor().clear();
            HealthCertificatEndDate.getEditor().clear();
            reelOccupation.getItems().clear();
            identityType.getItems().clear();
            employeeNumber.clear();
            employeeNationality.clear();
            residenceOccupation.clear();
            religion.clear();
            identityType.setItems(identityTypeList);
            fillCombo();
            addToTable();
        }


    }

    @FXML
    private ImageView imageHealth1;

    @FXML
    private ImageView imageIdentity1;

    @FXML
    private TextField employeeName1;

    @FXML
    private TextField employeeNumber1;

    @FXML
    private TextField employeeNationality1;

    @FXML
    private TextField religion1;

    @FXML
    private TextField identityType1;

    @FXML
    private TextField identityNumber1;

    @FXML
    private TextField residenceOccupation1;

    @FXML
    private TextField reelOccupation1;

    @FXML
    private TextField residenceEndDate1;

    @FXML
    private TextField HealthCertificateStartDate1;

    @FXML
    private TextField HealthCertificatEndDate1;
    @FXML
    void idReset(MouseEvent event) {
        employeeEditPrivilege.setText("تعديل موظف");
        int index= employeeTableView.getSelectionModel().getSelectedIndex();
        int idEmpl=employeeTableView.getItems().get(index).getIdEmployee();

        employeeName1.setText(employeeTableView.getItems().get(index).getEmployeeName());
        identityNumber1.setText(employeeTableView.getItems().get(index).getIdentityNumber());
        residenceEndDate1.setText(employeeTableView.getItems().get(index).getResidenceEndDate());
        HealthCertificateStartDate1.setText(employeeTableView.getItems().get(index).getHealthCertificateStartDate());
        HealthCertificatEndDate1.setText(employeeTableView.getItems().get(index).getResidenceEndDate());
        reelOccupation1.setText(employeeTableView.getItems().get(index).getReelOccupationName());
        identityType1.setText(employeeTableView.getItems().get(index).getIdentityType());
        employeeNumber1.setText(employeeTableView.getItems().get(index).getEmployeeNumber());
        employeeNationality1.setText(employeeTableView.getItems().get(index).getEmployeeNationality());
        residenceOccupation1.setText(employeeTableView.getItems().get(index).getResidenceOccupation());
        religion1.setText(employeeTableView.getItems().get(index).getReligion());
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `employees` WHERE `id`=?");
            pst.setInt(1,idEmpl);
            rs=pst.executeQuery();

            while (rs.next()){
                Blob certificateImageBlob= (Blob) rs.getBlob("certificateImage");
                Blob identityImageBlob= (Blob) rs.getBlob("identityImage");
                if (certificateImageBlob==null && identityImageBlob==null){
                    Image imageLogo=new Image("imgs/CompLogo.png");

                    imageHealth1.setImage(imageLogo);
                    imageIdentity1.setImage(imageLogo);

                }else if (certificateImageBlob!=null && identityImageBlob!=null){
                    InputStream inputStream1=certificateImageBlob.getBinaryStream();
                    InputStream inputStream2=identityImageBlob.getBinaryStream();
                    Image image1=new Image(inputStream1);
                    Image image2=new Image(inputStream2);
                    imageHealth1.setImage(image1);
                    imageIdentity1.setImage(image2);

                }else if (certificateImageBlob==null && identityImageBlob!=null){
                    Image imageLogo=new Image("imgs/CompLogo.png");
                    InputStream inputStream2=identityImageBlob.getBinaryStream();
                    Image image2=new Image(inputStream2);
                    imageHealth1.setImage(imageLogo);
                    imageIdentity1.setImage(image2);

                }else if (certificateImageBlob!=null && identityImageBlob==null){
                    Image imageLogo=new Image("imgs/CompLogo.png");

                    InputStream inputStream1=certificateImageBlob.getBinaryStream();
                    Image image1=new Image(inputStream1);
                    imageHealth1.setImage(image1);
                    imageIdentity1.setImage(imageLogo);
                }

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    public void uploadHealth(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        file1=fileChooser.showOpenDialog(null);

    }
    public void uploadIdentity(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        file2=fileChooser.showOpenDialog(null);


    }
}



