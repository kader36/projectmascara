
package Controlers;

public class Notification {
    private int idEmployee;
    private String employeeName,documentType,dateOfEnd;

    public Notification(int idEmployee, String employeeName, String documentType, String dateOfEnd) {
        this.idEmployee = idEmployee;
        this.employeeName = employeeName;
        this.documentType = documentType;
        this.dateOfEnd = dateOfEnd;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDateOfEnd() {
        return dateOfEnd;
    }
}
