package Controlers;

public class EmployeeForTable {
    private int idEmployee,idOccupation;
    private String employeeName,employeeNumber,employeeNationality,identityType,identityNumber,religion,reelOccupationName,residenceOccupation,residenceEndDate,HealthCertificateStartDate,HealthCertificatEndDate,employeeSex,HealthCertificateNumber,ClassificationNumber,ClassificationStartDate,ClassificationEndDate,employeeType;

//    public EmployeeForTable(int idEmployee, int idOccupation, String employeeName, String employeeNumber, String employeeNationality, String identityType, String identityNumber, String religion, String reelOccupationName, String residenceOccupation, String residenceEndDate, String healthCertificateStartDate, String healthCertificatEndDate) {
//        this.idEmployee = idEmployee;
//        this.idOccupation = idOccupation;
//        this.employeeName = employeeName;
//        this.employeeNumber = employeeNumber;
//        this.employeeNationality = employeeNationality;
//        this.identityType = identityType;
//        this.identityNumber = identityNumber;
//        this.religion = religion;
//        this.reelOccupationName = reelOccupationName;
//        this.residenceOccupation = residenceOccupation;
//        this.residenceEndDate = residenceEndDate;
//        HealthCertificateStartDate = healthCertificateStartDate;
//        HealthCertificatEndDate = healthCertificatEndDate;
//    }


    public EmployeeForTable(int idEmployee, int idOccupation, String employeeName, String employeeNumber, String employeeNationality, String identityType, String identityNumber, String religion, String reelOccupationName, String residenceOccupation, String residenceEndDate, String healthCertificateStartDate, String healthCertificatEndDate, String employeeSex, String healthCertificateNumber, String classificationNumber, String classificationStartDate, String classificationEndDate, String employeeType) {
        this.idEmployee = idEmployee;
        this.idOccupation = idOccupation;
        this.employeeName = employeeName;
        this.employeeNumber = employeeNumber;
        this.employeeNationality = employeeNationality;
        this.identityType = identityType;
        this.identityNumber = identityNumber;
        this.religion = religion;
        this.reelOccupationName = reelOccupationName;
        this.residenceOccupation = residenceOccupation;
        this.residenceEndDate = residenceEndDate;
        HealthCertificateStartDate = healthCertificateStartDate;
        HealthCertificatEndDate = healthCertificatEndDate;
        this.employeeSex = employeeSex;
        HealthCertificateNumber = healthCertificateNumber;
        ClassificationNumber = classificationNumber;
        ClassificationStartDate = classificationStartDate;
        ClassificationEndDate = classificationEndDate;
        this.employeeType = employeeType;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public int getIdOccupation() {
        return idOccupation;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmployeeNationality() {
        return employeeNationality;
    }

    public String getIdentityType() {
        return identityType;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public String getReligion() {
        return religion;
    }

    public String getReelOccupationName() {
        return reelOccupationName;
    }

    public String getResidenceOccupation() {
        return residenceOccupation;
    }

    public String getResidenceEndDate() {
        return residenceEndDate;
    }

    public String getHealthCertificateStartDate() {
        return HealthCertificateStartDate;
    }

    public String getHealthCertificatEndDate() {
        return HealthCertificatEndDate;
    }

    public String getEmployeeSex() {
        return employeeSex;
    }

    public String getHealthCertificateNumber() {
        return HealthCertificateNumber;
    }

    public String getClassificationNumber() {
        return ClassificationNumber;
    }

    public String getClassificationStartDate() {
        return ClassificationStartDate;
    }

    public String getClassificationEndDate() {
        return ClassificationEndDate;
    }

    public String getEmployeeType() {
        return employeeType;
    }
}
