package Controlers;

public class ProjectExtensionForTable {
    private int idProjectExtension,idProject;
    private String extensionStartDate,extensionEndDate,directPurchaseStartDate,directPurchaseEndDate,directPurchasePrice,approvalDirectPurchase,isClosed;

    public ProjectExtensionForTable(int idProjectExtension, int idProject, String extensionStartDate, String extensionEndDate, String directPurchaseStartDate, String directPurchaseEndDate, String directPurchasePrice, String approvalDirectPurchase, String isClosed) {
        this.idProjectExtension = idProjectExtension;
        this.idProject = idProject;
        this.extensionStartDate = extensionStartDate;
        this.extensionEndDate = extensionEndDate;
        this.directPurchaseStartDate = directPurchaseStartDate;
        this.directPurchaseEndDate = directPurchaseEndDate;
        this.directPurchasePrice = directPurchasePrice;
        this.approvalDirectPurchase = approvalDirectPurchase;
        this.isClosed = isClosed;
    }

    public int getIdProjectExtension() {
        return idProjectExtension;
    }

    public int getIdProject() {
        return idProject;
    }

    public String getExtensionStartDate() {
        return extensionStartDate;
    }

    public String getExtensionEndDate() {
        return extensionEndDate;
    }

    public String getDirectPurchaseStartDate() {
        return directPurchaseStartDate;
    }

    public String getDirectPurchaseEndDate() {
        return directPurchaseEndDate;
    }

    public String getDirectPurchasePrice() {
        return directPurchasePrice;
    }

    public String getApprovalDirectPurchase() {
        return approvalDirectPurchase;
    }

    public String getIsClosed() {
        return isClosed;
    }
}
