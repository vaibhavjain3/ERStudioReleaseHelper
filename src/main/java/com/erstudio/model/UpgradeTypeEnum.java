package main.java.com.erstudio.model;

import main.java.com.erstudio.constants.Constants;

public enum UpgradeTypeEnum {

    FULL_UPGRADE(Constants.FULL_UPGRADE),
    PATCH_UPGRADE(Constants.PATCH_UPGRADE);

    // declaring private variable for getting values
    private String action;

    // getter method
    public String getAction() {
        return this.action;
    }

    // enum constructor
    private UpgradeTypeEnum(String action) {
        this.action = action;
    }
}