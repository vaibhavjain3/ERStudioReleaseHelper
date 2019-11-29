package main.java.com.erstudio.tsversionchange;

import main.java.com.erstudio.constants.Constants;
import main.java.com.erstudio.tsversionchange.model.VersionInputModel;

/**
 * @author shrey.pasari
 * @description This entity will fetch the inputs provided by user and will process accordingly to update the version
 */
public class ChangeTSVersionNumber {

    public ChangeTSVersionNumber(VersionInputModel versionInputModel) {
        // processing excel file
        ProcessExcel processExcel = new ProcessExcel(Constants.VERSION_HELPER_SHEET);
    }
}
