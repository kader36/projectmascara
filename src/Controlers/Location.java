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
public class Location {
    private int idArea,idLocation;
    private String locationName;
    
    public Location(int idArea,int idLocation,String locationName){
        this.idArea=idArea;
        this.idLocation=idLocation;
        this.locationName=locationName;
    }
    
    public int getIdArea(){
        return idArea;
    }
    public int getIdLocation(){
        return idLocation;
    }
    public String getLocationName(){
        return locationName;
    }
    
}
