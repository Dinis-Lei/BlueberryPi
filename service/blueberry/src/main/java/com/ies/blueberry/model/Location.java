package com.ies.blueberry.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //An entity represents a table stored in a database
@Table(name = "location") 
public class Location {
    private long id;
    private String name;
    private List<UnitLoss> unitloss;
    private List<NetHarvest> netharvest;
    private List<PlantationTemperature> plantationtemperature;
    private List<SoilPH> soilph;
    private List<SoilWaterTension> soilwatertension;
    private List<StorageHumidity> storagehumidity;
    private List<StorageTemperature> storagetemperature;
    private Long timestamp;

    public Location() {}

    public Location(String name,Long timestamp)
    {
        this.name = name;
        this.unitloss = new ArrayList<UnitLoss>();
        //this.netharvest = new ArrayList<NetHarvest>();
        this.plantationtemperature = new ArrayList<PlantationTemperature>();
        this.soilph = new ArrayList<SoilPH>();
        this.soilwatertension = new ArrayList<SoilWaterTension>();
        this.storagehumidity = new ArrayList<StorageHumidity>();
        this.storagetemperature = new ArrayList<StorageTemperature>();
        this.timestamp = timestamp;
    }

    @Id //The ID will be auto generated
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "unitloss", nullable = false)
    public List<UnitLoss> getUnitLosses() {
        return this.unitloss;
    }

    public void setUnitLoss(UnitLoss ul) {
        this.unitloss.add(ul);
    }

    // @Column(name = "netharvest", nullable = false)
    // public List<NetHarvest> getNetHarvests() {
    //     return this.netharvest;
    // }

    // public void setNetHarvest(NetHarvest nh) {
    //     this.netharvest.add(nh);
    // }

    @Column(name = "plantationtemperature", nullable = false)
    public List<PlantationTemperature> getPlantationTemperatures() {
        return this.plantationtemperature;
    }

    public void setPlantationTemperature(PlantationTemperature pt) {
        this.plantationtemperature.add(pt);
    }

    @Column(name = "soilph", nullable = false)
    public List<SoilPH> getSoilPHs() {
        return this.soilph;
    }

    public void setSoilPH(SoilPH sp) {
        this.soilph.add(sp);
    }

    @Column(name = "soilwatertension", nullable = false)
    public List<SoilWaterTension> getSoilWaterTensions() {
        return this.soilwatertension;
    }

    public void setSoilWaterTension(SoilWaterTension swt) {
        this.soilwatertension.add(swt);
    }

    @Column(name = "storagehumidity", nullable = false)
    public List<StorageHumidity> getStorageHumidities() {
        return this.storagehumidity;
    }

    public void setStorageHumidity(StorageHumidity sh) {
        this.storagehumidity.add(sh);
    }

    @Column(name = "storagetemperature", nullable = false)
    public List<StorageTemperature> getStorageTemperatures() {
        return this.storagetemperature;
    }

    public void setStorageTemperature(StorageTemperature st) {
        this.storagetemperature.add(st);
    }

    @Column(name = "timestamp", nullable = false)
    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", unitloss='" + getUnitLosses() + "'" +
           // ", netharvest='" + getNetHarvests() + "'" +
            ", plantationtemperature='" + getPlantationTemperatures() + "'" +
            ", soilph='" + getSoilPHs() + "'" +
            ", soilwatertension='" + getSoilWaterTensions() + "'" +
            ", storagehumidity='" + getStorageHumidities() + "'" +
            ", storagetemperature='" + getStorageTemperatures() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }

}