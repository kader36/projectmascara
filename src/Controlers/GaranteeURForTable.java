package Controlers;

public class GaranteeURForTable {
    private int idGarantee,idArea,idLocation,idBank;
    private String nameArea,nameLocation,nameProject,garanteeNumber,garanteeType,bankName;
    private Double garanteePrice;
    private String garanteeJiha;

    public GaranteeURForTable(int idGarantee, int idArea, int idLocation, int idBank, String nameArea, String nameLocation, String nameProject, String garanteeNumber, String garanteeType, String bankName, Double garanteePrice, String garanteeJiha) {
        this.idGarantee = idGarantee;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.idBank = idBank;
        this.nameArea = nameArea;
        this.nameLocation = nameLocation;
        this.nameProject = nameProject;
        this.garanteeNumber = garanteeNumber;
        this.garanteeType = garanteeType;
        this.bankName = bankName;
        this.garanteePrice = garanteePrice;
        this.garanteeJiha = garanteeJiha;
    }

    public String getGaranteeJiha() {
        return garanteeJiha;
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
