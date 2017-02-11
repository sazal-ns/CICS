package com.sazal.siddiqui.cics.model;

/**
 * Created by sazal on 2017-02-12.
 */

public class CustomerXPackage {

    private int id, createdBy, updatedBy;
    private CustomerInformation customer;
    private Package aPackage;
    private String coustomField1,coustomField2,coustomField3,coustomField4, createdON, updatedOn;

    public CustomerXPackage() {
    }

    public CustomerXPackage(int id, CustomerInformation customer, Package aPackage) {
        this.id = id;
        this.customer = customer;
        this.aPackage = aPackage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setCustomer(CustomerInformation customer) {
        this.customer = customer;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
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

    public void setCreatedON(String createdON) {
        this.createdON = createdON;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getId() {
        return id;
    }

    public CustomerInformation getCustomer() {
        return customer;
    }

    public Package getaPackage() {
        return aPackage;
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
}
