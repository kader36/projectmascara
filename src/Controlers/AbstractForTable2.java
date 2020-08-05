package Controlers;

public class AbstractForTable2 {
    private int idAbstractYears,idAbstract;
    private String year,janvier,fevrier,mars,avril,may,juin,juilliet,aout,septembre,octobre,novembre,decembre;

    public AbstractForTable2(int idAbstractYears, int idAbstract, String year, String janvier, String fevrier, String mars, String avril, String may, String juin, String juilliet, String aout, String septembre, String octobre, String novembre, String decembre) {
        this.idAbstractYears = idAbstractYears;
        this.idAbstract = idAbstract;
        this.year = year;
        this.janvier = janvier;
        this.fevrier = fevrier;
        this.mars = mars;
        this.avril = avril;
        this.may = may;
        this.juin = juin;
        this.juilliet = juilliet;
        this.aout = aout;
        this.septembre = septembre;
        this.octobre = octobre;
        this.novembre = novembre;
        this.decembre = decembre;
    }

    public int getIdAbstractYears() {
        return idAbstractYears;
    }

    public int getIdAbstract() {
        return idAbstract;
    }

    public String getYear() {
        return year;
    }

    public String getJanvier() {
        return janvier;
    }

    public String getFevrier() {
        return fevrier;
    }

    public String getMars() {
        return mars;
    }

    public String getAvril() {
        return avril;
    }

    public String getMay() {
        return may;
    }

    public String getJuin() {
        return juin;
    }

    public String getJuilliet() {
        return juilliet;
    }

    public String getAout() {
        return aout;
    }

    public String getSeptembre() {
        return septembre;
    }

    public String getOctobre() {
        return octobre;
    }

    public String getNovembre() {
        return novembre;
    }

    public String getDecembre() {
        return decembre;
    }
}
