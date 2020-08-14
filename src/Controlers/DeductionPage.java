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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class DeductionPage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<DeductionNames> deductionNames= FXCollections.observableArrayList();
    int idArea=0,idLocation=0,idProject=0,idEmployee=0,idDeduction=0;

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> locationName;
    @FXML
    private ComboBox<String> typeDeduction;
    @FXML
    private TextField amountOfDeduction;

    @FXML
    private ComboBox<String> projectName;

    @FXML
    void selectArea(ActionEvent event) {
        int index= areaName.getSelectionModel().getSelectedIndex();
        idArea=areas.get(index).getIdArea();
        fillComboLocation();
        projectName.getItems().clear();

    }
    @FXML
    void selectLocation(ActionEvent event) {
        int index= locationName.getSelectionModel().getSelectedIndex();
        idLocation=locations.get(index).getIdLocation();
        fillComboProject();

    }
    @FXML
    void selectDeduction(ActionEvent event) {
        int index= typeDeduction.getSelectionModel().getSelectedIndex();
        idDeduction=deductionNames.get(index).getIdDeductionName();


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

    public void fillComboDeductionType(){
        deductionNames.clear();
        typeDeduction.getItems().clear();
        try {

            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductionnames`");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionNames.add(new DeductionNames(rs.getString("deductionName"), rs.getInt("id")));

            }

            for (int i=0;i<deductionNames.size();i++){
                typeDeduction.getItems().add(deductionNames.get(i).getDeductionName());
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

    public void areas(ActionEvent actionEvent) {
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


    public void addDeduction(ActionEvent actionEvent) {
        try {
            con=new ConnectDB().getConnection();
            if (idEmployee>0){
                pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `idEmployeeDeduction`, `dorp`) VALUES (?,?,?,?,?,?,?,?)");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setString(3,typeDeduction.getValue());
                pst.setFloat(4, Float.parseFloat(amountOfDeduction.getText()));
                pst.setInt(5,idProject);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );

                pst.setString(6, sdf.format(new Date()));
                pst.setInt(7,idEmployee);
                pst.setString(8,"d");

            }else{
                pst=con.prepareStatement("INSERT INTO `deductions`(`idArea`, `idLocation`, `typeDeduction`, `amountOfDeduction`, `idProject`, `deductionDate`, `dorp`) VALUES (?,?,?,?,?,?,?)");
                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setString(3,typeDeduction.getValue());
                pst.setFloat(4, Float.parseFloat(amountOfDeduction.getText()));
                pst.setInt(5,idProject);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );
                pst.setString(6, sdf.format(new Date()));
                pst.setString(7,"d");

            }



            pst.execute();
            projectName.getItems().clear();
            locationName.getItems().clear();
            areaName.getItems().clear();
            amountOfDeduction.clear();
            fillComboArea();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable();
        amountOfDeduction.clear();
    }

    @FXML
    private TableView<DeductionForTable> deductionTableView;

    @FXML
    private TableColumn<GaranteeForTable, String> areaNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> locationNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> projectNameTable;

    @FXML
    private TableColumn<GaranteeForTable, Float> amountOfDeductionTable;

    @FXML
    private TableColumn<GaranteeForTable, String> typeDeductionTable;
    @FXML
    private TableView<DeductionNames> deductionNamesTableView;

    @FXML
    private TableColumn<DeductionNames, String> nameDeductionnTable;
    ObservableList deductionsTable= FXCollections.observableArrayList();
    ObservableList deductionNamesTable= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboArea();
        fillComboDeductionType();
        addToTable();
        addToTable2();
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        amountOfDeductionTable.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
        typeDeductionTable.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
        deductionTableView.setItems(deductionsTable);
        nameDeductionnTable.setCellValueFactory(new PropertyValueFactory<>("deductionName"));
        deductionNamesTableView.setItems(deductionNamesTable);
    }
    public void addToTable(){
        deductionsTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductions` WHERE `dorp`='d'");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionsTable.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getProjectName(rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject")),getEmployeeName(rs.getInt("idEmployeeDeduction")),rs.getString("typeDeduction"),rs.getString("amountOfDeduction")));

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    public void addToTable2(){
        deductionNamesTable.clear();
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `deductionnames`");
            rs=pst.executeQuery();
            while (rs.next()){
                deductionNamesTable.add(new DeductionNames(rs.getString("deductionName"), rs.getInt("id")));

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
            con=new ConnectDB().getConnection();
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
    public String getLocationName(int idArea,int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new ConnectDB().getConnection();
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
    public String getProjectName(int idArea,int idLocation,int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `projects` WHERE `id`=? AND `areaId`=? AND `locationId`=?");
            pst.setInt(1,id);
            pst.setInt(2,idArea);
            pst.setInt(3,idLocation);
            rs=pst.executeQuery();
            while (rs.next()){
                return result= rs.getString("contractName");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return result;

    }
    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        int index= deductionTableView.getSelectionModel().getSelectedIndex();
        int idDelete=deductionTableView.getItems().get(index).getIdDeduction();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `deductions` WHERE `id`=?");
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
            areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
            locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
            projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
            amountOfDeductionTable.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
            typeDeductionTable.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
            deductionTableView.setItems(deductionsTable);
        }else{
            deductionsTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `deductions`,`projects` WHERE deductions.idProject =projects.id AND deductions.dorp='d' AND projects.contractName LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    deductionsTable.add(new DeductionForTable(rs.getInt("id"),rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject"),rs.getInt("idEmployeeDeduction"),getAreaName(rs.getInt("idArea")),getLocationName(rs.getInt("idArea"),rs.getInt("idLocation")),getProjectName(rs.getInt("idArea"),rs.getInt("idLocation"),rs.getInt("idProject")),getEmployeeName(rs.getInt("idEmployeeDeduction")),rs.getString("typeDeduction"),rs.getString("amountOfDeduction")));


                }
                areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
                locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
                projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
                amountOfDeductionTable.setCellValueFactory(new PropertyValueFactory<>("amountOfDeduction"));
                typeDeductionTable.setCellValueFactory(new PropertyValueFactory<>("typeDeduction"));
                deductionTableView.setItems(deductionsTable);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

    @FXML
    private Button edit;
    public void edit(ActionEvent actionEvent) {
        int index= deductionTableView.getSelectionModel().getSelectedIndex();
        int idEdit=deductionTableView.getItems().get(index).getIdDeduction();
        int idArea=0,idLocation=0,idEmployee=0,idProject=0;


        if (edit.getText().contains("تعديل إستقطاع")){
            edit.setText("حفظ");
            areaName.setValue(deductionTableView.getItems().get(index).getNameArea());
            locationName.setValue(deductionTableView.getItems().get(index).getNameLocation());
            projectName.setValue(deductionTableView.getItems().get(index).getNameProject());
            typeDeduction.setValue(deductionTableView.getItems().get(index).getTypeDeduction());
            amountOfDeduction.setText(deductionTableView.getItems().get(index).getAmountOfDeduction());
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

                for (int i=0; i<projects.size() ;i++){
                    if (projects.get(i).getContractName()==projectName.getValue()){
                        idProject=projects.get(i).getIdProject();
                    }
                }
                con = new Controlers.ConnectDB().getConnection();

                pst = con.prepareStatement("UPDATE `deductions` SET `idArea`=?,`idLocation`=?,`typeDeduction`=?,`amountOfDeduction`=?,`idProject`=? WHERE `id`=?");

                pst.setInt(1, idArea);
                pst.setInt(2, idLocation);
                pst.setString(3, typeDeduction.getValue());
                pst.setString(4, amountOfDeduction.getText());
                pst.setInt(5, idProject);
                pst.setInt(6, idEdit);

                pst.execute();
                edit.setText("تعديل إستقطاع");
                projectName.getItems().clear();
                locationName.getItems().clear();
                areaName.getItems().clear();
                amountOfDeduction.clear();

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
        edit.setText("تعديل إستقطاع");
    }




    @FXML
    private TextField nameDeductionn;

    public void addDeductionn(ActionEvent actionEvent) {
        try {
            con=new ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `deductionnames`(`deductionName`) VALUES (?)");
            pst.setString(1,nameDeductionn.getText());
            pst.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable2();
        fillComboDeductionType();
    }


    @FXML
    public void deleteRoww(ActionEvent actionEvent) {
        int index= deductionNamesTableView.getSelectionModel().getSelectedIndex();
        int idDelete=deductionNamesTableView.getItems().get(index).getIdDeductionName();
        if (idDelete>0) {
            try {
                con = new ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `deductionnames` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idDelete=0;
            addToTable2();
        }
        fillComboDeductionType();
    }
    @FXML
    private TextField search2;
    @FXML
    public void search2(KeyEvent keyEvent) {
        String key=search2.getText().trim();
        if (key.isEmpty()){
            addToTable2();
            nameDeductionnTable.setCellValueFactory(new PropertyValueFactory<>("deductionName"));
            deductionNamesTableView.setItems(deductionNamesTable);
        }else{
            deductionNamesTable.clear();
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM  `deductionnames` WHERE `deductionName` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    deductionNamesTable.add(new DeductionNames(rs.getString("deductionName"), rs.getInt("id")));
                }
                nameDeductionnTable.setCellValueFactory(new PropertyValueFactory<>("deductionName"));
                deductionNamesTableView.setItems(deductionNamesTable);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

}
