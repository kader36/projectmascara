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

public class ProjectPageEnded implements Initializable {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList projectsTable= FXCollections.observableArrayList();
    ObservableList<String> yesNo= FXCollections.observableArrayList("نعم","لا");

    ObservableList projectsExtensionTable= FXCollections.observableArrayList();


    @FXML
    private DatePicker extensionStartDate;

    @FXML
    private DatePicker extensionEndDate;

    @FXML
    private DatePicker directPurchaseStartDate;

    @FXML
    private DatePicker directPurchaseEndDate;

    @FXML
    private TextField directPurchasePrice;

    @FXML
    private ComboBox<String> approvalDirectPurchase;

    @FXML
    private ComboBox<String> isClosed;


    @FXML
    private TextField search;

    @FXML
    private TableView<ProjectForTable> projectEndedTableView;

    @FXML
    private TableColumn<ProjectForTable, String> projectNameTable;

    @FXML
    private TableColumn<ProjectForTable, String> projectTypeTable;

    @FXML
    private TableColumn<ProjectForTable, String> contractNumberTable;

    @FXML
    private TableColumn<ProjectForTable, String> areaNameTable;

    @FXML
    private TableColumn<ProjectForTable, String> locationNameTable;

    @FXML
    private TableColumn<ProjectForTable, String> contactDurationTable;

    @FXML
    private TableColumn<ProjectForTable, String> contractPriceTable;

    @FXML
    private TableColumn<ProjectForTable, String> contractStartDateTable;

    @FXML
    private TableColumn<ProjectForTable, String> contractEndDateTable;


    @FXML
    private TableView<ProjectExtensionForTable> projectExtensionTableView;

    @FXML
    private TableColumn<ProjectExtensionForTable, String> extensionStartDateTable;

    @FXML
    private TableColumn<ProjectExtensionForTable, String> extensionEndDateTable;

    @FXML
    private TableColumn<ProjectExtensionForTable, String> directPurchaseStartDateTable;

    @FXML
    private TableColumn<ProjectExtensionForTable, String> directPurchaseEndDateTable;

    @FXML
    private TableColumn<ProjectExtensionForTable, String> directPurchasePriceTable;

    @FXML
    private TableColumn<ProjectExtensionForTable, String> approvalDirectPurchaseTable;

    @FXML
    private TableColumn<ProjectExtensionForTable, String> isClosedTable;

    @FXML
    private Label usernameMenu;

    @FXML
    private Button areaMenuButton;

    @FXML
    private Button locationMenuButton;

    @FXML
    private Button projectMenuButton;

    @FXML
    private Button garanteeMenuButton;

    @FXML
    private Button occupationMenuButton;

    @FXML
    private Button employeeMenuButton;

    @FXML
    private Button abstractMenuButton;

    @FXML
    private Button deductionMenuButton;

    @FXML
    private Button penaltyMenuButton;

    @FXML
    private Button userMenuButton;

    @FXML
    private Button repportMenuButton;

    @FXML
    private Button projectDeletePrivilege4;

