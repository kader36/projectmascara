package Controlers;

public class Deduction {
    private int idDeduction,idArea,idLocation;
    private String typeDeduction;
    private float amountOfDeduction;

    public Deduction(int idDeduction, int idArea, int idLocation, String typeDeduction, float amountOfDeduction) {
        this.idDeduction = idDeduction;
        this.idArea = idArea;
        this.idLocation = idLocation;
        this.typeDeduction = typeDeduction;
        this.amountOfDeduction = amountOfDeduction;
    }

    public int getIdDeduction() {
        return idDeduction;
    }

    public int getIdArea() {
        return idArea;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public String getTypeDeduction() {
        return typeDeduction;
    }

    public float getAmountOfDeduction() {
        return amountOfDeduction;
    }
}
