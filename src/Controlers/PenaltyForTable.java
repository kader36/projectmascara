package Controlers;

public class PenaltyForTable {
    private int idPenalty,idArea,idLocation,idProject;
    private String nameArea,nameLocation,nameProject,typePenalty;
    private float amountOfPenalty;

    public PenaltyForTable(int idPenalty, int idArea, int idLocation, int idProject, String nameArea, String nameLocation, String nameProject, String typePenalty, float amountOfPenalty) {
        this.idPenalty = idPenalty;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.idProject = idProject;
        this.nameArea = nameArea;
        this.nameLocation = nameLocation;
        this.nameProject = nameProject;
        this.typePenalty = typePenalty;
        this.amountOfPenalty = amountOfPenalty;
    }

    public int getIdPenalty() {
        return idPenalty;
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

    public String getTypePenalty() {
        return typePenalty;
    }

    public float getAmountOfPenalty() {
        return amountOfPenalty;
    }
}
