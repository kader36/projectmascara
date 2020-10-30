package Controlers;

public class GaranteePolicyForTable {
    private int idPolicy,idArea,idLocation,idProject;
    private String contractName,contractNumber,contractStartDate,contractEndDate,policyNumber,policyBeginDate,policyEndDate,policyRenewDate;
    private Double contractPrice;

    public GaranteePolicyForTable(int idPolicy, int idArea, int idLocation, int idProject, String contractName, String contractNumber, String contractStartDate, String contractEndDate, String policyNumber, String policyBeginDate, String policyEndDate, String policyRenewDate, Double contractPrice) {
        this.idPolicy = idPolicy;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.idProject = idProject;
        this.contractName = contractName;
        this.contractNumber = contractNumber;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.policyNumber = policyNumber;
        this.policyBeginDate = policyBeginDate;
        this.policyEndDate = policyEndDate;
        this.policyRenewDate = policyRenewDate;
        this.contractPrice = contractPrice;
    }

    public Double getContractPrice() {
        return contractPrice;
    }

    public int getIdPolicy() {
        return idPolicy;
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

    public String getContractName() {
        return contractName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getPolicyBeginDate() {
        return policyBeginDate;
    }

    public String getPolicyEndDate() {
        return policyEndDate;
    }

    public String getPolicyRenewDate() {
        return policyRenewDate;
    }
}
