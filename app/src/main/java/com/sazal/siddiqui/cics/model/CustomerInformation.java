package com.sazal.siddiqui.cics.model;

/**
 * Created by sazal on 2017-02-12.
 */

public class CustomerInformation {


    private int coustomerId, createdBy, updatedBy;
    private String nameBangla, nameEnglish, coustomerNumber, mobile, email, altContactNumber, firstConectionDate, coustomField1,coustomField2,
            coustomField3,coustomField4, createdOn, updatedOn;
    private Provider provider;
    private Package aPackage;

    public CustomerInformation() {
    }

    public CustomerInformation(int coustomerId, int createdBy, int updatedBy, String nameBangla, String nameEnglish, String coustomerNumber, String mobile, String email, String altContactNumber, String firstConectionDate, String coustomField1, String coustomField2, String coustomField3, String coustomField4, String createdOn, String updatedOn, Provider provider, Package aPackage) {
        this.coustomerId = coustomerId;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.nameBangla = nameBangla;
        this.nameEnglish = nameEnglish;
        this.coustomerNumber = coustomerNumber;
        this.mobile = mobile;
        this.email = email;
        this.altContactNumber = altContactNumber;
        this.firstConectionDate = firstConectionDate;
        this.coustomField1 = coustomField1;
        this.coustomField2 = coustomField2;
        this.coustomField3 = coustomField3;
        this.coustomField4 = coustomField4;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.provider = provider;
        this.aPackage = aPackage;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public void setCoustomerId(int coustomerId) {
        this.coustomerId = coustomerId;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setNameBangla(String nameBangla) {
        this.nameBangla = nameBangla;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public void setCoustomerNumber(String coustomerNumber) {
        this.coustomerNumber = coustomerNumber;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAltContactNumber(String altContactNumber) {
        this.altContactNumber = altContactNumber;
    }

    public void setFirstConectionDate(String firstConectionDate) {
        this.firstConectionDate = firstConectionDate;
    }

    public void setCoustomField1(String coustomField1) {
        this.coustomField1 = coustomField1;
    }

    public void setCoustomField2(String coustomField2) {
        this.coustomField2 = coustomField2;
    }

    public void setCoustomField3(String coustomField3) {
        this.coustomField3 = coustomField3;
    }

    public void setCoustomField4(String coustomField4) {
        this.coustomField4 = coustomField4;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public int getCoustomerId() {
        return coustomerId;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public String getCoustomerNumber() {
        return coustomerNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAltContactNumber() {
        return altContactNumber;
    }

    public String getFirstConectionDate() {
        return firstConectionDate;
    }

    public String getCoustomField1() {
        return coustomField1;
    }

    public String getCoustomField2() {
        return coustomField2;
    }

    public String getCoustomField3() {
        return coustomField3;
    }

    public String getCoustomField4() {
        return coustomField4;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getNameBangla() {
        return nameBangla;
    }
}
