package Controlers;

public class HistoricalGaranteeForTable {
    private int idHistorical , idUser ,idGarantee;
    private String dateHistorical, description , nameUser;

    public HistoricalGaranteeForTable(int idHistorical, int idUser, int idGarantee, String dateHistorical, String description, String nameUser) {
        this.idHistorical = idHistorical;
        this.idUser = idUser;
        this.idGarantee = idGarantee;
        this.dateHistorical = dateHistorical;
        this.description = description;
        this.nameUser = nameUser;
    }

    public int getIdHistorical() {
        return idHistorical;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdGarantee() {
        return idGarantee;
    }

    public String getDateHistorical() {
        return dateHistorical;
    }

    public String getDescription() {
        return description;
    }

    public String getNameUser() {
        return nameUser;
    }
}
