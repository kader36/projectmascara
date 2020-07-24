package Controlers;

public class LocationForTable {
    private int idArea,idLocation;
    private String areaName,locationName;

    public LocationForTable(int idArea, int idLocation, String areaName, String locationName) {
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.areaName = areaName;
        this.locationName = locationName;
    }

    public int getIdArea() {
        return idArea;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getLocationName() {
        return locationName;
    }
}
