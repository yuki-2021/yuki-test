package org.hscoder.springboot.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "v_city_weather")
public class CityView {

    @Id
    private String city;

    @Column(name = "temp_lo")
    private Double tempLo;

    @Column(name = "temp_hi")
    private Double tempHi;

    private Double prcp;
    private Date date;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getTempLo() {
        return tempLo;
    }

    public void setTempLo(Double tempLo) {
        this.tempLo = tempLo;
    }

    public Double getTempHi() {
        return tempHi;
    }

    public void setTempHi(Double tempHi) {
        this.tempHi = tempHi;
    }

    public Double getPrcp() {
        return prcp;
    }

    public void setPrcp(Double prcp) {
        this.prcp = prcp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
