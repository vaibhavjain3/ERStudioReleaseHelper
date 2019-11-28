package main.java.com.erstudio.tsversionchange;

import java.io.IOException;

/**
 * @author shrey.pasari
 * @description This entity will fetch the inputs provided by user and will process accordingly to update the version
 */
public class ChangeTSVersionNumber {
    String filePath;
    String oldVersion;
    String newVersion;
    String GUID;
    int upgradeType;

    public ChangeTSVersionNumber(String filePath, String oldVersion, String newVersion, String GUID, int upgradeType) {
        this.filePath = filePath;
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;
        this.GUID = GUID;
        this.upgradeType = upgradeType;
    }

    public void readExcel() throws IOException {
        ReadExcel readExcel = new ReadExcel();
        readExcel.readExcel(this.filePath);
    }
}
