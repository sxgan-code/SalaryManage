package com.daniel.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


public class SanctionData {
    private Integer empid;

    private String sanctiontime;

    private String empname;

    private String finedescript;

    private BigDecimal fine;

    private String rewarddescript;

    private BigDecimal reward;

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getSanctiontime() {
        return sanctiontime;
    }

    public void setSanctiontime(String sanctiontime) {
        this.sanctiontime = sanctiontime;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getFinedescript() {
        return finedescript;
    }

    public void setFinedescript(String finedescript) {
        this.finedescript = finedescript;
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

    public String getRewarddescript() {
        return rewarddescript;
    }

    public void setRewarddescript(String rewarddescript) {
        this.rewarddescript = rewarddescript;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        if (reward==null){
            this.reward = new BigDecimal(0);
        }else {
            this.reward = reward;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", empid=").append(empid);
        sb.append(", sanctiontime=").append(sanctiontime);
        sb.append(", empname=").append(empname);
        sb.append(", finedescript=").append(finedescript);
        sb.append(", fine=").append(fine);
        sb.append(", rewarddescript=").append(rewarddescript);
        sb.append(", reward=").append(reward);
        sb.append("]");
        return sb.toString();
    }
}