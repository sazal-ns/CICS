package com.sazal.siddiqui.cics.model;

/**
 * Created by sazal on 2017-02-12.
 */

public class Provider {

    private int providerId, parentProviderId, createdBy, updatedBy;
    private String providerName, areaName, coustomField1,coustomField2,coustomField3,coustomField4, createdOn, updatedOn;

    public Provider() {
    }

    public Provider(int providerId, int parentProviderId, String providerName, String areaName) {
        this.providerId = providerId;
        this.parentProviderId = parentProviderId;
        this.providerName = providerName;
        this.areaName = areaName;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public void setParentProviderId(int parentProviderId) {
        this.parentProviderId = parentProviderId;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public int getProviderId() {
        return providerId;
    }

    public int getParentProviderId() {
        return parentProviderId;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getAreaName() {
        return areaName;
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

    @Override
    public String toString() {
        return providerName;
    }
}
