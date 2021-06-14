package com.daniel.domain;

import java.math.BigDecimal;

public class SalaryInfo {
    private Integer empid;
    
    private String distributiontime;
    
    private String empname;
    
    private BigDecimal basesalary = new BigDecimal(0);
    
    private BigDecimal subsidy = new BigDecimal(0);
    
    private BigDecimal housallowance = new BigDecimal(0);
    
    private BigDecimal superannuation = new BigDecimal(0);
    
    private BigDecimal overtimepay = new BigDecimal(0);
    
    private BigDecimal bonus = new BigDecimal(0);
    
    private BigDecimal fine = new BigDecimal(0);
    
    private BigDecimal sum = new BigDecimal(0);
    
    
    public Integer getEmpid() {
        return empid;
    }
    
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }
    
    public String getDistributiontime() {
        return distributiontime;
    }
    
    public void setDistributiontime(String distributiontime) {
        this.distributiontime = distributiontime;
    }
    
    public String getEmpname() {
        return empname;
    }
    
    public void setEmpname(String empname) {
        this.empname = empname;
    }
    
    public BigDecimal getBasesalary() {
        return basesalary;
    }
    
    public void setBasesalary(BigDecimal basesalary) {
        if (basesalary==null){
            this.basesalary = new BigDecimal(0);
        }else {
            this.basesalary = basesalary;
        }
        
    }
    
    public BigDecimal getSubsidy() {
        
        return subsidy;
    }
    
    public void setSubsidy(BigDecimal subsidy) {
        if (subsidy==null){
            this.subsidy = new BigDecimal(0);
        }else {
            this.subsidy = subsidy;
        }
    }
    
    public BigDecimal getHousallowance() {
        return housallowance;
    }
    
    public void setHousallowance(BigDecimal housallowance) {
        if (housallowance==null){
            this.housallowance = new BigDecimal(0);
        }else {
            this.housallowance = housallowance;
        }
    }
    
    public BigDecimal getSuperannuation() {
        return superannuation;
    }
    
    public void setSuperannuation(BigDecimal superannuation) {
        if (superannuation==null){
            this.superannuation = new BigDecimal(0);
        }else {
            this.superannuation = superannuation;
        }
    }
    
    public BigDecimal getOvertimepay() {
        return overtimepay;
    }
    
    public void setOvertimepay(BigDecimal overtimepay) {
        if (overtimepay==null){
            this.overtimepay = new BigDecimal(0);
        }else {
            this.overtimepay = overtimepay;
        }
    }
    
    public BigDecimal getBonus() {
        return bonus;
    }
    
    public void setBonus(BigDecimal bonus) {
        if (bonus==null){
            this.bonus = new BigDecimal(0);
        }else {
            this.bonus = bonus;
        }
    }
    
    public BigDecimal getFine() {
        return fine;
    }
    
    public void setFine(BigDecimal fine) {
        if (fine==null){
            this.fine = new BigDecimal(0);
        }else {
            this.fine = fine;
        }
    }
    
    public BigDecimal getSum() {
        return sum;
    }
    
    public void setSum(BigDecimal sum) {
        if (sum==null){
            this.sum = new BigDecimal(0);
        }else {
            this.sum = sum;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", empid=").append(empid);
        sb.append(", distributiontime=").append(distributiontime);
        sb.append(", empname=").append(empname);
        sb.append(", basesalary=").append(basesalary);
        sb.append(", subsidy=").append(subsidy);
        sb.append(", housallowance=").append(housallowance);
        sb.append(", superannuation=").append(superannuation);
        sb.append(", overtimepay=").append(overtimepay);
        sb.append(", bonus=").append(bonus);
        sb.append(", fine=").append(fine);
        sb.append(", sum=").append(sum);
        sb.append("]");
        return sb.toString();
    }
}