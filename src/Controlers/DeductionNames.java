package Controlers;

public class DeductionNames {
    private String deductionName;
    private int idDeductionName;

    public DeductionNames(String deductionName, int idDeductionName) {
        this.deductionName = deductionName;
        this.idDeductionName = idDeductionName;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public int getIdDeductionName() {
        return idDeductionName;
    }
}
