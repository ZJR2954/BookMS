package com.book_ms.pojo;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author xiao
 * @Time 2019/11/21
 * @Describe 图书类
 **/
public class Book {
    private Integer bkID;
    private String bkCode;
    private String bkName;
    private String bkAuthor;
    private String bkPress;
    private Date bkDatePress;
    private String bkISBN;
    private String bkCatalog;
    private Integer bkLanguage;
    private Integer bkPages;
    private Float bkPrice;
    private Date bkDateIn;
    private String bkBrief;
    private byte[] bkCover;
    private String bkStatus;

    public Integer getBkID() {
        return bkID;
    }

    public void setBkID(Integer bkID) {
        this.bkID = bkID;
    }

    public String getBkCode() {
        return bkCode;
    }

    public void setBkCode(String bkCode) {
        this.bkCode = bkCode;
    }

    public String getBkName() {
        return bkName;
    }

    public void setBkName(String bkName) {
        this.bkName = bkName;
    }

    public String getBkAuthor() {
        return bkAuthor;
    }

    public void setBkAuthor(String bkAuthor) {
        this.bkAuthor = bkAuthor;
    }

    public String getBkPress() {
        return bkPress;
    }

    public void setBkPress(String bkPress) {
        this.bkPress = bkPress;
    }

    public Date getBkDatePress() {
        return bkDatePress;
    }

    public void setBkDatePress(Date bkDatePress) {
        this.bkDatePress = bkDatePress;
    }

    public String getBkISBN() {
        return bkISBN;
    }

    public void setBkISBN(String bkISBN) {
        this.bkISBN = bkISBN;
    }

    public String getBkCatalog() {
        return bkCatalog;
    }

    public void setBkCatalog(String bkCatalog) {
        this.bkCatalog = bkCatalog;
    }

    public Integer getBkLanguage() {
        return bkLanguage;
    }

    public void setBkLanguage(Integer bkLanguage) {
        this.bkLanguage = bkLanguage;
    }

    public Integer getBkPages() {
        return bkPages;
    }

    public void setBkPages(Integer bkPages) {
        this.bkPages = bkPages;
    }

    public Float getBkPrice() {
        return bkPrice;
    }

    public void setBkPrice(Float bkPrice) {
        this.bkPrice = bkPrice;
    }

    public Date getBkDateIn() {
        return bkDateIn;
    }

    public void setBkDateIn(Date bkDateIn) {
        this.bkDateIn = bkDateIn;
    }

    public String getBkBrief() {
        return bkBrief;
    }

    public void setBkBrief(String bkBrief) {
        this.bkBrief = bkBrief;
    }

    public byte[] getBkCover() {
        return bkCover;
    }

    public void setBkCover(byte[] bkCover) {
        this.bkCover = bkCover;
    }

    public String getBkStatus() {
        return bkStatus;
    }

    public void setBkStatus(String bkStatus) {
        this.bkStatus = bkStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bkID=" + bkID +
                ", bkCode='" + bkCode + '\'' +
                ", bkName='" + bkName + '\'' +
                ", bkAuthor='" + bkAuthor + '\'' +
                ", bkPress='" + bkPress + '\'' +
                ", bkDatePress=" + bkDatePress +
                ", bkISBN='" + bkISBN + '\'' +
                ", bkCatalog='" + bkCatalog + '\'' +
                ", bkLanguage=" + bkLanguage +
                ", bkPages=" + bkPages +
                ", bkPrice=" + bkPrice +
                ", bkDateIn=" + bkDateIn +
                ", bkBrief='" + bkBrief + '\'' +
                ", bkCover=" + Arrays.toString(bkCover) +
                ", bkStatus='" + bkStatus + '\'' +
                '}';
    }
}
