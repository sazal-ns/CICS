package com.sazal.siddiqui.cics.model;

/**
 * Created by sazal on 2017-02-11.
 */

public class CustomerType {

    private int typeId;
    private String typeName;

    public CustomerType() {
    }

    public CustomerType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
