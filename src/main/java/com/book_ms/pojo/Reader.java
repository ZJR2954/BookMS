package com.book_ms.pojo;

import java.util.Date;

/**
 * @Author xiao
 * @Time 2019/11/21
 * @Describe 读者类
 **/
public class Reader {
    private Integer rdID;
    private String rdName;
    private String rdSex;
    private Integer rdType;
    private String rdDept;
    private String rdPhone;
    private String rdEmail;
    private Date rdDateReg;
    private byte[] rdPhoto;
    private String rdStatus;
    private Integer rdBorrowQty;
    private String rdPwd;
    private Integer rdAdminRoles;

    public Reader() {}
    public Reader(Integer rdType, String rdDept, String rdName, String rdSex, Date rdDateReg, String rdPhone, String rdEmail, byte[] rdPhoto) {
        this.rdType = rdType;
        this.rdDept = rdDept;
        this.rdName = rdName;
        this.rdSex = rdSex;
        this.rdDateReg = rdDateReg;
        this.rdPhone = rdPhone;
        this.rdEmail = rdEmail;
        this.rdPhoto = rdPhoto;
    }

    public Integer getRdID() {
        return rdID;
    }

    public void setRdID(Integer rdID) {
        this.rdID = rdID;
    }

    public String getRdName() {
        return rdName;
    }

    public void setRdName(String rdName) {
        this.rdName = rdName;
    }

    public String getRdSex() {
        return rdSex;
    }

    public void setRdSex(String rdSex) {
        this.rdSex = rdSex;
    }

    public Integer getRdType() {
        return rdType;
    }

    public void setRdType(Integer rdType) {
        this.rdType = rdType;
    }

    public String getRdDept() {
        return rdDept;
    }

    public void setRdDept(String rdDept) {
        this.rdDept = rdDept;
    }

    public String getRdPhone() {
        return rdPhone;
    }

    public void setRdPhone(String rdPhone) {
        this.rdPhone = rdPhone;
    }

    public String getRdEmail() {
        return rdEmail;
    }

    public void setRdEmail(String rdEmail) {
        this.rdEmail = rdEmail;
    }

    public Date getRdDateReg() {
        return rdDateReg;
    }

    public void setRdDateReg(Date rdDateReg) {
        this.rdDateReg = rdDateReg;
    }

    public byte[] getRdPhoto() {
        return rdPhoto;
    }

    public void setRdPhoto(byte[] rdPhoto) {
        this.rdPhoto = rdPhoto;
    }

    public String getRdStatus() {
        return rdStatus;
    }

    public void setRdStatus(String rdStatus) {
        this.rdStatus = rdStatus;
    }

    public Integer getRdBorrowQty() {
        return rdBorrowQty;
    }

    public void setRdBorrowQty(Integer rdBorrowQty) {
        this.rdBorrowQty = rdBorrowQty;
    }

    public String getRdPwd() {
        return rdPwd;
    }

    public void setRdPwd(String rdPwd) {
        this.rdPwd = rdPwd;
    }

    public Integer getRdAdminRoles() {
        return rdAdminRoles;
    }

    public void setRdAdminRoles(Integer rdAdminRoles) {
        this.rdAdminRoles = rdAdminRoles;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "rdID=" + rdID +
                ", rdName='" + rdName + '\'' +
                ", rdSex='" + rdSex + '\'' +
                ", rdType=" + rdType +
                ", rdDept='" + rdDept + '\'' +
                ", rdPhone='" + rdPhone + '\'' +
                ", rdEmail='" + rdEmail + '\'' +
                ", rdDateReg=" + rdDateReg +
                ", rdPhoto=" + rdPhoto +
                ", rdStatus='" + rdStatus + '\'' +
                ", rdBorrowQty=" + rdBorrowQty +
                ", rdPwd='" + rdPwd + '\'' +
                ", rdAdminRoles=" + rdAdminRoles +
                '}';
    }
}
