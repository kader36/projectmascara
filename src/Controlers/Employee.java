/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlers;

/**
 *
 * @author Mahdi
 */
public class Employee {
    private int idEmployee,idLocation,reelOccupation;
    private String employeeName,employeeNumber,employeeNationality,identityType,identityNumber,religion,residenceOccupation,residenceEndDate,HealthCertificateStartDate,HealthCertificatEndDate;
    
    public Employee(int idEmployee,String employeeName,int idLocation,String employeeNumber,String employeeNationality,String identityType,String identityNumber,String religion,String residenceOccupation,int reelOccupation,String residenceEndDate,String HealthCertificateStartDate,String HealthCertificatEndDate){
        this.idEmployee=idEmployee;
        this.idLocation=idLocation;
        this.employeeName=employeeName;
        this.employeeNumber=employeeNumber;
        this.employeeNationality=employeeNationality;
        this.identityType=identityType;
        this.identityNumber=identityNumber;
        this.religion=religion;
        this.residenceOccupation=residenceOccupation;
        this.reelOccupation=reelOccupation;
        this.residenceEndDate=residenceEndDate;
        this.HealthCertificateStartDate=HealthCertificateStartDate;
        this.HealthCertificatEndDate=HealthCertificatEndDate;



    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public int getReelOccupation() {
        return reelOccupation;
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
    
}
