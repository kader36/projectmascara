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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class GaranteePage implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Area> areas= FXCollections.observableArrayList();
    ObservableList<Location> locations= FXCollections.observableArrayList();
    ObservableList<Project> projects= FXCollections.observableArrayList();
    ObservableList<String> contracts= FXCollections.observableArrayList("طهي","توريد");
    ObservableList<String> garantees= FXCollections.observableArrayList("الافراج عن الضمان","تخفيض 5% من الضمان");
    int idArea=0,idLocation=0,idProject=0,idGarantee=0,idHistorical;

    @FXML
    private ComboBox<String> areaName;
    @FXML
    private ComboBox<String> locationName;
    @FXML
    private ComboBox<String> garanteeType;

    @FXML
    private TextField garanteeNumber;
    @FXML
    private ComboBox<String> projectName;


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
    public void fillComboProject(){
        projects.clear();
        projectName.getItems().clear();
        try {

            con=new Controlers.ConnectDB().getConnection();
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
        areaName.getItems().clear();
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


    public void addGarantee(ActionEvent actionEvent) {
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("INSERT INTO `garantees`(`areaId`, `locationId`,`idProject`, `garanteeNumber`, `garanteeType`) VALUES (?,?,?,?,?)");
            pst.setInt(1,idArea);
            pst.setInt(2,idLocation);
            pst.setInt(3,idProject);
            pst.setString(4,garanteeNumber.getText());
            pst.setString(5,garanteeType.getValue());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addToTable();
        areaName.getItems().clear();
        locationName.getItems().clear();
        projectName.getItems().clear();
        garanteeNumber.clear();
        garanteeType.getItems().clear();
        garanteeType.setItems(garantees);
        fillComboArea();
    }
    @FXML
    private TableView<GaranteeForTable> garanteeTableView;

    @FXML
    private TableColumn<GaranteeForTable, String> areaNameTable;

    @FXML
    private TableColumn<GaranteeForTable, String> locationNameTable;

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
        garanteeType.setItems(garantees);


        addToTable();
        locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
        projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
        garanteeNumberTable.setCellValueFactory(new PropertyValueFactory<>("garanteeNumber"));
        garanteeTypeTable.setCellValueFactory(new PropertyValueFactory<>("garanteeType"));
        garanteeTableView.setItems(garanteesTable);
    }
    public void addToTable(){
        garanteesTable.clear();
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `garantees`");
            rs=pst.executeQuery();
            while (rs.next()){
                garanteesTable.add(new GaranteeForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject"),getAreaName(rs.getInt("areaId")),getLocationName(rs.getInt("areaId"),rs.getInt("locationId")),getProjectName(rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject")),rs.getString("garanteeNumber"),rs.getString("garanteeType")));

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
    public String getProjectName(int idArea,int idLocation,int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
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
        int index= garanteeTableView.getSelectionModel().getSelectedIndex();
        int idDelete=garanteeTableView.getItems().get(index).getIdGarantee();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `garantees` WHERE `id`=?");
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
            locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
            areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
            projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
            garanteeNumberTable.setCellValueFactory(new PropertyValueFactory<>("garanteeNumber"));
            garanteeTypeTable.setCellValueFactory(new PropertyValueFactory<>("garanteeType"));
            garanteeTableView.setItems(garanteesTable);
        }else{
            garanteesTable.clear();
            try {
                con=new ConnectDB().getConnection();
                pst=con.prepareStatement("SELECT * FROM `garantees` WHERE `garanteeNumber` LIKE '%"+key+"%'");
                rs=pst.executeQuery();
                while (rs.next()){
                    garanteesTable.add(new GaranteeForTable(rs.getInt("id"),rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject"),getAreaName(rs.getInt("areaId")),getLocationName(rs.getInt("areaId"),rs.getInt("locationId")),getProjectName(rs.getInt("areaId"),rs.getInt("locationId"),rs.getInt("idProject")),rs.getString("garanteeNumber"),rs.getString("garanteeType")));
                }
                locationNameTable.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
                areaNameTable.setCellValueFactory(new PropertyValueFactory<>("nameArea"));
                projectNameTable.setCellValueFactory(new PropertyValueFactory<>("nameProject"));
                garanteeNumberTable.setCellValueFactory(new PropertyValueFactory<>("garanteeNumber"));
                garanteeTypeTable.setCellValueFactory(new PropertyValueFactory<>("garanteeType"));
                garanteeTableView.setItems(garanteesTable);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }

    @FXML
    private Button edit;
    public void edit(ActionEvent actionEvent) {
        int index= garanteeTableView.getSelectionModel().getSelectedIndex();
        int idEdit=garanteeTableView.getItems().get(index).getIdGarantee();
        int idArea=0,idLocation=0,idProject=0;


        if (edit.getText().contains("تعديل ضمان")){
            edit.setText("حفظ");
            areaName.setValue(garanteeTableView.getItems().get(index).getNameArea());
            locationName.setValue(garanteeTableView.getItems().get(index).getNameLocation());
            projectName.setValue(garanteeTableView.getItems().get(index).getNameProject());
            garanteeType.setValue(garanteeTableView.getItems().get(index).getGaranteeType());
            garanteeNumber.setText(garanteeTableView.getItems().get(index).getGaranteeNumber());
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
                pst = con.prepareStatement("UPDATE `garantees` SET `areaId`=?,`locationId`=?,`idProject`=?,`garanteeNumber`=?,`garanteeType`=? WHERE `id`=?");


                pst.setInt(1,idArea);
                pst.setInt(2,idLocation);
                pst.setInt(3,idProject);
                pst.setString(4,garanteeNumber.getText());
                pst.setString(5,garanteeType.getValue());
                pst.setInt(6,idEdit);
                pst.execute();
                edit.setText("تعديل ضمان");
                locationName.getItems().clear();
                projectName.getItems().clear();
                garanteeNumber.clear();
                garanteeType.getItems().clear();
                garanteeType.setItems(garantees);



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            addToTable();
            idEdit=0;
        }


    }


    ObservableList<HistoricalGaranteeForTable> historicalGarantee= FXCollections.observableArrayList();

    @FXML
    private TextArea description;

    @FXML
    void idReset(MouseEvent event) {
        edit.setText("تعديل ضمان");
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
            System.out.println(idGarantee);
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `historicalgarantees` WHERE `idGarantee`=?");
            pst.setInt(1,idGarantee);
            rs=pst.executeQuery();
            while (rs.next()){
                historicalGarantee.add(new HistoricalGaranteeForTable(rs.getInt("id"),rs.getInt("idUser"),rs.getInt("idGarantee"),rs.getString("dateHistorical"),rs.getString("description"),getUserName(rs.getInt("idUser"))));

            }


        } catch (SQLException throwables) {
            System.out.println("problem here");
        }
    }
    public String getUserName(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String result = null;
        try {
            con=new Controlers.ConnectDB().getConnection();
            pst=con.prepareStatement("SELECT * FROM `users` WHERE `id`=?");
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

    public void selectIdHistorical(MouseEvent mouseEvent) {
        int index=historicalGaranteeTableView.getSelectionModel().getSelectedIndex();
        idHistorical=historicalGaranteeTableView.getItems().get(index).getIdHistorical();
    }

    public void addHistorical(ActionEvent actionEvent) {
        if (idGarantee>0) {
            try {
                con=new Controlers.ConnectDB().getConnection();
                pst=con.prepareStatement("INSERT INTO `historicalgarantees`(`dateHistorical`, `description`, `idUser`, `idGarantee`) VALUES (?,?,?,?)");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" );

                pst.setString(1, sdf.format(new Date()));
                pst.setString(2,description.getText());
                pst.setInt(3,1);
                pst.setInt(4,idGarantee);
                pst.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            fillTableHistoricalGarantee();
        }

    }
    @FXML
    public void deleteRow2(ActionEvent actionEvent) {
        int index= historicalGaranteeTableView.getSelectionModel().getSelectedIndex();
        int idDelete=historicalGaranteeTableView.getItems().get(index).getIdHistorical();
        if (idDelete>0) {
            try {
                con = new Controlers.ConnectDB().getConnection();
                pst = con.prepareStatement("DELETE FROM `historicalgarantees` WHERE `id`=?");
                pst.setInt(1, idDelete);
                pst.execute();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idDelete=0;
            fillTableHistoricalGarantee();
        }
    }
}
