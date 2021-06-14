package com.daniel.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


public class OvertimeData {
    private Integer empid;

    private String overtimemonth;

    private String empname;

    private String overtimedescript;

    private Integer overtimeday;

    private BigDecimal overtimesalary;

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getOvertimemonth() {
        return overtimemonth;
    }

    public void setOvertimemonth(String overtimemonth) {
        this.overtimemonth = overtimemonth;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getOvertimedescript() {
        return overtimedescript;
    }

    public void setOvertimedescript(String overtimedescript) {
        this.overtimedescript = overtimedescript;
    }

    public Integer getOvertimeday() {
        return overtimeday;
    }

    public void setOvertimeday(Integer overtimeday) {
        this.overtimeday = overtimeday;
    }

    public BigDecimal getOvertimesalary() {
        return overtimesalary;
    }

    public void setOvertimesalary(BigDecimal overtimesalary) {
        if (overtimesalary==null){
            this.overtimesalary = new BigDecimal(0);
        }else {
            this.overtimesalary = overtimesalary;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", empid=").append(empid);
        sb.append(", overtimemonth=").append(overtimemonth);
        sb.append(", empname=").append(empname);
        sb.append(", overtimedescript=").append(overtimedescript);
        sb.append(", overtimeday=").append(overtimeday);
        sb.append(", overtimesalary=").append(overtimesalary);
        sb.append("]");
        return sb.toString();
    }
}