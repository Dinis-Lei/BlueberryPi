package com.ies.blueberry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //An entity represents a table stored in a database
@Table(name = "temperature") //The table is named movies
public class Temperature {
    private long id;
    private Double data;
    private String location;
    private Long timestamp;

    public Temperature() {}

    public Temperature(Double data,String location,Long timestamp)
    {
        this.data = data;
        this.location = location;
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

    @Column(name = "data", nullable = false)
    public Double getData() {
        return this.data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    @Column(name = "location", nullable = false)
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "Temperature [data=" + data + ", location=" + location + ", timestamp=" + timestamp + "]";
    }
}
