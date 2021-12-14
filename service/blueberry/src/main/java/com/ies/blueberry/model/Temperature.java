package com.ies.blueberry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity //An entity represents a table stored in a database
@Table(name = "temperature") //The table is named movies
public class Temperature {
    Double data;
    String location;
    Long timestamp;

    public Temperature() {}

    public Temperature(Double data,String location,Long timestamp)
    {
        this.data = data;
        this.location = location;
        this.timestamp = timestamp;
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

    public void setTime(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Temperature [data=" + data + ", location=" + location + ", timestamp=" + timestamp + "]";
    }
}
