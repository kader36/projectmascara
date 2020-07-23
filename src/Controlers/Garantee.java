/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaderproject;

/**
 *
 * @author Mahdi
 */
public class Garantee {
    private int idGarantee,idArea,idLocation;
    private String garanteeNumber,garanteeType,contractType;
    
    public Garantee(int idGarantee,int idArea,int idLocation,String garanteeNumber,String garanteeType,String contractType){
        this.idGarantee=idGarantee;
        this.idLocation=idLocation;
        this.idArea=idArea;
        this.garanteeNumber=garanteeNumber;
        this.garanteeType=garanteeType;
        this.contractType=contractType;

    }
    
    public int getIdGarantee(){
        return idGarantee;
    }
    public int getIdArea(){
        return idArea;
    }
    public int getIdLocation(){
        return idLocation;
    }
    public String getGaranteeNumber(){
        return garanteeNumber;
    }
    public String getGaranteeType(){
        return garanteeType;
    }
    public String getContractType(){
        return contractType;
    }
}
