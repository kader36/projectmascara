package Controlers;

public class EmployeeForList {
    private int id;
    private String employeeName;

    public EmployeeForList(int id, String employeeName) {
        this.id = id;
        this.employeeName = employeeName;
    }

    public int getId() {
        return id;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
