package com.daniel.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

public class AttendanceData {
    private Integer empid;
    
    private String absenteeismtime;
    
    private String empname;
    
    private String descript;
    
    private Integer absenteeismday;
    
    private BigDecimal fine;
    
    public Integer getEmpid() {
        return empid;
    }
    
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }
    
    public String getAbsenteeismtime() {
        return absenteeismtime;
    }
    
    public void setAbsenteeismtime(String absenteeismtime) {
        this.absenteeismtime = absenteeismtime;
    }
    
    public String getEmpname() {
        return empname;
    }
    
    public void setEmpname(String empname) {
        this.empname = empname;
    }
    
    public String getDescript() {
        return descript;
    }
    
    public void setDescript(String descript) {
        this.descript = descript;
    }
    
    public Integer getAbsenteeismday() {
        return absenteeismday;
    }
    
    public void setAbsenteeismday(Integer absenteeismday) {
        this.absenteeismday = absenteeismday;
    }
    
    public BigDecimal getFine() {
        return fine;
    }
    
    public void setFine(BigDecimal fine) {
        if (fine==null){
            this.fine = new BigDecimal(0);
        }{
            this.fine = fine;
        }
        
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", empid=").append(empid);
        sb.append(", absenteeismtime=").append(absenteeismtime);
        sb.append(", empname=").append(empname);
        sb.append(", descript=").append(descript);
        sb.append(", absenteeismday=").append(absenteeismday);
        sb.append(", fine=").append(fine);
        sb.append("]");
        return sb.toString();
    }
}