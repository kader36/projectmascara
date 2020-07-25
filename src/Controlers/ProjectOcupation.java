package Controlers;

public class ProjectOcupation {
    private int idProjectOccupation,idProject,idOccupation,maxNumber,realNumber;
    private String occupationName;

    public ProjectOcupation(int idProjectOccupation, int idProject, int idOccupation, int maxNumber, int realNumber,  String occupationName) {
        this.idProjectOccupation = idProjectOccupation;
        this.idProject = idProject;
        this.idOccupation = idOccupation;
        this.maxNumber = maxNumber;
        this.realNumber = realNumber;
        this.occupationName = occupationName;
    }

    public int getIdProjectOccupation() {
        return idProjectOccupation;
    }

    public int getIdProject() {
        return idProject;
    }

    public int getIdOccupation() {
        return idOccupation;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getRealNumber() {
        return realNumber;
    }

    public String getOccupationName() {
        return occupationName;
    }
}
