package Controlers;

public class projectEmployeeForTable {
    private int id,idArea,idLocation,idProject,idOccupation,idEmployee;
    private String areaName,locationName,projectName,occupationName,employeeName;

    public projectEmployeeForTable(int id, int idArea, int idLocation, int idProject, int idOccupation, int idEmployee, String areaName, String locationName, String projectName, String occupationName, String employeeName) {
        this.id = id;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.idProject = idProject;
        this.idOccupation = idOccupation;
        this.idEmployee = idEmployee;
        this.areaName = areaName;
        this.locationName = locationName;
        this.projectName = projectName;
        this.occupationName = occupationName;
        this.employeeName = employeeName;
    }

    public int getId() {
        return id;
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

    public int getIdOccupation() {
        return idOccupation;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
