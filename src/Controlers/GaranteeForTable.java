package Controlers;

public class GaranteeForTable {
    private int idGarantee,idArea,idLocation,idProject,idBank;
    private String nameArea,nameLocation,nameProject,garanteeNumber,garanteeType,bankName;
    private Double garanteePrice;

    public GaranteeForTable(int idGarantee, int idArea, int idLocation, int idProject, int idBank, String nameArea, String nameLocation, String nameProject, String garanteeNumber, String garanteeType, String bankName, Double garanteePrice) {
        this.idGarantee = idGarantee;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.idProject = idProject;
        this.idBank = idBank;
        this.nameArea = nameArea;
        this.nameLocation = nameLocation;
        this.nameProject = nameProject;
        this.garanteeNumber = garanteeNumber;
        this.garanteeType = garanteeType;
        this.bankName = bankName;
        this.garanteePrice = garanteePrice;
    }


    public String getBankName() {
        return bankName;
    }

    public int getIdBank() {
        return idBank;
    }

    public Double getGaranteePrice() {
        return garanteePrice;
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

}
