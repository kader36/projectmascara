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
public class Occupation {
    private int idOcupation;
    private String nameOcupation;
    
    public Occupation(int idOcupation,String nameOcupation){
        this.idOcupation=idOcupation;
        this.nameOcupation=nameOcupation;
    }
    
    public int getIdOcupation(){
        return idOcupation;
    }
    public String getNameOcupation(){
        return nameOcupation;
    }
    
}
