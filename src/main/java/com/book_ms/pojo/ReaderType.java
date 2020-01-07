package com.book_ms.pojo;

/**
 * @Author xiao
 * @Time 2019/11/21
 * @Describe 读者类型类
 **/
public class ReaderType {
    private Integer rdType;
    private String rdTypeName;
    private Integer canLendQty;
    private Integer canLendDay;
    private Integer canContinueTimes;
    private Float punishRate;
    private Integer dateValid;

    public Integer getRdType() {
        return rdType;
    }

    public void setRdType(Integer rdType) {
        this.rdType = rdType;
    }

    public String getRdTypeName() {
        return rdTypeName;
    }

    public void setRdTypeName(String rdTypeName) {
        this.rdTypeName = rdTypeName;
    }

    public Integer getCanLendQty() {
        return canLendQty;
    }

    public void setCanLendQty(Integer canLendQty) {
        this.canLendQty = canLendQty;
    }

    public Integer getCanLendDay() {
        return canLendDay;
    }

    public void setCanLendDay(Integer canLendDay) {
        this.canLendDay = canLendDay;
    }

    public Integer getCanContinueTimes() {
        return canContinueTimes;
    }

    public void setCanContinueTimes(Integer canContinueTimes) {
        this.canContinueTimes = canContinueTimes;
    }

    public Float getPunishRate() {
        return punishRate;
    }

    public void setPunishRate(Float punishRate) {
        this.punishRate = punishRate;
    }

    public Integer getDateValid() {
        return dateValid;
    }

    public void setDateValid(Integer dateValid) {
        this.dateValid = dateValid;
    }

    @Override
    public String toString() {
        return "ReaderType{" +
                "readerType=" + rdType +
                ", rdTypeName='" + rdTypeName + '\'' +
                ", canLendQty=" + canLendQty +
                ", canLendDay=" + canLendDay +
                ", canContinueTimes=" + canContinueTimes +
                ", punishRate=" + punishRate +
                ", dateValid=" + dateValid +
                '}';
    }

}
