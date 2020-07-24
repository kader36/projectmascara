package Controlers;

public class User {
    private int idUser,idArea,idLocation,privilegesId;
    private String username,password,phoneNumber,employeeNumber,email;

    public User(int idUser, int idArea, int idLocation, int privilegesId, String username, String password,String email, String phoneNumber, String employeeNumber) {
        this.idUser = idUser;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.privilegesId = privilegesId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employeeNumber = employeeNumber;
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

    public int getPrivilegesId() {
        return privilegesId;
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
}
