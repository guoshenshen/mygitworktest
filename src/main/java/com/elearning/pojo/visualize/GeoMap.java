package com.elearning.pojo.visualize;

public class GeoMap {
    private Integer id;

    private String location;

    private Double longitude;

    private Double latitude;

    public GeoMap(Integer id, String location, Double longitude, Double latitude) {
        this.id = id;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public GeoMap() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}