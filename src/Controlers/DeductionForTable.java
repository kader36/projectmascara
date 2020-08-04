package Controlers;

public class DeductionForTable {
    private int idDeduction,idArea,idLocation,idProject,idEmployee;
    private String nameArea,nameLocation,nameProject,nameEmployee,typeDeduction;
    private String amountOfDeduction;

    public DeductionForTable(int idDeduction, int idArea, int idLocation, int idProject, int idEmployee, String nameArea, String nameLocation, String nameProject, String nameEmployee, String typeDeduction, String amountOfDeduction) {
        this.idDeduction = idDeduction;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.idProject = idProject;
        this.idEmployee = idEmployee;
        this.nameArea = nameArea;
        this.nameLocation = nameLocation;
        this.nameProject = nameProject;
        this.nameEmployee = nameEmployee;
        this.typeDeduction = typeDeduction;
        this.amountOfDeduction = amountOfDeduction;
    }

    public int getIdDeduction() {
        return idDeduction;
    }

    public int getIdArea() {
        return idArea;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public int getIdProject() {
        return idProject;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getNameArea() {
        return nameArea;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public String getNameProject() {
        return nameProject;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public String getTypeDeduction() {
        return typeDeduction;
    }

    public String getAmountOfDeduction() {
        return amountOfDeduction;
    }
}
