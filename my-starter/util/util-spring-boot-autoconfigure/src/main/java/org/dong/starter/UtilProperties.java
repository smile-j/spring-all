package org.dong.starter;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("util.date")
public class UtilProperties {

    //根据经度或者时区计算当地时间

    private Double latitude = 120d;

    private int zone;

    private String pattern ="yyyy-MM-dd hh:mm:ss";

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
