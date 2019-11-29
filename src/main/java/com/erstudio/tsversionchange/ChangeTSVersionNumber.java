package main.java.com.erstudio.tsversionchange;

import main.java.com.erstudio.tsversionchange.model.VersionInputModel;

import java.io.IOException;

/**
 * @author shrey.pasari
 * @description This entity will fetch the inputs provided by user and will process accordingly to update the version
 */
public class ChangeTSVersionNumber {

    public static final String VERSION_HELPER_SHEET = "D:\\ERStudioReleaseHelper\\ERStudio.xlsx";


    public ChangeTSVersionNumber(VersionInputModel versionInputModel) {

        // processing excel file
        ProcessExcel processExcel = new ProcessExcel(VERSION_HELPER_SHEET);

    }

}
