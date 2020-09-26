
package Controlers;

public class Nationality {
    private int idNationality;
    private String nameNationality;

    public Nationality(int idNationality, String nameNationality) {
        this.idNationality = idNationality;
        this.nameNationality = nameNationality;
    }

    public int getIdNationality() {
        return idNationality;
    }

    public String getNameNationality() {
        return nameNationality;
    }
}
