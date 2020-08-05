package Controlers;

public class AbstractForTable {
    private int idAbstract,idArea,idLocation,idProject;
    private String nameArea,nameLocation,nameProject,contractNumber,contractType,contractStartDate,contractEndDate;

    public AbstractForTable(int idAbstract, int idArea, int idLocation, int idProject, String nameArea, String nameLocation, String nameProject, String contractNumber, String contractType, String contractStartDate, String contractEndDate) {
        this.idAbstract = idAbstract;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.idProject = idProject;
        this.nameArea = nameArea;
        this.nameLocation = nameLocation;
        this.nameProject = nameProject;
        this.contractNumber = contractNumber;
        this.contractType = contractType;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
    }

    public int getIdAbstract() {
        return idAbstract;
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

    public String getContractNumber() {
        return contractNumber;
    }

    public String getContractType() {
        return contractType;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }
}
