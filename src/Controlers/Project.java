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
public class Project {
    private int idProject,areaId,locationId,contactDuration;
    private String projectType,contractName,contractNumber,contractDate,contractStartDate,contractEndDate;
    private float contractPrice;
    
    public Project(int idProject,int areaId,int locationId,int contactDuration,String projectType,String contractName,String contractNumber,String contractDate,String contractStartDate,String contractEndDate,float contractPrice){
        this.idProject=idProject;
        this.areaId=areaId;
        this.locationId=locationId;
        this.contactDuration=contactDuration;
        this.projectType=projectType;
        this.contractName=contractName;
        this.contractNumber=contractNumber;
        this.contractDate=contractDate;
        this.contractStartDate=contractStartDate;
        this.contractEndDate=contractEndDate;
        this.contractPrice=contractPrice;

    }
    public int getIdProject(){
        return idProject;
    }
    public int getAreaId(){
        return areaId;
    }
    public int getLocationId(){
        return locationId;
    }
    public int getContactDuration(){
        return contactDuration;
    }
    public String getProjectType(){
        return projectType;
    }
    public String getContractName(){
        return contractName;
    }
    public String getContractNumber(){
        return contractNumber;
    }
    public String getContractDate(){
        return contractDate;
    }
    public String getContractStartDate(){
        return contractStartDate;
    }
    public String getContractEndDate(){
        return contractEndDate;
    }
    public float getContractPrice(){
        return contractPrice;
    }
    
    
}
