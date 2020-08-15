package Controlers;

public class ProjectForTable {
    private int projectId,areaId,locationId,contactDuration;
    private String contractName,areaName,locationName,projectType,contractStartDate,contractEndDate,contractNumber;
    private String contractPrice,contractPriceRest;

    public ProjectForTable(int projectId, int areaId, int locationId, int contactDuration, String contractName, String areaName, String locationName, String projectType, String contractStartDate, String contractEndDate, String contractPrice,String contractPriceRest, String contractNumber) {
        this.projectId = projectId;
        this.areaId = areaId;
        this.locationId = locationId;
        this.contactDuration = contactDuration;
        this.contractName = contractName;
        this.areaName = areaName;
        this.locationName = locationName;
        this.projectType = projectType;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractNumber = contractNumber;
        this.contractPrice = contractPrice;
        this.contractPriceRest = contractPriceRest;
    }

    public String getContractPriceRest() {
        return contractPriceRest;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getAreaId() {
        return areaId;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getContactDuration() {
        return contactDuration;
    }

    public String getContractName() {
        return contractName;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public String getContractPrice() {
        return contractPrice;
    }
}
