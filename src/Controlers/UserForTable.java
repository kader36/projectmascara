package Controlers;

public class UserForTable {

    private int idUser,idArea,idLocation,privilegeId;
    private String employeeName,username,password,email,phoneNumber,employeeNumber,areaName,locationName,privilegeName;

    public UserForTable(int idUser, int privilegeId, String employeeName, String username, String password, String email, String phoneNumber, String employeeNumber, String privilegeName) {
        this.idUser = idUser;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.privilegeId = privilegeId;
        this.employeeName = employeeName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employeeNumber = employeeNumber;
        this.areaName = areaName;
        this.locationName = locationName;
        this.privilegeName = privilegeName;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdArea() {
        return idArea;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public int getPrivilegeId() {
        return privilegeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }
}
