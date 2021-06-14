package com.daniel.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


public class TravelData {
    private Integer empid;

    private String traveltime;

    private String empname;

    private String traveldescript;

    private Integer travelday;

    private BigDecimal travelsalary;

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(String traveltime) {
        this.traveltime = traveltime;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getTraveldescript() {
        return traveldescript;
    }

    public void setTraveldescript(String traveldescript) {
        this.traveldescript = traveldescript;
    }

    public Integer getTravelday() {
        return travelday;
    }

    public void setTravelday(Integer travelday) {
        this.travelday = travelday;
    }

    public BigDecimal getTravelsalary() {
        return travelsalary;
    }

    public void setTravelsalary(BigDecimal travelsalary) {
        if (travelsalary==null){
            this.travelsalary = new BigDecimal(0);
        }else {
            this.travelsalary = travelsalary;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", empid=").append(empid);
        sb.append(", traveltime=").append(traveltime);
        sb.append(", empname=").append(empname);
        sb.append(", traveldescript=").append(traveldescript);
        sb.append(", travelday=").append(travelday);
        sb.append(", travelsalary=").append(travelsalary);
        sb.append("]");
        return sb.toString();
    }
}