package Controlers;

public class Penalty {
    private int idPenalty,idArea,idLocation;
    private String typePenalty;
    private float amountOfPenalty;

    public Penalty(int idPenalty, int idArea, int idLocation, String typePenalty, float amountOfPenalty) {
        this.idPenalty = idPenalty;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.typePenalty = typePenalty;
        this.amountOfPenalty = amountOfPenalty;
    }

    public int getIdPenalty() {
        return idPenalty;
    }

    public int getIdArea() {
        return idArea;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public String getTypePenalty() {
        return typePenalty;
    }

    public float getAmountOfPenalty() {
        return amountOfPenalty;
    }
}
