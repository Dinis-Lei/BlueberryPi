package com.ies.blueberry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alert")
public class Alert {
    @Id //The ID will be auto generated
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "sensor", nullable = false)
    private String sensor;

    @Column(name = "start", nullable = false)
    private Long start;

    @Column(name = "end", nullable = false)
    private Long end;

    @Column(name = "val", nullable = false)
    private Double val;

    public Alert(){}

    public Alert(String location, String sensor, Long start, Long end, Double val) {
        this.location = location;
        this.sensor = sensor;
        this.start = start;
        this.end = end;
        this.val = val;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Double getVal(){
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }
}
