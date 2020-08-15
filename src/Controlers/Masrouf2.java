package Controlers;

public class Masrouf2 {
    private int idMasrouf,idProject;
    private String masroufName;
    private Float masroufPrice;

    public Masrouf2(int idMasrouf, int idProject, String masroufName, Float masroufPrice) {
        this.idMasrouf = idMasrouf;
        this.idProject = idProject;
        this.masroufName = masroufName;
        this.masroufPrice = masroufPrice;
    }

    public int getIdMasrouf() {
        return idMasrouf;
    }

    public int getIdProject() {
        return idProject;
    }

    public String getMasroufName() {
        return masroufName;
    }

    public Float getMasroufPrice() {
        return masroufPrice;
    }
}