    @FXML
    private Button projectAddPrivilege3;

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
                if (rs.getInt("prsa1")==0){
                    projectAddPrivilege3.setDisable(true);

                }else{
                    projectAddPrivilege3.setDisable(false);

                }
                if (rs.getInt("prsd1")==0){
                    projectDeletePrivilege4.setDisable(true);

                }else{
                    projectDeletePrivilege4.setDisable(false);

                }
                if (rs.getInt("prse1")==0){
                    editButton.setDisable(true);

                }else{
                    editButton.setDisable(false);

                }

            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
    public void warningMsg(String title,String message ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    int resultMax=-1;int resultMin=-1;



    @FXML

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

    public void addToTable(){
        projectsTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects`,`areas`,`locations` WHERE projects.areaId=areas.id AND projects.locationId=locations.id AND projects.transfered=1 ");
            rs=pst.executeQuery();
            while (rs.next()){
                CheckBox ch=new CheckBox();
                projectsTable.add(new ProjectForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("contractName"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("contractPrice"),rs.getString("contractPrice"),rs.getString("contractNumber"),ch,rs.getString("jiha")));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }


    public void addToTableExtension(){
        int index= projectEndedTableView.getSelectionModel().getSelectedIndex();
        int id=projectEndedTableView.getItems().get(index).getProjectId();

        projectsExtensionTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projectextension` , `projects` WHERE projectextension.idProject=? AND projectextension.idProject=projects.id");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                projectsExtensionTable.add(new ProjectExtensionForTable(rs.getInt("id"),rs.getInt("idProject"),rs.getString("extensionStartDate"),rs.getString("extensionEndDate"),rs.getString("directPurchaseStartDate"),rs.getString("directPurchaseEndDate"),rs.getString("directPurchasePrice"),rs.getString("approvalDirectPurchase"),rs.getString("isClosed")));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }


    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
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


    @FXML
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


    @FXML
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



    @FXML
    public void search(KeyEvent keyEvent) {

        String key=search.getText().trim();
        if (key.isEmpty()){
            addToTable();
            projectEndedTableView.setItems(projectsTable);
        }else{
            projectsTable.clear();
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `projects`,`areas`,`locations` WHERE projects.transfered=1 AND projects.areaId=areas.id AND projects.locationId=locations.id AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    CheckBox ch=new CheckBox();
                    projectsTable.add(new ProjectForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("contactDuration"),rs.getString("contractName"),rs.getString("areaName"),rs.getString("locationName"),rs.getString("projectType"),rs.getString("contractStartDate"),rs.getString("contractEndDate"),rs.getString("contractPrice"),calculerRest(rs.getInt("id")),rs.getString("contractNumber"),ch,rs.getString("jiha")));
                }
                con.close();

                projectEndedTableView.setItems(projectsTable);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }



    }
    public String calculerRest(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null,somme=null,prixCont=null;
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT SUM(`masroufPrice`) AS SOMME FROM `projectmasroufate` WHERE `projectId`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                somme= String.valueOf(rs.getFloat("SOMME"));
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT `contractPrice` FROM `projects` WHERE `id`=?");
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while (rs.next()){
                prixCont= rs.getString("contractPrice");
            }
            pst.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return String.valueOf(Float.parseFloat(prixCont)-Float.parseFloat(somme));

    }

