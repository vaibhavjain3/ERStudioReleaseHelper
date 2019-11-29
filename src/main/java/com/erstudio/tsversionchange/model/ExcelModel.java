package main.java.com.erstudio.tsversionchange.model;

/**
 * @author rajat.batra
 * @description contains data of excel file
 */
public class ExcelModel {
    String filepath;
    String fullVersion;
    String patchVersion;
    String stringToReplaceFormat;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFullVersion() {
        return fullVersion;
    }

    public void setFullVersion(String fullVersion) {
        this.fullVersion = fullVersion;
    }

    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    public String getStringToReplaceFormat() {
        return stringToReplaceFormat;
    }

    public void setStringToReplaceFormat(String stringToReplaceFormat) {
        this.stringToReplaceFormat = stringToReplaceFormat;
    }
}
