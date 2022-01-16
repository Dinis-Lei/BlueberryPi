package com.ies.blueberry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity //An entity represents a table stored in a database
@Table(name = "storage_humidity") 
public class StorageHumidity {
    @Id //The ID will be auto generated
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "data", nullable = false)
    private Double data;

    //@ManyToOne
    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "timestamp", nullable = false)
    private Long timestamp;

    public StorageHumidity() {}

    public StorageHumidity(Double data, String location, Long timestamp)
    {
        this.data = data;
        this.location = location;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Double getData() {
        return this.data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Storage Humidity [data=" + data + ", location=" + location + ", timestamp=" + timestamp + "]";
    }
}


