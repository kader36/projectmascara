package Controlers;

public class AbstractForTable {
    private int idDeduction,idArea,idLocation,idProject;
    private String nameArea,nameLocation,nameProject,typeDeduction;
    private float amountOfDeduction;

    public AbstractForTable(int idDeduction, int idArea, int idLocation, int idProject, String nameArea, String nameLocation, String nameProject, String typeDeduction, float amountOfDeduction) {
        this.idDeduction = idDeduction;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.idProject = idProject;
        this.nameArea = nameArea;
        this.nameLocation = nameLocation;
        this.nameProject = nameProject;
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

    public String getNameArea() {
        return nameArea;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public String getNameProject() {
        return nameProject;
    }

    public String getTypeDeduction() {
        return typeDeduction;
    }

    public float getAmountOfDeduction() {
        return amountOfDeduction;
    }
}
