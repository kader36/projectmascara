package Controlers;

public class ProjectForTable {
    private int projectId,areaId,locationId,contactDuration;
    private String contractName,areaName,locationName,projectType,contractStartDate,contractEndDate;
    private float contractPrice;

    public ProjectForTable(int projectId,int areaId, int locationId, int contactDuration, String contractName, String areaName, String locationName, String projectType, String contractStartDate, String contractEndDate, float contractPrice) {
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
        this.contractPrice = contractPrice;
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

    public float getContractPrice() {
        return contractPrice;
    }
}
