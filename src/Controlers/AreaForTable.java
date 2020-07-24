package Controlers;

public class AreaForTable {
    private String areaName;
    private int areaId;

    public AreaForTable(String areaName, int areaId) {
        this.areaName = areaName;
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public int getAreaId() {
        return areaId;
    }
}
