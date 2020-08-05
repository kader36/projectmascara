package Controlers;

public class AbstractForTable {
    private int idAbstract,idArea,idLocation,idProject;
    private String nameArea,nameLocation,nameProject,contractNumber,contractType,contractStartDate,contractEndDate,janvier,fevrier,mars,avril,may,juin,juilliet,aout,septembre,octobre,novembre,decembre;

    public AbstractForTable(int idAbstract, int idArea, int idLocation, int idProject, String nameArea, String nameLocation, String nameProject, String contractNumber, String contractType, String contractStartDate, String contractEndDate, String janvier, String fevrier, String mars, String avril, String may, String juin, String juilliet, String aout, String septembre, String octobre, String novembre, String decembre) {
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
        this.janvier = janvier;
        this.fevrier = fevrier;
        this.mars = mars;
        this.avril = avril;
        this.may = may;
        this.juin = juin;
        this.juilliet = juilliet;
        this.aout = aout;
        this.septembre = septembre;
        this.octobre = octobre;
        this.novembre = novembre;
        this.decembre = decembre;
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

    public String getJanvier() {
        return janvier;
    }

    public String getFevrier() {
        return fevrier;
    }

    public String getMars() {
        return mars;
    }

    public String getAvril() {
        return avril;
    }

    public String getMay() {
        return may;
    }

    public String getJuin() {
        return juin;
    }

    public String getJuilliet() {
        return juilliet;
    }

    public String getAout() {
        return aout;
    }

    public String getSeptembre() {
        return septembre;
    }

    public String getOctobre() {
        return octobre;
    }

    public String getNovembre() {
        return novembre;
    }

    public String getDecembre() {
        return decembre;
    }
}