    @FXML
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addToTable();
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("areaName"));
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        projectTypeTable.setCellValueFactory(new PropertyValueFactory<>("projectType"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        contactDurationTable.setCellValueFactory(new PropertyValueFactory<>("contactDuration"));
        contractPriceTable.setCellValueFactory(new PropertyValueFactory<>("contractPrice"));
        contractStartDateTable.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
        contractEndDateTable.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));
        contractNumberTable.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        projectEndedTableView.setItems(projectsTable);
        approvalDirectPurchase.setItems(yesNo);
        isClosed.setItems(yesNo);

        extensionStartDateTable.setCellValueFactory(new PropertyValueFactory<>("extensionStartDate"));
        extensionEndDateTable.setCellValueFactory(new PropertyValueFactory<>("extensionEndDate"));
        directPurchaseStartDateTable.setCellValueFactory(new PropertyValueFactory<>("directPurchaseStartDate"));
        directPurchaseEndDateTable.setCellValueFactory(new PropertyValueFactory<>("directPurchaseEndDate"));
        directPurchasePriceTable.setCellValueFactory(new PropertyValueFactory<>("directPurchasePrice"));
        approvalDirectPurchaseTable.setCellValueFactory(new PropertyValueFactory<>("approvalDirectPurchase"));
        isClosedTable.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        projectExtensionTableView.setItems(projectsExtensionTable);

    }
    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        int index= projectExtensionTableView.getSelectionModel().getSelectedIndex();
        int idDelete=projectExtensionTableView.getItems().get(index).getIdProjectExtension();

        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `projectextension` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();
                warningMsg("حذف","تم الحذف بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("حذف","حدث خطأ أثناء الحذف");
            }
            idDelete=0;
            addToTableExtension();
        }
    }


    @FXML
    public void addProjectExtension(ActionEvent actionEvent) {
        int index= projectEndedTableView.getSelectionModel().getSelectedIndex();
        int idProject=projectEndedTableView.getItems().get(index).getProjectId();

            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `projectextension`(`idProject`, `extensionStartDate`, `extensionEndDate`, `directPurchaseStartDate`, `directPurchaseEndDate`, `directPurchasePrice`, `approvalDirectPurchase`, `isClosed`) VALUES (?,?,?,?,?,?,?,?)");
                pst.setInt(1,idProject);
                pst.setString(2,extensionStartDate.getEditor().getText());
                pst.setString(3,extensionEndDate.getEditor().getText());
                pst.setString(4,directPurchaseStartDate.getEditor().getText());
                pst.setString(5,directPurchaseEndDate.getEditor().getText());
                pst.setString(6,directPurchasePrice.getText());
                pst.setString(7,approvalDirectPurchase.getValue());
                pst.setString(8,isClosed.getValue());
                pst.execute();
                warningMsg("إظافة","تمت الإظافة بنجاح");
                pst.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                warningMsg("إظافة","حدث خطأ أثناء الإظافة");
            }
            addToTableExtension();
            extensionStartDate.getEditor().clear();
            extensionEndDate.getEditor().clear();
            directPurchaseStartDate.getEditor().clear();
            directPurchaseEndDate.getEditor().clear();
            directPurchasePrice.clear();


    }

    @FXML
    void reset(MouseEvent event) {
        addToTableExtension();
    }

    @FXML
    private Button editButton;
    @FXML
    public void edit1(ActionEvent actionEvent) {
        int index= projectExtensionTableView.getSelectionModel().getSelectedIndex();
        int idEdit=projectExtensionTableView.getItems().get(index).getIdProjectExtension();

        if (editButton.getText().contains("تعديل")){
            editButton.setText("حفظ");
            approvalDirectPurchase.setValue(projectExtensionTableView.getItems().get(index).getApprovalDirectPurchase());
            isClosed.setValue(projectExtensionTableView.getItems().get(index).getIsClosed());
            extensionStartDate.getEditor().setText(projectExtensionTableView.getItems().get(index).getExtensionStartDate());
            extensionEndDate.getEditor().setText(projectExtensionTableView.getItems().get(index).getExtensionEndDate());
            directPurchaseStartDate.getEditor().setText(projectExtensionTableView.getItems().get(index).getDirectPurchaseStartDate());
            directPurchaseEndDate.getEditor().setText(projectExtensionTableView.getItems().get(index).getDirectPurchaseEndDate());
            directPurchasePrice.setText(projectExtensionTableView.getItems().get(index).getDirectPurchasePrice());


        }else if (editButton.getText().contains("حفظ")){
                try {
                    con = new ConnectDB().getConnection();
                    pst = con.prepareStatement("UPDATE `projectextension` SET `extensionStartDate`=?,`extensionEndDate`=?,`directPurchaseStartDate`=?,`directPurchaseEndDate`=?,`directPurchasePrice`=?,`approvalDirectPurchase`=?,`isClosed`=? WHERE `id`=?");
                    pst.setString(1,extensionStartDate.getEditor().getText());
                    pst.setString(2,extensionEndDate.getEditor().getText());
                    pst.setString(3,directPurchaseStartDate.getEditor().getText());
                    pst.setString(4,directPurchaseEndDate.getEditor().getText());
                    pst.setString(5, directPurchasePrice.getText());
                    pst.setString(6, approvalDirectPurchase.getValue());
                    pst.setString(7, isClosed.getValue());
                    pst.setInt(8, idEdit);

                    pst.execute();
                    pst.close();

                    warningMsg("تعديل","تم التعديل بنجاح");
                    editButton.setText("تعديل");
                    extensionStartDate.getEditor().clear();
                    extensionEndDate.getEditor().clear();
                    directPurchaseStartDate.getEditor().clear();
                    directPurchaseEndDate.getEditor().clear();
                    directPurchasePrice.clear();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    warningMsg("تعديل","حدث خطأ أثناء التعديل");
                }
                addToTableExtension();
                idEdit=0;
        }


    }

}
