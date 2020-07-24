package Controlers;

public class OccupationForTable {
    private String occupationName;
    private int occupationId;
    public OccupationForTable(String occupationName, int occupationId) {
        this.occupationName = occupationName;
        this.occupationId = occupationId;
    }
    public String getOccupationName() {
        return occupationName;
    }

    public int getOccupationId() {
        return occupationId;
    }


}
