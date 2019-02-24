package com.app.sy.syan.data.event;

public class ModifyAddressEvent {
    private boolean modifySuccess;

    public boolean isModifySuccess() {
        return modifySuccess;
    }

    public ModifyAddressEvent(boolean modifySuccess) {
        this.modifySuccess = modifySuccess;
    }
}
