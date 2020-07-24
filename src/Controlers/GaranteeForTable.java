package Controlers;

public class GaranteeForTable {
    private int idGarantee,idArea,idLocation,idProject;
    private String nameArea,nameLocation,nameProject,garanteeNumber,garanteeType,contractType;

    public GaranteeForTable(int idGarantee, int idArea, int idLocation, int idProject, String nameArea, String nameLocation, String nameProject, String garanteeNumber, String garanteeType, String contractType) {
        this.idGarantee = idGarantee;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.idProject = idProject;
        this.nameArea = nameArea;
        this.nameLocation = nameLocation;
        this.nameProject = nameProject;
        this.garanteeNumber = garanteeNumber;
        this.garanteeType = garanteeType;
        this.contractType = contractType;
    }

    public int getIdGarantee() {
        return idGarantee;
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

    public String getGaranteeNumber() {
        return garanteeNumber;
    }

    public String getGaranteeType() {
        return garanteeType;
    }

    public String getContractType() {
        return contractType;
    }
}
