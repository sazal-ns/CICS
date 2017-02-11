package com.sazal.siddiqui.cics.model;

/**
 * Created by sazal on 2017-02-11.
 */

public class Package {
    private int  packageId, totalChannels, createdBy, updatedBy;
    private double price;
    private String packageName, coustomField1,coustomField2,coustomField3,coustomField4, createdOn, updatedOn;

    public Package() {
    }

    public Package(int totalChannels, double price, String packageName) {
        this.totalChannels = totalChannels;
        this.price = price;
        this.packageName = packageName;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public void setTotalChannels(int totalChannels) {
        this.totalChannels = totalChannels;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

    public int getPackageId() {
        return packageId;
    }

    public int getTotalChannels() {
        return totalChannels;
    }

    public double getPrice() {
        return price;
    }

    public String getPackageName() {
        return packageName;
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
