package Controlers;

public class Banks {
    private int idBank;
    private String bankName;

    public Banks(int idBank, String bankName) {
        this.idBank = idBank;
        this.bankName = bankName;
    }

    public int getIdBank() {
        return idBank;
    }

    public String getBankName() {
        return bankName;
    }
}
